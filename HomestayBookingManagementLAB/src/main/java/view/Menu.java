/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;
import utils.Inputter;
import java.util.ArrayList;

public class Menu {
    private String title;
    private ArrayList<String> options = new ArrayList<>();

    public Menu(String title) { this.title = title; }
    public void addOption(String op) { options.add(op); }
    
    public int getChoice() {
        System.out.println("\n=== " + title + " ===");
        for (int i = 0; i < options.size(); i++)
            System.out.println((i + 1) + ". " + options.get(i));
        return Inputter.inputInt("Choose: ", 1, options.size());
    }
}
