package com.jdbc;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner ds = new Scanner(System.in);
        UserDAO dao = new UserDAOImpl();

        while (true) {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Add User");
            System.out.println("2. Get User by ID");
            System.out.println("3. Get All Users");
            System.out.println("4. Update User");
            System.out.println("5. Delete User");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = ds.nextInt();
            ds.nextLine(); 
            switch (choice) {
                case 1:
                    System.out.print("Enter name: ");
                    String name = ds.nextLine().trim();

                    System.out.print("Enter email: ");
                    String email = ds.nextLine().trim();

                    User newUser = new User();
                    newUser.setName(name);
                    newUser.setEmail(email);
                    dao.addUser(newUser);
                    System.out.println("User added successfully.");
                    break;

                case 2:
                    System.out.print("Enter user ID: ");
                    int getId = ds.nextInt();
                    ds.nextLine();
                    User fetched = dao.getUserById(getId);
                    if (fetched != null) {
                        System.out.println("User: " + fetched.getName() + " - " + fetched.getEmail());
                    } else {
                        System.out.println("User not found.");
                    }
                    break;

                case 3:
                    List<User> users = dao.getAllUsers();
                    System.out.println("\nAll Users:");
                    for (User u : users) {
                        System.out.println(u.getId() + ": " + u.getName() + " - " + u.getEmail());
                    }
                    break;

                case 4:
                    System.out.print("Enter user ID to update: ");
                    int updateId = ds.nextInt();
                    ds.nextLine();

                    User userToUpdate = dao.getUserById(updateId);
                    if (userToUpdate != null) {
                        System.out.print("Enter new name: ");
                        String newName = ds.nextLine().trim();

                        System.out.print("Enter new email: ");
                        String newEmail = ds.nextLine().trim();

                        userToUpdate.setName(newName);
                        userToUpdate.setEmail(newEmail);

                        dao.updateUser(userToUpdate);
                        System.out.println("User updated successfully.");
                    } else {
                        System.out.println("User not found.");
                    }
                    break;

                case 5:
                    System.out.print("Enter user ID to delete: ");
                    int deleteId = ds.nextInt();
                    ds.nextLine();

                    dao.deleteUser(deleteId);
                    System.out.println("User deleted if existed.");
                    break;

                case 6:
                    System.out.println("Exiting... Goodbye!");
                    ds.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
