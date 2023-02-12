/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CA1;

import java.io.*;

/**
 *
 * @author rajka
 */
public class Manga extends Comic implements Serializable{

    private String Language;

    public Manga(String ISBN, String Title, int Pages, double Price, String Language) {
        super(ISBN, Title, Pages, Price);
        this.Language = Language;
    }

    //check if transalted
    public String getInfo() {
        String msg = null;
        if (Language.equals("JP"))
        {
            msg="This is a Manga in Japanese";
        } else
        {
            msg = "This is a Manga translated to English";
        }

        return msg;
    }

    public String getLanguage() {
        return Language;
    }
    
}
