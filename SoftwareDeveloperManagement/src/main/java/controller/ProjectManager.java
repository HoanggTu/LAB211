/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import data.ProjectDAO;
import model.Project;
import utils.DateUtils;
import utils.Inputter;
import java.util.Date;
import java.util.List;
import java.util.Collections;
/**
 *
 * @author rechiee
 */
public class ProjectManager {
    private List<Project> projList;
    private ProjectDAO dao;
    private DeveloperManager devMgr; // Cần cái này để check DevID có tồn tại ko

    // Constructor nhận vào DevManager (Kỹ thuật Dependency Injection)
    public ProjectManager(DeveloperManager devMgr) {
        this.devMgr = devMgr;
        this.dao = new ProjectDAO();
        this.projList = dao.readData();
    }
    
    // Hàm tìm kiếm nội bộ
    public Project findProject(String id) {
        for (Project p : projList) {
            if (p.getProjID().equalsIgnoreCase(id)) return p;
        }
        return null;
    }

    // --- 1. Hiển thị danh sách Project ---
    public void displayAll() {
        System.out.println("\n--- LIST OF PROJECTS ---");
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.printf("| %-8s | %-8s | %-20s | %-8s | %-12s |\n", 
                "ProjID", "DevID", "Project Name", "Duration", "Start Date");
        System.out.println("--------------------------------------------------------------------------------------");
        
        // Sắp xếp theo ngày bắt đầu (Requirement phổ biến)
        Collections.sort(projList, (p1, p2) -> p1.getStartDate().compareTo(p2.getStartDate()));

        for (Project p : projList) {
            p.showInfo();
        }
        System.out.println("--------------------------------------------------------------------------------------");
    }

    // --- 2. Thêm Project (Logic khó nhất: Check DevID) ---
    public void addProject() {
        System.out.println("\n--- ADD NEW PROJECT ---");
        
        // a. Nhập ID Project
        String id;
        while (true) {
            id = Inputter.inputString("Enter Project ID: ", false);
            if (findProject(id) == null) break;
            System.err.println("❌ Project ID Existed!");
        }

        // b. Nhập DevID (BẮT BUỘC PHẢI TỒN TẠI BÊN LIST DEVELOPER)
        String devID;
        while (true) {
            // Hiện danh sách Dev cho dễ chọn
            // devMgr.displayAll(); 
            devID = Inputter.inputString("Assign to Developer (ID): ", false);
            
            // Gọi sang DeveloperManager để kiểm tra
            if (devMgr.findDev(devID) != null) {
                break;
            }
            System.err.println("❌ Developer ID not found! Please check again.");
        }

        String name = Inputter.inputString("Enter Project Name: ", false);
        int duration = Inputter.inputInt("Duration (months): ", 1, 120);
        Date start = Inputter.inputDate("Start Date (dd/MM/yyyy): ");

        // Tạo mới và lưu
        // Lưu ý: Constructor Project phải khớp với thứ tự này (Sửa Model nếu cần)
        Project p = new Project(id, devID, name, duration, start);
        projList.add(p);
        dao.writeData(projList);
        System.out.println("✅ Project added and assigned to " + devID);
    }
    
    public void saveData() {
        dao.writeData(projList);
    }
}
