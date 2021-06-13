package com.example.ProyectoFinal.dao;

import com.example.ProyectoFinal.model.User;

import java.io.Serializable;

public class UserAndResult implements Serializable {

    User user;
    SaveUserResult result;

    public UserAndResult(User user, SaveUserResult result) {
        this.user = user;
        this.result = result;
    }
    public UserAndResult(){}

    public User getUser() {
        return user;
    }

    public SaveUserResult getResult() {
        return result;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setResult(SaveUserResult result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "UserAndResult{" +
                "user=" + user +
                ", result=" + result +
                '}';
    }
}
