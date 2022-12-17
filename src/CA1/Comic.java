/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CA1;

/**
 *
 * @author rajka
 */
public class Comic {

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
    
    
    
}
