package com.build.manager.buildmanager.build;


public class BuildRequest {
    private String buildEndDate;
    private String buildStartDate;
    private String assignedTo;
    private String status; 
    private String platform;

    public String getBuildEndDate() {
        return buildEndDate;
    }

    public void setBuildEndDate(String buildEndDate) {
        this.buildEndDate = buildEndDate;
    }

    public String getBuildStartDate() {
        return buildStartDate;
    }

    public void setBuildStartDate(String buildStartDate) {
        this.buildStartDate = buildStartDate;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }
}