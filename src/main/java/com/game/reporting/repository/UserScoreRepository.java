package com.game.reporting.repository;

import com.game.reporting.entity.UserScore;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserScoreRepository extends CrudRepository<UserScore, String> {
    UserScore findByUserId(String userId);

    @Query("FROM UserScore us ORDER BY us.score DESC")
    List<UserScore> getScores();
}
