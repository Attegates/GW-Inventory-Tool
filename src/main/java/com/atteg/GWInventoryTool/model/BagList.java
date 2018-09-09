/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atteg.GWInventoryTool.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Wrapper object to store character bags.
 * @author laaks
 */
public class BagList {

    private List<Bag> bags;
    
    public BagList() {
        this.bags = new ArrayList<>();
    }
   

    public List<Bag> getBags() {
        return bags;
    }

    public void setBags(List<Bag> bags) {
        this.bags = bags;
    }

}
