package com.inventory.db.manager;

import com.inventory.bean.SupplierInfo;
import com.inventory.db.Database;
import com.inventory.db.repositories.Profile;
import com.inventory.db.repositories.Supplier;
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
public class SupplierManager {
    private Profile profile;
    private Supplier supplier;
    private final Logger logger = LoggerFactory.getLogger(SupplierManager.class);
    
    /**
     * This method will create a new supplier
     * @param supplierInfo supplier info
     * @return ResultEvent
     * @author nazmul hasan on 28th december 2016
     */
    public ResultEvent createSupplier(SupplierInfo supplierInfo)
    {
        ResultEvent resultEvent = new ResultEvent();
        Connection connection = null;
        try {
            connection = Database.getInstance().getConnection();
            connection.setAutoCommit(false);
            
            supplierInfo.getProfileInfo().setGroupId(Constants.GROUP_ID_SUPPLIER);
            profile = new Profile(connection);
            int profileId = profile.createProfile(supplierInfo.getProfileInfo()); 
            if(profileId > 0)
            {
                supplierInfo.getProfileInfo().setId(profileId);
                supplier = new Supplier(connection);
                if(supplier.createSupplier(supplierInfo))
                {
                    resultEvent.setResponseCode(2000);
                    resultEvent.setMessage("Supplier is created successfully.");
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
     * This method will return all suppliers
     * @return list, supplier list
     * @author nazmul hasan on 28th december 2016
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
        return supplierList;
    }
    
    /**
     * This method will update supplier info
     * @param supplierInfo supplier info
     * @return ResultEvent
     * @author nazmul hasan on 28th december 2016
     */
    public ResultEvent updateSupplier(SupplierInfo supplierInfo)
    {
        ResultEvent resultEvent = new ResultEvent();
        Connection connection = null;
        try {
            connection = Database.getInstance().getConnection();
            connection.setAutoCommit(false);
            
            profile = new Profile(connection);
            if(profile.updateProfile(supplierInfo.getProfileInfo()))
            {
                supplier = new Supplier(connection);
                if(supplier.updateSupplierInfo(supplierInfo))
                {
                    resultEvent.setResponseCode(2000);
                    resultEvent.setMessage("Supplier info is updated successfully.");  
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
