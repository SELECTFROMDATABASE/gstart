package com.gstart.upms.rpc.api.pojo;

import javax.persistence.*;
import java.util.List;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer mainId;
    private Integer parentId;
    private String roleNo;
    private String roleName;
    private String remark;
    private Integer orderId;
    private Integer hasChild;
    private Integer isEnabled;

    @ManyToMany
    @JoinTable(name = "g_sys_role_menu",
            joinColumns = {@JoinColumn(name = "roleId")},
            inverseJoinColumns = {@JoinColumn(name = "menuId")})
    private List<Menu> menus;
    @ManyToMany()
    @JoinTable(name = "g_sys_role_position",
            joinColumns = {@JoinColumn(name = "roleId")},
            inverseJoinColumns = {@JoinColumn(name = "positionId")})
    private List<Position> positions;
    @ManyToMany()
    @JoinTable(name = "g_sys_role_menuright",
            joinColumns = {@JoinColumn(name = "roleId")},
            inverseJoinColumns = {@JoinColumn(name = "menurightId")})
    private List<MenuRight> menuRights;

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    public List<Position> getPositions() {
        return positions;
    }

    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }

    public List<MenuRight> getMenuRights() {
        return menuRights;
    }

    public void setMenuRights(List<MenuRight> menuRights) {
        this.menuRights = menuRights;
    }

    public Integer getMainId() {
        return mainId;
    }

    public void setMainId(Integer mainId) {
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

    @Override
    public String toString() {
        return "Role{" +
                "mainId=" + mainId +
                ", parentId=" + parentId +
                ", roleNo='" + roleNo + '\'' +
                ", roleName='" + roleName + '\'' +
                ", remark='" + remark + '\'' +
                ", orderId=" + orderId +
                ", hasChild=" + hasChild +
                ", isEnabled=" + isEnabled +
                ", menus=" + menus +
                ", positions=" + positions +
                ", menuRights=" + menuRights +
                '}';
    }
}
