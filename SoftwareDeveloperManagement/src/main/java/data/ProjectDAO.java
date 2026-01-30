/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;
import model.Project;
import utils.DateUtils; // Import công cụ xử lý ngày
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
/**
 *
 * @author rechiee
 */
public class ProjectDAO implements IFileReadWrite<Project> {
    private final String FILE_NAME = "src/files/projects.txt";

    @Override
    public List<Project> readData() {
        List<Project> list = new ArrayList<>();
        File f = new File(FILE_NAME);

        if (!f.exists()) return list;

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                String[] p = line.split(",");
                
                // File Project chuẩn có 5 phần tử: ID, Name, Start, End, Cost
                if (p.length >= 5) {
                    try {
                        String id = p[0].trim();
                        String name = p[1].trim();
                        
                        // Parse ngày tháng (Dùng DateUtils từ bài Homestay)
                        Date start = DateUtils.parseDate(p[2].trim());
                        Date end = DateUtils.parseDate(p[3].trim());
                        
                        double cost = Double.parseDouble(p[4].trim());

                        // Chỉ add nếu ngày tháng hợp lệ
                        if (start != null && end != null) {
                            list.add(new Project(id, name, start, end, cost));
                        } else {
                            System.err.println("⚠️ Invalid Date format in line: " + line);
                        }
                        
                    } catch (NumberFormatException e) {
                        System.err.println("❌ Number Error (Line): " + line);
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("❌ Read Project Error: " + e.getMessage());
        }
        return list;
    }

    @Override
    public void writeData(List<Project> list) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Project p : list) {
                pw.println(p.toString());
            }
            System.out.println("✅ Saved to projects.txt");
        } catch (IOException e) {
            System.err.println("❌ Save Error: " + e.getMessage());
        }
    }
}
