package controller;

/*
 * TourManager.java
 */
import data.TourDAO; 
// -----------------------------
import model.Homestay;
import model.Tour;
import utils.DateUtils;
import utils.Inputter;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author rechiee
 */
public class TourManager {
    private List<Tour> tours;
    private TourDAO dao;
    private HomestayManager hMgr; // DI

    public TourManager(HomestayManager hMgr) {
        this.hMgr = hMgr;
        this.dao = new TourDAO();
        this.tours = dao.readData();
    }

    private boolean isExist(String id) {
        for (Tour t : tours) if (t.getTourID().equalsIgnoreCase(id)) return true;
        return false;
    }
    
    private Tour findTour(String id) {
        for (Tour t : tours) if (t.getTourID().equalsIgnoreCase(id)) return t;
        return null;
    }

    private boolean isOverlap(String homeID, Date start, Date end, String ignoreID) {
        for (Tour t : tours) {
            if (ignoreID != null && t.getTourID().equalsIgnoreCase(ignoreID)) {
                continue; 
            }
            
            if (t.getHomeID().equalsIgnoreCase(homeID)) {
                if (DateUtils.isOverlap(start, end, t.getDepartureDate(), t.getEndDate())) return true;
            }
        }
        return false;
    }

    public void addTour() {
        System.out.println("\n--- ADD NEW TOUR ---");
        String id;
        while(true) {
            id = Inputter.inputString("Enter Tour ID: ", false);
            if(!isExist(id)) break;
            System.err.println("Existed!");
        }
        String name = Inputter.inputString("Enter Name: ", false);
        String dur = Inputter.inputString("Enter Duration: ", false);
        double price = Inputter.inputDouble("Enter Price: ", 0);

        hMgr.displayAll();
        String hID;
        Homestay home;
        
        while(true) {
            hID = Inputter.inputString("Enter Home ID: ", false);
            home = hMgr.searchHomestay(hID);
            if(home != null) break;
            System.err.println("Not Found!");
        }

        Date start, end;
        while(true) {
            start = Inputter.inputDate("Start Date (dd/MM/yyyy): ");
            end = Inputter.inputDate("End Date");
            if(end.before(start)) { System.err.println("End >= Start!"); continue; }
            if(isOverlap(hID, start, end, null)) { System.err.println("Homestay Busy!"); } 
            else break;
        }

        int num = Inputter.inputInt("Num Tourist: ", 1, home.getMaxCapacity());
        String Status = Inputter.inputString(" ", false);


        Tour t = new Tour(id, name, dur, price, hID, start, end, num,Status);
        tours.add(t);
        dao.writeData(tours);
        System.out.println("Success!");
    }

    
    
    public void updateTour() {
        System.out.println("\n--- UPDATE BOOKING ---");
        
        String id = Inputter.inputString("Enter Booking ID to update: ", false);
        Tour t = findTour(id);
        
        if (t == null) {
            System.err.println("❌ Booking ID not found!");
            return;
        }

        Homestay home = hMgr.searchHomestay(t.getHomeID());
        
        System.out.println("NOTE: Press ENTER to keep the old value.");
        
        String newName = Inputter.inputString("New Name [" + t.getTourName() + "]: ", true);
        if (!newName.isEmpty()) t.setTourName(newName);

        boolean isInfoChanged = false; 
        
        System.out.println("Old Dates: " + DateUtils.formatDate(t.getDepartureDate()) 
                + " -> " + DateUtils.formatDate(t.getEndDate()));
        
        String startStr = Inputter.inputString("New Start Date (dd/MM/yyyy): ", true);
        String endStr = Inputter.inputString("New End Date (dd/MM/yyyy): ", true);

        if (!startStr.isEmpty() && !endStr.isEmpty()) {
            Date newStart = DateUtils.parseDate(startStr);
            Date newEnd = DateUtils.parseDate(endStr);
            Date now = new Date();

            if (newStart != null && newEnd != null) {
                if (newStart.after(newEnd)) {
                    System.err.println("❌ Error: Start Date must be before End Date!");
                } 
                else if (isOverlap(t.getHomeID(), newStart, newEnd, t.getTourID())) {
                    System.err.println("❌ Error: Homestay is BUSY in this period! Update failed.");
                } 
                else {
                    t.setDepartureDate(newStart);
                    t.setEndDate(newEnd);
                    isInfoChanged = true; 
                    System.out.println("✅ Dates updated!");
                }
            } else {
                System.err.println("❌ Invalid Date Format!");
            }
        }

        String numStr = Inputter.inputString("New Num Tourist [" + t.getNumberTourist() + "]: ", true);
        if (!numStr.isEmpty()) {
            try {
                int newNum = Integer.parseInt(numStr);
                if (newNum > 0 && newNum <= home.getMaxCapacity()) {
                    t.setNumberTourist(newNum);
                    isInfoChanged = true; // Đánh dấu đã đổi người
                } else {
                    System.err.println("❌ Error: Over capacity (" + home.getMaxCapacity() + ")!");
                }
            } catch (Exception e) {
                System.err.println("❌ Invalid number!");
            }
        }

        
        if (isInfoChanged) {
            long diff = t.getEndDate().getTime() - t.getDepartureDate().getTime();
            long days = diff / (1000 * 60 * 60 * 24);
            if (days <= 0) days = 1;

            
            System.out.println("⚠️ Information changed (Dates/Guests). Please update pricing.");
            double pricePerNight = Inputter.inputDouble("Enter Current Price per Night ($): ", 0);

            double newBasePrice = pricePerNight * days * t.getNumberTourist();
            System.out.println("💰 New Base Price: " + newBasePrice + " $");

            double discount = Inputter.inputDouble("Enter Discount (%): ", 0);
            
            double finalPrice = newBasePrice;
            if (discount > 0) {
                finalPrice = newBasePrice - (newBasePrice * (discount / 100));
                System.out.println("✅ Price updated with " + discount + "% discount: " + finalPrice + " $");
            } else {
                System.out.println("Price updated (No discount).");
            }

            t.setPrice(finalPrice);
        }

        
        dao.writeData(tours); 
        System.out.println("Update process finished.");
        
    }

