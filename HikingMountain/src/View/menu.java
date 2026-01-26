/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import java.util.Scanner;

/**
 *
 * @author buiva
 */
public class menu {
    public void showMenu(){
        System.out.println("========== Muontain Hiking =========");
        System.out.println("1. New Registration");
        System.out.println("2. Update Registration Information");
        System.out.println("3. Display Registered List");
        System.out.println("4. Delete Registration Information");
        System.out.println("5. Search Participants by Name");
        System.out.println("6. Filter Data by Campus");
        System.out.println("7. Statistics of Registration Numbers by Location");
        System.out.println("8. Save Data to File");
        System.out.println("9. Exit");
        System.out.print("Choose [1-9]: ");
     }
}
