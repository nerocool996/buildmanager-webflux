package com.build.manager.buildmanager.build;

import com.build.manager.buildmanager.user.UserModel;
import com.build.manager.buildmanager.utils.StatusModal;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.time.Duration;
import java.time.ZonedDateTime;

enum Platform{
    ios,
    android,
    both
}

enum BuildStatus {
    none,
    ios_done,
    android_done,
    all_done
}

public class BuildModel extends StatusModal{
    @JsonInclude(Include.NON_NULL)
    @Id
    private String id;
    
    @JsonInclude(Include.NON_NULL)
    @Indexed
    private ZonedDateTime buildEndDate;
    @JsonInclude(Include.NON_NULL)
    private ZonedDateTime buildStartDate;
    @JsonInclude(Include.NON_NULL)
    private UserModel assignedTo;
    @JsonInclude(Include.NON_NULL)
    private BuildStatus status; 
    @JsonInclude(Include.NON_NULL) 
    private Platform platform;

    public BuildModel() {}

    public BuildModel(ZonedDateTime buildEndDate, ZonedDateTime buildStartDate, UserModel user, BuildStatus status, Platform platform) {
        this.buildEndDate = buildEndDate;
        this.buildStartDate = buildStartDate;
        this.assignedTo = user;
        this.status = status;
        this.platform = platform;
    }

    public BuildModel(ZonedDateTime buildEndDate, ZonedDateTime buildStartDate, BuildStatus status, Platform platform) {
        this.buildEndDate = buildEndDate;
        this.buildStartDate = buildStartDate;
        this.status = status;
        this.platform = platform;
    }

    public BuildModel( StatusModal status ) {
        this.setStatusCode(status.getStatusCode());
        this.setStatusMessage(status.getStatusMessage());
    }
    Duration getRemainingEndDate() {
        if(ZonedDateTime.now().isBefore(this.buildEndDate)){
            return Duration.between(ZonedDateTime.now(), this.buildEndDate);
        } else {
            return Duration.ofDays(0);
        }
    }

    Duration getRemainingStartDate() {
        if(ZonedDateTime.now().isBefore(this.buildStartDate)){
            return Duration.between(ZonedDateTime.now(), this.buildStartDate);
        } else {
            return Duration.ofDays(0);
        }
    }

    public void setBuildEndDate(ZonedDateTime buildEndDate) {
        this.buildEndDate = buildEndDate;
    }

    public void setBuildStartDate(ZonedDateTime buildStartDate) {
        this.buildStartDate = buildStartDate;
    }

    public UserModel getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(UserModel assignedTo) {
        this.assignedTo = assignedTo;
    }

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    public BuildStatus getStatus() {
        return status;
    }

    public void setStatus(BuildStatus status) {
        this.status = status;
    }
}