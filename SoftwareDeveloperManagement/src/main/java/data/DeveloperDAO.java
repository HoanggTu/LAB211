/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;
import model.Developer;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author rechiee
 */
public class DeveloperDAO implements IFileReadWrite<Developer> {
    private final String FILE_NAME = "src/files/developers.txt"; // Đường dẫn file

    @Override
    public List<Developer> readData() {
        List<Developer> list = new ArrayList<>();
        File f = new File(FILE_NAME);

        // Kiểm tra file có tồn tại không
        if (!f.exists()) {
            System.err.println("⚠️ File not found: " + FILE_NAME + ". Creating new list.");
            return list;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue; // Bỏ qua dòng trống

                // Cắt chuỗi bằng dấu phẩy
                String[] p = line.split(","); 
                
                // File Developer chuẩn có 5 phần tử: ID, Name, Skills, Exp, Salary
                if (p.length >= 5) {
                    try {
                        String id = p[0].trim();
                        String name = p[1].trim();
                        String skills = p[2].trim();
                        int exp = Integer.parseInt(p[3].trim());      // Parse số năm kinh nghiệm
                        double salary = Double.parseDouble(p[4].trim()); // Parse lương

                        list.add(new Developer(id, name, skills, exp, salary));
                    } catch (NumberFormatException e) {
                        System.err.println("❌ Data Error (Line): " + line + " -> " + e.getMessage());
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("❌ Read File Error: " + e.getMessage());
        }
        return list;
    }

    @Override
    public void writeData(List<Developer> list) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Developer d : list) {
                // Ghi đúng định dạng toString() bên Model
                pw.println(d.toString());
            }
            System.out.println("✅ Saved to developers.txt");
        } catch (IOException e) {
            System.err.println("❌ Save Error: " + e.getMessage());
        }
    }
}