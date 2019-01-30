package com.game.reporting.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.game.reporting.dto.UserScoreDTO;
import com.game.reporting.services.ReportingServices;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ReportingServicesImpl implements ReportingServices {

    @Override
    public List<UserScoreDTO> fetchScores(String contestId){
        String uri = "http://demo2494511.mockable.io/testing";
        if(!contestId.equals("global"))
            uri+="/"+contestId;
        System.out.println(uri);
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper mapper = new ObjectMapper();
        HttpHeaders headers=new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity requestEntity=new HttpEntity(headers);

        ResponseEntity<?> entityResponse = restTemplate.exchange(uri, HttpMethod.GET,requestEntity,List.class);

        System.out.println(entityResponse.toString());
        List scores = (List) entityResponse.getBody();
        Iterator iterator= scores.iterator();
        List<UserScoreDTO> scoreDTOS = new ArrayList<>();
        while (iterator.hasNext()) {
            UserScoreDTO productDTO = mapper.convertValue(iterator.next(), UserScoreDTO.class);
            scoreDTOS.add(productDTO);
        }
        for(UserScoreDTO dto: scoreDTOS){
            System.out.println(dto.getScore());
        }
        return scoreDTOS;
    }
}
