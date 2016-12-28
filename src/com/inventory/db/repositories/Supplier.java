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
    
    /**
     * This method will create a new supplier
     * @param supplierInfo supplier info
     * @throws DBSetupException
     * @throws SQLException
     * @return boolean
     * @author nazmul hasan on 28th december 2016
     */
    public boolean createSupplier(SupplierInfo supplierInfo) throws DBSetupException, SQLException
    {
        try (EasyStatement stmt = new EasyStatement(connection, QueryManager.CREATE_SUPPLIER)) {
            stmt.setInt(QueryField.USER_ID, supplierInfo.getProfileInfo().getId());
            stmt.setString(QueryField.REMARKS, supplierInfo.getRemarks());
            stmt.executeUpdate();
        }
        return true;
    }
    
    /**
     * This method will update supplier info
     * @param supplierInfo supplier info
     * @throws DBSetupException
     * @throws SQLException
     * @return boolean
     * @author nazmul hasan on 28th december 2016
     */
    public boolean updateSupplierInfo(SupplierInfo supplierInfo) throws DBSetupException, SQLException
    {
        try (EasyStatement stmt = new EasyStatement(connection, QueryManager.UPDATE_SUPPLIER_INFO)) {
            stmt.setString(QueryField.REMARKS, supplierInfo.getRemarks());
            stmt.setInt(QueryField.USER_ID, supplierInfo.getProfileInfo().getId());
            stmt.executeUpdate();
        }
        return true;
    }
    
    /**
     * This method will return all suppliers
     * @throws DBSetupException
     * @throws SQLException
     * @return List supplier list
     * @author nazmul hasan on 28th december 2016
     */
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
                ProfileInfo profileInfo = new ProfileInfo();
                profileInfo.setId(rs.getInt(QueryField.USER_ID));
                profileInfo.setFirstName(rs.getString(QueryField.FIRST_NAME));
                profileInfo.setLastName(rs.getString(QueryField.LAST_NAME));
                profileInfo.setEmail(rs.getString(QueryField.EMAIL));
                profileInfo.setPhone(rs.getString(QueryField.PHONE));
                profileInfo.setFax(rs.getString(QueryField.FAX));
                profileInfo.setWebsite(rs.getString(QueryField.WEBSITE));
                supplierInfo.setProfileInfo(profileInfo);
                supplierList.add(supplierInfo);
            }
        }
        return supplierList;
    }
}
