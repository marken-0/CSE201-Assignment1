package com.mycompany.app;

import java.time.Instant;
import java.time.Duration;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class assignment{
    public static void main(String args[]) {
        Database db = new Database();
        Scanner scn = new Scanner(System.in);
        System.out.println("Welcome to Library Management System :)");
        do {
            System.out.println("1. Enter as Librarian");
            System.out.println("2. Enter as Member");
            System.out.println("3. Exit");
            System.out.println("---------------*****---------------");
            System.out.print("Enter choice: ");
            int choice = scn.nextInt();
            scn.nextLine();
            System.out.println("---------------*****---------------");


            if (choice == 1) {
                System.out.println("(^_^) Welcome Librarian (^_^)");
                while (true){
                    System.out.println("---------------*****---------------");
                    System.out.println("1. Register Member");
                    System.out.println("2. Remove Member");
                    System.out.println("3. Add Book");
                    System.out.println("4. Remove Book");
                    System.out.println("5. List Member and Fine Details");
                    System.out.println("6. List Books");
                    System.out.println("7. Back");
                    System.out.println("---------------*****---------------");
                    System.out.print("Enter choice: ");

                    int choice2 = scn.nextInt();
                    scn.nextLine();

                    if (choice2 == 1) {
                        db.addMember();
                    } else if (choice2 == 2) {
                        db.removeMember();
                    } else if (choice2 == 3) {
                        db.addBook();
                    } else if (choice2 == 4) {
                        db.removeBook();
                    } else if (choice2 == 5) {
                        db.listMembers();
                    } else if (choice2 == 6) {
                        db.listBooks();
                    } else if (choice2 == 7) {
                        break;
                    }
                    else {
                        System.out.println("Invalid choice");
                    }
                }
            } 
            else if (choice == 2) {
                if (db.getTotalBooks() == 0) {
                    System.out.println("No members registered");
                    continue;
                }
                Member loggedMember = db.enterAsMember();
                if (loggedMember == null) {
                    continue;
                }

                while (true) {
                    System.out.println("---------------*****---------------");
                    System.out.println("1. List Available Books");
                    System.out.println("2. List My Books");
                    System.out.println("3. Issue Book");
                    System.out.println("4. Return Book");
                    System.out.println("5. Calculate and Pay Fine");
                    System.out.println("6. Back");
                    System.out.println("---------------*****---------------");
                    System.out.print("Enter choice: ");


                    int choice2 = scn.nextInt();
                    scn.nextLine();

                    if (choice2 == 1) {
                        db.listAvailableBooks();
                    } else if (choice2 == 2) {
                        if (loggedMember.getTotalIssuedBooks() == 0) {
                            System.out.println("No books issued");
                            continue;
                        }
                        loggedMember.printIssuedBooks();
                    } else if (choice2 == 3) {
                        db.listAvailableBooks();
                        System.out.print("Enter book ID: "); String bookId = scn.nextLine();
                        for (int i = 0; i < db.getTotalBooks(); i++) {
                            if (String.valueOf(db.books[i].getBookId()).equals(bookId)) {
                                loggedMember.issueBook(db.books[i]);
                                break;
                            }
                        }
                    } else if (choice2 == 4) {
                        if (loggedMember.getTotalIssuedBooks() == 0) {
                            System.out.println("No books issued");
                            continue;
                        }
                        loggedMember.printIssuedBooks();
                        System.out.print("Enter book ID: "); String bookId = scn.nextLine();
                        for (int i = 0; i < db.getTotalBooks(); i++) {
                            if (String.valueOf(db.books[i].getBookId()).equals(bookId)) {
                                loggedMember.returnBook(db.books[i]);
                                break;
                            }
                        }
                    } else if (choice2 == 5) {
                        if (loggedMember.getTotalIssuedBooks() == 0) {
                            System.out.println("No books issued");
                            continue;
                        }

                        loggedMember.setPenaltyAmount();
                        System.out.println("Fine: " + loggedMember.getFine());
                        System.out.println("Pay fine? (y/n)");
                        String choice3 = scn.nextLine();
                        if (choice3.equals("y")) {
                            System.out.println("Fine paid");
                            loggedMember.setFine(0);
                        }
                    } else if (choice2 == 6) {
                        break;
                    }
                    else {
                        System.out.println("Invalid choice");
                    }
                }
            } else if (choice == 3) {
                System.out.println("Thank you for using our system :)");
                break;
            } else {
                System.out.println("Invalid choice");
            }

        }
        while(true);
        scn.close();
    }

}

