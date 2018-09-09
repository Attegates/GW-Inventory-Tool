/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atteg.GWInventoryTool.service;

import com.atteg.GWInventoryTool.model.Item;
import com.google.common.base.Joiner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author laaks
 */
@Service
public class ItemDetailsServiceImpl implements ItemDetailsService {

    private final RestTemplate restTemplate;

    @Autowired
    public ItemDetailsServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public Item getItem(String id) {
        return this.restTemplate.getForObject("https://api.guildwars2.com/v2/items/{id}", Item.class, id);
    }

    /**
     * Get item details for specified ids. NOTE! Maximum amount of ids is 200
     * due to GW2 API limits.
     *
     * @param ids
     * @return
     */
    @Override
    public List<Item> getItems(List<Integer> ids) {
        Item[] response = restTemplate.getForObject("https://api.guildwars2.com/v2/items?ids={ids}", Item[].class, formatIds(ids));
        // Ensure response body was not empty. (Not [] but empty!)
        return Optional.ofNullable(Arrays.asList(response)).orElseGet(ArrayList::new);
    }

    /**
     *
     * @param ids
     * @return
     */
    private String formatIds(List<Integer> ids) {
        return Joiner.on(',').join(ids);
    }

}
