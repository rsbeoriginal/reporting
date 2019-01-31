package com.game.reporting.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.game.reporting.dto.ContestSubscriberCountsDTO;
import com.game.reporting.dto.ContestSubscriberDTO;
import com.game.reporting.dto.UserGlobalScoreDTO;
import com.game.reporting.entity.ContestSubscriber;
import com.game.reporting.entity.UserScore;
import com.game.reporting.repository.ContestSubscriberRepository;
import com.game.reporting.repository.UserScoreRepository;
import com.game.reporting.services.ReportingServices;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class ReportingServicesImpl implements ReportingServices {

    @Autowired
    UserScoreRepository userScoreRepository;

    @Autowired
    ContestSubscriberRepository contestSubscriberRepository;


    @Override
    public List<UserScore> fetchScores(String contestId){
        return userScoreRepository.getScores(contestId);
    }

    @Override
    public String getUserName(String userId) {
//        final String uri = " "+"?userId="+userId;
//        RestTemplate restTemplate = new RestTemplate();
//        return restTemplate.getForObject(uri, String.class);
        return "A";
    }

    @Override
    public UserScore findByUserId(String userId) {
        return userScoreRepository.findByUserId(userId);
    }

    @Override
    public UserScore addUserScore(UserScore userScore) {
        return userScoreRepository.save(userScore);
    }

    @Override
    public List<UserGlobalScoreDTO> fetchGlobalScores() {
        return userScoreRepository.getScoresGlobal();
    }

    @Override
    public List<ContestSubscriberCountsDTO> getContestSummary() {
        return contestSubscriberRepository.getSummary();
    }

    @Override
    public void addNewSubscriber(ContestSubscriberDTO contestSubscriberDTO) {
        ContestSubscriber contestSubscriber = new ContestSubscriber();
        BeanUtils.copyProperties(contestSubscriberDTO, contestSubscriber);
        contestSubscriberRepository.save(contestSubscriber);
    }
}
