/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;
import controller.DeveloperManager;
import controller.ProjectManager;
import controller.HRManager;
import view.Menu;
/**
 *
 * @author rechiee
 */
public class Main {
    public static void main(String[] args) {
        // 1. Dependency Injection: Khởi tạo các Manager theo đúng thứ tự
        // DevManager tạo trước -> Truyền vào ProjManager -> Cả 2 truyền vào HRManager
        DeveloperManager devMgr = new DeveloperManager();
        ProjectManager projMgr = new ProjectManager(devMgr); 
        HRManager hrMgr = new HRManager(devMgr, projMgr);   

        // 2. Xây dựng Menu (Dựa trên PDF yêu cầu)
        Menu menu = new Menu("SOFTWARE DEVELOPER MANAGEMENT");
        menu.addOption("Display all Developers");       // 1
        menu.addOption("Add new Developer");            // 2
        menu.addOption("Update Developer");             // 3
        menu.addOption("Delete Developer");             // 4
        menu.addOption("Display all Projects");         // 5
        menu.addOption("Add new Project");              // 6
        menu.addOption("Filter Developers by Skill");   // 7
        menu.addOption("Report Projects by Developer"); // 8
        menu.addOption("Calculate Total Experience");   // 9 (Bonus)
        menu.addOption("Sort Developers by Salary");    // 10
        menu.addOption("Save Data to Files");           // 11
        menu.addOption("Quit Program");                 // 12

        // 3. Vòng lặp chính
        while (true) {
            int choice = menu.getChoice();

            switch (choice) {
                case 1: devMgr.displayAll(); break;
                case 2: devMgr.addDeveloper(); break;
                case 3: devMgr.updateDeveloper(); break;
                
                // Case 4: Cậu cần thêm hàm deleteDeveloper() vào DeveloperManager nhé
                case 4: devMgr.deleteDeveloper(); break; 
                
                case 5: projMgr.displayAll(); break;
                case 6: projMgr.addProject(); break;
                
                // Các chức năng tổng hợp (HR)
                case 7: hrMgr.filterDevsBySkill(); break;
                case 8: hrMgr.reportProjectsByDev(); break;
                
                case 9:
                    // Chức năng phụ (nếu chưa kịp làm thì in thông báo)
                    System.out.println("🚧 Feature coming soon...");
                    break;
                    
                case 10:
                    // Sort (đã tích hợp trong displayAll, nhưng có thể tách riêng)
                    devMgr.displayAll();
                    break;
                    
                case 11:
                    // Gọi hàm lưu file (Cần thêm hàm saveData vào Manager)
                    devMgr.saveData();
                    projMgr.saveData();
                    break;
                    
                case 12:
                    System.out.println("Data saved. Goodbye!");
                    System.exit(0);
            }
        }
    }
}
