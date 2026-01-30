/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author rechiee
 */
public class Homestay implements Acceptable{
    private String homeID, homeName, address;
    private int roomNumber, maxCapacity;
    private double price;

    public Homestay(String homeID, String homeName,int roomNumber, String address, int maxCapacity, double price) {
        this.homeID = homeID;
        this.homeName = homeName;
        this.roomNumber = roomNumber; 
        this.address = address;       
        this.maxCapacity = maxCapacity;
        this.price = price;    }

    public String getHomeID() { return homeID; }
    public String getHomeName() { return homeName; }
    public String getAddress() { return address; }
    public int getRoomNumber() { return roomNumber; }
    public int getMaxCapacity() { return maxCapacity; }
    public double getPrice() { return price; }
    
    @Override
    public boolean isValid(){ 
        return !homeID.isEmpty(); 
    }

    public void setHomeID(String homeID) {
        this.homeID = homeID;
    }

    public void setHomeName(String homeName) {
        this.homeName = homeName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }
    
    public void showInfo() {
        String cleanName = this.homeName;
        if (cleanName.length() > 42) {
            cleanName = cleanName.substring(0, 21) + "...";
        }
        
        String cleanAddr = this.address;
        if (cleanAddr.length() > 60) {
            cleanAddr = cleanAddr.substring(0, 55) + "...";
        }

       
        System.out.printf("| %-8s | %-25s | %-6d | %-9d | %-60s |\n",
                homeID, cleanName, roomNumber, maxCapacity, cleanAddr);
    }

    public int setMaxCapacity() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int setRoomNumber() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
}
