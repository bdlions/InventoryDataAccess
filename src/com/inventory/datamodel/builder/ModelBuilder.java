/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inventory.datamodel.builder;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author alamgir
 * @param <T>
 */

public abstract class ModelBuilder<T>{
    Logger logger = LoggerFactory.getLogger(ModelBuilder.class);
    
    public T build(){
        return (T)this;
    }
    
    public T build(String jsonContent) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
            return mapper.readValue(jsonContent, (Class<T>)this.getClass());
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        return null;
    }
    
    @Override
    public String toString() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, true);
            return mapper.writeValueAsString(this);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        return null;
    }
}
