package com.inventory.db.repositories;

import com.inventory.bean.ProductCategoryInfo;
import com.inventory.bean.ProductInfo;
import com.inventory.bean.ProductTypeInfo;
import com.inventory.bean.UOMInfo;
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
import java.util.List;

/**
 *
 * @author nazmul hasan
 */
public class Product extends General{
    private Connection connection;
    /***
     * Restrict to call without connection
     */
    private Product(){}
    public Product(Connection connection) {
        this.connection = connection;
    }
    
    /**
     * This method will create a new product
     * @param productInfo product info
     * @throws DBSetupException
     * @throws SQLException
     * @return int last inserted id which is product id
     * @author nazmul hasan on 28th december 2016
     */
    public int createProduct(ProductInfo productInfo)throws DBSetupException, SQLException
    {
//        String sql = "insert into products(name, code, unit_price) values('qsname', 'qscode', 500)";
//        try (QueryStatement queryStmt = new QueryStatement(connection, sql, Statement.RETURN_GENERATED_KEYS)) 
//        {
//            queryStmt.executeUpdate();
//            ResultSet rs = queryStmt.getGeneratedKeys();
//            rs.next();
//            int lastInsertedId = rs.getInt(1);
//            System.out.println(lastInsertedId);
//        }
        
        
        //check product identity first
        int lastInsertedId = 0;
        Utils utils = new Utils();
        try (EasyStatement stmt = new EasyStatement(connection, QueryManager.CREATE_PRODUCT, Statement.RETURN_GENERATED_KEYS)) {
            //add some validation. If the values are not set then assign nulll instead of zero
            stmt.setInt(QueryField.CATEGORY_ID, productInfo.getProductCategoryInfo().getId());
            stmt.setInt(QueryField.TYPE_ID, productInfo.getProductTypeInfo().getId());
            stmt.setInt(QueryField.STANDARD_UOM_ID, productInfo.getStandardUOM().getId());
            stmt.setInt(QueryField.SALE_UOM_ID, productInfo.getSaleUOM().getId());
            stmt.setInt(QueryField.PURCHASE_UOM_ID, productInfo.getPurchasingUOM().getId());
            
            stmt.setString(QueryField.NAME, productInfo.getName());
            stmt.setString(QueryField.CODE, productInfo.getCode());
            stmt.setString(QueryField.LENGTH, productInfo.getLength());
            stmt.setString(QueryField.WIDTH, productInfo.getWidth());
            stmt.setString(QueryField.HEIGHT, productInfo.getHeight());
            stmt.setString(QueryField.WEIGHT, productInfo.getWeight());
            stmt.setDouble(QueryField.UNIT_PRICE, productInfo.getUnitPrice());
            stmt.setDouble(QueryField.CREATED_ON, utils.getCurrentUnixTime());
            stmt.setDouble(QueryField.MODIFIED_ON, utils.getCurrentUnixTime());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            lastInsertedId = rs.getInt(1);
        }
        return lastInsertedId;
    }
    
    /**
     * This method will update product info
     * @param productInfo , product info
     * @author nazmul hasan on 27th december 2016
     * @throws DBSetupException
     * @throws SQLException
     * @return boolean, true if update is successful else false
     */
    public boolean updateProduct(ProductInfo productInfo)throws DBSetupException, SQLException
    {
        //check product identity
        Utils utils = new Utils();
        try (EasyStatement stmt = new EasyStatement(connection, QueryManager.UPDATE_PRODUCT)) {
            stmt.setInt(QueryField.CATEGORY_ID, productInfo.getProductCategoryInfo().getId());
            stmt.setInt(QueryField.TYPE_ID, productInfo.getProductTypeInfo().getId());
            stmt.setInt(QueryField.STANDARD_UOM_ID, productInfo.getStandardUOM().getId());
            stmt.setInt(QueryField.SALE_UOM_ID, productInfo.getSaleUOM().getId());
            stmt.setInt(QueryField.PURCHASE_UOM_ID, productInfo.getPurchasingUOM().getId());
            
            stmt.setString(QueryField.NAME, productInfo.getName());
            stmt.setString(QueryField.CODE, productInfo.getCode());
            stmt.setString(QueryField.LENGTH, productInfo.getLength());
            stmt.setString(QueryField.WIDTH, productInfo.getWidth());
            stmt.setString(QueryField.HEIGHT, productInfo.getHeight());
            stmt.setString(QueryField.WEIGHT, productInfo.getWeight());
            stmt.setDouble(QueryField.UNIT_PRICE, productInfo.getUnitPrice());
            stmt.setDouble(QueryField.MODIFIED_ON, utils.getCurrentUnixTime());
            stmt.setInt(QueryField.ID, productInfo.getId());
            stmt.executeUpdate();
        }
        return true;
    }
    
