/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inventory.db.repositories;

import com.inventory.bean.ProductInfo;
import com.inventory.bean.PurchaseInfo;
import com.inventory.bean.SupplierInfo;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author nazmul hasan
 */
public class Purchase {
    private final Logger logger = LoggerFactory.getLogger(Purchase.class);
    private Utils utils = new Utils();
    private Connection connection;
    /***
     * Restrict to call without connection
     */
    private Purchase(){}
    public Purchase(Connection connection) {
        this.connection = connection;
    }
    public void addPurchaseOrder(PurchaseInfo purchaseInfo) throws DBSetupException, SQLException
    {
        try (EasyStatement stmt = new EasyStatement(connection, QueryManager.ADD_PURCHASE_ORDER)) {
            try{
                stmt.setString(QueryField.ORDER_NO, purchaseInfo.getOrderNo());
                stmt.setInt(QueryField.SUPPLIER_USER_ID, purchaseInfo.getSupplierUserId());
                stmt.setInt(QueryField.STATUS_ID, purchaseInfo.getStatusId());
                stmt.setLong(QueryField.ORDER_DATE, utils.getCurrentUnixTime());
                stmt.setLong(QueryField.REQUESTED_SHIP_DATE, purchaseInfo.getRequestShippedDate());
                stmt.setDouble(QueryField.DISCOUNT, purchaseInfo.getDiscount());
                stmt.setString(QueryField.REMARKS, purchaseInfo.getRemarks());
                stmt.executeUpdate();
            }
            catch(Exception ex){
                logger.debug(ex.getMessage());
            }
        }
        this.addWarehousePurchasedProductList(purchaseInfo);
        this.addWarehouseStock(purchaseInfo);
        this.addShowroomPurchasedProductList(purchaseInfo);
        this.addShowroomStock(purchaseInfo);
    }
    
    public void addWarehousePurchasedProductList(PurchaseInfo purchaseInfo) throws DBSetupException, SQLException
    {
        //right now we are using loop. later use insert batch
        List<ProductInfo> productList = purchaseInfo.getProductList();
        for(ProductInfo productInfo:productList)
        {
            try (EasyStatement stmt = new EasyStatement(connection, QueryManager.ADD_WAREHOUSE_PURCHASED_PRODUCT_LIST)) {
                stmt.setInt(QueryField.PRODUCT_ID, productInfo.getId());
                stmt.setString(QueryField.ORDER_NO, purchaseInfo.getOrderNo());
                stmt.setDouble(QueryField.UNIT_PRICE, productInfo.getUnitPrice());
                stmt.setDouble(QueryField.DISCOUNT, productInfo.getDiscount());            
                stmt.executeUpdate();
            }
        }
    }
    
    public void addWarehouseStock(PurchaseInfo purchaseInfo) throws DBSetupException, SQLException
    {
        //right now we are using loop. later use insert batch
        List<ProductInfo> productList = purchaseInfo.getProductList();
        for(ProductInfo productInfo:productList)
        {
            try (EasyStatement stmt = new EasyStatement(connection, QueryManager.ADD_WAREHOUSE_STOCK)) {
                stmt.setString(QueryField.ORDER_NO, purchaseInfo.getOrderNo());
                stmt.setInt(QueryField.PRODUCT_ID, productInfo.getId());
                stmt.setDouble(QueryField.STOCK_IN, productInfo.getQuantity());
                stmt.setDouble(QueryField.STOCK_OUT, 0);
                //right now transaction category id constant. later update it from config file
                stmt.setInt(QueryField.TRANSACTION_CATEGORY_ID, 1);           
                stmt.executeUpdate();
            }
        }
    }
    
    public void addShowroomPurchasedProductList(PurchaseInfo purchaseInfo) throws DBSetupException, SQLException
    {
        //right now we are using loop. later use insert batch
        List<ProductInfo> productList = purchaseInfo.getProductList();
        for(ProductInfo productInfo:productList)
        {
            try (EasyStatement stmt = new EasyStatement(connection, QueryManager.ADD_SHOWROOM_PURCHASED_PRODUCT_LIST)) {
                stmt.setInt(QueryField.PRODUCT_ID, productInfo.getId());
                stmt.setString(QueryField.ORDER_NO, purchaseInfo.getOrderNo());
                stmt.setDouble(QueryField.UNIT_PRICE, productInfo.getUnitPrice());
                stmt.setDouble(QueryField.DISCOUNT, productInfo.getDiscount());            
                stmt.executeUpdate();
            }
        }
    }
    
    public void addShowroomStock(PurchaseInfo purchaseInfo) throws DBSetupException, SQLException
    {
        //right now we are using loop. later use insert batch
        List<ProductInfo> productList = purchaseInfo.getProductList();
        for(ProductInfo productInfo:productList)
        {
            try (EasyStatement stmt = new EasyStatement(connection, QueryManager.ADD_SHOWROOM_STOCK)) {
                stmt.setString(QueryField.PURCHASE_ORDER_NO, purchaseInfo.getOrderNo());
                stmt.setString(QueryField.SALE_ORDER_NO, null);
                stmt.setInt(QueryField.PRODUCT_ID, productInfo.getId());
                stmt.setDouble(QueryField.STOCK_IN, productInfo.getQuantity());
                stmt.setDouble(QueryField.STOCK_OUT, 0);
                //right now transaction category id constant. later update it from config file
                stmt.setInt(QueryField.TRANSACTION_CATEGORY_ID, 1);           
                stmt.executeUpdate();
            }
        }
    }
    
    public List<PurchaseInfo> getAllPurchaseOrders() throws DBSetupException, SQLException
    {
        List<PurchaseInfo> purchaseList = new ArrayList<>();
        try (EasyStatement stmt = new EasyStatement(connection, QueryManager.GET_ALL_PURCHASE_ORDERS)){
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                PurchaseInfo purchaseInfo = new PurchaseInfo();
                purchaseInfo.setOrderNo(rs.getString(QueryField.ORDER_NO));
                purchaseInfo.setOrderDate(utils.convertUnixToHuman(rs.getLong(QueryField.ORDER_DATE)));
                purchaseInfo.setRemarks(rs.getString(QueryField.REMARKS));
                SupplierInfo supplierInfo = new SupplierInfo();
                supplierInfo.getProfileInfo().setFirstName(rs.getString(QueryField.FIRST_NAME));
                supplierInfo.getProfileInfo().setLastName(rs.getString(QueryField.LAST_NAME));
                purchaseInfo.setSupplierInfo(supplierInfo);
                purchaseList.add(purchaseInfo);
            }
        }
        return purchaseList;
    }
}
