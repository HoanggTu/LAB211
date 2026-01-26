/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import model.Tour;
import utils.DateUtils;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author rechiee
 */

public class TourDAO implements IFileReadWrite<Tour> {
    private final String FILE_NAME = "Tours.txt";
    
    @Override
    public List<Tour> readData() {
        List<Tour> list = new ArrayList<>();
        File f = new File(FILE_NAME);
        if (!f.exists()) return list;
        
       
        
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split(",");
                if (p.length >= 9) {
                    list.add(new Tour(p[0].trim(), p[1].trim(), p[2].trim(), Double.parseDouble(p[3].trim()), p[4].trim(),
                            DateUtils.parseDate(p[5].trim()), DateUtils.parseDate(p[6].trim()), 
                            Integer.parseInt(p[7].trim()), p[8].trim()));
                }
            }
        } catch (Exception e) {
            System.err.println("Load Tour Error: " + e.getMessage());
        }
        return list;
    }

    @Override
    public void writeData(List<Tour> list) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Tour t : list) pw.println(t.toString());
        } catch (IOException e) {
            System.err.println("Save error: " + e.getMessage());
        }
    }
}