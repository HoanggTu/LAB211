/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import data.HomestayDAO;
import java.util.Date;
import model.Homestay;
import java.util.List;
import model.Tour;
import utils.Inputter;
/**
 *
 * @author rechiee
 */
public class HomestayManager {
    private List<Homestay> list;
    private HomestayDAO dao;

    public HomestayManager() {
        dao = new HomestayDAO();
        list = dao.readData();
    }

    public List<Homestay> getList() {
        return list;
    }
    private boolean isExist(String id) {
        for (Homestay h : list) if (h.getHomeID().equalsIgnoreCase(id)) return true;
        return false;
    }
    
    public Homestay searchHomestay(String id) {
        for (Homestay h : list) {
            if (h.getHomeID().equalsIgnoreCase(id)) return h;
        }
        return null;
    }
    public void  searchByCity(){
        System.out.println("--SearchByCity--");
        
        String city = Inputter.inputString("Enter city: ", false).toLowerCase();
        
        System.out.println("--- SEARCH RESULT ---");
        boolean found = false;
        
        System.out.println("----------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-8s | %-25s | %-6s | %-9s | %-60s |\n", 
                "ID", "Name", "Num", "Capacity", "Address");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------");
        for(Homestay h : list){
            if(h.getAddress().toLowerCase().contains(city)){
                h.showInfo();
                found= true;
            }
        }
        System.out.println("----------------------------------------------------------------------------------------------------------------------------");

        if(!found){
            System.out.println("No Found");
        }
    }
    
    public void addHomeStay() {
        System.out.println("\n--- ADD NEW HOMESTAY ---");
        String id;
        while(true) {
            id = Inputter.inputString("Enter Homestay ID: ", false);
            if(!isExist(id)) break;
            System.err.println("Existed!");
        }
        String name = Inputter.inputString("Enter Name: ", false);
        
        
       
       
        int num = Inputter.inputInt("Enter number Room: ", 1, 100); 
        int cap = Inputter.inputInt("Enter Capacity: ", 1, 500);
        

        String add = Inputter.inputString("Enter Address: ", false);
        double price =Inputter.inputDouble ("Enter Price: ", 0);


        Homestay h = new Homestay(id, name, num, add,cap, price );
        list.add(h);
        dao.writeData(list);
        System.out.println("Success!");
    }

    public void displayAll() {
        System.out.println("\n--- HOMESTAY LIST ---");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-8s | %-25s | %-6s | %-9s | %-60s |\n", 
                "ID", "Name", "Num", "Capacity", "Address");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------");
        for (Homestay h : list){
               
               h.showInfo();
        }
        System.out.println("----------------------------------------------------------------------------------------------------------------------------");
    }
}













//public void searchByCapacity() {
//        System.out.println("\n--- SEARCH HOMESTAY BY CAPACITY ---");
//        // Nhập số người cần tìm (Validation số dương)
//        int numPeople = utils.Inputter.inputInt("Enter number of people in your group: ", 1,99);
//        
//        System.out.println("Here are suitable homestays for " + numPeople + " people:");
//        System.out.println("--------------------------------------------------------------------------------------");
//        // In lại header bảng cho đẹp
//        System.out.printf("| %-6s | %-25s | %-5s | %-30s | %-8s |\n", "ID", "Name", "Room", "Address", "Capacity");
//        System.out.println("--------------------------------------------------------------------------------------");
//        
//        boolean found = false;
//        for (model.Homestay h : list) {
//            if (h.getMaxCapacity() >= numPeople) {
//                System.out.printf("| %-6s | %-25s | %-5d | %-30s | %-30d |\n", 
//                        h.getHomeID(), h.getHomeName(), h.getRoomNumber(), h.getAddress(), h.getMaxCapacity());
//                found = true;
//            }
//        }
//                java.util.Collections.sort(list, (h1, h2)-> h1.getHomeName().compareTo(h2.getHomeName()));

//        if (!found) {
//            System.out.println("|                            NO HOMESTAY FOUND                                       |");
//        }
//        System.out.println("--------------------------------------------------------------------------------------");
//    }