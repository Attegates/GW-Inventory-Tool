/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atteg.GWInventoryTool.service;

import com.atteg.GWInventoryTool.model.Item;
import java.util.List;

/**
 *
 * @author laaks
 */
public interface ItemDetailsService {
    public Item getItem(String id);
    
    public List<Item> getItems(List<Integer> ids);
}
