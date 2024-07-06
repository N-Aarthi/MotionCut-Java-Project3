package expensetracker;

import java.io.*;
import java.util.HashMap;

public class UserManager {
    private HashMap<String, User> users;
    private final String fileName = "users.dat";

    public UserManager() {
        users = new HashMap<>();
        loadUsers();
    }

    public void registerUser(String username, String password) {
        if (users.containsKey(username)) {
            System.out.println("Username already exists!");
        } else {
            users.put(username, new User(username, password));
            saveUsers();
            System.out.println("User registered successfully!");
        }
    }

    public User loginUser(String username, String password) {
        if (users.containsKey(username) && users.get(username).getPassword().equals(password)) {
            return users.get(username);
        } else {
            System.out.println("Invalid username or password!");
            return null;
        }
    }

    private void saveUsers() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadUsers() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            users = (HashMap<String, User>) ois.readObject();
        } catch (FileNotFoundException e) {
        
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
