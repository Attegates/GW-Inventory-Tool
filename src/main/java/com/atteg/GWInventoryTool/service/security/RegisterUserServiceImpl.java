/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atteg.GWInventoryTool.service.security;

import com.atteg.GWInventoryTool.model.User;
import com.atteg.GWInventoryTool.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author laaks
 */
@Service
public class RegisterUserServiceImpl implements RegisterUserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;  // Bean in SecurityConfig.java

    @Autowired
    public RegisterUserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public void save(String username, String password) throws UsernameExistsException {

        if (usernameExists(username)) {
            throw new UsernameExistsException("Username \"" + username + "\" already exists.");
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        userRepository.save(user);
    }

    @Override
    public boolean usernameExists(String username) {

        User user = userRepository.findByUsername(username);
        return user != null;

    }

}
