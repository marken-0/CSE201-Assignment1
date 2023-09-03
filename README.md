# Library Management System

This Java project implements a simple Library Management System with a command-line interface. It allows users to interact with the system as either a librarian or a member. The system enables librarians to manage books and members, while members can browse available books, issue and return books, and pay fines if necessary.

## Table of Contents

- [Features](#features)
- [Prerequisites](#prerequisites)
- [How to Run](#how-to-run)
- [Menu Functions](#menu-functions)
  - [Librarian Menu](#librarian-menu)
  - [Member Menu](#member-menu)

## Features

- Librarian and Member login options.
- Librarian functions: Register Member, Remove Member, Add Book, Remove Book, List Member and Fine Details, List Books.
- Member functions: List Available Books, List My Books, Issue Book, Return Book, Calculate and Pay Fine.
- Book and Member data storage.
- Fine calculation based on the return date of books.
- Simple command-line interface.

## Prerequisites

Before running the project, ensure you have the following installed:

- Java Development Kit (JDK)
- Apache Maven

## How to Run

1. Clone/Extract the repository to your local machine:

   ```
   git clone <https://github.com/marken-0/CSE201-Assignment1.git>
   ```

2. Navigate to the project directory:

   ```
   cd <project_directory>
   ```

3. Build the project using Maven:

   ```
   mvn clean
   mvn install
   mvn compile

   ```

4. Run the jar file

## Menu Functions

### Librarian Menu

- **Register Member**: Allows librarians to register a new member by providing their name, age, and phone number. Members are uniquely identified by their phone numbers.

- **Remove Member**: Allows librarians to remove a registered member by specifying their phone number.

- **Add Book**: Lets librarians add books to the library's collection. They must provide the title, author, and the total number of copies to add.

- **Remove Book**: Allows librarians to remove a book from the library's collection, provided that it is not currently issued to a member.

- **List Member and Fine Details**: Displays a list of registered members along with their fine amounts if applicable.

- **List Books**: Lists all the books in the library's collection, including details such as Book ID, Title, Author, and Issue Status.

### Member Menu

- **List Available Books**: Displays a list of books that are currently available for members to borrow.

- **List My Books**: Shows the books currently issued to the logged-in member.

- **Issue Book**: Allows members to borrow a book from the library. Members need to specify the book ID to borrow it.

- **Return Book**: Lets members return a book they have borrowed by specifying the book ID.

- **Calculate and Pay Fine**: Calculates and displays the fine amount for overdue books. Members can choose to pay the fine (if any) or not.

- **Back**: Takes the user back to the main menu.

Note: If a member has overdue books, they must pay the fine before borrowing more books.

Enjoy using the Library Management System!