package com.victorborzaquel.whatsprompt.game;

import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.image.CreateImageRequest;
import com.theokanning.openai.service.OpenAiService;
import com.victorborzaquel.whatsprompt.api.controllers.GameController;
import com.victorborzaquel.whatsprompt.api.dto.CompleteGameResponse;
import com.victorborzaquel.whatsprompt.api.dto.CreateGameResponse;
import com.victorborzaquel.whatsprompt.api.dto.RankingResponse;
import com.victorborzaquel.whatsprompt.enums.FilterDates;
import com.victorborzaquel.whatsprompt.enums.Languages;
import com.victorborzaquel.whatsprompt.exceptions.GameCompletedException;
import com.victorborzaquel.whatsprompt.exceptions.GameNotFoundException;
import com.victorborzaquel.whatsprompt.utils.ScoreUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
@RequiredArgsConstructor
public class GameService {
        private final GameRepository repository;
        private final OpenAiService openAiService;
        private final PagedResourcesAssembler<RankingResponse> assembler;

        public PagedModel<EntityModel<RankingResponse>> getRanking(Pageable pageable, Languages language, FilterDates date) {
                Page<Game> pageGame = repository.findAllByRanking(pageable, language, date.getDate());
                Page<RankingResponse> pageResponse = pageGame.map(RankingResponse::fromGame);

                PagedModel<EntityModel<RankingResponse>> pageModel = methodOn(GameController.class).ranking(pageable.getPageNumber(), pageable.getPageSize(), language, date);
                Link link = linkTo(pageModel).withSelfRel();

                return assembler.toModel(pageResponse, link);
        }

        public Game findById(UUID id) {
                return repository.findById(id).orElseThrow(GameNotFoundException::new);
        }

        public CreateGameResponse createGame(Languages language, String nickname) {
                String translatedPrompt = getTranslatedPromptToRandomText(language);
                String promptToGenerateImage = generateRandomPromptToGenerateImage(translatedPrompt);
                String imageUrl = generateImageUrl(promptToGenerateImage);

                Game game = Game.builder()
                        .userNickName(nickname)
                        .language(language)
                        .correctAnswer(promptToGenerateImage)
                        .imageUrl(imageUrl)
                        .build();

                Game repositoryGame = repository.save(game);

                return CreateGameResponse.builder()
                        .id(repositoryGame.getId())
                        .imageUrl(repositoryGame.getImageUrl())
                        .build();
        }

        public CompleteGameResponse completeGame(UUID gameId, String answer) {
                Game game = findById(gameId);

                if (game.getUserAnswer() != null || game.getCompletedAt() != null) {
                        throw new GameCompletedException();
                }

                final int score = ScoreUtils.generateScore(game.getCorrectAnswer(), answer);

                game.setUserAnswer(answer);
                game.setScore(score);
                game.setCompletedAt(LocalDateTime.now());

                repository.save(game);

                return CompleteGameResponse.builder()
                        .imageUrl(game.getImageUrl())
                        .correctAnswer(game.getCorrectAnswer())
                        .userAnswer(game.getUserAnswer())
                        .score(game.getScore())
                        .build();
        }

        private String generateImageUrl(String prompt) {
                CreateImageRequest createImageRequest = CreateImageRequest.builder()
                        .prompt(prompt)
                        .responseFormat("url")
                        .n(1)
                        .size("256x256")
                        .build();

                return openAiService.createImage(createImageRequest).getData().get(0).getUrl();
        }

        private String generateRandomPromptToGenerateImage(String prompt) {
                CompletionRequest completionRequest = CompletionRequest.builder()
                        .prompt(prompt)
                        .model("text-davinci-003")
//                        .temperature(1.5)
                        .maxTokens(100)
                        .build();

                String text = openAiService.createCompletion(completionRequest).getChoices().get(0).getText();

                return text.replaceAll("[\n\"\r.]", "");
        }

        private String getTranslatedPromptToRandomText(Languages language) {
                return switch (language) {
                        case EN_US -> "generate a english random prompt for DALL-E to generate an image from.";
                        case PT_BR -> "gerar um prompt em português aleatório para o DALL-E gerar uma imagem.";
                };
        }
}
