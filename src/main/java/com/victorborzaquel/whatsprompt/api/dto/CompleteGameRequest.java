package com.victorborzaquel.whatsprompt.api.dto;

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
    private UUID gameId;
    private String answer;
}
