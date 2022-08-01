package com.demo.notesapp.models;

import java.util.Date;
import java.util.UUID;

public class Note {
    private String id;
    private String title;
    private String comment;
    private Date createDate;
    private Boolean IsChecked;

    public Note(String title, String comment){
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.comment = comment;
        this.createDate = new Date();
        IsChecked = false;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Boolean getChecked() {
        return IsChecked;
    }

    public void setChecked(Boolean checked) {
        IsChecked = checked;
    }


}
