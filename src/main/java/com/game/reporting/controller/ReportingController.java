package com.game.reporting.controller;

import com.game.reporting.dto.ContestDTO;
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
    public List<UserScoreDTO> getLeaderboard(){
        List<UserScore> userScores = reportingServices.fetchGlobalScores();
        List<UserScoreDTO> userScoreDTOS = new ArrayList<>();
        for(UserScore userScore: userScores){
            UserScoreDTO userScoreDTO = new UserScoreDTO();
            BeanUtils.copyProperties(userScore, userScoreDTO);
            userScoreDTOS.add(userScoreDTO);
        }
        return userScoreDTOS;
    }

    @GetMapping("/{contestId}/leaderboard")
    public List<UserScoreDTO> getContestLeaderBoard(@PathVariable("contestId") String contestId){
        return reportingServices.fetchScores(contestId);
    }

    @PostMapping("/addToGlobalLeaderboard")
    public void addToGlobalLeaderboard(@RequestBody List<UserScoreDTO> userScoreDTOList){
        for(UserScoreDTO userScoreDTO: userScoreDTOList){
            UserScore userScore = reportingServices.findByUserId(userScoreDTO.getUserId());
            if(userScore!=null)
                userScore.setScore(userScore.getScore()+userScoreDTO.getScore());
            else{
                userScore = new UserScore();
                BeanUtils.copyProperties(userScoreDTO, userScore);
            }
            reportingServices.addUserScore(userScore);
        }
    }

    @GetMapping("/contestReport")
    public List<ContestDTO> getContestReport(){
        return reportingServices.getContestSummary();
    }
}
