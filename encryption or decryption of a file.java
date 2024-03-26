import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileEncryptionDecryption {

    public static String encrypt(String text) {
        StringBuilder encryptedText = new StringBuilder();
        for (char c : text.toCharArray()) {
            encryptedText.append((char) (c + 1));
        }
        return encryptedText.toString();
    }

    public static String decrypt(String text) {
        StringBuilder decryptedText = new StringBuilder();
        for (char c : text.toCharArray()) {
            decryptedText.append((char) (c - 1));
        }
        return decryptedText.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose an option:");
        System.out.println("1. Encrypt");
        System.out.println("2. Decrypt");
        System.out.print("Enter your choice (1 or 2): ");
        String choice = scanner.nextLine();

        if (!choice.equals("1") && !choice.equals("2")) {
            System.out.println("Invalid choice.");
            return;
        }

        System.out.print("Enter the name or path of the file: ");
        String filename = scanner.nextLine();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }

            String result;
            if (choice.equals("1")) {
                result = encrypt(content.toString());
                filename = "encrypted_" + filename;
            } else {
                result = decrypt(content.toString());
                filename = "decrypted_" + filename;
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
                writer.write(result);
                System.out.println("File " + (choice.equals("1") ? "encrypted" : "decrypted") + " and saved as: " + filename);
            } catch (IOException e) {
                System.out.println("Error writing to file: " + e.getMessage());
            }

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
