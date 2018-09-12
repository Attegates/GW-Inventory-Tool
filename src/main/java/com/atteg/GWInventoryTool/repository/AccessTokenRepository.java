/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atteg.GWInventoryTool.repository;

import com.atteg.GWInventoryTool.model.AccessToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author laaks
 */
@Repository
public interface AccessTokenRepository extends JpaRepository<AccessToken, Integer> {

    @Query(value = "SELECT token FROM accesstoken WHERE user_id = (SELECT id FROM user WHERE username = :username)", nativeQuery = true)
    String findByUsername(@Param("username") String username);

}
