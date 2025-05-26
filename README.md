# 📱 QuickChat - Simple Java Messaging Application

**Author:** Katleho Khutsoane
**Language:** Java
**IDE Recommended:** IntelliJ IDEA / Eclipse

---

## 🚀 Project Overview

**QuickChat** is a console-based Java application that allows users to:

* Register with a valid username, password, and phone number.
* Log in with a verified name and surname.
* Send personalized SMS-style messages to recipients.
* Generate and display secure message hashes.
* Choose to send, store, or disregard messages.
* View all messages sent during the session.

---

## 🧠 Key Concepts Demonstrated

* Object-Oriented Programming (OOP)
* Input Validation using Regular Expressions
* Java Swing (`JOptionPane`) for GUI dialog boxes
* Message Hashing (custom format)
* Dynamic Menu System with Loops
* Error Handling (e.g., `NumberFormatException`)
* Data Storage using `ArrayList`

---

## 📁 Project Structure

```
QuickChatProject/
│
├── Main.java        # Entry point: handles registration and login
├── Login.java       # Handles validation and storing registration info
└── Message.java     # Messaging logic, message ID/hash generation
```

---

## ✅ Registration Rules

| Field        | Requirement                                                                 |
| ------------ | --------------------------------------------------------------------------- |
| Username     | Must include an underscore (`_`) and be no more than 5 characters           |
| Password     | At least 8 characters, with a capital letter, number, and special character |
| Phone Number | Must start with `+` and be either 12 or 13 characters (e.g., +27712345678)  |

---

## 🔐 Login Rules

* Name and surname must only contain letters and be at least 2 characters.
* If valid, user sees a welcome message and accesses the messaging system.

---

## 💬 Messaging System

Once logged in, users can:

1. **Send Messages**

   * Enter recipient number (validated like phone number)
   * Enter message (must be 50 characters or fewer)
   * Choose to:

     * Send message
     * Store for later
     * Disregard

2. **View Sent Messages**

   * Currently shows "Coming Soon."

3. **Quit**

   * Exit the messaging system

---

## 🔐 Message Hash Format

Each message is assigned a **message hash** in the format:

```
First 2 digits of message ID : Message Number : FIRSTWORD : LASTWORD
```

✅ Example:
`92:1:HELLO:WORLD`

---

## 📊 Sample Output Screenshot

```
=== REGISTRATION ===
Please Enter your Username: kat_
Please Enter your Password: Pass@123
Please Enter your Cell Phone Number: +27718693002
The two above conditions have been met, and the user has been registered successfully.

=== LOGIN ===
Name: Katleho
Surname: Khutsoane
Welcome	Katleho, its is great to see you again.

=== QUICKCHAT MENU ===
Option 1) Send Messages
Option 2) Show recently sent messages- this feature is still in development...
Option 3) Quit
```

---

## 🔧 How to Run

1. **Open in IntelliJ or any IDE that supports Java**
2. Compile all files (`Main.java`, `Login.java`, `Message.java`)
3. Run `Main.java`
4. Follow prompts on the console

---

## 📌 Notes

* You can expand this project to include JSON storage, GUI enhancements, or database support in the future.
* The project simulates real-world validation rules for a strong introduction to user data handling.

---

## 📞 Contact

> Made by **Katleho Khutsoane**




