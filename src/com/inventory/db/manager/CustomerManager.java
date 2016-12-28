package com.inventory.db.manager;

import com.inventory.bean.CustomerInfo;
import com.inventory.db.Database;
import com.inventory.db.query.helper.EasyStatement;
import com.inventory.db.repositories.Customer;
import com.inventory.db.repositories.Profile;
import com.inventory.exceptions.DBSetupException;
import com.inventory.response.ResultEvent;
import com.inventory.util.Constants;
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
    
    /**
     * This method will create a new customer
     * @param customerInfo customer info
     * @return ResultEvent
     * @author nazmul hasan on 28th december 2016
     */
    public ResultEvent createCustomer(CustomerInfo customerInfo)
    {
        ResultEvent resultEvent = new ResultEvent();
        Connection connection = null;
        try {
            connection = Database.getInstance().getConnection();
            connection.setAutoCommit(false);            
            customerInfo.getProfileInfo().setGroupId(Constants.GROUP_ID_CUSTOMER);            
            profile = new Profile(connection);
            int profileId = profile.createProfile(customerInfo.getProfileInfo()); 
            if(profileId > 0)
            {
                customerInfo.getProfileInfo().setId(profileId);
                customer = new Customer(connection);
                if(customer.createCustomer(customerInfo))
                {
                    resultEvent.setResponseCode(2000);
                resultEvent.setMessage("Customer is created successfully.");
                }
                else
                {
                    //set error message here
                }
            }
            else
            {
                //set error message here
            }
            connection.commit();
            connection.close();            
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
            try {
                if(connection != null){
                    connection.rollback();
                    connection.close();
                }
            } catch (SQLException ex1) {
                logger.error(ex1.getMessage());
            }
            //set error message here
        } catch (DBSetupException ex) {
            logger.error(ex.getMessage());
            //set error message here
        }
        return resultEvent;
    }
    
    /**
     * This method will return all customers
     * @return list, customer list
     * @author nazmul hasan on 28th december 2016
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
        return customerList;
    }
    
    /**
     * This method will update customer info
     * @param customerInfo customer info
     * @return ResultEvent
     * @author nazmul hasan on 28th december 2016
     */
    public ResultEvent updateCustomer(CustomerInfo customerInfo)
    {
        ResultEvent resultEvent = new ResultEvent();
        Connection connection = null;
        try {
            connection = Database.getInstance().getConnection();
            connection.setAutoCommit(false);
            
            profile = new Profile(connection);
            if(profile.updateProfile(customerInfo.getProfileInfo()))
            {
                //right now there is no fields for customer other than profile fields
                resultEvent.setResponseCode(2000);
                resultEvent.setMessage("Customer info is updated successfully.");  
            }
            else
            {
                //set error message here
            }
            connection.commit();
            connection.close();            
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
            try {
                if(connection != null){
                    connection.rollback();
                    connection.close();
                }
            } catch (SQLException ex1) {
                logger.error(ex1.getMessage());
            }
            //set error message here
        } catch (DBSetupException ex) {
            logger.error(ex.getMessage());
            //set error message here
        }
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
