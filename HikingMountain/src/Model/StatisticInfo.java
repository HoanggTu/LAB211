/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author buiva
 */
public class StatisticInfo {
    private String mountainCode;
    private int numOfRegistration;
    private double totalCost;

    public StatisticInfo(String mountainCode, int numOfRegistration, double totalCost) {
        this.mountainCode = mountainCode;
        this.numOfRegistration = numOfRegistration;
        this.totalCost = totalCost;
    }

    public String getMountainCode() {
        return mountainCode;
    }

    public void setMountainCode(String mountainCode) {
        this.mountainCode = mountainCode;
    }

    public int getNumOfRegistration() {
        return numOfRegistration;
    }

    public void setNumOfRegistration(int numOfStudent) {
        this.numOfRegistration = numOfRegistration;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }
    
    @Override
    public String toString() {
        return String.format("| %-11s| %-24d| %-12s|", mountainCode, numOfRegistration, totalCost);
    }
}
