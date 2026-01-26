package controller;

import Model.Mountain;
import Model.Student;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import static javax.script.ScriptEngine.FILENAME;
import util.Inputter;
import util.Acceptable;

public class ManagerStudent {

    private List<Student> list;
    private final ManagerMountain managerMountain;

    public ManagerStudent() {
        list = new ArrayList<>(); 
        managerMountain = new ManagerMountain("MountainList.csv");
    }

    public Student getStudentByID(String id) {
       
        for (Student student : list) { 
            if (student.getId().equalsIgnoreCase(id)) {
                return student;
            }
        }
        return null;
    }
    

    //=========================functrion 1===================================
   public  boolean addNewRegistration(Student newStudent){
       if(getStudentByID(newStudent.getId())!= null){
           return false;
       }
       return list.add(newStudent);
   }
   public boolean addNewRegistration(){
//       System.out.println(managerMountain.getAll().size());
       String id = Inputter.inputAndLoop("Enter ID: ", Acceptable.STU_ID_VALID);
        String name = Inputter.inputAndLoop("Enter name: ", Acceptable.NAME_VALID);
        String phone = Inputter.inputAndLoop("Enter phone number: ", Acceptable.PHONE_VALID);
         String email = Inputter.inputAndLoop("Enter Email: ", Acceptable.Email); 
         double tuitionFee=6000000;
         if(phone.matches(Acceptable.VIETTEL_VALID)|| phone.matches(Acceptable.VNPT_VALID))
             tuitionFee=tuitionFee*0.65;
         String mountain=Inputter.inputString("Enter mountain: ");
         Model.Student s = new Model.Student(id, name, phone, email, mountain);
         s.setTutionFee(tuitionFee);
         list.add(s);
         return true;
   }

    //=========================functrion 2====================================
    public  boolean updateRegistration(Scanner sc){
        System.out.print("Enter ma sinh vien can update: ");
        String id= sc.nextLine().trim();
        Student s = getStudentByID(id);
        if(s==null){
            System.out.println("Thong tin cua sinh vien nay khong ton tai");
         return false;
        }
        System.out.println("====Cap nhat thong tin sinh vien====");
        String name=Inputter.inputAndLoop("Enter new name: ", Acceptable.NAME_VALID);
        String phone = Inputter.inputAndLoop("Enter new phone number: ",Acceptable.PHONE_VALID);
        String email=Inputter.inputAndLoop("Enter new email address: ",Acceptable.Email );
        System.out.print("Enter new mountain: ");
        String mountain = sc.nextLine().trim();
         double tuitionFee=6000000;
         if(phone.matches(Acceptable.VIETTEL_VALID)||phone.matches(Acceptable.VNPT_VALID)){
             tuitionFee=tuitionFee*0.65;
         }
         if(!mountain.isEmpty()){
             if(managerMountain.findByCode(mountain)!=null){
                 s.setMountainCode(mountain);
                 System.out.println("Da cap nhat nui moi: ");
             }else System.out.println("Chua cap nhat, giu ma nui cu");
         }
          s.setTutionFee(tuitionFee);
         s.setName(name);
         s.setPhone(phone);
         s.setEmail(email);
         
         System.out.println("Da update xong: ");
        return true;
    }

    //=========================functrion 3====================================
   public void showAllList(){
       if(list.isEmpty()){
           System.out.println("Chua co sinh vien nao dang ky");       
       }
       System.out.println("Show all list student: ");
       System.out.println("---------------------------------------------------------------------------------------------");
       System.out.printf(" %-10s | %-20s | %-15s | %-20s | %-10s | %-15s%n","ID","NAME","PHONE","EMAIL","PEAK CODE","FEE");
       System.out.println("---------------------------------------------------------------------------------------------");
       for(Student s: list){
           System.out.printf(" %-10s | %-20s | %-15s | %-25s | %-10s | %-15.2f%n",
           s.getId(),
           s.getName(),
           s.getPhone(),
           s.getEmail(),
           s.getMountainCode(),
           s.getTutionFee());
            
       }
     
   }

