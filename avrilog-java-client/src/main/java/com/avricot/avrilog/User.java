package com.avricot.avrilog;

import org.msgpack.annotation.Message;

@Message
public class User {
    private String id;
    private String firstname;
    private String lastname;
    private String email;
    private String groupId;
    private String ip;

    public String getId() {
        return id;
    }

    public User setId(final String id) {
        this.id = id;
        return this;
    }

    public String getFirstname() {
        return firstname;
    }

    public User setFirstname(final String firstname) {
        this.firstname = firstname;
        return this;
    }

    public String getLastname() {
        return lastname;
    }

    public User setLastname(final String lastname) {
        this.lastname = lastname;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(final String email) {
        this.email = email;
        return this;
    }

    public String getGroupId() {
        return groupId;
    }

    public User setGroupId(final String groupId) {
        this.groupId = groupId;
        return this;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(final String ip) {
        this.ip = ip;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", email=" + email + ", groupId=" + groupId + ", ip=" + ip + "]";
    }

}
