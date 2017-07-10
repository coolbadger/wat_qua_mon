package com.hd.ibus.util.shenw;

import com.hd.ibus.util.PageBean;

import java.io.Serializable;

/**
 * 分页工具类.
 */
public class PageHelp implements Serializable{
    private static PageHelp pageHelp;
    private PageHelp(){};

    public static PageHelp getInstance(){
        if(pageHelp==null){
            pageHelp=new PageHelp();
        }
        return pageHelp;
    }

    private static final long serialVersionUID = -3817231542610983296L;

    private Object object;

    private PageBean pageBean;

    private String selectStr;

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public PageBean getPageBean() {
        return pageBean;
    }

    public void setPageBean(PageBean pageBean) {
        this.pageBean = pageBean;
    }

    public String getSelectStr() {
        return selectStr;
    }

    public void setSelectStr(String selectStr) {
        this.selectStr = selectStr;
    }
}
