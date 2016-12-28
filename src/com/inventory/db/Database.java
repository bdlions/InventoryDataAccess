/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inventory.db;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import com.inventory.exceptions.DBSetupException;
import com.inventory.exceptions.DBUserNameException;
import com.inventory.exceptions.DatabaseNameException;
import com.inventory.util.DbPropertyProvider;
import com.inventory.util.DbSetupQueryProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author alamgir
 */
public class Database {
    private static Database _database;
    private String _connectionURL;
    private String _dbName = "inventory_db";
    private String _driver = "com.mysql.jdbc.Driver";
    private String _userName = "root";
    private String _password = "";
    private String _host = "localhost";
    private String _port = "3306";
    static Logger _logger = LoggerFactory.getLogger(Database.class.getName());
    
    /***
     * 
     * @throws DBSetupException 
     */
    private Database() throws DBSetupException{
        
        _host = DbPropertyProvider.get("host");
        _dbName = DbPropertyProvider.get("dbName");
        _port = DbPropertyProvider.get("port");
        _userName = DbPropertyProvider.get("username");
        _password = DbPropertyProvider.get("password");
        
        
        _connectionURL = "jdbc:mysql://"+_host+":"+_port+"/";
        
        try{
            if(_userName == null || _userName.isEmpty()){
                throw new DBUserNameException();
            }
            if(_dbName == null || _dbName.isEmpty()){
                throw  new DatabaseNameException();
            }
            
            Class.forName(_driver); 
            try{
                if(!isDatabaseExists()){
                    setUpDatabase();
                }
            }
            catch(SQLException ex){
                throw new DBSetupException("Incomplete db setup error: " + ex.getMessage());
            }
            _connectionURL += _dbName;
        }
        catch(ClassNotFoundException | DBUserNameException | DatabaseNameException ex){
            _logger.info(ex.getMessage());
        }
    }
    
    /***
     * 
     * @return true/false
     * @throws SQLException 
     */
    private boolean isDatabaseExists() throws SQLException{
        boolean found;
        try (Connection connection = (Connection)DriverManager.getConnection(_connectionURL, _userName, _password)) {
            found = false;
            try (ResultSet resultSet = connection.getMetaData().getCatalogs()) {
                while (resultSet.next() && !resultSet.isLast()) {
                    if(resultSet.getString("TABLE_CAT").equalsIgnoreCase(_dbName)){
                        found = true;
                        break;
                    }
                }
            }
        }
        
        return found;
    }
    
