package com.victorborzaquel.whatsprompt.api.controllers;

import com.victorborzaquel.whatsprompt.api.dto.*;
import com.victorborzaquel.whatsprompt.enums.FilterDates;
import com.victorborzaquel.whatsprompt.enums.Languages;
import com.victorborzaquel.whatsprompt.game.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/game")
public class GameController {

    private final GameService service;

    @PostMapping("/create")
    public CreateGameResponse createGame(
            @RequestParam Languages language,
            @RequestBody CreateGameRequest request
    ) {
        return service.createGame(language, request.getNickname());
    }

    @PostMapping("/complete")
    public CompleteGameResponse completeGame(
            @RequestBody CompleteGameRequest request
    ) {
        return service.completeGame(request.getGameId(), request.getAnswer());
    }

    @GetMapping("/ranking")
    public PagedModel<EntityModel<RankingResponse>> ranking(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "25") int size,
            @RequestParam(value = "language", required = false) Languages language,
            @RequestParam(value = "date", defaultValue = "ALL") FilterDates date
    ) {
        Pageable pageable = PageRequest.ofSize(size).withPage(page);

        return service.getRanking(pageable, language, date);
    }
}
