
package CA1;

import java.io.Serializable;

/**
 *M.Rajkaran
 * 2109039
 * DIT/FT/1B/02
 * 
 */
public class Comic implements Serializable {

    private String ISBN, Title;
    private int Pages;
    private double Price;
 
    public Comic(String ISBN, String Title, int Pages, double Price) {
        this.ISBN = ISBN;
        this.Title = Title;
        this.Pages = Pages;
        this.Price = Price;
        
    }
   
    public String getISBN() {
        return ISBN;
    }

    public String getTitle() {
        return Title;
    }

    public int getPages() {
        return Pages;
    }

    public double getPrice() {
        return Price;
    }

    public String getDepositFee(){
        return String.format("%.2f", (Price*1.1));
    }
    
    public String getRentalFee(){
        return String.format("%.2f", (Price/20));
    }
    
    public String getInfo(){
        String msg;
        msg = "This is a Comic in English";
        return msg;
    }
    
    
}
