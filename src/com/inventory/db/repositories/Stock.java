/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inventory.db.repositories;

import com.inventory.bean.ProductInfo;
import com.inventory.bean.SaleInfo;
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
public class Stock {
    private Connection connection;
    /***
     * Restrict to call without connection
     */
    private Stock(){}
    public Stock(Connection connection) {
        this.connection = connection;
    }
    
    /**
     * This method will return current available stock of products at showroom
     * @throws DBSetupException
     * @throws SQLException
     * @return List, product list
    */
    public List<ProductInfo> getCurrentStocks() throws DBSetupException, SQLException
    {
        List<ProductInfo> productList = new ArrayList<>();
        try (EasyStatement stmt = new EasyStatement(connection, QueryManager.GET_CURRENT_STOCKS)){
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                ProductInfo productInfo = new ProductInfo();
                productInfo.setId(rs.getInt(QueryField.PRODUCT_ID));
                productInfo.setName(rs.getString(QueryField.NAME));
                productInfo.setCode(rs.getString(QueryField.CODE));
                productInfo.setQuantity(rs.getDouble(QueryField.CURRENT_STOCK));
                productList.add(productInfo);
            }
        }
        return productList;
    }
}
