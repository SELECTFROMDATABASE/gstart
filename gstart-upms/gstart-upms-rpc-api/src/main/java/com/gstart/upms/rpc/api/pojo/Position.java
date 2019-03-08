package com.gstart.upms.rpc.api.pojo;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * @ Author     ：yangguangye.
 * @ Date       ：Created in 10:11 2019/3/8
 * @ Description：
 * @ Modified By：
 * @Version: $
 */
@Table
@Entity(name = "g_sys_position")
public class Position {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer mainId;
    private Integer departmentId;
    private String positionName;
    private String description;
    private Integer superiorPosId;
    private Integer adminGrade;
    private Integer orderId;
    private Integer isEnabled;

/*
    @OneToMany(mappedBy = "position",fetch = FetchType.EAGER)
    private List<User> users;*/
/*    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }*/

    public Integer getMainId() {
        return mainId;
    }

    public void setMainId(Integer mainId) {
        this.mainId = mainId;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSuperiorPosId() {
        return superiorPosId;
    }

    public void setSuperiorPosId(Integer superiorPosId) {
        this.superiorPosId = superiorPosId;
    }

    public Integer getAdminGrade() {
        return adminGrade;
    }

    public void setAdminGrade(Integer adminGrade) {
        this.adminGrade = adminGrade;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
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
        Position position = (Position) o;
        return Objects.equals(mainId, position.mainId) &&
                Objects.equals(departmentId, position.departmentId) &&
                Objects.equals(positionName, position.positionName) &&
                Objects.equals(description, position.description) &&
                Objects.equals(superiorPosId, position.superiorPosId) &&
                Objects.equals(adminGrade, position.adminGrade) &&
                Objects.equals(orderId, position.orderId) &&
                Objects.equals(isEnabled, position.isEnabled);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mainId, departmentId, positionName, description, superiorPosId, adminGrade, orderId, isEnabled);
    }


}
