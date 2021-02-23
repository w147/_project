package com.plaso;

public class Student {

    private Integer id;

    private String loginname;

    public Integer getId() {
        return id;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", loginname='" + loginname + '\'' +
                '}';
    }
}
