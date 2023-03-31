package com.victorborzaquel.whatsprompt.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateGameResponse {
        @JsonProperty("game_id")
        private UUID id;
        @JsonProperty("image_url")
        private String imageUrl;
}
