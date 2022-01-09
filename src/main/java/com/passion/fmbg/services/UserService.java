package com.passion.fmbg.services;

import com.passion.fmbg.entities.User;
import com.passion.fmbg.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private UserRepo repo;

    @Autowired
    public UserService(UserRepo repo) {
        this.repo = repo;
    }

    public User create(User user) {
        return repo.save(user);
    }

    public User findById(Long id) {
        return repo.findById(id).get();
    }

    public List<User> findAll() {
        Iterable<User> allUsers = repo.findAll();
        List<User> userList = new ArrayList<>();
        allUsers.forEach(userList::add);
        return userList;
    }

    public User update(Long id, User newUserData) {
        User userInDatabase = this.findById(id);
        userInDatabase.setFirstName(newUserData.getFirstName());
        userInDatabase.setLastName(newUserData.getLastName());
        userInDatabase.setEmailAddress(newUserData.getEmailAddress());
        userInDatabase.setUsername(newUserData.getUsername());

        userInDatabase = repo.save(userInDatabase);
        return userInDatabase;
    }

    public User deleteById(Long id) {
        User userToBeDeleted = this.findById(id);
        repo.delete(userToBeDeleted);
        return userToBeDeleted;
    }
}
