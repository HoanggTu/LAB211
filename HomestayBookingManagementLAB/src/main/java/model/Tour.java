/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import utils.DateUtils;
import java.util.Date;
import java.util.Locale;
/**
 *
 * @author rechiee
 */
public class Tour implements Acceptable {
    private String tourID, tourName, duration, homeID, status; 
    private double price;
    private Date departureDate, endDate;
    private int numberTourist;

    

    public Tour(String tourID, String tourName, String duration, double price, String homeID, 
                Date departureDate, Date endDate, int numberTourist, String status ) {
        this.tourID = tourID;
        this.tourName = tourName;
        this.duration = duration;
        this.price = price;       
        this.homeID = homeID;     
        this.departureDate = departureDate;
        this.endDate = endDate;
        this.numberTourist = numberTourist;
        this.status = status;
    }

    

    public String getTourID() { return tourID; }
    public String getHomeID() { return homeID; }
    public Date getDepartureDate() { return departureDate; }
    public Date getEndDate() { return endDate; }
    
//    public double getDiscount() {
//        return discount;
//    }
//
//    public void setDiscount(double discount) {
//        this.discount = discount;
//    }
    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    public String getTourName() {
        return tourName;
    }

    public void setTourName(String tourName) {
        this.tourName = tourName;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getNumberTourist() {
        return numberTourist;
    }

    public void setNumberTourist(int numberTourist) {
        this.numberTourist = numberTourist;
    }
    
    
    @Override
    public boolean isValid() { return !endDate.before(departureDate); }

    @Override
    public String toString() { 
        return String.format(Locale.US, "%s,%s,%s,%.1f,%s,%s,%s,%d,%s",
                tourID, tourName, duration, price, homeID,
                DateUtils.formatDate(departureDate), DateUtils.formatDate(endDate),
                numberTourist, status );
    }
    
    public void showInfo() {
        String cleanName = this.tourName;
        if (cleanName.length() > 20) {
            cleanName = cleanName.substring(0, 17) + "...";
        }
        
        // 2. Xử lý Duration: Nếu dài quá 12 ký tự thì cắt
        String cleanDur = this.duration;
        if (cleanDur.length() > 16) {
            cleanDur = cleanDur.substring(0, 14) + "...";
        }

        // 3. In ra bảng thẳng tắp
        // %-7s: Căn trái 7 ô (ID)
        // %-20s: Căn trái 20 ô (Name)
        // %,10.1f: Căn phải 10 ô, có dấu phẩy ngăn ngàn, 1 số lẻ (Price)
        System.out.printf("| %-7s | %-20s | %-16s | %-10s | %-8s | %-12s | %-12s | %-6s |\n", 
                tourID, 
                cleanName, 
                cleanDur, 
                price, 
                homeID, 
                utils.DateUtils.formatDate(departureDate), 
                utils.DateUtils.formatDate(endDate), 
                numberTourist
                
        );
        
    }
    
}
