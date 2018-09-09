/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atteg.GWInventoryTool.model;

/**
 *
 * @author laaks
 */
public class ItemStorage extends Item {
    
    private int count;
    
    public ItemStorage() {
        super();
    }
    
    public ItemStorage(int id, String name, int count) {
        super(id, name);
        this.count = count;
    }

    @Override
    public int getCount() {
        return count;
    }
    
    @Override
    public String toString() {
        return super.getId() + " - " + super.getName() + " - " + this.getCount();
    }
    
    
    
    
    
}