    /***
     * initial setup database if not exist
     * @throws SQLException 
     */
    private void setUpDatabase() throws SQLException{
        Connection connection = getConnection();
        try (Statement statement = (Statement) connection.createStatement()) {
            //create database
            statement.executeUpdate(MessageFormat.format(DbSetupQueryProvider.get("db.create"), _dbName));
            statement.executeUpdate(MessageFormat.format(DbSetupQueryProvider.get("db.use"), _dbName));
            
            //create tables
            statement.executeUpdate(DbSetupQueryProvider.get("db.table.product_categories"));
            statement.executeUpdate(DbSetupQueryProvider.get("db.table.product_types"));
            statement.executeUpdate(DbSetupQueryProvider.get("db.table.uoms"));
            statement.executeUpdate(DbSetupQueryProvider.get("db.table.products"));
            statement.executeUpdate(DbSetupQueryProvider.get("db.table.groups"));
            statement.executeUpdate(DbSetupQueryProvider.get("db.table.users"));
            statement.executeUpdate(DbSetupQueryProvider.get("db.table.users_groups"));
            statement.executeUpdate(DbSetupQueryProvider.get("db.table.address_types"));
            statement.executeUpdate(DbSetupQueryProvider.get("db.table.address_categories"));
            statement.executeUpdate(DbSetupQueryProvider.get("db.table.users_addressess"));
            statement.executeUpdate(DbSetupQueryProvider.get("db.table.suppliers"));
            statement.executeUpdate(DbSetupQueryProvider.get("db.table.customers"));
            statement.executeUpdate(DbSetupQueryProvider.get("db.table.purchase_order_statuses"));
            statement.executeUpdate(DbSetupQueryProvider.get("db.table.purchase_orders"));
            statement.executeUpdate(DbSetupQueryProvider.get("db.table.po_warehouse_products"));
            statement.executeUpdate(DbSetupQueryProvider.get("db.table.ws_transaction_categories"));
            statement.executeUpdate(DbSetupQueryProvider.get("db.table.warehouse_stocks"));
            statement.executeUpdate(DbSetupQueryProvider.get("db.table.po_showroom_products"));
            statement.executeUpdate(DbSetupQueryProvider.get("db.table.sale_order_statuses"));
            statement.executeUpdate(DbSetupQueryProvider.get("db.table.sale_orders"));
            statement.executeUpdate(DbSetupQueryProvider.get("db.table.sale_order_products"));
            statement.executeUpdate(DbSetupQueryProvider.get("db.table.ss_transaction_categories"));
            statement.executeUpdate(DbSetupQueryProvider.get("db.table.showroom_stocks"));
            
            //create relations
            statement.executeUpdate(DbSetupQueryProvider.get("db.rel.products"));
            statement.executeUpdate(DbSetupQueryProvider.get("db.rel.users_groups"));
            statement.executeUpdate(DbSetupQueryProvider.get("db.rel.users_addressess"));
            statement.executeUpdate(DbSetupQueryProvider.get("db.rel.suppliers"));
            statement.executeUpdate(DbSetupQueryProvider.get("db.rel.customers"));
            statement.executeUpdate(DbSetupQueryProvider.get("db.rel.purchase_orders"));
            statement.executeUpdate(DbSetupQueryProvider.get("db.rel.po_warehouse_products"));
            statement.executeUpdate(DbSetupQueryProvider.get("db.rel.warehouse_stocks"));
            statement.executeUpdate(DbSetupQueryProvider.get("db.rel.po_showroom_products"));
            statement.executeUpdate(DbSetupQueryProvider.get("db.rel.sale_orders"));
            statement.executeUpdate(DbSetupQueryProvider.get("db.rel.sale_order_products"));
            statement.executeUpdate(DbSetupQueryProvider.get("db.rel.showroom_stocks"));
            
            //insert data
            statement.executeUpdate(DbSetupQueryProvider.get("db.defaultData.product_categories"));
            statement.executeUpdate(DbSetupQueryProvider.get("db.defaultData.product_types"));
            statement.executeUpdate(DbSetupQueryProvider.get("db.defaultData.uoms"));
            statement.executeUpdate(DbSetupQueryProvider.get("db.defaultData.groups"));
            statement.executeUpdate(DbSetupQueryProvider.get("db.defaultData.users"));
            statement.executeUpdate(DbSetupQueryProvider.get("db.defaultData.users_groups"));
            statement.executeUpdate(DbSetupQueryProvider.get("db.defaultData.address_types"));
            statement.executeUpdate(DbSetupQueryProvider.get("db.defaultData.address_categories"));
            statement.executeUpdate(DbSetupQueryProvider.get("db.defaultData.purchase_order_statuses"));
            statement.executeUpdate(DbSetupQueryProvider.get("db.defaultData.ws_transaction_categories"));
            statement.executeUpdate(DbSetupQueryProvider.get("db.defaultData.sale_order_statuses"));
            statement.executeUpdate(DbSetupQueryProvider.get("db.defaultData.ss_transaction_categories"));
        }
    }
    
    /***
     * get the connection of the database with required username, password, dbname 
     * @return connection
     * @throws SQLException 
     */
    public Connection getConnection() throws SQLException{
        return (Connection) DriverManager.getConnection(_connectionURL, _userName, _password);
    }
    
    /**
     * get the instance of the database
     * singleton instance
     * 
     * @return
     * @throws DBSetupException 
     */
    public static synchronized Database getInstance() throws DBSetupException{
        if(_database == null){
            _database = new Database();
        }
        return _database;
    }
    
    
}