class Book {
    private static final AtomicInteger id = new AtomicInteger(1);
    private int bookId;
    private String title;
    private String author;
    private boolean isIssued;
    private Instant issuedAt;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.bookId = id.getAndIncrement();
        this.isIssued = false;
    }

    public Instant getIssuedAt() {
        return this.issuedAt;
    }

    public void setIssuedAt(Instant issuedAt) {
        this.issuedAt = issuedAt;
    }

    public int getBookId() {
        return this.bookId;
    }

    public boolean getIssueStatus() {
        return this.isIssued;
    }

    public void setIssueStatus(boolean isIssued) {
        this.isIssued = isIssued;
    }

    public void printBookDetails() {
        System.out.println("---------------*****---------------");
        System.out.println("Book ID: " + this.bookId);
        System.out.println("Title: " + this.title);
        System.out.println("Author: " + this.author);
        System.out.println("Issued: " + this.isIssued);
        System.out.println("---------------*****---------------");
        System.out.println();
    }
}

class Member {
    private static final AtomicInteger id = new AtomicInteger(1);
    private int memberId;
    private String name;
    private String age;
    private String phoneNumber; // this is the unique id
    private int fine;
    private final int bookLimit = 2;
    private Book [] booksIssued = new Book[bookLimit];
    private int totalBooksIssued;

    public Member(String name, String age, String phoneNumber) {
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.fine = 0;
        this.memberId = id.getAndIncrement();
    }

    public int getTotalIssuedBooks() {
        return this.totalBooksIssued;
    }

    public void setFine(int fine) {
        this.fine = fine;
    }

    public int getFine() {
        return this.fine;
    }

    private void setFine(){
        for (int i = 0; i < this.totalBooksIssued; i++){
            Instant now = Instant.now();
            Duration diff = Duration.between(this.booksIssued[i].getIssuedAt(), now);
            if (diff.toSeconds() > 10) {
                this.fine += 3 * (diff.toSeconds() - 10);
            }
        }
    }

    public void setPenaltyAmount() {
        this.setFine();
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public String getName() {
        return this.name;
    }

    public void issueBook(Book book) {
        this.setPenaltyAmount();
        if (this.totalBooksIssued == this.bookLimit) {
            System.out.println("Book limit reached");
            return;
        }
        if (book.getIssueStatus()) {
            System.out.println("Book already issued");
            return;
        }
        if (this.fine > 0) {
            System.out.println("Pay fine first");
            return;
        }
        book.setIssueStatus(true);
        book.setIssuedAt(Instant.now());
        this.booksIssued[this.totalBooksIssued++] = book;
        System.out.println("Book issued successfully");
    }

    public void returnBook(Book book) {
        if (!book.getIssueStatus()) {
            System.out.println("Book not issued");
            return;
        }
        book.setIssueStatus(false);
        book.setIssuedAt(null);
        for (int i = 0; i < this.booksIssued.length; i++) {
            if (this.booksIssued[i].getBookId() == book.getBookId()) {
                this.booksIssued[i] = this.booksIssued[this.totalBooksIssued - 1];
                this.totalBooksIssued--;
                break;
            }
        } 
        System.out.println("Book returned successfully");
    }

    public void printIssuedBooks() {
        if (this.totalBooksIssued == 0) {
            return;
        }
        for (int i = 0; i < this.totalBooksIssued; i++) {
            this.booksIssued[i].printBookDetails();
        }
    }

    public void printMemberDetails() {
        this.setPenaltyAmount();
        System.out.println("---------------*****---------------");
        System.out.println("Name: " + this.name);
        System.out.println("Age: " + this.age);
        System.out.println("Phone Number: " + this.phoneNumber);
        System.out.println("Fine: " + this.fine);
        System.out.println("---------------*****---------------");
        System.out.println();
    }
}

class Database{
    public Book[] books;
    public Member[] members;
    private int totalBooks;
    private int totalMembers;

