package com.inventory.db.repositories;

import com.inventory.bean.AddressCategoryInfo;
import com.inventory.bean.AddressInfo;
import com.inventory.bean.AddressTypeInfo;
import com.inventory.bean.ProfileInfo;
import com.inventory.db.query.helper.QueryField;
import com.inventory.db.query.helper.QueryManager;
import com.inventory.db.query.helper.EasyStatement;
import com.inventory.exceptions.DBSetupException;
import com.inventory.util.Utils;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    
    /**
     * This method will create a new user profile
     * @param profileInfo user profile info
     * @throws DBSetupException
     * @throws SQLException
     * @return int new user profile id
     * @author nazmul hasan on 28th december 2016
     */
    public int createProfile(ProfileInfo profileInfo) throws DBSetupException, SQLException
    {        
        //right now random int is used. later get last inserted id
        Utils utils = new Utils();
        int profileId = utils.generateProfileId();
        profileInfo.setId(profileId);        
        try (EasyStatement stmt = new EasyStatement(connection, QueryManager.CREATE_PROFILE)) {
            stmt.setInt(QueryField.ID, profileId);
            stmt.setString(QueryField.FIRST_NAME, profileInfo.getFirstName());
            stmt.setString(QueryField.LAST_NAME, profileInfo.getLastName());
            stmt.setString(QueryField.EMAIL, profileInfo.getEmail());
            stmt.setString(QueryField.PHONE, profileInfo.getPhone());
            stmt.setString(QueryField.FAX, profileInfo.getFax());
            stmt.setString(QueryField.WEBSITE, profileInfo.getWebsite());
            stmt.setDouble(QueryField.CREATED_ON, utils.getCurrentUnixTime());
            stmt.setDouble(QueryField.MODIFIED_ON, utils.getCurrentUnixTime());
            stmt.executeUpdate();
        }
        this.addProfileToGroup(profileInfo);
        return profileId;
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
     * This method will update profile info
     * @param profileInfo profile info
     * @throws DBSetupException
     * @throws SQLException
     * @return boolean
     * @author nazmul hasan on 28th december 2016
     */
    public boolean updateProfile(ProfileInfo profileInfo) throws DBSetupException, SQLException
    {
        Utils utils = new Utils();
        try (EasyStatement stmt = new EasyStatement(connection, QueryManager.UPDATE_PROFILE)) {
            stmt.setString(QueryField.FIRST_NAME, profileInfo.getFirstName());
            stmt.setString(QueryField.LAST_NAME, profileInfo.getLastName());
            stmt.setString(QueryField.EMAIL, profileInfo.getEmail());
            stmt.setString(QueryField.PHONE, profileInfo.getPhone());
            stmt.setString(QueryField.FAX, profileInfo.getFax());
            stmt.setString(QueryField.WEBSITE, profileInfo.getWebsite());
            stmt.setLong(QueryField.MODIFIED_ON, utils.getCurrentUnixTime());
            stmt.setInt(QueryField.ID, profileInfo.getId());
            stmt.executeUpdate();
        }
        return true;
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
