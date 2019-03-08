package com.gstart.upms.rpc.api.pojo;

import javax.persistence.*;
import java.util.Objects;

/**
 * @ Author     ：yangguangye.
 * @ Date       ：Created in 10:10 2019/3/8
 * @ Description：
 * @ Modified By：
 * @Version: $
 */
@Entity
@Table(name = "g_sys_department")
public class Department {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private String mainId;
    private Integer parentId;
    private String departmentNo;
    private String departmentName;
    private String remark;
    private Integer orderId;
    private Integer hasChild;
    private Integer isEnabled;

    public String getMainId() {
        return mainId;
    }

    public void setMainId(String mainId) {
        this.mainId = mainId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getDepartmentNo() {
        return departmentNo;
    }

    public void setDepartmentNo(String departmentNo) {
        this.departmentNo = departmentNo;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getHasChild() {
        return hasChild;
    }

    public void setHasChild(Integer hasChild) {
        this.hasChild = hasChild;
    }

    public Integer getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(Integer isEnabled) {
        this.isEnabled = isEnabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return Objects.equals(mainId, that.mainId) &&
                Objects.equals(parentId, that.parentId) &&
                Objects.equals(departmentNo, that.departmentNo) &&
                Objects.equals(departmentName, that.departmentName) &&
                Objects.equals(remark, that.remark) &&
                Objects.equals(orderId, that.orderId) &&
                Objects.equals(hasChild, that.hasChild) &&
                Objects.equals(isEnabled, that.isEnabled);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mainId, parentId, departmentNo, departmentName, remark, orderId, hasChild, isEnabled);
    }
}
