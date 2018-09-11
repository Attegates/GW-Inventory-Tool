/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atteg.GWInventoryTool.repository;

import com.atteg.GWInventoryTool.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author laaks
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

    @Query("select u from User u where u.username=:username")
    User findByUsername(@Param("username") String username);
    
}
