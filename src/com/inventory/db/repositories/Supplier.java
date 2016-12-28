/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inventory.db.repositories;

import com.inventory.bean.SupplierInfo;
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
public class Supplier {
    private Connection connection;
    /***
     * Restrict to call without connection
     */
    private Supplier(){}
    public Supplier(Connection connection) {
        this.connection = connection;
    }
    
    public void createSupplier(SupplierInfo supplierInfo) throws DBSetupException, SQLException
    {
        try (EasyStatement stmt = new EasyStatement(connection, QueryManager.CREATE_SUPPLIER)) {
            stmt.setInt(QueryField.USER_ID, supplierInfo.getProfileInfo().getId());
            stmt.setString(QueryField.REMARKS, supplierInfo.getRemarks());
            stmt.executeUpdate();
        }
    }
    
    public List<SupplierInfo> getAllSuppliers()throws DBSetupException, SQLException
    {
        List<SupplierInfo> supplierList = new ArrayList<>();
        try (EasyStatement stmt = new EasyStatement(connection, QueryManager.GET_ALL_SUPPLIERS))
        {
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                SupplierInfo supplierInfo = new SupplierInfo();
                supplierInfo.setRemarks(rs.getString(QueryField.REMARKS));
                ProfileInfo userInfo = new ProfileInfo();
                userInfo.setId(rs.getInt(QueryField.USER_ID));
                userInfo.setFirstName(rs.getString(QueryField.FIRST_NAME));
                userInfo.setLastName(rs.getString(QueryField.LAST_NAME));
                userInfo.setEmail(rs.getString(QueryField.EMAIL));
                userInfo.setPhone(rs.getString(QueryField.PHONE));
                userInfo.setFax(rs.getString(QueryField.FAX));
                userInfo.setWebsite(rs.getString(QueryField.WEBSITE));
                supplierInfo.setProfileInfo(userInfo);
                supplierList.add(supplierInfo);
            }
        }
        return supplierList;
    }
}
