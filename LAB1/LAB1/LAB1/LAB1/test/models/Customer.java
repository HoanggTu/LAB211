
package models;


public class Customer {
    private String customerCode;
    private String name;
    private String phone;
    private String email;
    
    public Customer(){
        
    }

    public Customer(String customerCode, String name, String phone, String email) {
        this.customerCode = customerCode;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String toString(){
        return String.format("%-6s | %-22s | %-10s | %-25s", customerCode, name, phone, email);
    }
    
   
    
    
}
