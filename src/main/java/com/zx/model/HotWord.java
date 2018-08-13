package com.zx.model;

import java.util.List;

public class HotWord {
    private Integer id;

    private String name;

    private Double size;

    private List<String> children;

    private List<String> detail;

    private List<String> advice;

    public List<String> getChildren() {
        return children;
    }

    public void setChildren(List<String> children) {
        this.children = children;
    }

    public List<String> getDetail() {
        return detail;
    }

    public void setDetail(List<String> detail) {
        this.detail = detail;
    }

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
        this.name = name == null ? null : name.trim();
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    public List<String> getAdvice() {
        return advice;
    }

    public void setAdvice(List<String> advice) {
        this.advice = advice;
    }
}