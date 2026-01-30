package service;

public class CustomerValidator {
    public static class CustomerCodeValidator implements Validator{
        
        private CustomerService service;
        
        public CustomerCodeValidator(CustomerService service) {
        this.service = service;
        
    }
        public String validate(String input){
        if (input == null || input.trim().isEmpty()) {
                return "Customer code cannot be blank!";
        }
        
        if (service.duplicateCode(input)) {
            return "Error: This code already exists!";
        }
        if (!input.matches("^[CGKcgk]\\d{4}$")) {
            return "Error, The first character is C, G, K, followed by 4 digits!";
        }
        return null; 
        }
    }
    
    
    public static class NameValidator implements Validator {
        @Override
        public String validate(String input) {
            if (input == null || input.trim().isEmpty()) {
                return "Name cannot be blank!";
            }
            if (input.length() < 2 || input.length() > 25) {
                return "Name characters must be between 2 and 25!";
            }
            return null; 
        }
    }

    
    public static class PhoneValidator implements Validator {
        @Override
        public String validate(String input) {
            if (input == null || input.trim().isEmpty()) {
                return "Phone cannot be blank!";
            }
            //check loi sai mang Viet Nam va dung 10 so
            if (!input.matches("^(032|033|034|035|036|037|038|039|086|096|097|098|070|076|077|078|079|089|090|093|081|082|083|084|085|088|091|094|052|056|058|092|059|099|087)\\d{7}$") && input.length() !=10) {
                return "Invalid phone number (Must be 10 digits according to Vietnamese network!";
            }
            
            return null; 
        }
    }

    
    public static class EmailValidator implements Validator {
        @Override
        public String validate(String input) {
            if (input == null || input.trim().isEmpty()) {
                return "Email cannot be blank!";
            }
            if (!input.matches("^[a-zA-Z0-9._%+-]{1,64}@[a-zA-Z0-9.-]{2,255}\\.[a-zA-Z]{2,}$")) {
                return "Email is not in correct format!";
            }
            return null; 
        }
    } 
    
    
    
}
