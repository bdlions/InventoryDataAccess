/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inventory.exceptions;

/**
 *
 * @author alamgir
 */
public class QueryExecException extends Exception{
    public QueryExecException(String query) {
        super("Query Executing Error: " + query);
    }
}
