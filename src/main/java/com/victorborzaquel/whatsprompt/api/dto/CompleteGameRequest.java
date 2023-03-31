package com.victorborzaquel.whatsprompt.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompleteGameRequest {
    @JsonDeserialize
    @JsonProperty("game_id")
    private UUID gameId;
    private String answer;
}
