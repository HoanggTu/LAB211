package main;

/**
 *
 * @author HOANGANH
 */
import java.util.Scanner;
import service.CustomerService;
import service.FeastMenuService;
import service.OrderService;

public class Main {

    // Nhóm Khách hàng
    private static final int REGISTER_CUSTOMERS = 1;
    private static final int UPDATE_CUSTOMER = 2;
    private static final int SEARCH_CUSTOMER = 3;
    // Nhóm Đơn hàng
    private static final int DISPLAY_FEAST_MENU = 4;
    private static final int PLACE_ORDER = 5;
    private static final int UPDATE_ORDER = 6;
    private static final int CANCEL_ORDER = 7; 
    // Nhóm Hệ thống
    private static final int SAVE_DATA = 8;
    private static final int DISPLAY_ALL = 9;
    private static final int EXIT = 0;

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
        System.out.println("\n==============================================");
        System.out.println("   TRADITIONAL FEAST MANAGEMENT SYSTEM");
        System.out.println("==============================================");
        System.out.println("1. Register customers");
        System.out.println("2. Update customer information");
        System.out.println("3. Search customer by name");
        System.out.println("4. Display feast menu");
        System.out.println("5. Place order");
        System.out.println("6. Update order information");
        System.out.println("7. Cancel order"); // Mới
        System.out.println("8. Save data to file");
        System.out.println("9. Display customer or Order lists");
        System.out.println("0. Exit");
        System.out.print("Enter your selection: ");
    }

    private static void processMenuChoice(int choice) {
        switch (choice) {
            case REGISTER_CUSTOMERS:
                cs.registerCustomers();
                break;
            case UPDATE_CUSTOMER:
                cs.updateCustomer();
                break;
            case SEARCH_CUSTOMER:
                cs.searchCustomerByName();
                break;
            case DISPLAY_FEAST_MENU:
                fm.showAll();
                break;
            case PLACE_ORDER:
                os.placeOrder();
                break;
            case UPDATE_ORDER:
                os.updateOrderInformation();
                break;
            case CANCEL_ORDER:
                os.cancelOrder();
                break; // Gọi hàm cancel
            case SAVE_DATA:
                cs.saveCustomerData();
                os.saveOrderData();
                break;
            case DISPLAY_ALL:
                showAll();
                break;
            case EXIT:
                System.out.println("Saving progress...");
                cs.saveCustomerData();
                os.saveOrderData();
                System.out.println("Good Bye!");
                break;
            default:
                System.out.println("Invalid selection. Please try again.");
        }
    }

    private static void showAll() {
        while (true) {
            try {
                System.out.println("\n--- VIEW DATA ---");
                System.out.println("1. Show all customers");
                System.out.println("2. Show all orders");
                System.out.print("Select (1-2, 0 to back): ");
                int sel = Integer.parseInt(sc.nextLine());
                if (sel == 1) {
                    cs.showAllCustomers();
                } else if (sel == 2) {
                    os.showAllOrders();
                } else if (sel == 0) {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Invalid input!");
            }
        }
    }

    public static void main(String[] args) {
        initializeSystem();
        int choice;
        do {
            displayMainMenu();
            try {
                choice = Integer.parseInt(sc.nextLine());
                processMenuChoice(choice);
            } catch (Exception e) {
                System.out.println("Please enter a valid number!");
                choice = -1;
            }
        } while (choice != EXIT);
    }
}
