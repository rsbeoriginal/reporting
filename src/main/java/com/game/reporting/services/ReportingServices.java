package com.game.reporting.services;

import com.game.reporting.dto.ContestDTO;
import com.game.reporting.dto.UserScoreDTO;
import com.game.reporting.entity.UserScore;

import java.util.List;

public interface ReportingServices {
    List<UserScoreDTO> fetchScores(String contestId);

    String getUserName(String userId);

    UserScore findByUserId(String userId);

    UserScore addUserScore(UserScore userScore);

    List<UserScore> fetchGlobalScores();

    List<ContestDTO> getContestSummary();
}
