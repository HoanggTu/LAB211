package service;

import models.Customer;
import models.FeastMenu;
import models.Order;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
//0
public class OrderService {
    private List<Order> orderList = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);
    private CustomerService customerService; 
    private FeastMenuService feastMenuService; 

    // Constructor nhận vào đối tượng CustomerService và FeastMenuService
    public OrderService(CustomerService customerService, FeastMenuService feastMenuService) {
        this.customerService = customerService;
        this.feastMenuService = feastMenuService;
    }

    
    public void placeOrder() {
        System.out.println("---------- PLACE ORDER ----------");

        // 1.
        Customer customer = getValidCustomer();
        if (customer == null) {
            System.out.println("No customers found. Please register first.");
            System.out.println("Press Enter to return to the main menu...");
            sc.nextLine();
            return;
        }

        // 2. 
        FeastMenu feastMenu = getValidFeastMenu();
        if (feastMenu == null) {
            System.out.println("The Set Menu code is invalid or does not exist.");
            System.out.println("Press Enter to return to the main menu...");
            sc.nextLine();
            return;
        }

        // 3. 
        int numberOfTables = getValidNumberOfTables();
        if (numberOfTables == -1) { 
            System.out.println("Press Enter to return to the main menu...");
            sc.nextLine();
            return;
        }

        // 4. 
        LocalDate eventDate = getValidEventDate();
        if (eventDate == null) {
            System.out.println("Press Enter to return to the main menu...");
            sc.nextLine();
            return;
        }

        // 5. 
        if (isDuplicateOrder(customer.getCustomerCode(), feastMenu.getCode(), eventDate)) {
            System.out.println("Dupplicate data!.");
            System.out.println("Nhấn Enter để quay lại menu chính...");
            sc.nextLine();
            return;
        }

        // 6. 
        String orderCode = generateOrderCode(); 

        // 7. 
        long totalCost = (long) feastMenu.getPrice() * numberOfTables;

        // 8. 
        Order newOrder = new Order(orderCode, customer, feastMenu, numberOfTables, eventDate, totalCost);
        orderList.add(newOrder);

        // 9. 
        System.out.println("Order successful!");
        displayOrderDetails(newOrder);

        System.out.println("Press Enter to return to the main menu...");
        sc.nextLine();
    }

    // 10 
    private Customer getValidCustomer() {
        String customerCode;
        Customer customer = null;
        boolean isValid = false;
        while (!isValid) {
            System.out.print("Customer Code: ");
            customerCode = sc.nextLine().trim();

            if (customerCode.isEmpty()) {
                System.out.println("Customer Code cannot be blank!");
                continue;
            }

            customer = findCustomerByCode(customerCode);
            if (customer == null) {
                System.out.println("Customer code does not exist in the system. Please check again.!");
                break;
            } else {
                isValid = true;
            }
        }
        return customer;
    }

    // 11 
    private Customer findCustomerByCode(String code) {
        for (Customer c : customerService.getCustomers()) {
            if (c.getCustomerCode().equalsIgnoreCase(code)) {
                return c;
            }
        }
        return null;
    }

    //12  
    private FeastMenu getValidFeastMenu() {
        String menuCode;
        FeastMenu feastMenu = null;
        boolean isValid = false;
        while (!isValid) {
            System.out.print("Set Menu Code: ");
            menuCode = sc.nextLine().trim();

            if (menuCode.isEmpty()) {
                System.out.println("Set Menu cannot be blank!");
                continue;
            }

            feastMenu = feastMenuService.findFeastMenuByCode(menuCode);
            if (feastMenu == null) {
                System.out.println("Set menu code does not exist in the system. Please check again.!");
            } else {
                isValid = true;
            }
        }
        return feastMenu;
    }

    //13 
    private int getValidNumberOfTables() {
        int tables = -1;
        boolean isValid = false;
        while (!isValid) {
            System.out.print("Enter number of table:  ");
            try {
                tables = Integer.parseInt(sc.nextLine().trim());
                if (tables <= 0) {
                    System.out.println("Number of tables must be greater than 0!");
                } else {
                    isValid = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid table number. Please enter an integer!");
            }
        }
        return tables;
    }

    // 14 
    private LocalDate getValidEventDate() {
        LocalDate eventDate = null;
        boolean isValid = false;
        while (!isValid) {
            System.out.print("Enter date(dd/MM/yyyy): ");
            String dateString = sc.nextLine().trim();
            try {
                eventDate = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                if (eventDate.isBefore(LocalDate.now())) {
                    System.out.println("The event date must be in the future!");
                } else {
                    isValid = true;
                }
            } catch (DateTimeParseException e) {
                System.out.println("Date is not in dd/MM/yyyy format. Please re-enter!");
            }
        }
        return eventDate;
    }

    // 15 
    private boolean isDuplicateOrder(String customerCode, String menuCode, LocalDate eventDate) {
        for (Order order : orderList) {
            if (order.getCustomer().getCustomerCode().equalsIgnoreCase(customerCode) &&
                order.getFeastMenu().getCode().equalsIgnoreCase(menuCode) &&
                order.getEventDate().isEqual(eventDate)) {
                return true;
            }
        }
        return false;
    }

    // 16 
    private String generateOrderCode() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        return LocalDateTime.now().format(formatter); 
    }

    // 17 
    private void displayOrderDetails(Order order) {
        
        System.out.println("---------------------------------------------------------------------------------------------------");
        System.out.println("Customer order information [Order ID: " + order.getOrderCode() + "]");
        System.out.println("---------------------------------------------------------------------------------------------------");

        System.out.printf("%-18s: %s\n", "Customer code", order.getCustomer().getCustomerCode());
        System.out.printf("%-18s: %s\n", "Customer name", order.getCustomer().getName());
        System.out.printf("%-18s: %s\n", "Phone number", order.getCustomer().getPhone());
        System.out.printf("%-18s: %s\n", "Email", order.getCustomer().getEmail());

        System.out.println("---------------------------------------------------------------------------------------------------");
        System.out.printf("%-18s: %s\n", "Code of Set Menu", order.getFeastMenu().getCode());
        System.out.printf("%-18s: %s\n", "Set menu name", order.getFeastMenu().getName());
        System.out.printf("%-18s: %s\n", "Event date", order.getEventDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        System.out.printf("%-18s: %d\n", "Number of tables", order.getNumberOfTables());
        System.out.printf("%-18s: %,d Vnd\n", "Price", order.getFeastMenu().getPrice());

        System.out.println("Ingredients:");
        String formattedIngredients = order.getFeastMenu().getIngredients()
            .replace("\"", "")
            .replace("#", "\n");
        System.out.println(formattedIngredients);

        System.out.println("---------------------------------------------------------------------------------------------------");
        System.out.printf("%-18s: %,d Vnd\n", "Total cost", order.getTotalCost());
        System.out.println("---------------------------------------------------------------------------------------------------\n");
    }
    public List<Order> getAllOrders() {
        return orderList;
        }
    public void showAllOrders() {
        if (orderList.isEmpty()) {
            System.out.println("No order data yet");
                return;
            }

            for (Order order : orderList) {
                displayOrderDetails(order);
            }
            
        }
}

