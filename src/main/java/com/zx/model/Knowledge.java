package com.zx.model;


import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author pgm_sup
 */
@Entity
public class Knowledge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(name = "descr")
    private String describe;

    private String hotWord;

    @Column(name = "department")
    private String department;

    private String operator;

    private Date operTime;

    @OneToMany(cascade = {CascadeType.ALL},fetch = FetchType.EAGER)
    @JoinColumn(name = "knowledge_id")
    private Set<Ruler> rulers = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getHotWord() {
        return hotWord;
    }

    public void setHotWord(String hotWord) {
        this.hotWord = hotWord;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Date getOperTime() {
        return operTime;
    }

    public void setOperTime(Date operTime) {
        this.operTime = operTime;
    }

    public Set<Ruler> getRulers() {
        return rulers;
    }

    public void setRulers(Set<Ruler> rulers) {
        this.rulers = rulers;
    }

    @Override
    public String toString() {
        return "Knowledge{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", describe='" + describe + '\'' +
                ", hotWord='" + hotWord + '\'' +
                ", department='" + department + '\'' +
                ", operator='" + operator + '\'' +
                ", operTime=" + operTime +
                ", rulers=" + rulers +
                '}';
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
