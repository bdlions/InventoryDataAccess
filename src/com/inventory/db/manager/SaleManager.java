/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inventory.db.manager;

import com.inventory.bean.PurchaseInfo;
import com.inventory.bean.SaleInfo;
import com.inventory.db.Database;
import com.inventory.db.query.helper.EasyStatement;
import com.inventory.db.repositories.Purchase;
import com.inventory.db.repositories.Sale;
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
public class SaleManager {
    private Sale sale;
    private final Logger logger = LoggerFactory.getLogger(EasyStatement.class);
    public ResultEvent addSaleOrder(SaleInfo saleInfo)
    {
        ResultEvent resultEvent = new ResultEvent();
        Connection connection = null;
        try {
            connection = Database.getInstance().getConnection();
            connection.setAutoCommit(false);
            
            sale = new Sale(connection);
            sale.addSaleOrder(saleInfo);
            resultEvent.setResponseCode(2000);
            resultEvent.setMessage("Sale is executed successfully."); 
                
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
    
    public List<SaleInfo> getAllSaleOrders()
    {
        List<SaleInfo> saleList = new ArrayList<>();
        Connection connection = null;
        try {
            connection = Database.getInstance().getConnection();
            
            sale = new Sale(connection);
            saleList = sale.getAllSaleOrders();
            
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
        return saleList;
    }
    
    public List<SaleInfo> searchAllSaleOrders(String orderNo)
    {
        List<SaleInfo> saleList = new ArrayList<>();
        Connection connection = null;
        try {
            connection = Database.getInstance().getConnection();
            
            sale = new Sale(connection);
            saleList = sale.getAllSaleOrdersByOrderNo(orderNo);
            
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
        return saleList;
    }
}
