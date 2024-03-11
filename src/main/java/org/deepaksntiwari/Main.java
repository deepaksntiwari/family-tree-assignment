package org.deepaksntiwari;

import org.deepaksntiwari.managers.FamilyTreeManager;
import org.deepaksntiwari.models.Person;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        FamilyTreeManager treeManager = new FamilyTreeManager();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Family Tree Manager!");

        while (true) {
            System.out.println("\nEnter a command (e.g., family-tree add person <name>):");
            String command = scanner.nextLine();
            if (command.equalsIgnoreCase("exit")) {
                System.out.println("Exiting...");
                break;
            }
            executeCommand(command, treeManager);
        }
        scanner.close();
    }

    private static void executeCommand(String command, FamilyTreeManager treeManager) {
        String[] parts = command.split(" ");
        if (parts.length < 4) {
            System.out.println("Invalid command format.");
            return;
        }

        String operation = parts[1];

        switch (operation) {
            case "add":
                handleAddCommand(parts, treeManager);
                break;
            case "connect":
                handleConnectCommand(parts, treeManager);
                break;
            case "count":
                handleQuery(parts, treeManager);
                break;
            default:
                System.out.println("Invalid operation.");
                break;
        }
    }

    private static void handleAddCommand(String[] parts, FamilyTreeManager treeManager) {
        String entity = parts[2];


        switch (entity) {
            case "relationship": String name = parts[3];
                treeManager.addRelationship(name);
                System.out.println("Added relationship: " + name);
                break;
            default:
                 name = parts[2]+" "+parts[3];
                treeManager.addPerson(new Person(name));
                System.out.println("Added person: " + name);
                break;
        }
    }

    private static void handleConnectCommand(String[] parts, FamilyTreeManager treeManager) {
        String name1 = parts[2]+" "+parts[3];
        String relationship = parts[5];
        String name2 = parts[7] + " " + parts[8]; // Handling multiple word names

        treeManager.connect(name1, relationship, name2);
        System.out.println("Connected " + name1 + " as " + relationship + " of " + name2);
    }

    private static void handleQuery(String[] parts, FamilyTreeManager treeManager) {
        String name = parts[4] + " " + parts[5]; // Handling multiple word names
                String objectType = parts[2];
                switch (objectType) {
                    case "sons":
                        System.out.println("Count of sons of " + name + ": " + treeManager.countSons(new Person(name)));
                        break;
                    case "daughters":
                        System.out.println("Count of daughters of " + name + ": " + treeManager.countDaughters(new Person(name)));
                        break;
                    case "wives":
                        System.out.println("Count of wives of " + name + ": " + treeManager.countWives(new Person(name)));
                        break;
                    default:
                        System.out.println("Invalid object type for count operation.");
                        break;
                }
    }
}
