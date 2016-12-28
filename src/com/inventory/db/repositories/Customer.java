/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inventory.db.repositories;

import com.inventory.bean.CustomerInfo;
import com.inventory.bean.ProfileInfo;
import com.inventory.db.manager.ProductManager;
import com.inventory.db.query.helper.QueryField;
import com.inventory.db.query.helper.QueryManager;
import com.inventory.db.query.helper.EasyStatement;
import com.inventory.exceptions.DBSetupException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nazmul hasan
 */
public class Customer{
    private Connection connection;
    /***
     * Restrict to call without connection
     */
    private Customer(){}
    public Customer(Connection connection) {
        this.connection = connection;
    }
    public void createCustomer(CustomerInfo customerInfo) throws DBSetupException, SQLException
    {
        try (EasyStatement stmt = new EasyStatement(connection, QueryManager.CREATE_CUSTOMER)) {
            stmt.setInt(QueryField.USER_ID, customerInfo.getProfileInfo().getId());
            stmt.executeUpdate();
        }
    }
    
    public List<CustomerInfo> getAllCustomers()throws DBSetupException, SQLException
    {
        List<CustomerInfo> customerList = new ArrayList<>();
        try (EasyStatement stmt = new EasyStatement(connection, QueryManager.GET_ALL_CUSTOMERS))
        {
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                CustomerInfo customerInfo = new CustomerInfo();
                ProfileInfo userInfo = new ProfileInfo();
                userInfo.setId(rs.getInt(QueryField.USER_ID));
                userInfo.setFirstName(rs.getString(QueryField.FIRST_NAME));
                userInfo.setLastName(rs.getString(QueryField.LAST_NAME));
                userInfo.setEmail(rs.getString(QueryField.EMAIL));
                userInfo.setPhone(rs.getString(QueryField.PHONE));
                userInfo.setFax(rs.getString(QueryField.FAX));
                userInfo.setWebsite(rs.getString(QueryField.WEBSITE));
                customerInfo.setProfileInfo(userInfo);
                customerList.add(customerInfo);
            }
        }
        return customerList;
    }
}
