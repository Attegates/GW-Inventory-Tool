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
@SpringUI(path = SignupUI.PATH)
public class SignupUI extends UI {

    public final static String PATH = "/signup";

    private final SignupUIFactory signupUIFactory;
    
    @Autowired
    public SignupUI(SignupUIFactory signupUIFactory) {
        this.signupUIFactory = signupUIFactory;
    }

    @Override
    protected void init(VaadinRequest request) {
        setContent(signupUIFactory.createComponent());
    }
}
