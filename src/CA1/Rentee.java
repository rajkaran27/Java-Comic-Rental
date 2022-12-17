/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CA1;


/**
 *
 * @author rajka
 */
public class Rentee {

    private String Name, ID;
    private Comic[] ComicsLoaned;

    public Rentee(String ID, String Name, Comic[] ComicsLoaned) {
        this.ID = ID;
        this.Name = Name;
        this.ComicsLoaned = ComicsLoaned;
    }

    public String getName() {
        return Name;
    }

    public String getID() {
        return ID;
    }

    public String getComicsTitle() {
        String out = "\n";
        for (int i = 0; i < ComicsLoaned.length; i++)
        {
            out += +(i + 1) + ". " + ComicsLoaned[i].getTitle() + "\n";

        }
        return out;
    }

    public double getComicsPrice() {
        double output = 0;
        for (int i = 0; i < ComicsLoaned.length; i++)
        {
            output += Double.parseDouble(ComicsLoaned[i].getRentalFee());
        }
        return output;
    }

}
