/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data; 

import java.util.List;

public interface IFileReadWrite<T> {
    List<T> readData();
    void writeData(List<T> list);
}