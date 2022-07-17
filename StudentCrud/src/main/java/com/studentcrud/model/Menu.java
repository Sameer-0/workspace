package com.studentcrud.model;

public class Menu {

    private int id;
    private String name;
    private String parentId;
    private String link;

    public Menu() {
    }

    public Menu(int id, String name, String parentId, String link) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
        this.link = link;
    }

    @Override
    public String toString() {
        return "Menu [id=" + id + ", link=" + link + ", name=" + name + ", parentId=" + parentId + "]";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

}
