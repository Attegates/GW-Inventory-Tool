/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atteg.GWInventoryTool.service;

import com.atteg.GWInventoryTool.model.AccessToken;
import com.atteg.GWInventoryTool.repository.AccessTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetAccessTokenServiceImpl implements SetAccessTokenService {

    private final AccessTokenRepository repository;
    
    @Autowired
    public SetAccessTokenServiceImpl(AccessTokenRepository repository) {
        this.repository = repository;
    }
    
    @Override
    public void setToken(AccessToken tokenDAO) {
        AccessToken token = new AccessToken();
        token.setAccessToken(tokenDAO.getAccessToken());
        token.setUser(tokenDAO.getUser());
        repository.save(token);
    }
    
}
