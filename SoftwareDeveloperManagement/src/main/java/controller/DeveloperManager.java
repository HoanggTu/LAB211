/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import data.DeveloperDAO;
import model.Developer;
import utils.Inputter;
import java.util.List;
import java.util.Collections;
/**
 *
 * @author rechiee
 */
public class DeveloperManager {
    private List<Developer> devList;
    private DeveloperDAO dao;

    public DeveloperManager() {
        dao = new DeveloperDAO();
        devList = dao.readData(); // Tự động load dữ liệu từ file khi new Manager
    }

    // --- 1. Hàm tìm kiếm Dev bằng ID (Dùng nội bộ để check trùng) ---
    public Developer findDev(String id) {
        for (Developer d : devList) {
            if (d.getDevID().equalsIgnoreCase(id)) return d;
        }
        return null;
    }

    // --- 2. Hàm hiển thị danh sách (Requirement) ---
    public void displayAll() {
        System.out.println("\n--- LIST OF DEVELOPERS ---");
        System.out.println("----------------------------------------------------------------------");
        System.out.printf("| %-8s | %-20s | %-15s | %-5s | %-10s |\n", 
                "ID", "Name", "Skills", "Exp", "Salary($)");
        System.out.println("----------------------------------------------------------------------");
        
        // Sắp xếp theo tên trước khi in (Optional - cho đẹp)
        Collections.sort(devList, (d1, d2) -> d1.getName().compareToIgnoreCase(d2.getName()));

        for (Developer d : devList) {
            d.showInfo();
        }
        System.out.println("----------------------------------------------------------------------");
    }

    // --- 3. Hàm thêm Dev mới (Create) ---
    public void addDeveloper() {
        System.out.println("\n--- CREATE NEW DEVELOPER ---");
        String id;
        
        // Validation: ID không được trùng
        while (true) {
            id = Inputter.inputString("Enter ID (e.g D01): ", false);
            if (findDev(id) == null) break;
            System.err.println("❌ ID Existed! Try another.");
        }

        String name = Inputter.inputString("Enter Name: ", false);
        String skills = Inputter.inputString("Enter Skills (e.g Java, C#): ", false);
        int exp = Inputter.inputInt("Enter Experience Years: ", 0, 50);
        double salary = Inputter.inputDouble("Enter Salary ($): ", 0);

        // Tạo và thêm vào list
        Developer newDev = new Developer(id, name, skills, exp, salary);
        devList.add(newDev);

        // Lưu xuống file ngay lập tức
        dao.writeData(devList);
        System.out.println("✅ Developer added successfully!");
    }
    
    // --- 4. Hàm cập nhật Dev (Update) - Tớ làm mẫu, cậu có thể phát triển thêm ---
    public void updateDeveloper() {
        System.out.println("\n--- UPDATE DEVELOPER ---");
        String id = Inputter.inputString("Enter ID to update: ", false);
        Developer d = findDev(id);
        
        if (d == null) {
            System.err.println("❌ Developer not found!");
            return;
        }
        
        System.out.println("Found: " + d.getName());
        System.out.println("NOTE: Press Enter to keep old value.");

        String newName = Inputter.inputString("New Name [" + d.getName() + "]: ", true);
        if (!newName.isEmpty()) d.setName(newName);
        
        String newSkill = Inputter.inputString("New Skills [" + d.getSkills() + "]: ", true);
        if (!newSkill.isEmpty()) d.setSkills(newSkill);
        
        // Với số (int/double), Inputter cũ của cậu chưa hỗ trợ "Enter để bỏ qua"
        // Nên tạm thời bắt nhập lại, hoặc cậu nâng cấp Inputter sau nhé.
        int newExp = Inputter.inputInt("New Exp [" + d.getExpYear() + "]: ", 0, 50);
        d.setExpYear(newExp);
        
        // Lưu thay đổi
        dao.writeData(devList);
        System.out.println("✅ Update success!");
    }
}