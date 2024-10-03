package Java_Project;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class Contact {
    private String name;
    private String phoneNumber;

    public Contact(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Phone: " + phoneNumber;
    }
}

public class project {
    private final ArrayList<Contact> phoneBook = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        project pb = new project();
        pb.run();
    }

    public void run() {
        boolean exit = false;
        while (!exit) {
            System.out.println("\nPhone Book Menu:");
            System.out.println("1. Add Contact");
            System.out.println("2. List Contacts");
            System.out.println("3. Modify Contact");
            System.out.println("4. Search Contact");
            System.out.println("5. Delete Contact");
            System.out.println("6. Export Contacts to File");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addContact();
                    break;
                case 2:
                    listContacts();
                    break;
                case 3:
                    modifyContact();
                    break;
                case 4:
                    searchContact();
                    break;
                case 5:
                    deleteContact();
                    break;
                case 6:
                    exportContactsToFile();
                    break;
                case 7:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private void addContact() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter phone number: ");
        String phone = scanner.nextLine();
        phoneBook.add(new Contact(name, phone));
        System.out.println("Contact added.");
    }

    private void listContacts() {
        if (phoneBook.isEmpty()) {
            System.out.println("Phone book is empty.");
        } else {
            for (int i = 0; i < phoneBook.size(); i++) {
                System.out.println((i + 1) + ". " + phoneBook.get(i));
            }
        }
    }

    private void modifyContact() {
        System.out.print("Enter contact number to modify: ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine();
        if (index >= 0 && index < phoneBook.size()) {
            System.out.print("Enter new name: ");
            String newName = scanner.nextLine();
            System.out.print("Enter new phone number: ");
            String newPhone = scanner.nextLine();
            phoneBook.get(index).setName(newName);
            phoneBook.get(index).setPhoneNumber(newPhone);
            System.out.println("Contact modified.");
        } else {
            System.out.println("Invalid contact number.");
        }
    }

    private void searchContact() {
        System.out.print("Enter name to search: ");
        String name = scanner.nextLine();
        boolean found = false;
        for (Contact contact : phoneBook) {
            if (contact.getName().equalsIgnoreCase(name)) {
                System.out.println(contact);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Contact not found.");
        }
    }

    private void deleteContact() {
        System.out.print("Enter contact number to delete: ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine();
        if (index >= 0 && index < phoneBook.size()) {
            phoneBook.remove(index);
            System.out.println("Contact deleted.");
        } else {
            System.out.println("Invalid contact number.");
        }
    }

    private void exportContactsToFile() {
        try (FileWriter writer = new FileWriter("contacts.txt")) {
            for (Contact contact : phoneBook) {
                writer.write(contact + "\n");
            }
            System.out.println("Contacts exported to contacts.txt.");
        } catch (IOException e) {
            System.out.println("Error exporting contacts: " + e.getMessage());
        }
    }
}
