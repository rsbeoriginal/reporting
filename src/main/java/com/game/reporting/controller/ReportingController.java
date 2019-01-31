package com.game.reporting.controller;

import com.game.reporting.dto.ContestDTO;
import com.game.reporting.dto.UserGlobalScoreDTO;
import com.game.reporting.dto.UserScoreDTO;
import com.game.reporting.entity.UserScore;
import com.game.reporting.services.ReportingServices;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/getReport")
public class ReportingController {

    @Autowired
    ReportingServices reportingServices;

    @GetMapping("/leaderboard")
    public List<UserGlobalScoreDTO> getLeaderboard(){
        return reportingServices.fetchGlobalScores();
    }

    @GetMapping("/{contestId}/leaderboard")
    public List<UserScoreDTO> getContestLeaderBoard(@PathVariable("contestId") String contestId){
        List<UserScore> userScores = reportingServices.fetchScores(contestId);
        List<UserScoreDTO> userScoreDTOS = new ArrayList<>();
        for(UserScore userScore: userScores){
            UserScoreDTO userScoreDTO = new UserScoreDTO();
            BeanUtils.copyProperties(userScore,userScoreDTO);
            userScoreDTOS.add(userScoreDTO);
        }
        return userScoreDTOS;
    }

    @PostMapping("/addToLeaderboard")
    public void addToLeaderboard(@RequestBody List<UserScoreDTO> userScoreDTOList){
        for(UserScoreDTO userScoreDTO: userScoreDTOList){
            UserScore userScore = new UserScore();
            BeanUtils.copyProperties(userScoreDTO, userScore);
            userScore.setUserName(reportingServices.getUserName(userScore.getUserId()));
            reportingServices.addUserScore(userScore);
        }
    }

    @GetMapping("/contestReport")
    public List<ContestDTO> getContestReport(){
        return reportingServices.getContestSummary();
    }
}
