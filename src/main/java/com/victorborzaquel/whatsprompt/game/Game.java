package com.victorborzaquel.whatsprompt.game;

import com.victorborzaquel.whatsprompt.Languages;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Game {
    @Id
    @GeneratedValue
    private UUID id;
    @NotBlank
    private String userNickName;
    private String userAnswer;
    @NotBlank
    private String correctAnswer;
    @NotBlank
    @Column(columnDefinition = "TEXT")
    private String imageUrl;
    @Enumerated(EnumType.STRING)
    private Languages language;
    @Builder.Default
    private LocalDate createdAt = LocalDate.now();
    private Integer score;
}
