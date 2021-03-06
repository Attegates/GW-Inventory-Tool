/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atteg.GWInventoryTool.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CharacterServiceImpl implements CharacterService {


    private final RestTemplate restTemplate;

    @Autowired
    public CharacterServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public List<String> getCharacterNames(String accessToken) {
        
        final String url = "https://api.guildwars2.com/v2/characters?access_token={accessToken}";

        String[] response = restTemplate.getForObject(url, String[].class, accessToken);

        List<String> characters = Optional.ofNullable(Arrays.asList(response)).orElseGet(ArrayList::new);
        
        return characters;

    }

}
