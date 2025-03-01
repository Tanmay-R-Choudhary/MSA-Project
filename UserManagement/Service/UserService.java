package project.UserManagement.Service;


import project.UserManagement.Entity.User;

import java.util.List;

public interface UserService {
    User authenticateUser(String username, String password);
    User createUser(User User);
    User updateUser(User User);
    void deleteUser(int userid);
}