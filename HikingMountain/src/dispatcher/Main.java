package dispatcher;
import View.menu;
import controller.ManagerMountain;
import controller.ManagerStudent;
import java.util.Scanner;
public class Main {  
    public static void main(String[] args) throws Exception {   
    Scanner sc = new Scanner(System.in);
    menu mn = new menu();
    ManagerStudent mang = new ManagerStudent();
    ManagerMountain managerMountain = new ManagerMountain("MountainList.csv");   
        while (true) {           
        mn.showMenu();
        int choice = sc.nextInt();
      

            switch (choice) {
                case 1:
                    sc.nextLine();
                    mang.addNewRegistration();
                    break;
                case 2:
                    sc.nextLine();
                    mang.updateRegistration(sc);
                    break;
                case 3:
                    mang.showAllList();
                    break;
                case 4:
                    sc.nextLine();
                    mang.deleteInformation(sc);
                    break;
                case 6:
                    mang.SearchByCampus(sc);
                    break;
                case 7:
                    mang.showPeopleAdd();
                    break;
                case 8:
                    mang.saveDataToFile();
                    break;
                case 9:
                    mang.ExitProgram(sc);
                    break;
                
            }
        }
    }
}



