package com.victorborzaquel.whatsprompt.game;

import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.image.CreateImageRequest;
import com.theokanning.openai.service.OpenAiService;
import com.victorborzaquel.whatsprompt.Languages;
import com.victorborzaquel.whatsprompt.api.dto.CompleteGameResponse;
import com.victorborzaquel.whatsprompt.api.dto.CreateGameResponse;
import com.victorborzaquel.whatsprompt.utils.ScoreUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GameService {
        private final GameRepository repository;
        private final OpenAiService openAiService;

        public List<Game> getRanking() {
                return repository.findAllByOrderByScoreDesc();
        }

        public Game findById(UUID id) {
                return repository.findById(id).orElseThrow(() -> new RuntimeException("Game not found!"));
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

                if (game.getUserAnswer() != null) {
                        throw new RuntimeException("Game already completed!");
                }

                game.setUserAnswer(answer);
                game.setScore(ScoreUtils.generateScore(game.getCorrectAnswer(), game.getUserAnswer()));

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
