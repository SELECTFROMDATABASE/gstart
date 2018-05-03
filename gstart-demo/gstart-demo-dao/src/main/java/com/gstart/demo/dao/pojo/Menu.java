package com.gstart.demo.dao.pojo;

import java.util.List;

/**
 * @author yangguangye
 * @Create by gzpykj
 * @Date 2018-04-19 22:50
 */
public class Menu {
    public String mainId;
    public String parentId;
    public String menuNo;
    public String menuName;
    public String menuLabel;
    public String url;
    public String icon80;
    public String icon32;
    public String icon16;
    public String iconClass;
    public String isAuth;
    public String target;
    public String remark;
    public String orderId;
    public String hasChild;
    public String isEnabled;
    public List<Menu> childrenItems;

    public String getIconClass() {
        return iconClass;
    }

    public void setIconClass(String iconClass) {
        this.iconClass = iconClass;
    }
    public List<Menu> getChildrenItems() {
        return childrenItems;
    }

    public void setChildrenItems(List<Menu> childrenItems) {
        this.childrenItems = childrenItems;
    }

    public String getMainId() {
        return mainId;
    }

    public void setMainId(String mainId) {
        this.mainId = mainId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getMenuNo() {
        return menuNo;
    }

    public void setMenuNo(String menuNo) {
        this.menuNo = menuNo;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuLabel() {
        return menuLabel;
    }

    public void setMenuLabel(String menuLabel) {
        this.menuLabel = menuLabel;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon80() {
        return icon80;
    }

    public void setIcon80(String icon80) {
        this.icon80 = icon80;
    }

    public String getIcon32() {
        return icon32;
    }

    public void setIcon32(String icon32) {
        this.icon32 = icon32;
    }

    public String getIcon16() {
        return icon16;
    }

    public void setIcon16(String icon16) {
        this.icon16 = icon16;
    }

    public String getIsAuth() {
        return isAuth;
    }

    public void setIsAuth(String isAuth) {
        this.isAuth = isAuth;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getHasChild() {
        return hasChild;
    }

    public void setHasChild(String hasChild) {
        this.hasChild = hasChild;
    }

    public String getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(String isEnabled) {
        this.isEnabled = isEnabled;
    }

    public String getApptype() {
        return apptype;
    }

    public void setApptype(String apptype) {
        this.apptype = apptype;
    }

    public String apptype;

}
