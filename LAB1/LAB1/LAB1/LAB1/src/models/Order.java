package models;

/**
 *
 * @author HOANGANH
 */
import java.time.LocalDate;

public class Order {

    private String orderCode;
    private Customer customer;
    private FeastMenu feastMenu;
    private int numberOfTables;
    private LocalDate eventDate;
    private long totalCost;

    // Constructor
    public Order(String orderCode, Customer customer, FeastMenu feastMenu, int numberOfTables, LocalDate eventDate, long totalCost) {
        this.orderCode = orderCode;
        this.customer = customer;
        this.feastMenu = feastMenu;
        this.numberOfTables = numberOfTables;
        this.eventDate = eventDate;
        this.totalCost = totalCost;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public FeastMenu getFeastMenu() {
        return feastMenu;
    }

    public void setFeastMenu(FeastMenu feastMenu) {
        this.feastMenu = feastMenu;
    }

    public int getNumberOfTables() {
        return numberOfTables;
    }

    public void setNumberOfTables(int numberOfTables) {
        this.numberOfTables = numberOfTables;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public long getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(long totalCost) {
        this.totalCost = totalCost;
    }

}
