package com.game.reporting.services;

import com.game.reporting.dto.ContestSubscriberCountsDTO;
import com.game.reporting.dto.ContestSubscriberDTO;
import com.game.reporting.dto.UserGlobalScoreDTO;
import com.game.reporting.entity.ContestSubscriber;
import com.game.reporting.entity.UserScore;

import java.util.List;

public interface ReportingServices {
    List<UserScore> fetchScores(String contestId);

    String getUserName(String userId);

    UserScore findByUserId(String userId);

    UserScore addUserScore(UserScore userScore);

    List<UserGlobalScoreDTO> fetchGlobalScores();

    List<ContestSubscriberCountsDTO> getContestSummary();

    void addNewSubscriber(ContestSubscriberDTO contestSubscriberDTO);
}
