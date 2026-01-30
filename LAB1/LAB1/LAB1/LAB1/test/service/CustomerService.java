
package service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import models.Customer;

public class CustomerService {
    private List<Customer> customerList = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);
    
    private Validator codeValidator = new CustomerValidator.CustomerCodeValidator(this);
    private Validator nameValidator = new CustomerValidator.NameValidator();
    private Validator phoneValidator = new CustomerValidator.PhoneValidator();
    private Validator emailValidator = new CustomerValidator.EmailValidator();
     
            
    public void registerCustomer(){
      System.out.println("______REGISTER CUSTOMER______");
      String code = getInput("Enter Customercode: ", codeValidator);
      String name = getInput("Enter name: ", nameValidator);
      String phone = getInput("Enter phone: ", phoneValidator);
      String email = getInput("Enter email: ", emailValidator);
      
      Customer newCustomer = new Customer(code, name, phone, email);
      customerList.add(newCustomer);
        
    }
    //prompt voi Validator la bien dau vao cua getInput
    private String getInput(String prompt, Validator validator)  {
    String input;
    
    while (true) {
        System.out.print(prompt);
        input = sc.nextLine().trim();
        String error = validator.validate(input); // nếu hợp lệ thì trả về null
        if (error == null) break;
        System.out.println(error);
    }
    return input;
}
    
    public boolean duplicateCode(String code){
        
        if(code ==null || customerList == null){
            return false;
        }
    
        for(Customer c : customerList){
            if(c.getCustomerCode() !=null && c.getCustomerCode().equalsIgnoreCase(code)){
                return true;
            }   
        }
        return false;
        
    }
    
    
    
    
    public void showAllCustomers(){
        if(customerList.isEmpty()){
            System.out.println("No Customer data yet");
            return;
        } 
        System.out.println("------------------------------------------------------------------");
        System.out.printf("%-6s | %-22s | %-10s | %-25s\n", "Code", "Customer Name", "Phone", "Email");
        System.out.println("------------------------------------------------------------------");
        
        for(Customer c : customerList){
            System.out.printf("%-6s | %-22s | %-10s | %-25s\n",
            c.getCustomerCode(), c.getName(), c.getPhone(), c.getEmail());   
        }
        System.out.println("------------------------------------------------------------------");
    }
            
    public List<Customer> getCustomers() {
        return customerList;
    }
    
    
    public void registerCustomers() { 
        int option;
        do {
            registerCustomer(); 
            System.out.println("1. Continue entering new customers");
            System.out.println("2. Return to the main menu");
            System.out.print("Enter your option: ");
            try {
                option = sc.nextInt();
                sc.nextLine(); // Xóa bộ đệm
            } catch (Exception e) {
                System.out.println("Invalid option! Please enter a number.");
                sc.nextLine(); // Xóa input sai
                option = 1; // Đặt lại để tiếp tục vòng lặp
            }
        } while (option == 1);
    }
    
    private Customer findCustomerByCode(String code) {
        for (Customer c : customerList) {
            if (c.getCustomerCode().equalsIgnoreCase(code)) {
             return c;
            }
        }
        return null;
    }
    public void updateCustomer() {
        System.out.println("______UPDATE CUSTOMER______");
        int option;
        do {

            System.out.print("Enter customer code to update: ");
            String code = sc.nextLine().trim().toUpperCase();

        
            Customer customer = findCustomerByCode(code);

        
            if (customer == null) {
                System.out.println(" This customer does not exist.");
            } else {
                
                //Nhập thông tin mới (có thể bỏ trống)
                System.out.println("Enter new info:");

                String newName = getUpdatedInput("New name: ", customer.getName(), nameValidator);
                customer.setName(newName);

                String newPhone = getUpdatedInput("New phone: ", customer.getPhone(), phoneValidator);
                customer.setPhone(newPhone);

                String newEmail = getUpdatedInput("New email: ", customer.getEmail(), emailValidator);
                customer.setEmail(newEmail);

                
                System.out.println("|Customer updated successfully!|");
            }

            
            System.out.println("1. Continue updating another customer");
            System.out.println("2. Return to main menu");
            System.out.print("Enter your option: ");

            try {
                option = sc.nextInt();
                sc.nextLine();
            } catch (Exception e) {
                System.out.println("Invalid option! Please enter a number.");
                option = 2;
            }

        } while (option == 1);
}

    private String getUpdatedInput(String prompt, String oldValue, Validator validator) {
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine().trim();

            
            if (input.isEmpty()) {
                return oldValue;
            }

            String error = validator.validate(input);
            if (error == null) {
                return input; 
            }

            System.out.println("❌ " + error); // nếu sai → in lỗi và hỏi lại
        }
    }
        public void searchCustomerByName() {
            System.out.print("Enter full or partial customer name to search: ");
            String keyname = sc.nextLine().trim().toLowerCase();

            List<Customer> matches = new ArrayList<>();

            for (Customer c : customerList) {
              if (c.getName().toLowerCase().contains(keyname)) {
                  matches.add(c);
                }
         }

         if (matches.isEmpty()) {
              System.out.println("No one matches the search criteria!");
            } else {
              // Sắp xếp theo tên (alphabet)
                matches.sort(Comparator.comparing(c -> c.getName().toLowerCase()));

                System.out.println("Matching Customers: " + keyname);
                System.out.println("------------------------------------------------------------------");
                System.out.printf("%-6s | %-22s | %-10s | %-25s\n", "Code", "Customer Name", "Phone", "Email");
                System.out.println("------------------------------------------------------------------");

                for (Customer c : matches) {
                    System.out.println(c.toString());
                }
                System.out.println("------------------------------------------------------------------");
            }

            System.out.println("Press Enter to return to main menu...");
            sc.nextLine();
        }

    
}


