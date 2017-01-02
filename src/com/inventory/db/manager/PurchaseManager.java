/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inventory.db.manager;
import com.inventory.bean.PurchaseInfo;
import com.inventory.db.Database;
import com.inventory.db.query.helper.EasyStatement;
import com.inventory.db.repositories.Product;
import com.inventory.db.repositories.Purchase;
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
public class PurchaseManager {
    private Purchase purchase;
    private final Logger logger = LoggerFactory.getLogger(EasyStatement.class);
    
    public ResultEvent addPurchaseOrder(PurchaseInfo purchaseInfo)
    {
        ResultEvent resultEvent = new ResultEvent();
        Connection connection = null;
        try {
            connection = Database.getInstance().getConnection();
            connection.setAutoCommit(false);
            
            purchase = new Purchase(connection);
            purchase.addPurchaseOrder(purchaseInfo);
            
            resultEvent.setResponseCode(2000);
            resultEvent.setMessage("Purchase is executed successfully."); 
            
            connection.commit();
            connection.close();
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
    
    public List<PurchaseInfo> getAllPurchaseOrders()
    {
        List<PurchaseInfo> purchaseList = new ArrayList<>();
        Connection connection = null;
        try {
            connection = Database.getInstance().getConnection();
            
            purchase = new Purchase(connection);
            purchaseList = purchase.getAllPurchaseOrders();
            
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
        return purchaseList;
    }
    
    public ResultEvent getPurchaseOrderInfo(String orderNo)
    {
        ResultEvent resultEvent = new ResultEvent();
        
        PurchaseInfo purchaseInfo = new PurchaseInfo();
        Connection connection = null;
        try {
            connection = Database.getInstance().getConnection();
            
            purchase = new Purchase(connection);
            purchaseInfo = purchase.getPurchaseOrderInfo(orderNo);   
            if(purchaseInfo.getOrderNo() != null)
            {
                resultEvent.setResponseCode(2000);
                resultEvent.setResult(purchaseInfo);
            }
            else
            {
                resultEvent.setMessage("Invalid purchase order."); 
            }
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
        return resultEvent;
    }
    
    public List<PurchaseInfo> searchPurchaseOrders(String orderNo)
    {
        List<PurchaseInfo> purchaseList = new ArrayList<>();
        Connection connection = null;
        try {
            connection = Database.getInstance().getConnection();
            
            purchase = new Purchase(connection);
            purchaseList = purchase.getAllPurchaseOrdersByOrderNo(orderNo);
            
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
        return purchaseList;
    }
}
