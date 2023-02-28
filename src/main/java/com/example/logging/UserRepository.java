package com.example.logging;

import java.util.List;

public class UserRepository {
    List<User> users;

    public void addUser(User user){
        users.add(user);
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public UserRepository(List<User> users) {
        this.users = users;
    }


}
