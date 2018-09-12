/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atteg.GWInventoryTool.ui;

import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author laaks
 */
@SpringUI(path = SetTokenUI.PATH)
public class SetTokenUI extends UI {

    public static final String PATH = "/set_token";

    private final SetTokenUIFactory setTokenUIFactory;

    @Autowired
    public SetTokenUI(SetTokenUIFactory setTokenUIFactory) {
        this.setTokenUIFactory = setTokenUIFactory;
    }

    @Override
    protected void init(VaadinRequest request) {
        setContent(setTokenUIFactory.createComponent());
    }
}