    public Database() {
        this.books = new Book[1000000];
        this.members = new Member[1000000];
        this.totalBooks = 0;
        this.totalMembers = 0;
    }

    public int getTotalBooks() {
        return this.totalBooks;
    }

    public Member enterAsMember(){
        Scanner scn = new Scanner(System.in);
        System.out.print("Enter name: "); String name = scn.nextLine();
        System.out.print("Enter phone number: "); String phoneNumber = scn.nextLine();
        for (int i = 0; i < this.totalMembers; i++) {
            if (this.members[i].getPhoneNumber().equals(phoneNumber) && this.members[i].getName().equals(name)) {
                System.out.println("(^_^) Welcome " + this.members[i].getName() + " (^_^)");
                return this.members[i];
            }
            else if (this.members[i].getPhoneNumber().equals(phoneNumber) && !this.members[i].getName().equals(name)) {
                System.out.println("Invalid name");
                return null;
            }
            else if (!this.members[i].getPhoneNumber().equals(phoneNumber) && this.members[i].getName().equals(name)) {
                System.out.println("Invalid phone number");
                return null;
            }
        }
        System.out.println("Member not registered");
        return null;
    }

    public void addBook() {
        Scanner scn = new Scanner(System.in);
        System.out.print("Enter title: "); String title = scn.nextLine();
        System.out.print("Enter author: "); String author = scn.nextLine();
        System.out.print("Enter total copies: "); int totalCopies = scn.nextInt(); scn.nextLine();
        for (int i = 0; i < totalCopies; i++) {
            this.books[this.totalBooks++] = new Book(title, author);
        }
    }

    public void removeBook() {
        if (this.totalBooks == 0) {
            System.out.println("No books available");
            return;
        }

        Scanner scn = new Scanner(System.in);
        System.out.println("Enter book ID: "); String bookId = scn.nextLine();
        for (int i = 0; i < this.totalBooks; i++) {
            if (String.valueOf(this.books[i].getBookId()).equals(bookId) && !this.books[i].getIssueStatus()) {
                this.books[i] = this.books[this.totalBooks - 1];
                this.totalBooks--;
                break;
            }
            else if (String.valueOf(this.books[i].getBookId()).equals(bookId) && this.books[i].getIssueStatus()) {
                System.out.println("Book is issued");
                return;
            }
            else {
                System.out.println("Book not found");
                return;
            }
        }
    }

    public void addMember() {
        Scanner scn = new Scanner(System.in);
        System.out.print("Enter name: "); String name = scn.nextLine();
        System.out.print("Enter age: "); String age = scn.nextLine();
        System.out.print("Enter phone number: "); String phoneNumber = scn.nextLine();

        for (int i = 0; i < this.totalMembers; i++) {
            if (this.members[i].getPhoneNumber().equals(phoneNumber) ) {
                System.out.println("Member already registered");
                return;
            }
        }

        this.members[this.totalMembers++] = new Member(name, age, phoneNumber);
        System.out.println("Member registered successfully :)");
    }

    public void removeMember() {
        if (this.totalMembers == 0) {
            System.out.println("No members registered");
            return;
        }

        Scanner scn = new Scanner(System.in);
        System.out.print("Enter phone number: "); String phoneNumber = scn.nextLine();
        for (int i = 0; i < this.totalMembers; i++) {
            if (this.members[i].getPhoneNumber().equals(phoneNumber)) {
                this.members[i] = this.members[this.totalMembers - 1];
                this.totalMembers--;
                break;
            }
        }
    }

    public void listBooks() {
        if (this.totalBooks == 0) {
            System.out.println("No books available");
        }
        for (int i = 0; i < this.totalBooks; i++) {
            this.books[i].printBookDetails();
        }
        System.out.println("Printed Details Successfully");
    }

    public void listAvailableBooks() {
        if (this.totalBooks == 0) {
            System.out.println("No books available");
        }
        for (int i = 0; i < this.totalBooks; i++) {
            if (!this.books[i].getIssueStatus()) {
                this.books[i].printBookDetails();
            }
        }
    }

    public void listMembers() {
        if (this.totalMembers == 0) {
            System.out.println("No members registered");
        }
        for (int i = 0; i < this.totalMembers; i++) {
            this.members[i].printMemberDetails();
        }
    }

}