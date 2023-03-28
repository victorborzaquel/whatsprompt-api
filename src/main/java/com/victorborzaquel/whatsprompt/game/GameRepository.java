package com.victorborzaquel.whatsprompt.game;

import com.victorborzaquel.whatsprompt.enums.Languages;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.UUID;

@Repository
public interface GameRepository extends JpaRepository<Game, UUID> {

    @Query(value = """
        SELECT g
        FROM Game g
        WHERE g.score > 0
            AND g.userAnswer IS NOT NULL
            AND (:language is null OR g.language = :language)
            AND (:greaterThanDate is null OR g.createdAt > :greaterThanDate)
        ORDER BY g.score DESC
    """)
    Page<Game> findAllByRanking(Pageable pageable, Languages language, LocalDate greaterThanDate);
}