    //=========================functrion 4====================================
      public boolean deleteInformation(Scanner sc){
          System.out.println("Nhap id sinh vien can xoa:");
          String id = sc.nextLine().trim();
          Student s = getStudentByID(id);
          if(s==null){
              System.out.println("sinnh vien nay chua dang ky: "+id);
              return false;
          }
          System.out.println("Ban muon xoa Y/N: ");
          String yes=sc.nextLine().trim();
          if(yes.equalsIgnoreCase("Y")){
              list.remove(s);
              System.out.println("Da xoa thanh cong: "+id);
              return true;
          }else{
              System.out.println("Huy xoa:");
              return false;
          }
       
      } 

    //=========================functrion 5====================================
      
    //=========================functrion 6====================================
    public void SearchByCampus(Scanner sc) {
        System.out.print("Ma Campus (SE, HE, QE, DE, CE): ");
        String campus = sc.nextLine().trim().toUpperCase();
        

        List<Student> results = new ArrayList<>();
        for (Student s : list) {
            if (s.getId().substring(0, 2).equalsIgnoreCase(campus)) {
                results.add(s);

            }
        }
 if(results.isEmpty()){
     System.out.println("Khong thay campus nao trong danh sach: ");
 }else{
     
 
        System.out.println("Search student by Campus: ");
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.printf("%-10s | %-15s | %-12s | %-25s | %-10s | %-10s%n","ID"," Name","Phone","Email","Peak Code","Fee");
        System.out.println("--------------------------------------------------------------------------------------------");
        for (Student s : results) {
            System.out.printf("%-10s | %-15s | %-12s | %-25s | %-10s | %-10.2f%n",
                    s.getId(),
                    s.getName() ,
                    s.getPhone(), 
                    s.getEmail(),
                    s.getMountainCode(),
                    s.getTutionFee());
        }
 }
    }

    //=========================functrion 7====================================
    public void showPeopleAdd() {
        if (list.isEmpty()) {
            System.out.println("Chua co ai dk");
            return;
        }

        Map<String, List<Student>> registrationsByMountain = new HashMap<>();
        for (Student s : list) {
            String mountainCode = s.getMountainCode();

            registrationsByMountain.putIfAbsent(mountainCode, new ArrayList<>());

            registrationsByMountain.get(mountainCode).add(s);
        }

        System.out.println("--- Thong ke so luong dang ky theo ngon nui ---");
        String header = "-----------------------------------------------------------------------------------------";
        System.out.println(header);
        System.out.printf("| %-15s | %-25s | %-20s | %-16s |%n", "Ma nui", "Ten nui", "So luong SV", "Tong chi phi");
        System.out.println(header);

        for (Map.Entry<String, List<Student>> entry : registrationsByMountain.entrySet()) {
            String mountainCode = entry.getKey();
            List<Student> studentsInGroup = entry.getValue();

            int participantCount = studentsInGroup.size();

            double totalFee = 0;
            for (Student student : studentsInGroup) {
                totalFee += student.getTutionFee();
            }

            Mountain mountain = managerMountain.findByCode(mountainCode);
            String mountainName = (mountain != null) ? mountain.getMountain() : "N/A";

            System.out.printf("| %-15s | %-25s | %-20d | %,.0f VND |%n",
                    mountainCode, mountainName, participantCount, totalFee);
        }
        System.out.println(header);
    }

    //=========================functrion 8====================================
    public void saveDataToFile() {

        try ( ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILENAME))) {

            oos.writeObject(list);

            System.out.println("Registration data has been successfully saved to `" + FILENAME + "`.");

        } catch (IOException e) {
            System.err.println("Da xay ra loi khi luu file: " + e.getMessage());
        }
    }

    public void loadDataFromFile() {
        try ( ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILENAME))) {

            list = (List<Student>) ois.readObject();

            System.out.println("Da tai du lieu thanh cong tu file " + FILENAME);

        } catch (FileNotFoundException e) {
            System.out.println("Khong tim thay file du lieu. Khoi tao danh sach moi.");
            list = new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) { 
            System.err.println("File du lieu bi loi. Khoi tao danh sach moi.");
            list = new ArrayList<>();
        }
    }

    //=========================functrion 9====================================
    public boolean ExitProgram(Scanner sc) {
        System.out.println("Exit");
        return true;
    }

}
