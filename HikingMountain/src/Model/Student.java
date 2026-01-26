package Model;
import java.io.Serializable; 

public class Student implements Serializable { 

    //dòng này để quản lý phiên bản của lớp
    private static final long serialVersionUID = 1L;
    private String id;
    private String name;
    private String phone;
    private String email;
    private String mountainCode;
    private double tutionFee;

    public Student(String id, String name, String phone, String email, String mountainCode) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.mountainCode = mountainCode;
        
        
    }

    public Student() {
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getMountainCode() {
        return mountainCode;
    }

    public void setMountainCode(String mountainCode) {
        this.mountainCode = mountainCode;
    }

    public double getTutionFee() {
        return tutionFee;
    }

    public void setTutionFee(double tutionFee) {
        this.tutionFee = tutionFee;
    }

    @Override
    public String toString() {
        return id +","+name+","+phone+","+email+","+mountainCode+","+tutionFee;
    }
    
   
}

 