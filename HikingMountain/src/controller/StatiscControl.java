package controller;

import Model.StatisticInfo; 
import Model.Student;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author buiva
 */
public class StatiscControl extends HashMap<String, StatisticInfo> {

    private final String HEADER_TABLE
            = "+-----------------+---------------------------+------------------+\n"
            + "| Peak Name       | Number of Participants    | Total Cost       |\n"
            + "+-----------------+---------------------------+------------------+";
    private final String FOOTER_TABLE
            = "+-----------------+---------------------------+------------------+";

    
    public StatiscControl() {
        super();
    }
    
    
    public StatiscControl(List<Student> l) {
        super();
        this.statisticalize(l);
    }
    
    public final void statisticalize(List<Student> l) {
        for (Student i : l) {
            if (this.containsKey(i.getMountainCode())) {
                StatisticInfo x = this.get(i.getMountainCode());
                x.setNumOfRegistration(x.getNumOfRegistration() + 1);
                x.setTotalCost(x.getTotalCost() + i.getTutionFee()); 
            } else {
                
                StatisticInfo z = new StatisticInfo(i.getMountainCode(), 1, i.getTutionFee());
                this.put(i.getMountainCode(), z);
            }
        }
    }
    
    public void show() {
        System.out.println(HEADER_TABLE);
        
        for (StatisticInfo i : this.values()) {
            System.out.println(i);
        }
        System.out.println(FOOTER_TABLE);
    }
}