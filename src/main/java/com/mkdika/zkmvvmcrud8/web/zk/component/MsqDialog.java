package com.mkdika.zkmvvmcrud8.web.zk.component;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zul.Window;

/**
 *
 * @author Maikel Chandika <mkdika@gmail.com>
 */
public class MsqDialog {

    private String title;
    private String content;
    private String bindMethodYes;

    @Init
    public void init(@ExecutionArgParam("sTitle") String title,
                     @ExecutionArgParam("sContent")String content,
                     @ExecutionArgParam("sBindMethodYes")String bindMethodYes) {
        this.title = title;
        this.content = content;
        this.bindMethodYes = bindMethodYes;        
    }

    @Command
    public void selectYes(@BindingParam("window") Window window) {        
        BindUtils.postGlobalCommand(null, null, bindMethodYes, null);
        window.onClose();
    }

    @Command
    public void selectNo(@BindingParam("window") Window window) {
        window.onClose();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }        
}
