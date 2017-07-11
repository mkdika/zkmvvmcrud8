package com.mkdika.zkmvvmcrud8.web.vm;

import com.mkdika.zkmvvmcrud8.model.TbExperience;
import com.mkdika.zkmvvmcrud8.model.TbPerson;
import com.mkdika.zkmvvmcrud8.web.zk.template.CrudFormVm;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.SmartNotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.ListModelList;

/**
 *
 * @author Maikel Chandika <mkdika@gmail.com>
 */
public class Index extends CrudFormVm {

    private final String appInfo = "Zk MVVM CRUD 8 - Head Detail (OneToMany)";

    private final String[] genderData = {"FEMALE", "MALE"};
    private final String[] idTypeData = {"KTP", "SIM", "PASPOR", "KITAS"};
    private final String[] sectorData = {"Trading", "Manufacture", "Education", "Service", "Banking"};

    private String gridId;
    private TbPerson selected;
    private List<String> genderList;
    private List<String> idTypeList;
    private List<String> sectorList;
    private ListModelList<TbExperience> experiences;

    @Init
    public void init(@BindingParam("gridId") String gridId) {
        super.init();
        this.gridId = gridId;

        setGenderList(new ArrayList<>(Arrays.asList(genderData)));
        setIdTypeList(new ArrayList<>(Arrays.asList(idTypeData)));
        setSectorList(new ArrayList<>(Arrays.asList(sectorData)));
        setExperiences(new ListModelList<>(new ArrayList<TbExperience>()));
    }

    @Command
    @SmartNotifyChange({"selected", "experiences"})
    @Override
    public void addClick() {
        setSelected(new TbPerson());
        getSelected().setExperiences(new ArrayList<TbExperience>());
        setExperiences(new ListModelList<>(getSelected().getExperiences()));
        addButtonState();
        initScrollBar(gridId);
    }

    @Command    
    @Override
    public void deleteClick() {
        msq("Zk MVVM CRUD 8","Delete Confirmation?", "Index$delConfirmed");
    }

    @GlobalCommand
    @SmartNotifyChange({"selected", "experiences"})
    public void Index$delConfirmed() {       
        if (getSelected() != null) {       
            try {
                deleteModel(getSelected());
                setSelected(null);
                setExperiences(null);
                normalButtonState();
                Clients.showNotification("Delete successful.");
            } catch (Exception e) {
                Clients.showNotification("Delete failed.\n" + e.getLocalizedMessage());
            }
        } else {
            Clients.showNotification("Record not found.");
            editButtonState();
        }
    }

    @Command
    @SmartNotifyChange({"selected", "experiences"})
    @Override
    public void saveClick() {
        try {
            getSelected().setExperiences(getExperiences());
            saveModel(getSelected());
            setSelected(null);
            setExperiences(null);
            normalButtonState();
            Clients.showNotification("Save successful.");
        } catch (Exception e) {
            Clients.showNotification("Save failed.\n" + e.getLocalizedMessage());
        }
    }

    @Command
    @SmartNotifyChange({"selected", "experiences"})
    @Override
    public void cancelClick() {
        setSelected(null);
        setExperiences(null);
        normalButtonState();
    }

    @Command
    @Override
    public void browseClick() {
        Executions.createComponents("/template/component/browsedata.zul", null, null);
    }

    @GlobalCommand
    @SmartNotifyChange({"selected", "experiences"})
    public void Index$browseSelected(@BindingParam("personSelected") TbPerson personSelected) {
        if (personSelected != null) {
            setSelected(personSelected);
            setExperiences(new ListModelList<>(personSelected.getExperiences()));
            editButtonState();
            initScrollBar(gridId);
        }
    }

    @Command
    @Override
    public void infoClick() {
        Clients.showNotification(appInfo + "<br/>" + "First version writen by:<br/>Maikel Chandika (mkdika@gmail.com)");
    }

    @Command
    @SmartNotifyChange({"experiences"})
    public void delExpItem(@BindingParam("data") TbExperience data) {
        int index = experiences.indexOf(data);
        if (index != -1) {
            experiences.remove(data);
        }
    }

    @Command
    @SmartNotifyChange({"experiences"})
    public void addExpItem() {
        TbExperience t = new TbExperience(getSelected());
        experiences.add(t);
        scrollToBottom(gridId);
    }

    public String getAppInfo() {
        return appInfo;
    }

    public TbPerson getSelected() {
        return selected;
    }

    public void setSelected(TbPerson selected) {
        this.selected = selected;
    }

    public void setGenderList(List<String> genderList) {
        this.genderList = genderList;
    }

    public List<String> getIdTypeList() {
        return idTypeList;
    }

    public void setIdTypeList(List<String> idTypeList) {
        this.idTypeList = idTypeList;
    }

    public List<String> getSectorList() {
        return sectorList;
    }

    public void setSectorList(List<String> sectorList) {
        this.sectorList = sectorList;
    }

    public ListModelList<TbExperience> getExperiences() {
        return experiences;
    }

    public void setExperiences(ListModelList<TbExperience> experiences) {
        this.experiences = experiences;
    }

    public void scrollToBottom(String gridId) {
        Clients.evalJavaScript("$(zk.Widget.$('$" + gridId + "').ebody).scrollTop(zk.Widget.$('$" + gridId + "').ebodyrows.scrollHeight);setTimeout(function(){$(zk.Widget.$('$" + gridId + "').ebody).scrollTop(zk.Widget.$('$" + gridId + "').ebodyrows.scrollHeight);},1000)");

    }

    public void initScrollBar(String gridId) {
        Clients.evalJavaScript("var index = 1; var interval = setInterval(function(){if(zk.Widget.$('$" + gridId + "')!=null){for(var i = 0;i<zk.Widget.$('$" + gridId + "').ebodyrows.scrollHeight;i+=10){$(zk.Widget.$('$" + gridId + "').ebody).scrollTop(i);}clearInterval(interval);$(zk.Widget.$('$" + gridId + "').ebody).scrollTop(0);}},1)");
    }

    public String[] getGenderData() {
        return genderData;
    }

    public List<String> getGenderList() {
        return genderList;
    }       
}
