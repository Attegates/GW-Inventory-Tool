/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atteg.GWInventoryTool.ui;

import com.atteg.GWInventoryTool.model.Item;
import com.atteg.GWInventoryTool.model.ItemStorage;
import com.atteg.GWInventoryTool.service.CharacterService;
import com.atteg.GWInventoryTool.service.GetAccessTokenService;
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
import com.atteg.GWInventoryTool.service.security.UserPrincipal;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.vaadin.data.HasValue;
import com.vaadin.data.TreeData;
import com.vaadin.data.provider.TreeDataProvider;
import com.vaadin.ui.TextField;
import java.util.ArrayList;
import java.util.stream.Collectors;
import org.springframework.security.core.context.SecurityContextHolder;

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
    
    private final GetAccessTokenService tokenService;

    @Autowired
    public MainUI(StorageService storageService, ItemDetailsService itemDetailsService, CharacterService characterService, GetAccessTokenService tokenService) {
        this.storageService = storageService;
        this.itemDetailsService = itemDetailsService;
        this.characterService = characterService;
        this.tokenService = tokenService;
    }

    @Override
    protected void init(VaadinRequest request) {
        VerticalLayout root = new VerticalLayout();

        // TESTAUKSIA
        String accessToken = tokenService.getToken(((UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
        List<ItemStorage> allItems = new ArrayList<>();
        allItems.addAll(storageService.getBank(accessToken));
        allItems.addAll(storageService.getSharedInventory(accessToken));
        allItems.addAll(storageService.getMaterialStorage(accessToken));

        List<String> characters = characterService.getCharacterNames(accessToken);

        for (String s : characters) {
            allItems.addAll(storageService.getCharacterInventory(accessToken, s));
        }

        //List<Integer> itemIds = allItems.stream().map(Item::getId).collect(Collectors.toList());
        // Make list of ids not including duplicate ids
        List<Integer> itemIds = Lists.newArrayList(Sets.newHashSet(allItems.stream().map(Item::getId).collect(Collectors.toList())));


        List<Item> items = itemDetailsService.getItems(itemIds);



        for (Item i : items) {
            for (Item b : allItems) {
                if (i.getId() == b.getId()) {
                    i.addStorage((ItemStorage) b);
                }
            }
        }


        TreeGrid<Item> grid = new TreeGrid<>();

        grid.addColumn(Item::getName).setCaption("Name");
        grid.addColumn(Item::getCount).setCaption("Quantity");

        
        TreeData treeData = new TreeData(); 
        treeData.addItems(null, items);
        items.forEach(item -> treeData.addItems(item, item.getStorages()));
        TreeDataProvider<Item> dataProvider = new TreeDataProvider<>(treeData);
        grid.setDataProvider(dataProvider);

        // Filter hides child elements TODO fix or find better solution.
        TextField filterText = new TextField();
        filterText.addValueChangeListener(s -> {
            dataProvider.setFilter(item -> item.getName().toLowerCase().contains(s.getValue().toLowerCase()));
        });
        
        
        root.addComponent(filterText);
        root.addComponent(grid);
        setContent(root);

    }
    

}
