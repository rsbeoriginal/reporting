package com.game.reporting.services;

import com.game.reporting.dto.UserScoreDTO;

import java.util.List;

public interface ReportingServices {
    List<UserScoreDTO> fetchScores(String contestId);
}
