/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atteg.GWInventoryTool.ui;

import com.atteg.GWInventoryTool.model.Item;
import com.atteg.GWInventoryTool.model.ItemStorage;
import com.atteg.GWInventoryTool.service.CharacterService;
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
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author laaks
 */
@SpringUI(path = MainUI.PATH)
@Theme("valo")
public class MainUI extends UI {

    public static final String PATH = "/ui";

    private final StorageService storageService;

    private final ItemDetailsService itemDetailsService;

    private final CharacterService characterService;

    @Autowired
    public MainUI(StorageService storageService, ItemDetailsService itemDetailsService, CharacterService characterService) {
        this.storageService = storageService;
        this.itemDetailsService = itemDetailsService;
        this.characterService = characterService;
    }

    @Override
    protected void init(VaadinRequest request) {
        VerticalLayout root = new VerticalLayout();

        // TESTAUKSIA
        List<ItemStorage> allItems = new ArrayList<>();
        allItems.addAll(storageService.getBank());
        System.out.println("bank fetched");
        allItems.addAll(storageService.getSharedInventory());
        System.out.println("shared fetched");
        allItems.addAll(storageService.getMaterialStorage());
        System.out.println("mats fetched");

        List<String> characters = characterService.getCharacterNames();
        System.out.println("char names fetched");
        for (String s : characters) {
            allItems.addAll(storageService.getCharacterInventory(s));
            System.out.println(s + "'s inventory fetched");
        }

        //List<Integer> itemIds = allItems.stream().map(Item::getId).collect(Collectors.toList());
        // Make list of ids not including duplicate ids
        List<Integer> itemIds = Lists.newArrayList(Sets.newHashSet(allItems.stream().map(Item::getId).collect(Collectors.toList())));

        System.out.println("fetching item details");
        List<Item> items = itemDetailsService.getItems(itemIds);
        System.out.println("item details fetched");

        System.out.println("connecting items and storages");
        for (Item i : items) {
            for (Item b : allItems) {
                if (i.getId() == b.getId()) {
                    i.addStorage((ItemStorage) b);
                }
            }
        }
        System.out.println("items and storages connected");

        TreeGrid<Item> grid = new TreeGrid<>();

        grid.addColumn(Item::getName).setCaption("Name");
        grid.addColumn(Item::getCount).setCaption("Quantity");

        grid.setItems(items, Item::getStorages);
        root.addComponent(grid);

        setContent(root);

    }
}
