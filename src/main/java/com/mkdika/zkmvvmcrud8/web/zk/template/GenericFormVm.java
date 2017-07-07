package com.mkdika.zkmvvmcrud8.web.zk.template;

import com.mkdika.zkmvvmcrud8.service.AppService;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;

/**
 *
 * @author Maikel Chandika <mkdika@gmail.com>
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class GenericFormVm {
    
    @WireVariable
    private AppService appService;
        
    public void saveModel(Object obj) {
        getService().saveOrUpdate(obj);
    }
    
    public void deleteModel(Object obj) {
        getService().delete(obj);
    }

    public AppService getService() {
        return appService;
    }            
}