    public void deleteTour() {
        System.out.println("\n--- DELETE TOUR ---");
        String id = Inputter.inputString("Enter Tour ID to delete: ", false);
        Tour t = findTour(id);
        
        if (t == null) {
            System.err.println("Tour not found!");
        } else {
            tours.remove(t);
            dao.writeData(tours); 
            System.out.println("Deleted successfully!");
        }
    }

    public void displayTours() {
        System.out.println("\n--- TOUR LIST ---");
        if(tours.isEmpty()) System.out.println("List Empty.");
        System.out.println("---------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-7s | %-20s | %-16s | %-10s | %-8s | %-12s | %-12s | %-6s |\n", 
                "ID", "Tour Name", "Duration", "Price ($)", "HomeID", "Start Date", "End Date", "Guests");
        System.out.println("---------------------------------------------------------------------------------------------------------------------");
        
        for(Tour t : tours) t.showInfo();
        System.out.println("---------------------------------------------------------------------------------------------------------------------");

    }

    public void searchBookingByname() {
        System.out.println("\n--- SEARCH BOOKING BY NAME ---");
        String keyword = utils.Inputter.inputString("Enter keyword (Name): ", false).toLowerCase();
        
        System.out.println("--- SEARCH RESULT ---");
        boolean found = false;
        
        System.out.println("---------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-7s | %-20s | %-16s | %-10s | %-8s | %-12s | %-12s | %-6s |\n", 
                "ID", "Tour Name", "Duration", "Price ($)", "HomeID", "Start Date", "End Date", "Guests");
        System.out.println("---------------------------------------------------------------------------------------------------------------------");

        for (model.Tour t : tours) {
            if (t.getTourName().toLowerCase().contains(keyword)) {
                System.out.printf("| %-7s | %-20s | %-16s | %-10s | %-8s | %-12s | %-12s | %-6s |\n", 
                    t.getTourID(),
                    (t.getTourName().length() > 15 ? t.getTourName().substring(0,12)+"..." : t.getTourName()),
                    t.getDuration(),
                    t.getPrice(),
                    t.getHomeID(),
                    utils.DateUtils.formatDate(t.getDepartureDate()),
                    utils.DateUtils.formatDate(t.getEndDate()),
                    t.getNumberTourist()
                ); 
                found = true;
            }
        }
        System.out.println("-----------------------------------------------------------------------------------------------------------------");
        
        if (!found) System.out.println("No booking found with name containing: " + keyword);
    }
    
    public void statistics() {
        System.out.println("\n--- STATISTICS: TOURISTS PER HOMESTAY ---");
        System.out.println("------------------------------------------------------------");
        System.out.printf("| %-10s | %-25s | %-15s |\n", "HomeID", "Homestay Name", "Total Tourists");
        System.out.println("------------------------------------------------------------");

        List<Homestay> homestays = hMgr.getList(); 
        
        for (Homestay h : homestays) {
            int total = 0;
            for (Tour t : tours) {
                if (t.getHomeID().equalsIgnoreCase(h.getHomeID())) {
                    total += t.getNumberTourist();
                }
            }
            
            System.out.printf("| %-10s | %-25s | %-15d |\n", 
                    h.getHomeID(), 
                    (h.getHomeName().length() > 25 ? h.getHomeName().substring(0,22)+"..." : h.getHomeName()), 
                    total);
        }
        System.out.println("------------------------------------------------------------");
    }
}
