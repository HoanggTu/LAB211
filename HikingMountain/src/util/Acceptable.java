/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

/**
 *
 * @author buiva
 */
public class Acceptable {
     public static final String STU_ID_VALID="^[CcDdHhSsQq][Ee]\\d{6}$";
    public static final String NAME_VALID="^.{2,20}$";
    public static final String DOUBLE_VALID ="^[+-]?\\\\d+.?(\\\\d+)?";
    public static final String INTEGER_VALID="\\d+";
    public static final String PHONE_VALID="0\\d{9}$";
    public static final String VNPT_VALID="(081|082|083|084|085|088|092|094)\\d{7}$";
    public static final String VIETTEL_VALID="(086|096|097|098|032|033|034\035|036|037|038|039)\\d{7}$";
    public static final String Email="^[\\w._%+-]+@[\\w.-]+\\.[A-Za-z]{2,6}$"
                                      ;
    public static  boolean isValid(String data, String pattern){
        return data.matches(pattern);
    }
}
