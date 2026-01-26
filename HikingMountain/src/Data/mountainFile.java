/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data;

import Model.Mountain;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;



/**
 *
 * @author Hung_PC
 */
public class mountainFile implements IFileReadWrite<Mountain>{
   
   @Override
    public List<Mountain> read(String FILE_NAME) throws Exception {
        List<Mountain> list = new ArrayList<>();
        File f;
        FileInputStream file = null;
        BufferedReader myInput = null;// create Buffer    
        try {
            f = new File(FILE_NAME);//open file
            String fullPath = f.getAbsolutePath(); //get Fullpath of file
            file = new FileInputStream(fullPath);
            myInput = new BufferedReader(new InputStreamReader(file));
            // read line until the end of the file
            String line = null;
            boolean first = true;
            while ((line = myInput.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                if(first){
                    first = false;
                    continue;
                }
                Mountain moun = convertToMountain(line);
                list.add(moun);
            }

        } catch (IOException ex) {
            throw ex;
        } finally {
            if (myInput != null) {
                myInput.close();
            }
            if (file != null) {
                file.close();
            }
        }
        return list;
    }

    @Override
    public boolean write(List<Mountain> list) throws Exception {
        File f;
        return true;
    }

    private String convertToString(Mountain moun) {
        String str = "";
        str = String.format("%s\t%s\t%s\t%s", moun.getMountainCode(), moun.getMountain(),
                 moun.getProvince(), moun.getDescription());
        return str;
    }

    private Mountain convertToMountain(String str) {
        Mountain moun = null;

        String[] p = str.split(",");
        String code = p[0].trim();
        String name = p[1].trim();
        String province = p[2].trim();
        String description = "";
        if (p.length >= 4) {
            description = p[3].trim();
        }
        moun = new Mountain(code, name, province, description);
        return moun;
    }
}

