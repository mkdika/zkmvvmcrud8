package com.mkdika.zkmvvmcrud8.service.template;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Maikel Chandika <mkdika@gmail.com>
 */
public interface GenericService {
    
    public boolean save(Object obj);       
    
    public boolean update(Object obj);       
    
    public boolean saveOrUpdate(Object obj);       

    public boolean delete(Object obj);
    
    public List findAll(Class type) throws Exception;
    
    public List findAll(Class type, String order) throws Exception; 
    
    public Object findById(Class type, Serializable srlzbl);
    
    public List selectNativeQuery(String query);

    public void executeNativeQuery(String query);
}
