/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inventory.db.manager;

import com.inventory.bean.CustomerInfo;
import com.inventory.bean.SupplierInfo;
import com.inventory.db.Database;
import com.inventory.db.query.helper.EasyStatement;
import com.inventory.db.repositories.Profile;
import com.inventory.db.repositories.Supplier;
import com.inventory.exceptions.DBSetupException;
import com.inventory.response.ResultEvent;
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
public class SupplierManager {
    private Profile profile;
    private Supplier supplier;
    private final Logger logger = LoggerFactory.getLogger(EasyStatement.class);
    /**
     * This method will create a new supplier
     * @param supplierInfo, 
     */
    public ResultEvent createSupplier(SupplierInfo supplierInfo)
    {
        ResultEvent resultEvent = new ResultEvent();
        //create a new user
        Connection connection = null;
        try {
            connection = Database.getInstance().getConnection();
            connection.setAutoCommit(false);
            
            //right now group id constant. Later update it from configuraiton file
            supplierInfo.getProfileInfo().setGroupId(1);
            profile = new Profile(connection);
            int userId = profile.createProfile(supplierInfo.getProfileInfo()); 
            
            supplierInfo.getProfileInfo().setId(userId);
            supplier = new Supplier(connection);
            supplier.createSupplier(supplierInfo);

            connection.commit();
            connection.close();
            resultEvent.setResponseCode(2000);
            resultEvent.setMessage("Supplier is created successfully.");
        } catch (SQLException ex) {
            try {
                if(connection != null){
                    connection.rollback();
                    connection.close();
                }
            } catch (SQLException ex1) {
                logger.error(ex1.getMessage());
            }
        } catch (DBSetupException ex) {
            logger.error(ex.getMessage());
        }
        //add user under a group
        //add address
        //add supplier info
        return resultEvent;
    }
    
    /**
     * This method will return all suppliers
     * @return list, supplier list
     */
    public List<SupplierInfo> getAllSuppliers()
    {
        List<SupplierInfo> supplierList = new ArrayList<>(); 
        Connection connection = null;
        try {
            connection = Database.getInstance().getConnection();
            
            supplier = new Supplier(connection);
            supplierList = supplier.getAllSuppliers();

            connection.close();
        } catch (SQLException ex) {
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
        return supplierList;
    }
    
    public ResultEvent updateSupplier(SupplierInfo supplierInfo)
    {
        ResultEvent resultEvent = new ResultEvent();
        resultEvent.setResponseCode(2000);
        resultEvent.setMessage("Supplier info is updated successfully.");  
        return resultEvent;
    }
    
    public List<SupplierInfo> searchSuppliers(String phone)
    {
        List<SupplierInfo> supplierList = new ArrayList<>(); 
        Connection connection = null;
        try {
            connection = Database.getInstance().getConnection();
            
            supplier = new Supplier(connection);
            supplierList = supplier.getAllSuppliers();

            connection.close();
        } catch (SQLException ex) {
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
        return supplierList;
    }
    
}
