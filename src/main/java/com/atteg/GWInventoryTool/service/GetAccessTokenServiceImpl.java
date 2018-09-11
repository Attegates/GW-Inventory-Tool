/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atteg.GWInventoryTool.service;

import org.springframework.stereotype.Service;

@Service
public class GetAccessTokenServiceImpl implements GetAccessTokenService {

    @Override
    public String getToken(String userName) {
        // TODO return current users api key.
        return "3657F510-9D21-8C43-855E-FE582C4C49741BB22799-4929-4BE1-8AAF-B61BBFD991FA";
    }

}
