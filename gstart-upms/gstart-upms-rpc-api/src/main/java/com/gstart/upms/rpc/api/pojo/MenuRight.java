package com.gstart.upms.rpc.api.pojo;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @author yangguangye
 * @Create by gzpykj
 * @Date 2019-03-07 22:12
 */
@Entity()
@Table(name = "g_sys_menuright")
public class MenuRight {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer mainId;
    private String rightName;
    private String rightLabel;
    @ManyToMany(mappedBy = "menuRights")
    private Set<Role> roles;

    @ManyToOne
    @JoinColumn(name = "menuId")
    private Menu menu;


    public Integer getMainId() {
        return mainId;
    }

    public void setMainId(Integer mainId) {
        this.mainId = mainId;
    }

    public String getRightName() {
        return rightName;
    }

    public void setRightName(String rightName) {
        this.rightName = rightName;
    }

    public String getRightLabel() {
        return rightLabel;
    }

    public void setRightLabel(String rightLabel) {
        this.rightLabel = rightLabel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuRight menuRight = (MenuRight) o;
        return mainId == menuRight.mainId &&
                Objects.equals(rightName, menuRight.rightName) &&
                Objects.equals(rightLabel, menuRight.rightLabel);
    }

    @Override
    public int hashCode() {

        return Objects.hash(mainId, rightName, rightLabel);
    }

    @Override
    public String toString() {
        return "MenuRight{" +
                "mainId=" + mainId +
                ", rightName='" + rightName + '\'' +
                ", rightLabel='" + rightLabel + '\'' +
                ", roles=" + roles +
                ", menu=" + menu +
                '}';
    }
}
