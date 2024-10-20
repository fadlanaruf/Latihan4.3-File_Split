import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FileSplitter fileSplitter = new FileSplitter();

        System.out.print("Masukkan path file yang ingin dibaca: ");
        String filePath = scanner.nextLine();

        System.out.print("Masukkan jumlah baris per bagian: ");
        int partSize = scanner.nextInt();

        try {
            // Membaca file
            fileSplitter.readFile(filePath);
            // Memotong file
            fileSplitter.splitFile(partSize);
        } catch (IOException e) {
            System.err.println("Error saat membaca file: " + e.getMessage());
        }

        scanner.close();
    }
}