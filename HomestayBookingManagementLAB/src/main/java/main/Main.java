/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import controller.HomestayManager;
import controller.TourManager;
import view.Menu;
/**
 *
 * @author rechiee
 */
public class Main {
    public static void main(String[] args) {
        HomestayManager hMgr = new HomestayManager();
        TourManager tMgr = new TourManager(hMgr); 

        while (true) {
            System.out.println("\n=== HOMESTAY BOOKING MANAGEMENT ===");
            System.out.println("1. Display all Homestays");
            System.out.println("2. Search Homestay by City");
            System.out.println("3. Display all Bookings (Tours)");
            System.out.println("4. Create new Booking");
            System.out.println("5. Update Booking Information");
            System.out.println("6. Delete Booking");
            System.out.println("7. Search Booking by Name");
            System.out.println("8. Statistics (Tourists per Homestay)");
            System.out.println("9. Exit");
            
            int choice = utils.Inputter.inputInt("Your choice: ", 1, 10);

            switch (choice) {
                case 1: hMgr.displayAll(); break;
                case 2: hMgr.searchByCity(); break;
                case 3: tMgr.displayTours(); break;
                case 4: tMgr.addTour(); break;
                case 5: tMgr.updateTour(); break;
                case 6: tMgr.deleteTour(); break;
                case 7: tMgr.searchBookingByname(); break;
                case 8: tMgr.statistics(); break;
                case 9: 
                    System.out.println("Data saved. Goodbye!");
                    System.exit(0);
            }
        }    }
}

