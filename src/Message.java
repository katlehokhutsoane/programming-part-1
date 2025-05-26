 // Message.java (Part 2 - Sending Messages)
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

        private int numMessagesSent = 0;
        private Random random = new Random();

        // Main menu for messaging system
        public void showMainMenu(Scanner scanner) {
            while (true) {
                System.out.println("\n=== QUICKCHAT MENU ===");
                System.out.println("Option 1) Send Messages");
                System.out.println("Option 2) Show recently sent messages- this feature is still in development and should display the following message: \"Coming Soon.\"");
                System.out.println("Option 3) Quit");
                System.out.print("Choose an option: ");

               // int choice = scanner.nextInt();
                //scanner.nextLine(); // consume newline

                String choice=scanner.nextLine().trim();
                switch (choice) {
                    case "1":
                        sendMessagesFlow(scanner);
                        break;
                    case "2":
                        System.out.println("Coming Soon.");
                        break;
                    case "3":
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
                return; // Exit the method if invalid
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

                // Display appropriate message based on choice
                if (sendChoice.equals("Send Message")) {
                    System.out.println("Message successfully sent.");
                    numMessagesSent++;
                } else if (sendChoice.equals("Disregard Message")) {
                    System.out.println("Press 0 to delete message.");
                } else if (sendChoice.equals("Store Message")) {
                    storeMessage();
                    System.out.println("Message successfully stored.");
                }
            }

            // Display total messages sent
            System.out.println("\nTotal messages sent: " + returnTotalMessagesSent());

            // Print all messages
            System.out.println(printMessages());
        }

        // Method to ensure message ID (message length) is not more than 50 characters
        public static boolean checkMessageID(String message) {
            return message.length() <= 50;
        }

        // Method to ensure recipient cell number is no more than 10 characters and starts with international code
        public static boolean checkRecipientCell(String cellNumber) {
            // Check if it's exactly 12 characters (international format like +27718693002)
            // Or if it starts with + and contains only digits after
            if (cellNumber.length() >= 12 && cellNumber.length() <= 13 &&
                    cellNumber.startsWith("+") &&
                    cellNumber.substring(1).matches("\\d{11,12}")) {
                return true;
            }
            return false;
        }

        // Method to create and return Message Hash
        public String createMessageHash(String messageID, String message) {
            String[] words = message.trim().split("\\s+");
            String firstWord = words.length > 0 ? words[0] : "";
            String lastWord = words.length > 1 ? words[words.length - 1] : firstWord;

            int messageNumber=numMessagesSent +1;
            // Format: first two digits of messageID : messageNumber : firstWord : lastWord
            String hash = messageID.substring(0, 2) + ":" + messageNumber + ":" +
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

        // Method to return list of all messages sent while program is running
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

        // Store message method (using simple file writing concept)
        public void storeMessage() {
            // As per requirement, this would use ChatGPT to help create JSON storage
            // For now, we'll just store in memory as the messages are already stored in ArrayLists
            System.out.println("Messages stored in JSON format (simulated)");
        }
    }

