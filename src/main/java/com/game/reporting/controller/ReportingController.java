package com.game.reporting.controller;

import com.game.reporting.dto.ContestSubscriberCountsDTO;
import com.game.reporting.dto.ContestSubscriberDTO;
import com.game.reporting.dto.UserGlobalScoreDTO;
import com.game.reporting.dto.UserScoreDTO;
import com.game.reporting.entity.ContestSubscriber;
import com.game.reporting.entity.UserScore;
import com.game.reporting.services.ReportingServices;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
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
    public String addToLeaderboard(@RequestBody UserScoreDTO userScoreDTO){
        System.out.println("Adding To LeaderBoard");
        try {
            UserScore userScore = new UserScore();
            BeanUtils.copyProperties(userScoreDTO, userScore);
            userScore.setUserName(reportingServices.getUserName(userScore.getUserId()));
            reportingServices.addUserScore(userScore);
            return "SUCCESS";
        }
        catch (Exception e){
            return "FAILURE";
        }
    }

    @GetMapping("/contestReport")
    public List<ContestSubscriberCountsDTO> getContestReport(){
        return reportingServices.getContestSummary();
    }

    @PostMapping("/addNewSubscriber")
    public String addNewSubscriber(@RequestBody ContestSubscriberDTO contestSubscriberDTO){
        try{
            System.out.println("ADDING NEW SUBSCRIBER:" + contestSubscriberDTO);
            reportingServices.addNewSubscriber(contestSubscriberDTO);
            return "SUCCESS";
        }
        catch (Exception e){
            return "FAILURE";
        }
    }
}
