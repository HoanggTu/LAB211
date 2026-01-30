package main;

import java.util.Scanner;
import service.CustomerService;
import service.FeastMenuService;
import service.OrderService;

public class Main {
    private static final int REGISTER_CUSTOMERS = 1;
    private static final int UPDATE_CUSTOMER = 2;
    private static final int SEARCH_CUSTOMER = 3;
    private static final int DISPLAY_MENU = 4;
    private static final int PLACE_ORDER = 5;
    private static final int UPDATE_ORDER = 6;
    private static final int SAVE_DATA = 7;
    private static final int DISPLAY_ALL = 8;
    private static final int EXIT = 9;

    private static CustomerService cs;
    private static FeastMenuService fm;
    private static OrderService os;
    private static Scanner sc;

    private static void initializeSystem() {
        cs = new CustomerService();
        fm = new FeastMenuService();
        os = new OrderService(cs, fm);
        sc = new Scanner(System.in);
    }

    private static void displayMainMenu() {
        System.out.println("----------MAIN MENU------------");
        System.out.println("1. Register customers.");
        System.out.println("2. Update customer information.");
        System.out.println("3. Search customer by name.");
        System.out.println("4. Display feast menu.");
        System.out.println("5. Place order");
        System.out.println("8. Display customer or Order lists.");
        System.out.println("9. Exit.");
        System.out.print("Enter your selection: ");
    }
    private static int showAll(){
        int select = 0;
       while (true) {
        try {
            System.out.println("1. Show all customers");
            System.out.println("2. Show all orders");
            System.out.print("Enter select: ");
            select = Integer.parseInt(new Scanner(System.in).nextLine());

            if (select == 1) {
                cs.showAllCustomers();
                return 0;
            } else if (select == 2) {
                os.showAllOrders();
                return 0;
            } else {
                System.out.println("Please select 1 or 2:");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
        }
    }
            
    }
    private static int getMenuChoice() {
        try {
            return Integer.parseInt(sc.nextLine());
        } catch (Exception e) {
            
            return 0;
        }
    }

    private static void processMenuChoice(int testcase) {
        switch (testcase) {
            case REGISTER_CUSTOMERS:
                cs.registerCustomers();
                break;
            case UPDATE_CUSTOMER:
                cs.updateCustomer();
                break;
            case SEARCH_CUSTOMER:
                cs.searchCustomerByName();
                break;
            case DISPLAY_MENU:
                fm.showAll();
                break;
            case PLACE_ORDER:
                os.placeOrder();
                break;
            case DISPLAY_ALL:
                showAll();
                break;
            case EXIT:
                System.out.println("Good Bye...");
                break;
            default:
                System.out.println("Invalid input. Please enter a number (1-9)");
                break;
        }
    }

    private static void runMainMenu() {
        int testcase; 
        do {
            displayMainMenu();
            testcase = getMenuChoice();
            processMenuChoice(testcase);
        } while (testcase != EXIT);
    }
    

    public static void main(String[] args) {
        initializeSystem();
        runMainMenu();
    }
}