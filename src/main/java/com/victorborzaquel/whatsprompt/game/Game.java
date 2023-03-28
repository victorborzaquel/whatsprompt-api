package com.victorborzaquel.whatsprompt.game;

import com.victorborzaquel.whatsprompt.enums.Languages;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Game implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "UUID")
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
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime completedAt;
    private Integer score;
}
