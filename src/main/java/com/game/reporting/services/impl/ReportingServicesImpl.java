package com.game.reporting.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.game.reporting.dto.UserGlobalScoreDTO;
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
    public List<UserGlobalScoreDTO> fetchScores(){
        final String uri = "";

        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper mapper = new ObjectMapper();
        HttpHeaders headers=new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity requestEntity=new HttpEntity(headers);

        ResponseEntity<?> entityResponse = restTemplate.exchange(uri, HttpMethod.GET,requestEntity,List.class);

        System.out.println(entityResponse.toString());
        List scores = (List) entityResponse.getBody();
        Iterator iterator= scores.iterator();
        List<UserGlobalScoreDTO> scoreDTOS = new ArrayList<>();
        while (iterator.hasNext()) {
            UserGlobalScoreDTO productDTO = mapper.convertValue(iterator.next(), UserGlobalScoreDTO.class);
            scoreDTOS.add(productDTO);
        }
        for(UserGlobalScoreDTO dto: scoreDTOS){
            System.out.println(dto.getScore());
        }
        return scoreDTOS;
    }
}
