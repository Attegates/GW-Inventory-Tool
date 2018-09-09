/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atteg.GWInventoryTool.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 *
 * @author laaks
 */
public class Bag {

    @JsonProperty("inventory")
    private List<ItemStorage> inventory;

    public List<ItemStorage> getInventory() {
        return inventory;
    }

    public void setInventory(List<ItemStorage> inventory) {
        this.inventory = inventory;
    }

}
