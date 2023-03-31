package com.victorborzaquel.whatsprompt.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompleteGameResponse {
        @JsonProperty("image_url")
        private String imageUrl;
        @JsonProperty("correct_answer")
        private String correctAnswer;
        @JsonProperty("user_answer")
        private String userAnswer;
        @JsonProperty("score")
        private Integer score;
}
