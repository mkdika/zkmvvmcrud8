package com.mkdika.zkmvvmcrud8.helper;

/**
 *
 * @author Maikel Chandika <mkdika@gmail.com>
 */
public class AppUtil {
    public static String getOsPathSlashChar() {
       String tmp = "";
        String os = System.getProperty("os.name").toLowerCase();
        if ((os.contains("win"))) {
            tmp = "\\";
        } else {
            tmp = "/";
        }
        return tmp;       
   }
}
