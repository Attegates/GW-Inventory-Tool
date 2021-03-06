/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atteg.GWInventoryTool.service;

import com.atteg.GWInventoryTool.repository.AccessTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetAccessTokenServiceImpl implements GetAccessTokenService {

    private final AccessTokenRepository repository;
    
    @Autowired
    public GetAccessTokenServiceImpl(AccessTokenRepository repository) {
        this.repository = repository;
    }
    
    @Override
    public String getToken(String userName) {
        //return "3657F510-9D21-8C43-855E-FE582C4C49741BB22799-4929-4BE1-8AAF-B61BBFD991FA";
        return repository.findByUsername(userName);
    }

}
