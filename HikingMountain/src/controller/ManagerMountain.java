package controller;


import Data.mountainFile;
import Model.Mountain;
import java.util.ArrayList;
import java.util.List;
public class ManagerMountain {
    private List<Mountain> mountains = new ArrayList<>();   
     public ManagerMountain(String pathFile) {
        try {
            mountainFile file = new mountainFile();
            mountains = file.read(pathFile);
        } catch (Exception e) {
            mountains = new ArrayList<>();
        }
    }
    public List<Mountain> getAll() {
        return mountains;
    }
    public Mountain findByCode(String code) {
        if (code.trim().isEmpty()) {
            return null;
        }
        String findCode = code.trim();
        for ( Mountain m : getAll()) {
            String mountainCode = String.valueOf(m.getMountainCode());
            
            if (mountainCode.equalsIgnoreCase(findCode)) {
            return m;
            }
        }
        return null;
    }                
}
