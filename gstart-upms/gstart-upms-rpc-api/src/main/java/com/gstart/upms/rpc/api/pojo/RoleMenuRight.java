package com.gstart.upms.rpc.api.pojo;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

/**
 * @author yangguangye
 * @Create by gzpykj
 * @Date 2019-03-07 22:12
 */
@Entity
@Table(name = "g_sys_role_menuright")
public class RoleMenuRight {
    private String mainId;

    public String getMainId() {
        return mainId;
    }

    public void setMainId(String mainId) {
        this.mainId = mainId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleMenuRight that = (RoleMenuRight) o;
        return Objects.equals(mainId, that.mainId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(mainId);
    }
}
