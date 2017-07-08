package com.mkdika.zkmvvmcrud8.web.zk.template;

import com.mkdika.zkmvvmcrud8.service.AppService;
import java.util.HashMap;
import java.util.Map;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;

/**
 *
 * @author Maikel Chandika <mkdika@gmail.com>
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class GenericVm {
    
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
    
    public void msq(String title, String content,  String bindMethodYes) {        
        Map<String, Object> args = new HashMap<>();                
        args.put("sTitle", title);
        args.put("sContent", content);        
        args.put("sBindMethodYes", bindMethodYes);            
        Executions.createComponents("/template/component/msqdialog.zul", null, args);
    }
}
