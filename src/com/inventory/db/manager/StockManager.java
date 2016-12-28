/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inventory.db.manager;

import com.inventory.bean.ProductInfo;
import com.inventory.db.Database;
import com.inventory.db.repositories.Stock;
import com.inventory.exceptions.DBSetupException;
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
public class StockManager {
    private Stock stock;
    private final Logger logger = LoggerFactory.getLogger(StockManager.class);
    
    
    /**
     * This method will return current stock of products at showroom
     * @return list, product list
     */
    public List<ProductInfo> getCurrentStocks()
    {
        List<ProductInfo> productList = new ArrayList<>(); 
        Connection connection = null;
        try {
            connection = Database.getInstance().getConnection();
            
            stock = new Stock(connection);
            productList = stock.getCurrentStocks();

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
        return productList;
    }
}
