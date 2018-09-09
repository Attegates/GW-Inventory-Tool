/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atteg.GWInventoryTool.service;

import com.atteg.GWInventoryTool.model.ItemStorage;
import java.util.List;

/**
 *
 * @author laaks
 */
public interface StorageService {
    public List<ItemStorage> getBank();
    public List<ItemStorage> getSharedInventory();
    public List<ItemStorage> getCharacterInventory(String characterName);
    public List<ItemStorage> getMaterialStorage();
}
