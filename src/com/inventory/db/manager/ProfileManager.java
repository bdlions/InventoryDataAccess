/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inventory.db.manager;

import com.inventory.bean.AddressCategoryInfo;
import com.inventory.bean.AddressTypeInfo;
import com.inventory.db.Database;
import com.inventory.db.repositories.Profile;
import com.inventory.exceptions.DBSetupException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author nazmul hasan
 */
public class ProfileManager {
    private Profile user;
    private final Logger logger = LoggerFactory.getLogger(ProfileManager.class);
    /**
     * This method will return all address types
     * @return List, address type list
     */
    public List<AddressTypeInfo> getAllAddressTypes()
    {
        List<AddressTypeInfo> addressTypeList = new ArrayList<>();
        Connection connection = null;
        try {
            connection = Database.getInstance().getConnection();
            
            user = new Profile(connection);
            addressTypeList = user.getAllAddressTypes();

            connection.close();
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
            try {
                if(connection != null){
                    connection.close();
                }
            } catch (SQLException ex1) {
                logger.error(ex1.getMessage());
            }
        } catch (DBSetupException ex) {
            logger.error(ex.getMessage());
        }
        return addressTypeList;
    }
    
    /**
     * This method will return all address categories
     * @return List, address category list
     */
    public List<AddressCategoryInfo> getAllAddressCategories()
    {
        List<AddressCategoryInfo> addressCategoryList = new ArrayList<>();
        Connection connection = null;
        try {
            connection = Database.getInstance().getConnection();
            
            user = new Profile(connection);
            addressCategoryList = user.getAllAddressCategories();

            connection.close();
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
            try {
                if(connection != null){
                    connection.close();
                }
            } catch (SQLException ex1) {
                logger.error(ex1.getMessage());
            }
        } catch (DBSetupException ex) {
            logger.error(ex.getMessage());
        }
        return addressCategoryList;
    }
}
