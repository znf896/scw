package com.alibaba.scwproject.bean;

public class TProjectImages {
    private Integer id;

    private Integer projectId;

    private String imageUrl;

    private Byte imageType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl == null ? null : imageUrl.trim();
    }

    public Byte getImageType() {
        return imageType;
    }

    public void setImageType(Byte imageType) {
        this.imageType = imageType;
    }
}