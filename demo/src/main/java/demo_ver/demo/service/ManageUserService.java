package demo_ver.demo.service;

import org.springframework.stereotype.Service;

import demo_ver.demo.model.ManageUser;

import java.util.ArrayList;
import java.util.List;

@Service
public class ManageUserService {

    private static List<ManageUser> userList = new ArrayList<ManageUser>() {
        {
            add(new ManageUser(2000, "teenesh@gmail.com", "Teenesh", "123456", "Admin"));
            add(new ManageUser(2001, "user@gmail.com", "John", "654321", "Software Tester"));
        }
    };

    public static List<ManageUser> getAllUsers() {
        return userList;
    }

    public void addUser(ManageUser newUser) {
        // Check if a user with the same user ID already exists
        if (userList.stream().noneMatch(user -> user.getUsername().equalsIgnoreCase(newUser.getUsername()) ||
                user.getEmail().equalsIgnoreCase(newUser.getEmail()))) {

            newUser.setUserID(generateUserID()); // Assign a new user ID
            userList.add(newUser);

            // Invoke notification method here

        } else {
            System.out.println("User with username or email already exists.");
        }
    }

    public void deleteUser(int userID) {
    userList.removeIf(user -> user.getUserID() == userID);
    }

    private int generateUserID() {
        return userList.get(userList.size() - 1).getUserID() + 1;
    }

    public boolean isUsernameExists(String username) {
        return userList.stream().anyMatch(user -> user.getUsername().equalsIgnoreCase(username));
    }

    public boolean isEmailExists(String email) {
        return userList.stream().anyMatch(user -> user.getEmail().equalsIgnoreCase(email));
    }

}
