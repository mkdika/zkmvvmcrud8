package com.mkdika.zkmvvmcrud8.service;

import com.mkdika.zkmvvmcrud8.model.TbPerson;
import com.mkdika.zkmvvmcrud8.service.template.GenericService;
import java.util.List;

/**
 *
 * @author Maikel Chandika <mkdika@gmail.com>
 */
public interface AppService extends GenericService {
    
    /*
     * To add customization of service interface only.
     * General data transaction method have been supported 
     * by GenericService interface & its implementation.
     */
    
    // START custom service
    List<TbPerson> getTbPersonByGender(Integer gender);
    List<TbPerson> searchTbPerson(String key);
}
