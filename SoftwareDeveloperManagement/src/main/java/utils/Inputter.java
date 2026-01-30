/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;
import java.util.Date;
import java.util.Scanner;
/**
 *
 * @author rechiee
 */
public class Inputter {
    public static final Scanner sc = new Scanner(System.in);

    /**
     * Nhập chuỗi văn bản
     * @param msg Câu nhắc (Ví dụ: Enter Name:)
     * @param allowEmpty Cho phép để trống không? (True = dùng cho Update)
     */
    public static String inputString(String msg, boolean allowEmpty) {
        while (true) {
            System.out.print(msg);
            String str = sc.nextLine().trim();
            
            // Nếu cho phép rỗng và người dùng không nhập gì -> Trả về rỗng (để giữ giá trị cũ)
            if (allowEmpty && str.isEmpty()) {
                return "";
            }
            
            // Nếu không cho rỗng mà vẫn Enter -> Bắt nhập lại
            if (!allowEmpty && str.isEmpty()) {
                System.err.println("❌ Input cannot be empty! Please try again.");
                continue;
            }
            
            return str;
        }
    }

    /**
     * Nhập số nguyên (int) với giới hạn min/max
     */
    public static int inputInt(String msg, int min, int max) {
        while (true) {
            System.out.print(msg);
            String str = sc.nextLine().trim();
            if (str.isEmpty()) {
                System.err.println("❌ Input cannot be empty!");
                continue;
            }
            try {
                int n = Integer.parseInt(str);
                if (n >= min && n <= max) return n;
                System.err.println("❌ Please enter a number between " + min + " and " + max);
            } catch (NumberFormatException e) {
                System.err.println("❌ Invalid number format! Must be an Integer.");
            }
        }
    }

    /**
     * Nhập số thực (double) với giới hạn min
     */
    public static double inputDouble(String msg, double min) {
        while (true) {
            System.out.print(msg);
            String str = sc.nextLine().trim();
            if (str.isEmpty()) {
                System.err.println("❌ Input cannot be empty!");
                continue;
            }
            try {
                double n = Double.parseDouble(str);
                if (n > min) return n;
                System.err.println("❌ Number must be greater than " + min);
            } catch (NumberFormatException e) {
                System.err.println("❌ Invalid number format! Must be a Double.");
            }
        }
    }

    /**
     * Nhập ngày tháng (Sử dụng DateUtils)
     */
    public static Date inputDate(String msg) {
        while (true) {
            System.out.print(msg);
            String str = sc.nextLine().trim();
            if (str.isEmpty()) {
                System.err.println("❌ Date cannot be empty!");
                continue;
            }
            
            // Gọi hàm parseDate từ DateUtils (cậu nhớ copy file DateUtils từ bài cũ nhé)
            Date d = DateUtils.parseDate(str);
            if (d != null) return d;
            
            System.err.println("❌ Invalid Date! Format must be: " + DateUtils.DATE_FORMAT);
        }
    }
    
    /**
     * Hàm nhập ID có kiểm tra Regex (Dùng cho Developer và Project)
     * @param mode 1: Check DevID, 2: Check ProjID
     */
    public static String inputID(String msg, int mode) {
        while(true) {
            String id = inputString(msg, false).toUpperCase();
            boolean isValid = false;
            
            if (mode == 1) isValid = Validation.isValidDevID(id);
            else if (mode == 2) isValid = Validation.isValidProjID(id);
            
            if (isValid) return id;
            
            System.err.println("❌ Invalid ID Format!");
            if (mode == 1) System.err.println("👉 Format: DEVxxx (e.g., DEV001, DEV01A)");
            else System.err.println("👉 Format: PROJxx (e.g., PROJ01, PROJ12)");
        }
    }
}
