/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *
 * @author rechiee
 */
public class DateUtils {
    public static final String DATE_FORMAT="dd/MM/yyyy";
    private static final SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
    
    static {
        sdf.setLenient(false); // Chặn ngày vô lý (30/02)
    }
    
    public static Date parseDate(String dateStr) {
        try {
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            return null;
        }
    }
    
    public static String formatDate(Date date){
        if(date == null) return "";
        return sdf.format(date);
    }
    
    public static boolean isOverlap (Date start1, Date end1, Date start2, Date end2){
        return !start1.after(end2) && !end1.before(start2);
    }
    
}
