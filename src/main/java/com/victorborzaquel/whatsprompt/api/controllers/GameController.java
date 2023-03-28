package com.victorborzaquel.whatsprompt.api.controllers;

import com.victorborzaquel.whatsprompt.api.dto.CompleteGameRequest;
import com.victorborzaquel.whatsprompt.api.dto.CompleteGameResponse;
import com.victorborzaquel.whatsprompt.api.dto.CreateGameRequest;
import com.victorborzaquel.whatsprompt.api.dto.CreateGameResponse;
import com.victorborzaquel.whatsprompt.enums.Languages;
import com.victorborzaquel.whatsprompt.game.Game;
import com.victorborzaquel.whatsprompt.game.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public Page<Game> ranking(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "100") int size
    ) {
        Pageable pageable = PageRequest.ofSize(size).withPage(page);

        return service.getRanking(pageable);
    }
}
