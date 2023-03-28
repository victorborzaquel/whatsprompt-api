package com.victorborzaquel.whatsprompt.api.controllers;

import com.victorborzaquel.whatsprompt.Languages;
import com.victorborzaquel.whatsprompt.api.dto.CompleteGameRequest;
import com.victorborzaquel.whatsprompt.api.dto.CompleteGameResponse;
import com.victorborzaquel.whatsprompt.api.dto.CreateGameRequest;
import com.victorborzaquel.whatsprompt.api.dto.CreateGameResponse;
import com.victorborzaquel.whatsprompt.game.Game;
import com.victorborzaquel.whatsprompt.game.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<Game> ranking() {
        return service.getRanking();
    }
}
