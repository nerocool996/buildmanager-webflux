package com.build.manager.buildmanager.build;

import com.build.manager.buildmanager.user.UserModel;
import com.build.manager.buildmanager.utils.StatusModal;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class BuildResponse extends StatusModal{

    @JsonInclude(Include.NON_NULL)
    private long remainingEndDays;
    @JsonInclude(Include.NON_NULL)
    private long remainingStartDays;
    @JsonInclude(Include.NON_NULL)
    private UserModel assignedTo;
    @JsonInclude(Include.NON_NULL)
    private BuildStatus status; 
    @JsonInclude(Include.NON_NULL) 
    private Platform platform;

    public BuildResponse(){ }

    public BuildResponse(BuildModel build){ 
        this.remainingEndDays = build.getRemainingEndDate().toDays();
        this.remainingStartDays = build.getRemainingStartDate().toDays();
        this.assignedTo = build.getAssignedTo();
        this.platform = build.getPlatform();
        this.status = build.getStatus();
    }

    public BuildStatus getStatus() {
        return status;
    }

    public void setStatus(BuildStatus status) {
        this.status = status;
    }

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    public long getRemainingEndDays() {
        return remainingEndDays;
    }

    public void setRemainingEndDays(long remainingEndDays) {
        this.remainingEndDays = remainingEndDays;
    }

    public long getRemainingStartDays() {
        return remainingStartDays;
    }

    public void setRemainingStartDays(long remainingStartDays) {
        this.remainingStartDays = remainingStartDays;
    }

    public UserModel getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(UserModel assignedTo) {
        this.assignedTo = assignedTo;
    }
}