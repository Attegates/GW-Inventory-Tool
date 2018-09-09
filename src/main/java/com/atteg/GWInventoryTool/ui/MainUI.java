/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atteg.GWInventoryTool.ui;

import com.atteg.GWInventoryTool.model.Item;
import com.atteg.GWInventoryTool.model.ItemStorage;
import com.atteg.GWInventoryTool.service.ItemDetailsService;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.TreeGrid;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.atteg.GWInventoryTool.service.StorageService;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 *
 * @author laaks
 */
@SpringUI(path = "/treegrid")
@Theme("valo")
public class MainUI extends UI {

    private final StorageService storageService;

    private final ItemDetailsService itemDetailsService;

    @Autowired
    public MainUI(StorageService storageService, ItemDetailsService itemDetailsService) {
        this.storageService = storageService;
        this.itemDetailsService = itemDetailsService;
    }

    @Override
    protected void init(VaadinRequest request) {
        VerticalLayout root = new VerticalLayout();

        // TESTAUKSIA
        List<ItemStorage> allItems = new ArrayList<>(); 
        allItems.addAll(storageService.getBank());
        allItems.addAll(storageService.getSharedInventory());
        
        List<Integer> itemIds = allItems.stream().map(Item::getId).collect(Collectors.toList());
        List<Item> items = itemDetailsService.getItems(itemIds);
        
        ItemStorage x = new ItemStorage(70250, "Bibibi", 5);
        allItems.add(x);

        for (Item i : items) {
            for (Item b : allItems) {
                if (i.getId() == b.getId()) {
                    i.addStorage((ItemStorage) b);
                }
            }
        }
        
        //System.out.println(storageService.getCharacterInventory("Anyame"));
        System.out.println(storageService.getMaterialStorage());

        TreeGrid<Item> grid = new TreeGrid<>();

        grid.addColumn(Item::getName).setCaption("Name");
        grid.addColumn(Item::getCount).setCaption("Quantity");

        grid.setItems(items, Item::getStorages);

        root.addComponent(grid);

        setContent(root);

    }
}
