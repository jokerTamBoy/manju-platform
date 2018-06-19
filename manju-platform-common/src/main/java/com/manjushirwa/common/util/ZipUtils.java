package com.manjushirwa.common.util;

/*
* Zip.java
*
* Created on 2008��4��2��, ����2:20
*
* To change this template, choose Tools | Template Manager
* and open the template in the editor.
*/

/**
*   压缩工具类
* @author GuoJiale
*/
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


public class ZipUtils {
    static final int BUFFER = 2048;
    
    public static boolean zip( String[] filename ,String outname){
        
        boolean test = true;
        try {
            BufferedInputStream origin = null;
            FileOutputStream dest = new FileOutputStream(outname);
            ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(
                    dest));
            byte data[] = new byte[BUFFER];
            // File f= new File("d:\\111\\");
            //   File files[] = f.listFiles();
            
            for (int i = 0; i < filename.length; i++) {
                File file = new File(filename[i]);
                FileInputStream fi = new FileInputStream(file);
                origin = new BufferedInputStream(fi, BUFFER);
                ZipEntry entry = new ZipEntry(file.getName());
                out.putNextEntry(entry);
                int count;
                while ((count = origin.read(data, 0, BUFFER)) != -1) {
                    out.write(data, 0, count);
                }
                origin.close();
            }
            out.close();
        } catch (Exception e) {
            test = false;
            e.printStackTrace();
        }
        return test;
    }
}
 
