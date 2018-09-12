/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atteg.GWInventoryTool.service;

import com.atteg.GWInventoryTool.model.BagList;
import com.atteg.GWInventoryTool.model.ItemStorage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author laaks
 */
@Service
public class StorageServiceImpl implements StorageService {
    
    private final RestTemplate restTemplate;

    @Autowired
    public StorageServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public List<ItemStorage> getBank(String accessToken) {
        /*
        ResponseEntity<List<ItemStorage>> response = this.restTemplate.exchange("https://api.guildwars2.com/v2/account/bank?access_token={accessToken}",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<ItemStorage>>() {
        }, accessToken);

        // Filter out null values
        List<ItemStorage> bank = response.getBody().stream().filter(Objects::nonNull).collect(Collectors.toList());

        // Add name "Bank" to each storage
        bank.forEach(storage -> storage.setName("Bank"));
         */

        final String url = "https://api.guildwars2.com/v2/account/bank?access_token={accessToken}";
        
        ItemStorage[] response = restTemplate.getForObject(url, ItemStorage[].class, accessToken);

        // Ensure response body was not empty. (Not [] but empty!)
        List<ItemStorage> bank = Optional.ofNullable(Arrays.asList(response)).orElseGet(ArrayList::new);

        // Filter out null values
        bank = bank.stream().filter(Objects::nonNull).collect(Collectors.toList());
        // Add name "Bank" to each storage
        bank.forEach(storage -> storage.setName("Bank"));

        return bank;

    }

    @Override
    public List<ItemStorage> getSharedInventory(String accessToken) {
        final String url = "https://api.guildwars2.com/v2/account/inventory?access_token={accessToken}";
        
        ItemStorage[] response = restTemplate.getForObject(url, ItemStorage[].class, accessToken);

        // Ensure response body was not empty. (Not [] but empty!)
        List<ItemStorage> inventory = Optional.ofNullable(Arrays.asList(response)).orElseGet(ArrayList::new);

        // Filter out null values
        inventory = inventory.stream().filter(Objects::nonNull).collect(Collectors.toList());
        // Add name "Bank" to each storage
        inventory.forEach(storage -> storage.setName("Shared Inventory"));

        return inventory;
    }

    @Override
    public List<ItemStorage> getCharacterInventory(String accessToken, String characterName) {
        final String url = "https://api.guildwars2.com/v2/characters/{characterName}/inventory?access_token={accessToken}";

        BagList response = restTemplate.getForObject(url, BagList.class, characterName, accessToken);

        BagList bags = Optional.ofNullable(response).orElseGet(BagList::new);

        List<ItemStorage> fullInventory = new ArrayList<>();

        // Combine contents of each bag into one list and filter out null values
        bags.getBags().forEach((b) -> {
            fullInventory.addAll(b.getInventory().stream().filter(Objects::nonNull).collect(Collectors.toList()));
        });

        // Add characters name to each storage
        fullInventory.forEach(storage -> storage.setName(characterName));

        return fullInventory;

    }

    @Override
    public List<ItemStorage> getMaterialStorage(String accessToken) {
        final String url = "https://api.guildwars2.com/v2/account/materials?access_token={accessToken}";

        ItemStorage[] response = restTemplate.getForObject(url, ItemStorage[].class, accessToken);

        List<ItemStorage> storage = Optional.ofNullable(Arrays.asList(response)).orElseGet(ArrayList::new);
        
        // This API does not return null for empty slots, instead the count is 0.
        // Filter out all items with count 0
        storage = storage.stream().filter(s -> s.getCount() != 0).collect(Collectors.toList());
        storage.forEach(s -> s.setName("Material Storage"));


        return storage;
    }

}
