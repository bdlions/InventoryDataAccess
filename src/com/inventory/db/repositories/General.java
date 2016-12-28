package com.inventory.db.repositories;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nazmul
 */
public class General {
    public List<String> errors = new ArrayList<>();
    public List<String> messages = new ArrayList<>();
    
    public General() {
        
    }
    
    public void addMessage(String message)
    {
        messages.add(message);
    }
    
    public void addError(String error)
    {
        errors.add(error);
    }
    
    public List<String> getAllMessages()
    {
        return messages;
    }
    public List<String> getAllErrors()
    {
        return errors;
    }
}
