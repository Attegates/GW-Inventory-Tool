/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atteg.GWInventoryTool.ui;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author laaks
 */
@SpringComponent
public class LoginUIFactory {

    private final DaoAuthenticationProvider daoAuthenticationProvider;  // Bean in SecurityConfig.java

    @Autowired
    public LoginUIFactory(DaoAuthenticationProvider daoAuthenticationProvider) {
        this.daoAuthenticationProvider = daoAuthenticationProvider;
    }

    private class LoginForm {

        private VerticalLayout root;
        private Panel panel;
        private TextField username;
        private PasswordField password;
        private Button loginButton;
        private Button signupButton;

        public LoginForm init() {

            root = new VerticalLayout();
            root.setMargin(true);
            root.setHeight("100%");

            panel = new Panel("Login");
            panel.setSizeUndefined();

            username = new TextField("Username");
            password = new PasswordField("Password");

            loginButton = new Button("Login");
            loginButton.setStyleName(ValoTheme.BUTTON_FRIENDLY);
            signupButton = new Button("Signup");
            signupButton.setStyleName(ValoTheme.BUTTON_PRIMARY);

            return this;
        }

        public Component layout() {
            root.addComponent(panel);
            root.setComponentAlignment(panel, Alignment.MIDDLE_CENTER);

            FormLayout loginLayout = new FormLayout();
            loginLayout.addComponent(username);
            loginLayout.addComponent(password);

            loginLayout.addComponent(new HorizontalLayout(loginButton, signupButton));
            loginLayout.setSizeUndefined();
            loginLayout.setMargin(true);

            loginButton.addClickListener(event -> {
                try {
                    Authentication auth = new UsernamePasswordAuthenticationToken(username.getValue(), password.getValue());
                    Authentication authenticated = daoAuthenticationProvider.authenticate(auth);
                    SecurityContextHolder.getContext().setAuthentication(authenticated);

                    UI.getCurrent().getPage().setLocation(MainUI.PATH);
                } catch (AuthenticationException e) {
                    Notification.show("Error!", "Login failed. Try again.", Notification.Type.ERROR_MESSAGE);
                }
                password.clear();
            });

            signupButton.addClickListener(event -> {
                UI.getCurrent().getPage().setLocation(SignupUI.PATH);
            });

            panel.setContent(loginLayout);

            return root;
        }
    }

    Component createComponent() {
        return new LoginForm().init().layout();
    }
}
