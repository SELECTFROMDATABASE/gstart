package com.gstart.demo.repository.pojo;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "demo")
public class Demo {
    private String mainId;
    private String name;
    private String password;

    @Id
    @Column(name = "mainId" ,nullable = false,length = 50)
    public String getMainId() {
        return mainId;
    }
    public void setMainId(String mainId) {
        this.mainId = mainId;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "password", nullable = true, length = 255)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Demo{" +
                "mainId='" + mainId + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Demo demo = (Demo) o;
        return Objects.equals(name, demo.name) &&
                Objects.equals(password, demo.password);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, password);
    }
}
