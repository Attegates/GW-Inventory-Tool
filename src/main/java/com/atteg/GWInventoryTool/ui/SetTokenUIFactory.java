/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atteg.GWInventoryTool.ui;

import com.atteg.GWInventoryTool.model.AccessToken;
import com.atteg.GWInventoryTool.model.MyUser;
import com.atteg.GWInventoryTool.repository.AccessTokenRepository;
import com.atteg.GWInventoryTool.service.SetAccessTokenService;
import com.atteg.GWInventoryTool.service.security.UserPrincipal;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author laaks
 */
@SpringComponent
public class SetTokenUIFactory {

    private final SetAccessTokenService accessTokenService;
    
    @Autowired
    public SetTokenUIFactory(SetAccessTokenService accessTokenService) {
        this.accessTokenService = accessTokenService;
    }

    private class SetTokenForm {

        private VerticalLayout root;
        private Panel panel;
        private TextField tokenField;
        private Button save;

        public SetTokenForm init() {
            root = new VerticalLayout();
            root.setMargin(true);
            root.setHeight("100%");

            panel = new Panel("Set or change API Key");
            panel.setSizeUndefined();

            tokenField = new TextField("API KEY");

            save = new Button("Save key");
            save.setStyleName(ValoTheme.BUTTON_FRIENDLY);

            return this;
        }

        public Component layout() {
            root.addComponent(panel);
            root.setComponentAlignment(panel, Alignment.MIDDLE_CENTER);
            FormLayout layout = new FormLayout();

            layout.addComponent(tokenField);
            layout.addComponent(save);
            layout.setSizeUndefined();
            layout.setMargin(true);
            
            save.addClickListener(event -> {
                
                MyUser user = ((UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
                AccessToken token = new AccessToken();
                token.setUser(user);
                token.setAccessToken(tokenField.getValue());
                accessTokenService.setToken(token);

            });
            
            panel.setContent(layout);

            return root;
        }
    }

    Component createComponent() {
        return new SetTokenForm().init().layout();
    }

}
