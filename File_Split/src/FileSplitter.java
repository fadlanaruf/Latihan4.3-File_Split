import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class FileSplitter {
    private Queue<String> linesQueue; // Queue untuk menyimpan baris-baris file

    public FileSplitter() {
        linesQueue = new LinkedList<>();
    }

    // Fungsi untuk membaca file dan menyimpan baris ke dalam queue
    public void readFile(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;

        while ((line = reader.readLine()) != null) {
            linesQueue.add(line); // Menambahkan setiap baris ke dalam queue
        }
        reader.close();
    }

    // Fungsi untuk memotong file menjadi bagian-bagian
    public void splitFile(int partSize) {
        int partNumber = 1;
        StringBuilder partContent = new StringBuilder();

        while (!linesQueue.isEmpty()) {
            // Ambil baris dari queue
            partContent.append(linesQueue.poll()).append(System.lineSeparator());

            // Jika ukuran bagian sudah mencapai batas yang ditentukan
            if (partContent.toString().split(System.lineSeparator()).length >= partSize) {
                savePart(partContent.toString(), partNumber++);
                partContent.setLength(0); // Reset StringBuilder untuk bagian berikutnya
            }
        }

        // Menyimpan sisa bagian jika ada
        if (partContent.length() > 0) {
            savePart(partContent.toString(), partNumber);
        }
    }

    // Fungsi untuk menyimpan bagian ke file
    private void savePart(String content, int partNumber) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("part" + partNumber + ".txt"))) {
            writer.write(content);
            System.out.println("Bagian " + partNumber + " disimpan.");
        } catch (IOException e) {
            System.err.println("Error saat menyimpan bagian: " + e.getMessage());
        }
    }
}
