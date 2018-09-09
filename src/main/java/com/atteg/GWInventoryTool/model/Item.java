/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atteg.GWInventoryTool.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author laaks
 */
public class Item {

    private int id;

    private String name;

    private List<Item> storages;

    public Item() {
        this.storages = new ArrayList<>();
    }

    public Item(int id, String name) {
        this();
        this.id = id;
        this.name = name;
    }

    public void addStorage(ItemStorage storage) {
        this.storages.add(storage);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        /* Toimii */
        return this.getStorages().stream().map(Item::getCount).reduce(0, Integer::sum);
        /* Toimii
        return this.getStorages().stream().map((itemStorage) -> itemStorage.getCount()).reduce(0, Integer::sum);
         */
 /* Toimii
        return this.getStorages().stream().map((item) -> item.getCount()).reduce(0, Integer::sum);
         */
 /* Ei toimi?
        return this.getStorages().stream().map(ItemStorage::getCount).reduce(0, Integer::sum);
         */
    }

    public List<Item> getStorages() {
        return storages;
    }

    public void setStorages(List<Item> storages) {
        this.storages = storages;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "id: " + getId() + " - name: " + getName();
    }

}
