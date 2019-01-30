package com.game.reporting.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.game.reporting.dto.UserGlobalScoreDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/getReport")
public class ReportingController {
    @GetMapping("/leaderboard")
    public List<UserGlobalScoreDTO> getLeaderboard(){
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
