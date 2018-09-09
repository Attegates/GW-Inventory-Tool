/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atteg.GWInventoryTool.model;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author laaks
 */
public class ItemTest {
    
    @Test
    public void testEquals() {
        Item i = new Item(1, "");
        ItemStorage is = new ItemStorage(1, "", 0);
        boolean expected = true;
        boolean actual = i.equals(is);
        assertEquals(expected, actual);
    }
    
}
