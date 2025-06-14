import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Message {
    // Arrays to store message information
    private ArrayList<String> messageIDs = new ArrayList<>();
    private ArrayList<String> messageHashes = new ArrayList<>();
    private ArrayList<String> recipients = new ArrayList<>();
    private ArrayList<String> messages = new ArrayList<>();
    private ArrayList<String> sendStatuses = new ArrayList<>();

    // Additional ArrayLists for Part 3 functionality
    private ArrayList<String> sentMessages = new ArrayList<>();
    private ArrayList<String> sentRecipients = new ArrayList<>();
    private ArrayList<String> disregardedMessages = new ArrayList<>();
    private ArrayList<String> storedMessages = new ArrayList<>();
    private ArrayList<String> storedRecipients = new ArrayList<>();

    private int numMessagesSent = 0;
    private int totalMessagesCreated = 0; // FIXED: Added for proper hash numbering
    private Random random = new Random();
    private boolean testDataPopulated = false; // FIXED: Prevent multiple population

    // Method to populate arrays with test data
    public void populateTestData() {
        if (testDataPopulated) return; // FIXED: Only populate once
        testDataPopulated = true;

        // Test Data from the assignment
        addTestMessage("+27834557896", "Did you get the cake?", "Sent");
        addTestMessage("+27838884567", "Where are you? You are late! I have asked you to be on time.", "Stored");
        addTestMessage("+27834484567", "Yohooooo, I am at your gate.", "Disregard");
        addTestMessage("0838884567", "It is dinner time!", "Sent"); // Note: This should be invalid format
        addTestMessage("+27838884567", "Ok, I am leaving without you.", "Stored");
    }

    // Helper method to add test messages
    private void addTestMessage(String recipient, String message, String status) {
        String messageID = generateMessageID();
        totalMessagesCreated++; // FIXED: Proper increment for hash
        String messageHash = createMessageHash(messageID, message);

        messageIDs.add(messageID);
        messageHashes.add(messageHash);
        recipients.add(recipient);
        messages.add(message);
        sendStatuses.add(status);

        // Populate specific arrays based on status
        if (status.equals("Sent")) {
            sentMessages.add(message);
            sentRecipients.add(recipient);
            numMessagesSent++;
        } else if (status.equals("Disregard")) {
            disregardedMessages.add(message);
        } else if (status.equals("Stored")) {
            storedMessages.add(message);
            storedRecipients.add(recipient);
        }
    }


    public void showMainMenu(Scanner scanner) {
        // Populate test data when menu starts
        populateTestData();

        while (true) {
            System.out.println("\n=== QUICKCHAT MENU ===");
            System.out.println("Option 1) Send Messages");
            System.out.println("Option 2) Display Sender and Recipient of All Sent Messages");
            System.out.println("Option 3) Display Longest Message");
            System.out.println("Option 4) Search for Message by ID");
            System.out.println("Option 5) Search Messages by Recipient");
            System.out.println("Option 6) Delete Message by Hash");
            System.out.println("Option 7) Display Full Report");
            System.out.println("Option 8) Create JSON File for Stored Messages");
            System.out.println("Option 9) Quit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    sendMessagesFlow(scanner);
                    break;
                case "2":
                    displaySenderAndRecipient();
                    break;
                case "3":
                    displayLongestMessage();
                    break;
                case "4":
                    searchByMessageID(scanner);
                    break;
                case "5":
                    searchByRecipient(scanner);
                    break;
                case "6":
                    deleteMessageByHash(scanner);
                    break;
                case "7":
                    displayFullReport();
                    break;
                case "8":
                    createJSONFile();
                    break;
                case "9":
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    // Send messages flow
    public void sendMessagesFlow(Scanner scanner) {
        System.out.print("How many messages do you wish to enter? ");
        String input = scanner.nextLine();
        int numberOfMessages;
        try {
            numberOfMessages = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            return;
        }

        for (int i = 0; i < numberOfMessages; i++) {
            System.out.println("\n--- Message " + (i + 1) + " ---");

            // Get recipient number
            System.out.print("Recipient Number: ");
            String recipientNumber = scanner.nextLine();

            // Validate recipient number
            if (!checkRecipientCell(recipientNumber)) {
                System.out.println("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.");
                i--; // retry this message
                continue;
            } else {
                System.out.println("Cell phone number successfully captured.");
            }

            // Get message content
            System.out.print("Message: ");
            String messageContent = scanner.nextLine();

            // Validate message length
            if (!checkMessageID(messageContent)) {
                System.out.println("Please enter a message of less than 50 characters.");
                i--; // retry this message
                continue;
            }

            // Generate message components
            String messageID = generateMessageID();
            totalMessagesCreated++; // FIXED: Proper increment
            String messageHash = createMessageHash(messageID, messageContent);

            // Store message data
            messageIDs.add(messageID);
            messageHashes.add(messageHash);
            recipients.add(recipientNumber);
            messages.add(messageContent);

            // Display message details using JOptionPane
            String messageDetails = "MessageID: " + messageID +
                    "\nMessage Hash: " + messageHash +
                    "\nRecipient: " + recipientNumber +
                    "\nMessage: " + messageContent;
            JOptionPane.showMessageDialog(null, messageDetails);

            // Ask user what to do with message
            String sendChoice = sendMessage();
            sendStatuses.add(sendChoice);

            // Update specific arrays based on choice and display appropriate message
            if (sendChoice.equals("Send Message")) {
                System.out.println("Message successfully sent.");
                sentMessages.add(messageContent);
                sentRecipients.add(recipientNumber);
                numMessagesSent++;
            } else if (sendChoice.equals("Disregard Message")) {
                System.out.println("Press 0 to delete message.");
                disregardedMessages.add(messageContent);
            } else if (sendChoice.equals("Store Message")) {
                storeMessage();
                System.out.println("Message successfully stored.");
                storedMessages.add(messageContent);
                storedRecipients.add(recipientNumber);
            }
        }

        // Display total messages sent
        System.out.println("\nTotal messages sent: " + returnTotalMessagesSent());

        // Print all messages
        System.out.println(printMessages());
    }

    // PART 3 METHODS:

    // 2a. Display the sender and recipient of all sent messages
    public void displaySenderAndRecipient() {
        System.out.println("\n=== SENT MESSAGES - SENDER AND RECIPIENT ===");
        if (sentMessages.isEmpty()) {
            System.out.println("No sent messages found.");
            return;
        }

        for (int i = 0; i < sentMessages.size(); i++) {
            System.out.println("Message " + (i + 1) + ":");
            System.out.println("Sender: Your System");
            System.out.println("Recipient: " + sentRecipients.get(i));
            System.out.println("Message: " + sentMessages.get(i));
            System.out.println("------------------------");
        }
    }

    // 2b. Display the longest sent message
    public void displayLongestMessage() {
        System.out.println("\n=== LONGEST SENT MESSAGE ===");
        if (sentMessages.isEmpty()) {
            System.out.println("No sent messages found.");
            return;
        }

        String longestMessage = "";
        String longestRecipient = "";

        for (int i = 0; i < sentMessages.size(); i++) {
            if (sentMessages.get(i).length() > longestMessage.length()) {
                longestMessage = sentMessages.get(i);
                longestRecipient = sentRecipients.get(i);
            }
        }

        System.out.println("Longest message (" + longestMessage.length() + " characters):");
        System.out.println("Recipient: " + longestRecipient);
        System.out.println("Message: " + longestMessage);
    }

    // 2c. Search for a message ID and display the corresponding recipient and message
    public void searchByMessageID(Scanner scanner) {
        System.out.println("\n=== SEARCH BY MESSAGE ID ===");
        System.out.print("Enter Message ID: ");
        String searchID = scanner.nextLine();

        for (int i = 0; i < messageIDs.size(); i++) {
            if (messageIDs.get(i).equals(searchID)) {
                System.out.println("Message found:");
                System.out.println("Message ID: " + messageIDs.get(i));
                System.out.println("Recipient: " + recipients.get(i));
                System.out.println("Message: " + messages.get(i));
                System.out.println("Status: " + sendStatuses.get(i));
                return;
            }
        }
        System.out.println("Message ID not found.");
    }

    // 2d. Search for all messages sent to a particular recipient
    public void searchByRecipient(Scanner scanner) {
        System.out.println("\n=== SEARCH BY RECIPIENT ===");
        System.out.print("Enter recipient phone number: ");
        String searchRecipient = scanner.nextLine();

        boolean found = false;
        System.out.println("Messages for recipient: " + searchRecipient);

        for (int i = 0; i < recipients.size(); i++) {
            if (recipients.get(i).equals(searchRecipient)) {
                found = true;
                System.out.println("Message ID: " + messageIDs.get(i));
                System.out.println("Message: " + messages.get(i));
                System.out.println("Status: " + sendStatuses.get(i));
                System.out.println("Hash: " + messageHashes.get(i));
                System.out.println("------------------------");
            }
        }

        if (!found) {
            System.out.println("No messages found for this recipient.");
        }
    }

    // 2e. Delete a message using the message hash
    public void deleteMessageByHash(Scanner scanner) {
        System.out.println("\n=== DELETE MESSAGE BY HASH ===");
        System.out.print("Enter message hash: ");
        String searchHash = scanner.nextLine();

        for (int i = 0; i < messageHashes.size(); i++) {
            if (messageHashes.get(i).equals(searchHash)) {
                System.out.println("Message found and will be deleted:");
                System.out.println("Message: " + messages.get(i));
                System.out.println("Recipient: " + recipients.get(i));

                // Store values before removal
                String messageToDelete = messages.get(i);
                String recipientToDelete = recipients.get(i);
                String status = sendStatuses.get(i);

                // Remove from main arrays
                messageIDs.remove(i);
                messageHashes.remove(i);
                recipients.remove(i);
                messages.remove(i);
                sendStatuses.remove(i);

                // Remove from specific status arrays
                if (status.equals("Sent")) {
                    sentMessages.remove(messageToDelete);
                    sentRecipients.remove(recipientToDelete);
                    numMessagesSent--;
                } else if (status.equals("Stored")) {
                    storedMessages.remove(messageToDelete);
                    storedRecipients.remove(recipientToDelete);
                } else if (status.equals("Disregard")) {
                    disregardedMessages.remove(messageToDelete);
                }

                System.out.println("Message successfully deleted.");
                return;
            }
        }
        System.out.println("Message hash not found.");
    }

    // 2f. Display a report that lists the full details of all messages
    public void displayFullReport() {
        System.out.println("\n=== FULL MESSAGE REPORT ===");
        System.out.println("Total messages in system: " + messageIDs.size());
        System.out.println("Messages sent: " + numMessagesSent);
        System.out.println("Messages stored: " + storedMessages.size());
        System.out.println("Messages disregarded: " + disregardedMessages.size());
        System.out.println("\n--- DETAILED REPORT ---");

        for (int i = 0; i < messageIDs.size(); i++) {
            System.out.println("Message " + (i + 1) + ":");
            System.out.println("Message Hash: " + messageHashes.get(i));
            System.out.println("Recipient: " + recipients.get(i));
            System.out.println("Message: " + messages.get(i));
            System.out.println("Message ID: " + messageIDs.get(i));
            System.out.println("Status: " + sendStatuses.get(i));
            System.out.println("------------------------");
        }
    }

    // Method to create JSON file for stored messages
    public void createJSONFile() {
        try {
            FileWriter file = new FileWriter("stored_messages.json");
            file.write("{\n");
            file.write("  \"stored_messages\": [\n");

            for (int i = 0; i < storedMessages.size(); i++) {
                file.write("    {\n");
                file.write("      \"message\": \"" + storedMessages.get(i) + "\",\n");
                file.write("      \"recipient\": \"" + storedRecipients.get(i) + "\"\n");
                file.write("    }");
                if (i < storedMessages.size() - 1) {
                    file.write(",");
                }
                file.write("\n");
            }

            file.write("  ]\n");
            file.write("}");
            file.close();
            System.out.println("JSON file created successfully: stored_messages.json");
        } catch (IOException e) {
            System.out.println("Error creating JSON file: " + e.getMessage());
        }
    }

    // VALIDATION AND UTILITY METHODS:

    // Method to ensure message ID (message length) is not more than 50 characters
    public static boolean checkMessageID(String message) {
        return message.length() <= 50;
    }

    // Method to ensure recipient cell number is correctly formatted
    public static boolean checkRecipientCell(String cellNumber) {
        // Check if it's 12-13 characters, starts with +, and contains only digits after
        if (cellNumber.length() >= 12 && cellNumber.length() <= 13 &&
                cellNumber.startsWith("+") &&
                cellNumber.substring(1).matches("\\d{11,12}")) {
            return true;
        }
        return false;
    }

   //Method to create and return Message Hash
    public String createMessageHash(String messageID, String message) {
        String[] words = message.trim().split("\\s+");
        String firstWord = words.length > 0 ? words[0] : "";
        String lastWord = words.length > 1 ? words[words.length - 1] : firstWord;

// Use totalMessagesCreated for proper numbering
        String hash = messageID.substring(0, 2) + ":" + totalMessagesCreated + ":" +
                firstWord.toUpperCase() + ":" + lastWord.toUpperCase();
        return hash;
    }

    // Method to allow user to choose send, store, or disregard
    public String sendMessage() {
        String[] options = {"Send Message", "Disregard Message", "Store Message to send later"};
        int choice = JOptionPane.showOptionDialog(null,
                "What would you like to do with this message?",
                "Message Options",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);

        switch (choice) {
            case 0: return "Send Message";
            case 1: return "Disregard Message";
            case 2: return "Store Message";
            default: return "Send Message";
        }
    }

    // Method to return list of all messages
    public String printMessages() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n=== ALL MESSAGES ===\n");

        for (int i = 0; i < messageIDs.size(); i++) {
            sb.append("MessageID: ").append(messageIDs.get(i)).append("\n");
            sb.append("Message Hash: ").append(messageHashes.get(i)).append("\n");
            sb.append("Recipient: ").append(recipients.get(i)).append("\n");
            sb.append("Message: ").append(messages.get(i)).append("\n");
            sb.append("Status: ").append(sendStatuses.get(i)).append("\n");
            sb.append("------------------------\n");
        }

        return sb.toString();
    }

    // Method to return total number of messages sent
    public int returnTotalMessagesSent() {
        return numMessagesSent;
    }

    // Helper method to generate random 10-digit message ID
    private String generateMessageID() {
        StringBuilder id = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            id.append(random.nextInt(10));
        }
        return id.toString();
    }

    // Store message method.
    public void storeMessage() {
        // Messages are stored in memory via ArrayLists
        System.out.println("Messages stored in JSON format (simulated)");
    }

    // Main method to run the application
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Message messageSystem = new Message();
        messageSystem.showMainMenu(scanner);
        scanner.close();
    }
}