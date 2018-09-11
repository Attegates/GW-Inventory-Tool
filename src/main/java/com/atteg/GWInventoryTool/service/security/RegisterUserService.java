/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atteg.GWInventoryTool.service.security;

/**
 *
 * @author laaks
 */
public interface RegisterUserService {

    public void save(String username, String password)
            throws UsernameExistsException;

    public boolean usernameExists(String username);
}
