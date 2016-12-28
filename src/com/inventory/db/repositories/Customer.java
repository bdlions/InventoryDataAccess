package com.inventory.db.repositories;

import com.inventory.bean.CustomerInfo;
import com.inventory.bean.ProfileInfo;
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
    
    /**
     * This method will create a customer
     * @param customerInfo customer info
     * @throws DBSetupException
     * @throws SQLException
     * @return boolean
     * @author nazmul hasan on 28th december 2016
     */
    public boolean createCustomer(CustomerInfo customerInfo) throws DBSetupException, SQLException
    {
        try (EasyStatement stmt = new EasyStatement(connection, QueryManager.CREATE_CUSTOMER)) {
            stmt.setInt(QueryField.USER_ID, customerInfo.getProfileInfo().getId());
            stmt.executeUpdate();
        }
        return true;
    }
    
    /**
     * This method will return all customers
     * @throws DBSetupException
     * @throws SQLException
     * @return List supplier list
     * @author nazmul hasan on 28th december 2016
     */
    public List<CustomerInfo> getAllCustomers()throws DBSetupException, SQLException
    {
        List<CustomerInfo> customerList = new ArrayList<>();
        try (EasyStatement stmt = new EasyStatement(connection, QueryManager.GET_ALL_CUSTOMERS))
        {
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                CustomerInfo customerInfo = new CustomerInfo();
                ProfileInfo profileInfo = new ProfileInfo();
                profileInfo.setId(rs.getInt(QueryField.USER_ID));
                profileInfo.setFirstName(rs.getString(QueryField.FIRST_NAME));
                profileInfo.setLastName(rs.getString(QueryField.LAST_NAME));
                profileInfo.setEmail(rs.getString(QueryField.EMAIL));
                profileInfo.setPhone(rs.getString(QueryField.PHONE));
                profileInfo.setFax(rs.getString(QueryField.FAX));
                profileInfo.setWebsite(rs.getString(QueryField.WEBSITE));
                customerInfo.setProfileInfo(profileInfo);
                customerList.add(customerInfo);
            }
        }
        return customerList;
    }
}
