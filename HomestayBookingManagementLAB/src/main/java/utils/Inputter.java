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
    public static  final Scanner sc =new Scanner(System.in);
    
    public static String inputString(String msg, boolean allowEmpty){
        while (true){
            System.out.print(msg);
            String str = sc.nextLine().trim();
            if (allowEmpty && str.isEmpty()) return "";
            if(allowEmpty || !str.isEmpty()){
                return str;
            }
            System.out.println("Input cannot be empty");

        }
    }
    
    public static int inputInt(String msg, int min, int max){
        while (true){
            System.out.print(msg);
            String str = sc.nextLine().trim();
            
            if (str.isEmpty()) {
                System.err.println("Input cannot be empty!");
                continue;
            }
            try{
                int  n = Integer.parseInt(str);
                if(n>= min && n <= max) return n;
                System.out.println("ERROR range[" + min + "-" + max + "]");
            }catch(NumberFormatException e){
                System.out.println("Number must be an Interger");
            }
        }

    }
    
    public static double inputDouble (String msg, double min){
        while (true) {
            System.out.print(msg);
            String str = sc.nextLine().trim();
            if (str.isEmpty()) {
                System.err.println("Error: Input cannot be empty!");
                continue;
            }
            try {
                double n = Double.parseDouble(str);
                if (n > min) return n;
                System.err.println("Error: Must be > " + min);
            } catch (NumberFormatException e) {
                System.out.println("Number must be an Interger");
            }
        }
    }
    
    public static Date inputDate(String msg) {
        while (true) {
            System.out.print(msg);
            String str = sc.nextLine().trim();
            if (str.isEmpty()) {
                System.err.println("Error: Input cannot be empty!");
                continue;
            }
            Date d = DateUtils.parseDate(str);
            if(d!=null)return d;
            System.out.println("invalid date");
        }
    
    }

    
}
