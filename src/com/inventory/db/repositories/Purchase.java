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
import com.inventory.db.query.helper.QueryStatement;
import com.inventory.exceptions.DBSetupException;
import com.inventory.util.Utils;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Hashtable;
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
                supplierInfo.getProfileInfo().setId(rs.getInt(QueryField.USER_ID));
                supplierInfo.getProfileInfo().setFirstName(rs.getString(QueryField.FIRST_NAME));
                supplierInfo.getProfileInfo().setLastName(rs.getString(QueryField.LAST_NAME));
                supplierInfo.getProfileInfo().setEmail(rs.getString(QueryField.EMAIL));
                supplierInfo.getProfileInfo().setPhone(rs.getString(QueryField.PHONE));
                supplierInfo.getProfileInfo().setFax(rs.getString(QueryField.FAX));
                supplierInfo.getProfileInfo().setWebsite(rs.getString(QueryField.WEBSITE));
                purchaseInfo.setSupplierInfo(supplierInfo);
                
                ProductInfo productInfo1 = new ProductInfo();
                productInfo1.setName("dummyname1");
                productInfo1.setCode("dummycode1");
                productInfo1.setUnitPrice(100);
                ProductInfo productInfo2 = new ProductInfo();
                productInfo2.setName("dummyname2");
                productInfo2.setCode("dummycode2");
                productInfo2.setUnitPrice(200);
                
                purchaseInfo.getProductList().add(productInfo1);
                purchaseInfo.getProductList().add(productInfo2);
                
                purchaseList.add(purchaseInfo);
            }
        }
        return purchaseList;
    }
    
    public PurchaseInfo getPurchaseOrderInfo(String orderNo) throws DBSetupException, SQLException
    {
        PurchaseInfo purchaseInfo = new PurchaseInfo();
        String sql = "select purchase_orders.*, users.*, suppliers.*, showroom_stocks.stock_in as quantity, products.name as product_name, products.code as product_code from purchase_orders join suppliers on purchase_orders.supplier_user_id = suppliers.user_id join users on suppliers.user_id = users.id join showroom_stocks on purchase_orders.order_no = showroom_stocks.purchase_order_no join po_showroom_products on showroom_stocks.purchase_order_no = po_showroom_products.order_no and showroom_stocks.product_id = po_showroom_products.product_id join products on po_showroom_products.product_id = products.id where showroom_stocks.transaction_category_id = 1 and purchase_orders.order_no = '"+orderNo+"'";
        try (QueryStatement queryStmt = new QueryStatement(connection, sql, Statement.NO_GENERATED_KEYS)) 
        {
            ResultSet rs = queryStmt.executeQuery();
            while(rs.next())
            {
                if(purchaseInfo.getOrderNo() == null)
                {
                    purchaseInfo.setOrderNo(rs.getString("order_no"));
                    purchaseInfo.setOrderDate(utils.convertUnixToHuman(rs.getLong(QueryField.ORDER_DATE)));
                    //purchaseInfo.setRemarks(rs.getString(QueryField.REMARKS));
                    SupplierInfo supplierInfo = new SupplierInfo();
                    supplierInfo.getProfileInfo().setId(rs.getInt(QueryField.USER_ID));
                    supplierInfo.getProfileInfo().setFirstName(rs.getString(QueryField.FIRST_NAME));
                    supplierInfo.getProfileInfo().setLastName(rs.getString(QueryField.LAST_NAME));
                    supplierInfo.getProfileInfo().setEmail(rs.getString(QueryField.EMAIL));
                    supplierInfo.getProfileInfo().setPhone(rs.getString(QueryField.PHONE));
                    supplierInfo.getProfileInfo().setFax(rs.getString(QueryField.FAX));
                    supplierInfo.getProfileInfo().setWebsite(rs.getString(QueryField.WEBSITE));
                    purchaseInfo.setSupplierInfo(supplierInfo);
                }                
                ProductInfo productInfo = new ProductInfo();
                productInfo.setName(rs.getString("product_name"));
                productInfo.setCode(rs.getString("product_code"));
                productInfo.setQuantity(rs.getInt("quantity"));
                productInfo.setUnitPrice(100);
                purchaseInfo.getProductList().add(productInfo);
            }
            
        }
        return purchaseInfo;
    }
    
    public List<PurchaseInfo> getAllPurchaseOrdersByOrderNo(String orderNo) throws DBSetupException, SQLException
    {
        List<PurchaseInfo> purchaseList = new ArrayList<>();
        try (EasyStatement stmt = new EasyStatement(connection, QueryManager.GET_ALL_PURCHASE_ORDERS_BY_ORDER_NO)){
            stmt.setString(QueryField.ORDER_NO, orderNo);
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                PurchaseInfo purchaseInfo = new PurchaseInfo();
                purchaseInfo.setOrderNo(rs.getString(QueryField.ORDER_NO));
                purchaseInfo.setOrderDate(utils.convertUnixToHuman(rs.getLong(QueryField.ORDER_DATE)));
                purchaseInfo.setRemarks(rs.getString(QueryField.REMARKS));
                SupplierInfo supplierInfo = new SupplierInfo();
                supplierInfo.getProfileInfo().setId(rs.getInt(QueryField.USER_ID));
                supplierInfo.getProfileInfo().setFirstName(rs.getString(QueryField.FIRST_NAME));
                supplierInfo.getProfileInfo().setLastName(rs.getString(QueryField.LAST_NAME));
                supplierInfo.getProfileInfo().setEmail(rs.getString(QueryField.EMAIL));
                supplierInfo.getProfileInfo().setPhone(rs.getString(QueryField.PHONE));
                supplierInfo.getProfileInfo().setFax(rs.getString(QueryField.FAX));
                supplierInfo.getProfileInfo().setWebsite(rs.getString(QueryField.WEBSITE));
                purchaseInfo.setSupplierInfo(supplierInfo);
                purchaseList.add(purchaseInfo);
            }
        }
        return purchaseList;
    }
}
