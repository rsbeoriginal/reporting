package com.game.reporting.repository;

import com.game.reporting.dto.UserGlobalScoreDTO;
import com.game.reporting.entity.UserScore;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserScoreRepository extends CrudRepository<UserScore, String> {
    UserScore findByUserId(String userId);

    @Query("FROM UserScore us WHERE contestId=?1 ORDER BY us.score DESC")
    List<UserScore> getScores(String contestId);

    @Query("SELECT new com.game.reporting.dto.UserGlobalScoreDTO(userId, SUM(score) as totalScore, count(contestId)) FROM UserScore GROUP BY userId ORDER BY totalScore DESC")
    List<UserGlobalScoreDTO> getScoresGlobal();
}
