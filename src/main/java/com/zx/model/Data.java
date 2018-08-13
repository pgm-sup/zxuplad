package com.zx.model;

import java.util.List;

public class Data {
    private String name;
    private List<HotWord> children;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<HotWord> getChildren() {
        return children;
    }

    public void setChildren(List<HotWord> children) {
        this.children = children;
    }
}
