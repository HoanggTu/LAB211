/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author rechiee
 */
public class Developer implements Acceptable {
    private String devID;
    private String name;
    private String skills;  // Ví dụ: Java, C#, Python
    private int expYear;    // Số năm kinh nghiệm
    private double salary;  // Lương cơ bản

    public Developer(String devID, String name, String skills, int expYear, double salary) {
        this.devID = devID;
        this.name = name;
        this.skills = skills;
        this.expYear = expYear;
        this.salary = salary;
    }

    // --- Getter & Setter ---
    public String getDevID() { return devID; }
    public void setDevID(String devID) { this.devID = devID; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSkills() { return skills; }
    public void setSkills(String skills) { this.skills = skills; }
    
    public int getExpYear() { return expYear; }
    public void setExpYear(int expYear) { this.expYear = expYear; }

    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }

    @Override
    public boolean isValid() {
        return !devID.isEmpty() && !name.isEmpty();
    }

    @Override
    public String toString() {
        // Format để ghi vào file (giả sử ngăn cách bằng dấu phẩy hoặc gạch ngang)
        return String.format("%s,%s,%s,%d,%.2f", devID, name, skills, expYear, salary);
    }
    
    public void showInfo() {
        System.out.printf("| %-8s | %-20s | %-15s | %-5d | %-10.2f |\n", 
                devID, name, skills, expYear, salary);
    }
}
