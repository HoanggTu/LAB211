/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.util.Date;
import utils.DateUtils; // Nhớ copy DateUtils từ bài cũ sang nhé
/**
 *
 * @author rechiee
 */
public class Project implements Acceptable {
    private String projID;
    private String projName;
    private Date startDate, endDate;
    private double cost;
    // Quan trọng: Một Project sẽ chứa danh sách các Dev làm trong đó
    // Nhưng vì đề bài dùng file txt riêng lẻ, ta sẽ xử lý link này sau ở Controller.
    
    public Project(String projID, String projName, Date startDate, Date endDate, double cost) {
        this.projID = projID;
        this.projName = projName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.cost = cost;
    }

    // --- Getter & Setter (Cậu tự generate nhé cho ngắn) ---
    public String getProjID() { return projID; }
    public String getProjName() { return projName; }
    public double getCost() { return cost; }
    // ...

    @Override
    public boolean isValid() {
        return !projID.isEmpty();
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%.2f", 
                projID, projName, DateUtils.formatDate(startDate), DateUtils.formatDate(endDate), cost);
    }
    
    public void showInfo() {
        System.out.printf("| %-8s | %-20s | %-12s | %-12s | %-12.2f |\n", 
                projID, projName, DateUtils.formatDate(startDate), DateUtils.formatDate(endDate), cost);
    }
}
