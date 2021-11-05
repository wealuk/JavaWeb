package com.zhuihaikejixueyuan.pojo;

import java.util.Date;

/**
 * @author shkstart
 * @create 2021-09-24 15:53
 */
public class Orgnization {
//    社团组织表单主要字段：社团名称、负责人姓名、负责人所在专业、成立日期、现有成员

    private String massname;
    private String funtionaryname;
    private String major;
    private Date createtime;
    private String member;

    public Orgnization() {
    }

    public Orgnization(String massname, String funtionaryname, String major, Date createtime, String member) {
        this.massname = massname;
        this.funtionaryname = funtionaryname;
        this.major = major;
        this.createtime = createtime;
        this.member = member;
    }

    public String getMassname() {
        return massname;
    }

    public void setMassname(String massname) {
        this.massname = massname;
    }

    public String getFuntionaryname() {
        return funtionaryname;
    }

    public void setFuntionaryname(String funtionaryname) {
        this.funtionaryname = funtionaryname;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }

    @Override
    public String toString() {
        return "Orgnization{" +
                "massname='" + massname + '\'' +
                ", functionaryname='" + funtionaryname + '\'' +
                ", major='" + major + '\'' +
                ", createtime=" + createtime +
                ", member='" + member + '\'' +
                '}';
    }
}
