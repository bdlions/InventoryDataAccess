package com.inventory.db.manager;

import com.inventory.bean.ProductCategoryInfo;
import com.inventory.bean.ProductInfo;
import com.inventory.bean.ProductTypeInfo;
import com.inventory.bean.UOMInfo;
import com.inventory.db.Database;
import com.inventory.db.repositories.Product;
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
public class ProductManager {
    private Product product;
    private final Logger logger = LoggerFactory.getLogger(ProductManager.class);
    
    
    /**
     * This method will create a product
     * @param productInfo, product info
     * @return ResultEvent
     * @author nazmul hasan
     */
    public ResultEvent createProduct(ProductInfo productInfo)
    {
        ResultEvent resultEvent = new ResultEvent();
        Connection connection = null;
        try {
            connection = Database.getInstance().getConnection();            
            product = new Product(connection);
            if(product.createProduct(productInfo))
            {
                resultEvent.setResponseCode(2000);
                resultEvent.setMessage("Product is created successfully."); 
            }
            else
            {
                //set error message here
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
            //set error message here
        } catch (DBSetupException ex) {
            logger.error(ex.getMessage());
            //set error message here
        }
        return resultEvent;
    }
    /**
     * This method will return all products
     * @return List, product list
     * @author nazmul hasan
     */
    public List<ProductInfo> getAllProducts()
    {
        List<ProductInfo> productList = new ArrayList<>(); 
        Connection connection = null;
        try {
            connection = Database.getInstance().getConnection();
            
            product = new Product(connection);
            productList = product.getAllProducts();

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
    
    /**
     * This method will return all product categories
     * @return List, product category list
     * @author nazmul hasan
     */
    public List<ProductCategoryInfo> getAllProductCategories()
    {
        List<ProductCategoryInfo> productCategoryList = new ArrayList<>();
        Connection connection = null;
        try {
            connection = Database.getInstance().getConnection();
            
            product = new Product(connection);
            productCategoryList = product.getAllProductCategories();

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
        return productCategoryList;
    }
    /**
     * This method will return all product types
     * @return List, product type list
     * @author nazmul hasan
     */
    public List<ProductTypeInfo> getAllProductTypes()
    {
        List<ProductTypeInfo> productTypeList = new ArrayList<>();
        Connection connection = null;
        try {
            connection = Database.getInstance().getConnection();
            
            product = new Product(connection);
            productTypeList = product.getAllProductTypes();

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
        return productTypeList;
    }
    /**
     * This method will return all unit of measurements
     * @return List, unit of measurement list
     * @author nazmul hasan
     */
    public List<UOMInfo> getAllUOMs()
    {
        List<UOMInfo> uomList = new ArrayList<>();
        Connection connection = null;
        try {
            connection = Database.getInstance().getConnection();
            
            product = new Product(connection);
            uomList = product.getAllUOMs();

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
        return uomList;
    }
    
    /**
     * this method will search product
     * @param name, product name
     * @return List, product list
     * @author nazmul hasan on 27th december 2016
     */
    public List<ProductInfo> searchProduct(String name)
    {
        List<ProductInfo> productList = new ArrayList<>(); 
        Connection connection = null;
        try {
            connection = Database.getInstance().getConnection();
            
            product = new Product(connection);
            productList = product.searchProduct(name);
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
    
    /**
    * this method will update product info
    * @param productInfo product info
    * @return ResultEvent
    * @author nazmul hasan on 24th november 2016
    */
    public ResultEvent updateProduct(ProductInfo productInfo)
    {
        ResultEvent resultEvent = new ResultEvent();
        Connection connection = null;
        try {
            connection = Database.getInstance().getConnection();
            
            product = new Product(connection);
            if(product.updateProduct(productInfo))
            {
                resultEvent.setResponseCode(2000);
                resultEvent.setMessage("Product is updated successfully.");
            }
            else
            {
                resultEvent.setResponseCode(5001);
                resultEvent.setMessage("Error while updating product info.");
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
}
