package com.mkdika.zkmvvmcrud8.web.zk.template;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.DefaultCommand;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.util.Clients;

/**
 *
 * @author Maikel Chandika <mkdika@gmail.com>
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public abstract class CrudFormVm extends GenericVm {   

    private Boolean visibleAdd = false;    
    private Boolean visibleDelete = false;
    private Boolean visibleSave = false;
    private Boolean visibleCancel = false;   
    private Boolean visibleBrowse = true;    
    private Boolean visibleInfo = true;
    
    public void init() {        
        normalButtonState();
    }  
    
    // toolbar command    
    public abstract void addClick();   

    public abstract void deleteClick();

    public abstract void saveClick();

    public abstract void cancelClick();
    
    public abstract void browseClick();

    public abstract void infoClick();

    @DefaultCommand
    public void defaultCommand() {
        Clients.showNotification("Features is not implemented.");
    }

    public void normalButtonState() {
        setVisibleAdd(true);        
        setVisibleDelete(false);
        setVisibleSave(false);
        setVisibleCancel(false);
        setVisibleSearch(true);
    }

    public void addButtonState() {
        setVisibleAdd(false);       
        setVisibleDelete(false);
        setVisibleSave(true);
        setVisibleCancel(true);
        setVisibleSearch(false);
    }  

    public void editButtonState() {
        setVisibleAdd(false);        
        setVisibleDelete(true);
        setVisibleSave(true);
        setVisibleCancel(true);
        setVisibleSearch(false);
    }

    // ------------ SETTER & GETTER ---------------------------

    public Boolean getVisibleAdd() {
        return visibleAdd;
    }  

    public Boolean getVisibleDelete() {
        return visibleDelete;
    }

    public Boolean getVisibleSave() {
        return visibleSave;
    }

    public Boolean getVisibleCancel() {
        return visibleCancel;
    }

    public Boolean getVisibleSearch() {
        return visibleBrowse;
    }

    public Boolean getVisibleInfo() {
        return visibleInfo;
    }
    
    public void setVisibleSearch(boolean visibleSearch) {
        this.visibleBrowse = visibleSearch;        
        BindUtils.postNotifyChange(null,null,this,"visibleSearch");
    }
   
    public void setVisibleInfo(boolean visibleInfo) {
        this.visibleInfo = visibleInfo;        
        BindUtils.postNotifyChange(null,null,this,"visibleInfo");
    }
    
    public void setVisibleAdd(boolean visibleAdd) {
        this.visibleAdd = visibleAdd;        
        BindUtils.postNotifyChange(null,null,this,"visibleAdd");
    }
       
    public void setVisibleDelete(boolean visibleDelete) {
        this.visibleDelete = visibleDelete;        
        BindUtils.postNotifyChange(null,null,this,"visibleDelete");
    }
    
    public void setVisibleSave(boolean visibleSave) {
        this.visibleSave = visibleSave;        
        BindUtils.postNotifyChange(null,null,this,"visibleSave");
    }
      
    public void setVisibleCancel(boolean visibleCancel) {
        this.visibleCancel = visibleCancel;        
        BindUtils.postNotifyChange(null,null,this,"visibleCancel");
    }
}