    /**
     * This method will return product list     
     * @throws DBSetupException
     * @throws SQLException
     * @return List product list
     * @author nazmul hasan on 27th december 2016
     */
    public List<ProductInfo> getAllProducts()throws DBSetupException, SQLException
    {
        List<ProductInfo> productList = new ArrayList<>();
        try (EasyStatement stmt = new EasyStatement(connection, QueryManager.GET_ALL_PRODUCTS)){
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                ProductInfo productInfo = new ProductInfo();
                productInfo.setId(rs.getInt(QueryField.ID));
                productInfo.getProductCategoryInfo().setTitle(rs.getString(QueryField.PRODUCT_CATEGORY));
                productInfo.getProductTypeInfo().setTitle(rs.getString(QueryField.PRODUCT_TYPE));
                productInfo.setName(rs.getString(QueryField.NAME));
                productInfo.setCode(rs.getString(QueryField.CODE));
                productInfo.setLength(rs.getString(QueryField.LENGTH));
                productInfo.setWidth(rs.getString(QueryField.WIDTH));
                productInfo.setHeight(rs.getString(QueryField.HEIGHT));
                productInfo.setWeight(rs.getString(QueryField.WEIGHT));
                productInfo.setUnitPrice(rs.getDouble(QueryField.UNIT_PRICE));
                productList.add(productInfo);
            }
        }
        return productList;
    }
    
    public List<ProductCategoryInfo> getAllProductCategories()throws DBSetupException, SQLException
    {
        List<ProductCategoryInfo> productCategoryList = new ArrayList<>();
        try (EasyStatement stmt = new EasyStatement(connection, QueryManager.GET_ALL_PRODUCT_CATEGORIES)){
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                ProductCategoryInfo productCategoryInfo = new ProductCategoryInfo();
                productCategoryInfo.setId(rs.getInt(QueryField.ID));
                productCategoryInfo.setTitle(rs.getString(QueryField.TITLE));
                productCategoryList.add(productCategoryInfo);
            }
        }
        return productCategoryList;
    }
    
    public List<ProductTypeInfo> getAllProductTypes()throws DBSetupException, SQLException
    {
        List<ProductTypeInfo> productTypeList = new ArrayList<>();
        try (EasyStatement stmt = new EasyStatement(connection, QueryManager.GET_ALL_PRODUCT_TYPES)){
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                ProductTypeInfo productTypeInfo = new ProductTypeInfo();
                productTypeInfo.setId(rs.getInt(QueryField.ID));
                productTypeInfo.setTitle(rs.getString(QueryField.TITLE));
                productTypeList.add(productTypeInfo);
            }
        }
        return productTypeList;
    }
    
    public List<UOMInfo> getAllUOMs()throws DBSetupException, SQLException
    {
        List<UOMInfo> uomList = new ArrayList<>();
        try (EasyStatement stmt = new EasyStatement(connection, QueryManager.GET_ALL_UOMS)){
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                UOMInfo uomInfo = new UOMInfo();
                uomInfo.setId(rs.getInt(QueryField.ID));
                uomInfo.setTitle(rs.getString(QueryField.TITLE));
                uomList.add(uomInfo);
            }
        }
        return uomList;
    }
    
    public List<ProductInfo> searchProduct(String name)throws DBSetupException, SQLException
    {
        List<ProductInfo> productList = new ArrayList<>();
        try (EasyStatement stmt = new EasyStatement(connection, QueryManager.SEARCH_PRODUCT_BY_NAME)){
            stmt.setString(QueryField.NAME, name);
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                ProductInfo productInfo = new ProductInfo();
                productInfo.setId(rs.getInt(QueryField.ID));
                productInfo.getProductCategoryInfo().setTitle(rs.getString(QueryField.PRODUCT_CATEGORY));
                productInfo.getProductTypeInfo().setTitle(rs.getString(QueryField.PRODUCT_TYPE));
                productInfo.setName(rs.getString(QueryField.NAME));
                productInfo.setCode(rs.getString(QueryField.CODE));
                productInfo.setLength(rs.getString(QueryField.LENGTH));
                productInfo.setWidth(rs.getString(QueryField.WIDTH));
                productInfo.setHeight(rs.getString(QueryField.HEIGHT));
                productInfo.setWeight(rs.getString(QueryField.WEIGHT));
                productInfo.setUnitPrice(rs.getDouble(QueryField.UNIT_PRICE));
                productList.add(productInfo);
            }
        }
        return productList;
    }
}
