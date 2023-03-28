package com.victorborzaquel.whatsprompt.api.dto;

import com.victorborzaquel.whatsprompt.enums.Languages;
import com.victorborzaquel.whatsprompt.game.Game;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RankingResponse {
    private UUID id;
    private String userNickName;
    private String userAnswer;
    private String correctAnswer;
    private String imageUrl;
    private Languages language;
    private LocalDate createdAt;
    private LocalDate completedAt;
    private Integer score;

    public static RankingResponse fromGame(Game game) {
        RankingResponse response = new RankingResponse();
        BeanUtils.copyProperties(game, response);
        return response;
    }
}
