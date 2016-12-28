package com.inventory.db.repositories;

import com.inventory.bean.AddressCategoryInfo;
import com.inventory.bean.AddressInfo;
import com.inventory.bean.AddressTypeInfo;
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
import java.util.Random;

/**
 *
 * @author nazmul hasan
 */
public class Profile {
    private Connection connection;
    /***
     * Restrict to call without connection
     */
    private Profile(){}
    public Profile(Connection connection) {
        this.connection = connection;
    }
    
    public int createProfile(ProfileInfo userInfo) throws DBSetupException, SQLException
    {
        //right now random int is used. later get last inserted id
        Random random = new Random();
        int userId = random.nextInt(10000000) + 1;
        userInfo.setId(userId);
        
        try (EasyStatement stmt = new EasyStatement(connection, QueryManager.CREATE_USER)) {
            stmt.setInt(QueryField.ID, userId);
            stmt.setString(QueryField.FIRST_NAME, userInfo.getFirstName());
            stmt.setString(QueryField.LAST_NAME, userInfo.getLastName());
            stmt.setString(QueryField.EMAIL, userInfo.getEmail());
            stmt.setString(QueryField.PHONE, userInfo.getPhone());
            stmt.setString(QueryField.FAX, userInfo.getFax());
            stmt.setString(QueryField.WEBSITE, userInfo.getWebsite());
            stmt.executeUpdate();
        }
        this.addProfileToGroup(userInfo);
        return userId;
    }
    
    public void addProfileToGroup(ProfileInfo userInfo) throws DBSetupException, SQLException
    {
        try (EasyStatement stmt = new EasyStatement(connection, QueryManager.ADD_USER_TO_GROUP)) {
            stmt.setInt(QueryField.USER_ID, userInfo.getId());
            stmt.setInt(QueryField.GROUP_ID, userInfo.getGroupId());
            stmt.executeUpdate();
        }
        this.addProfileAddresses(userInfo);
    }
    
    public void addProfileAddresses(ProfileInfo userInfo) throws DBSetupException, SQLException
    {
        //right now we are using loop. later use insert batch
        List<AddressInfo> addresses = userInfo.getAddresses();
        for (AddressInfo address : addresses) {
            try (EasyStatement stmt = new EasyStatement(connection, QueryManager.ADD_USER_ADDRESS)) {
                stmt.setInt(QueryField.USER_ID, userInfo.getId());
                stmt.setInt(QueryField.ADDRESS_TYPE_ID, address.getAddressTypeInfo().getId());
                stmt.setInt(QueryField.ADDRESS_CATEGORY_ID, address.getAddressCategoryInfo().getId());
                stmt.setString(QueryField.ADDRESS, address.getAddress());
                stmt.setString(QueryField.CITY, address.getCity());
                stmt.setString(QueryField.STATE, address.getState());
                stmt.setString(QueryField.ZIP, address.getZip());
                stmt.executeUpdate();
            }
        }
    }
    
    /**
     * This method will return all address types
     * @return List, address type list
     * @throws DBSetupException, DBSetupException
     * @throws SQLException, SQLException
     */
    public List<AddressTypeInfo> getAllAddressTypes() throws DBSetupException, SQLException
    {
        List<AddressTypeInfo> addressTypeList = new ArrayList<>();
        try (EasyStatement stmt = new EasyStatement(connection, QueryManager.GET_ALL_ADDRESS_TYPES)){
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                AddressTypeInfo addressTypeInfo = new AddressTypeInfo();
                addressTypeInfo.setId(rs.getInt(QueryField.ID));
                addressTypeInfo.setTitle(rs.getString(QueryField.TITLE));
                addressTypeList.add(addressTypeInfo);
            }
        }
        return addressTypeList;
    }
    
    /**
     * This method will return all address categories
     * @return List, address category list
     * @throws DBSetupException, DBSetupException
     * @throws SQLException, SQLException
     */
    public List<AddressCategoryInfo> getAllAddressCategories() throws DBSetupException, SQLException
    {
        List<AddressCategoryInfo> addressCategoryList = new ArrayList<>();
        try (EasyStatement stmt = new EasyStatement(connection, QueryManager.GET_ALL_ADDRESS_CATEGORIES)){
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                AddressCategoryInfo addressCategoryInfo = new AddressCategoryInfo();
                addressCategoryInfo.setId(rs.getInt(QueryField.ID));
                addressCategoryInfo.setTitle(rs.getString(QueryField.TITLE));
                addressCategoryList.add(addressCategoryInfo);
            }
        }
        return addressCategoryList;
    }
}
