/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import model.Homestay;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.nio.charset.StandardCharsets;
/**
 *
 * @author rechiee
 */


public class HomestayDAO implements IFileReadWrite<Homestay> {
    private final String FILE_NAME = "Homestays.txt";

    @Override
    public List<Homestay> readData() {
        List<Homestay> list = new ArrayList<>();
        File f = new File(FILE_NAME);

        if (!f.exists()) {
            System.err.println("❌ LỖI: Không tìm thấy file tại " + f.getAbsolutePath());
            return list;
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f), StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                String[] p = line.split("-");

              
                if (p.length >= 5) {
                    try {
                        String id = p[0].trim();
                        String name = p[1].trim();
                        int room = Integer.parseInt(p[2].trim());
                        
                        int capacity = Integer.parseInt(p[p.length - 1].trim());

                        String address = "";
                        for (int i = 3; i < p.length - 1; i++) {
                            address += p[i] + (i < p.length - 2 ? "-" : "");
                        }
                        address = address.trim();

                        double price = 0.0;
                        list.add(new Homestay(id, name, room, address, capacity, price));
                        
                    } catch (NumberFormatException e) {
                        System.err.println("Lỗi dòng: " + line);
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Load Homestay Error: " + e.getMessage());
        }
        return list;
    }

    @Override
    public void writeData(List<Homestay> list) {
    try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Homestay h : list) {
                pw.println(String.format("%s-%s-%d-%s-%d", 
                        h.getHomeID(), h.getHomeName(), h.getRoomNumber(), 
                        h.getAddress(), h.getMaxCapacity()));
            }
        } catch (IOException e) {
            System.err.println("Save error: " + e.getMessage());
        }
    }
}