/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import model.Developer;
import model.Project;
import utils.Inputter;
import java.util.List;
import java.util.ArrayList;
/**
 *
 * @author rechiee
 */
public class HRManager {
    // HR cần nắm trong tay cả 2 danh sách
    private DeveloperManager devMgr;
    private ProjectManager projMgr;

    public HRManager(DeveloperManager devMgr, ProjectManager projMgr) {
        this.devMgr = devMgr;
        this.projMgr = projMgr;
    }

    // --- Chức năng 1 (Rubric): Lọc Developer theo ngôn ngữ lập trình ---
    public void filterDevsBySkill() {
        System.out.println("\n--- FILTER DEVELOPERS BY SKILL ---");
        String keyword = Inputter.inputString("Enter Programming Language (e.g Java): ", false).toLowerCase();
        
        List<Developer> list = devMgr.getList(); // Cần thêm getter getList() bên DevManager
        boolean found = false;

        System.out.println("------------------------------------------------------");
        System.out.printf("| %-8s | %-20s | %-15s |\n", "ID", "Name", "Skills");
        System.out.println("------------------------------------------------------");

        for (Developer d : list) {
            // Logic tìm kiếm thông minh (cleanup dữ liệu)
            if (d.getSkills().toLowerCase().contains(keyword)) {
                System.out.printf("| %-8s | %-20s | %-15s |\n", 
                        d.getDevID(), d.getName(), d.getSkills());
                found = true;
            }
        }
        System.out.println("------------------------------------------------------");
        if (!found) System.out.println("❌ No developers found with skill: " + keyword);
    }

    // --- Chức năng 2 (Rubric): Xem các Project mà 1 Dev đang làm ---
    public void reportProjectsByDev() {
        System.out.println("\n--- VIEW PROJECTS BY DEVELOPER ---");
        String devID = Inputter.inputString("Enter Developer ID: ", false);
        
        // Check xem Dev có tồn tại không
        Developer dev = devMgr.findDev(devID);
        if (dev == null) {
            System.err.println("❌ Developer not found!");
            return;
        }

        System.out.println("Checking projects for: " + dev.getName().toUpperCase());
        boolean hasProject = false;
        
        // Duyệt danh sách Project (Phải thêm getter getList() bên ProjectManager nếu chưa có)
        // Cách lấy list: Cậu có thể thêm hàm public List<Project> getList() vào ProjectManager
        // Giả sử cậu đã thêm hàm đó:
        /* for (Project p : projMgr.getList()) {
            if (p.getDevID().equalsIgnoreCase(devID)) {
                p.showInfo();
                hasProject = true;
            }
        } 
        */
        
        if (!hasProject) {
            System.out.println("👉 This developer is currently not assigned to any project.");
        }
    }
}
