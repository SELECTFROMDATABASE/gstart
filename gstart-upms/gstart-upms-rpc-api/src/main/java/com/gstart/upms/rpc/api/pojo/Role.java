package com.gstart.upms.rpc.api.pojo;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author yangguangye
 * @Create by gzpykj
 * @Date 2019-03-07 22:12
 */
@Entity
@Table(name = "g_sys_role")
public class Role {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private String mainId;
    private Integer parentId;
    private String roleNo;
    private String roleName;
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

    public String getRoleNo() {
        return roleNo;
    }

    public void setRoleNo(String roleNo) {
        this.roleNo = roleNo;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
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
        Role role = (Role) o;
        return mainId == role.mainId &&
                Objects.equals(parentId, role.parentId) &&
                Objects.equals(roleNo, role.roleNo) &&
                Objects.equals(roleName, role.roleName) &&
                Objects.equals(remark, role.remark) &&
                Objects.equals(orderId, role.orderId) &&
                Objects.equals(hasChild, role.hasChild) &&
                Objects.equals(isEnabled, role.isEnabled);
    }

    @Override
    public int hashCode() {

        return Objects.hash(mainId, parentId, roleNo, roleName, remark, orderId, hasChild, isEnabled);
    }
}
