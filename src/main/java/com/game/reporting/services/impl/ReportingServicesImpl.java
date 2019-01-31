package com.game.reporting.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.game.reporting.dto.ContestDTO;
import com.game.reporting.dto.UserScoreDTO;
import com.game.reporting.entity.UserScore;
import com.game.reporting.repository.UserScoreRepository;
import com.game.reporting.services.ReportingServices;
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
    @Override
    public List<UserScoreDTO> fetchScores(String contestId){
        final String uri = "http://demo2494511.mockable.io/testing";
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper mapper = new ObjectMapper();
        HttpHeaders headers=new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity requestEntity=new HttpEntity(headers);

        ResponseEntity<?> entityResponse = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, List.class);

        System.out.println(entityResponse.toString());
        List scores = (List) entityResponse.getBody();
        Iterator iterator= scores.iterator();
        List<UserScoreDTO> scoreDTOS = new ArrayList<>();
        while (iterator.hasNext()) {
            UserScoreDTO userScoreDTO = mapper.convertValue(iterator.next(), UserScoreDTO.class);
            userScoreDTO.setUserName(getUserName(userScoreDTO.getUserId()));
            scoreDTOS.add(userScoreDTO);
        }
        for(UserScoreDTO dto: scoreDTOS){
            System.out.println(dto.getScore());
        }
//        Collections.sort(scoreDTOS, new Comparator<UserScoreDTO>() {
//            @Override
//            public int compare(UserScoreDTO o1, UserScoreDTO o2) {
//                return Integer.compare(o1.getScore(), o2.getScore());
//            }
//        });
        return scoreDTOS;
    }

    @Override
    public String getUserName(String userId) {
        final String uri = " "+"?userId="+userId;
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(uri, String.class);
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
    public List<UserScore> fetchGlobalScores() {
        return (List) userScoreRepository.getScores();
    }

    @Override
    public List<ContestDTO> getContestSummary() {
        final String uriDynamic = "http://demo2494511.mockable.io/testing";
        final String uriStatic = "http://demo2494511.mockable.io/testing";

        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper mapper = new ObjectMapper();
        HttpHeaders headers=new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity requestEntity=new HttpEntity(headers);
        List<ContestDTO> contestDTOS = new ArrayList<>();

        //Dynamic
        ResponseEntity<?> entityResponse = restTemplate.exchange(uriDynamic, HttpMethod.GET, requestEntity, List.class);
        List contests = (List) entityResponse.getBody();
        Iterator iterator= contests.iterator();
        while (iterator.hasNext()) {
            ContestDTO contestDTO = mapper.convertValue(iterator.next(), ContestDTO.class);
            contestDTO.setType("Dynamic");
            contestDTOS.add(contestDTO);
        }

        //Static
        entityResponse = restTemplate.exchange(uriStatic, HttpMethod.GET, requestEntity, List.class);
        contests = (List) entityResponse.getBody();
        iterator= contests.iterator();
        while (iterator.hasNext()) {
            ContestDTO contestDTO = mapper.convertValue(iterator.next(), ContestDTO.class);
            contestDTO.setType("Static");
            contestDTOS.add(contestDTO);
        }
        return contestDTOS;
    }
}
