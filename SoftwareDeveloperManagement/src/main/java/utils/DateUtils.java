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
    // Định dạng ngày chuẩn của đề bài (dd/MM/yyyy)
    public static final String DATE_FORMAT = "dd/MM/yyyy";

    /**
     * Chuyển đổi chuỗi String thành đối tượng Date
     * @param dateStr Chuỗi ngày (ví dụ: "30/01/2026")
     * @return Đối tượng Date (hoặc null nếu lỗi định dạng)
     */
    public static Date parseDate(String dateStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
            // Quan trọng: setLenient(false) để chặn ngày ảo (ví dụ 30/02)
            sdf.setLenient(false); 
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            return null; // Trả về null để bên Inputter biết là nhập sai
        }
    }

    /**
     * Chuyển đổi đối tượng Date thành chuỗi String (để ghi vào file)
     * @param date Đối tượng Date
     * @return Chuỗi ngày (ví dụ: "30/01/2026")
     */
    public static String formatDate(Date date) {
        if (date == null) return "";
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        return sdf.format(date);
    }
    
    /**
     * Hàm tiện ích: Kiểm tra xem ngày có hợp lệ không (dùng nếu cần)
     */
    public static boolean isValidDate(String dateStr) {
        return parseDate(dateStr) != null;
    }
}
