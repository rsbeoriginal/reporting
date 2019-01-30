package com.game.reporting.services;

import com.game.reporting.dto.UserGlobalScoreDTO;

import java.util.List;

public interface ReportingServices {
    List<UserGlobalScoreDTO> fetchScores();
}
