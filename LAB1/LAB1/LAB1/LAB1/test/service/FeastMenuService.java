package service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import models.FeastMenu;

public class FeastMenuService {
    List<FeastMenu> menuList = new ArrayList<>();

    //1
    public FeastMenuService() {
        // Nạp dữ liệu từ file khi khởi tạo
        try {
            String filePath = "E://Study//Major//SUM2025//LAB211//FeastMenu.csv/";
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8"));
            reader.readLine(); // Bỏ qua dòng tiêu đề
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String code = parts[0];
                String name = parts[1];
                int price = Integer.parseInt(parts[2]);
                String ingredients = parts[3];
                FeastMenu menu = new FeastMenu(code, name, price, ingredients);
                menuList.add(menu);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Cannot read the file!");
        }
    }
//2
    public void showAll() {
        System.out.println("----------------------------------------");
        System.out.println("List of Set Menus for ordering party:");
        System.out.println("----------------------------------------");

        Collections.sort(menuList, Comparator.comparingInt(FeastMenu::getPrice));

        for (FeastMenu menu : menuList) {
            System.out.format("%-12s: %s\n", "Code", menu.getCode());
            System.out.format("%-12s: %s\n", "Name", menu.getName());
            System.out.format("%-12s: %,d Vnd\n", "Price", menu.getPrice());
            System.out.println("Ingredients:");
            String formattedIngredients = menu.getIngredients()
                .replace("\"", "")
                .replace("#", "\n");
            System.out.println(formattedIngredients);
            System.out.println("----------------------------------------");
        }
        System.out.println("Press Enter to return to main menu...");
    }
//3
    public FeastMenu findFeastMenuByCode(String code) {
        for (FeastMenu menu : menuList) {
            if (menu.getCode().equalsIgnoreCase(code)) {
                return menu;
            }
        }
        return null;
    }
}