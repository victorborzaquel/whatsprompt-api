package com.victorborzaquel.whatsprompt.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompleteGameResponse {
        private String imageUrl;
        private String correctAnswer;
        private String userAnswer;
        private Integer score;
}
