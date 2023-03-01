package com.example.logging;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    List<User> users = new ArrayList<>();

    @Override
    public String toString() {
        return "UserRepository{" +
                "users=" + users +
                '}';
    }

    public UserRepository() {
        users.add(new User("MSteen", "banan", "Michelle", "Steenvoorden"));
        users.add(new User("Fredrik", "apple", "Fredrik", "Bjuren"));
        }

    public void addUser(User user){
        users.add(user);
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }




}
