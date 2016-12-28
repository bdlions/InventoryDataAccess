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
import com.inventory.db.repositories.Customer;
import com.inventory.db.repositories.Supplier;
import com.inventory.db.repositories.Profile;
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
public class CustomerManager {
    private Profile profile;
    private Customer customer;
    private final Logger logger = LoggerFactory.getLogger(EasyStatement.class);
    public ResultEvent createCustomer(CustomerInfo customerInfo)
    {
        ResultEvent resultEvent = new ResultEvent();
        //create a new user
        Connection connection = null;
        try {
            connection = Database.getInstance().getConnection();
            connection.setAutoCommit(false);
            
            //right now group id constant. Later update it from configuraiton file
            customerInfo.getProfileInfo().setGroupId(2);
            
            profile = new Profile(connection);
            int userId = profile.createProfile(customerInfo.getProfileInfo()); 
            
            customerInfo.getProfileInfo().setId(userId);
            customer = new Customer(connection);
            customer.createCustomer(customerInfo);

            connection.commit();
            connection.close();
            resultEvent.setResponseCode(2000);
            resultEvent.setMessage("Customer is created successfully.");
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
        return resultEvent;
    }
    
    /**
     * This method will return all customers
     * @return list, customer list
     */
    public List<CustomerInfo> getAllCustomers()
    {
        List<CustomerInfo> customerList = new ArrayList<>(); 
        Connection connection = null;
        try {
            connection = Database.getInstance().getConnection();
            
            customer = new Customer(connection);
            customerList = customer.getAllCustomers();

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
        return customerList;
    }
    
    public ResultEvent updateCustomer(CustomerInfo customerInfo)
    {
        ResultEvent resultEvent = new ResultEvent();
        resultEvent.setResponseCode(2000);
        resultEvent.setMessage("Customer info is updated successfully.");  
        return resultEvent;
    }
    
    public List<CustomerInfo> searchCustomers(String phone)
    {
        List<CustomerInfo> customerList = new ArrayList<>(); 
        Connection connection = null;
        try {
            connection = Database.getInstance().getConnection();
            
            customer = new Customer(connection);
            customerList = customer.getAllCustomers();

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
        return customerList;
    }
}
