package util;
import java.util.Scanner;
public class Inputter {
       private static final Scanner sc = new Scanner(System.in);

 public static String inputString(String prompt){
    System.out.print(prompt);
    return sc.nextLine().trim();
}
 public String getString(String mess) {
        System.out.print(mess);
        return sc.nextLine().trim();
    }
 public static String inputAndLoop(String mess, String pattern){
    while(true){
        System.out.print(mess);
        String input = sc.nextLine().trim();
        if(Acceptable.isValid(input, pattern)){
            return input;
        }
        else{
            System.out.println("Input invalid!");
        }
 }
 }
public static int inputpositiveInt (String mess){
     while(true){
         System.out.print(mess);
         String input = sc.nextLine().trim();
         try{
             int number = Integer.parseInt(input);
             if (number > 0) return number;
            System.out.println("Must be a positive integer!");
         }catch (NumberFormatException e){
             System.out.println("Invalid input!");
         }
     }
 }
} 
    

  
       

