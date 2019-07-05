package com.build.manager.buildmanager.user;

import com.build.manager.buildmanager.utils.StatusModal;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

public class UserModel extends StatusModal{

    @JsonIgnore()
    @Id
    private String id;

    @JsonInclude(Include.NON_NULL)
    private String firstName;
    @JsonInclude(Include.NON_NULL)
    private String lastName;
    @JsonInclude(Include.NON_NULL)
    @Indexed
    private String email;

    public UserModel() {
        super();
    }

    public UserModel(String firstName, String lastName, String email) {
        super();
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserModel [email=" + email + ", firstName=" + firstName + ", id=" + id + ", lastName=" + lastName + "]";
    }
}