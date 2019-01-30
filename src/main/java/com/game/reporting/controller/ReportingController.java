package com.game.reporting.controller;

import com.game.reporting.dto.UserScoreDTO;
import com.game.reporting.services.ReportingServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/getReport")
public class ReportingController {

    @Autowired
    ReportingServices reportingServices;

    @GetMapping("/leaderboard")
    public List<UserScoreDTO> getLeaderboard(){
        return reportingServices.fetchScores("global");
    }

    @GetMapping("/{contestId}/leaderboard")
    public List<UserScoreDTO> getContestLeaderBoard(@PathVariable("contestId") String contestId){
        return null;
    }
}
