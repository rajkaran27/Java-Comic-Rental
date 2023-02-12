package CA1;

import java.util.ArrayList;
import javax.swing.*;
import java.io.*;

/**
 * M.Rajkaran 2109039 DIT/FT/1B/02
 *
 */
public class RentalSystem implements Serializable {

    private ReadWriteFile RW = new ReadWriteFile();

    private ArrayList<Rentee> Members = new ArrayList<Rentee>();
    private ArrayList<Comic> Comics = new ArrayList<Comic>();

    public RentalSystem() {

        RW.ReadComics(Comics);
        RW.ReadRentees(Members, Comics);

    }

    public int getComicIndex(String ISBN) {
        int index = 0;
        for (int i = 0; i < Comics.size(); i++)
        {

            if (Comics.get(i).getISBN().equals(ISBN))
            {
                index = i;
            }
        }
        return index;
    }

    public int getMemberIndex(String MemberID) {
        int index = 0;
        for (int i = 0; i < Members.size(); i++)
        {

            if (Members.get(i).getID().equals(MemberID))
            {
                index = i;
            }
        }
        return index;
    }

    public int getSizeOfComic() {
        int size = Comics.size();
        return size;
    }

    public int getSizeOfMember() {
        int size = Members.size();
        return size;
    }

    public Comic searchComicByIndex(int index) {

        Comic comicFound = Comics.get(index);

        return comicFound;

    }

    public Rentee searchMemberByIndex(int index) {

        Rentee memberFound = Members.get(index);

        return memberFound;

    }

    public Comic SearchBook(String ISBN) {
        Comic comicfound = null;

        for (int i = 0; i < Comics.size(); i++)
        {
            if (ISBN.equals(Comics.get(i).getISBN()))
            {
                comicfound = Comics.get(i);
            }
        }

        return comicfound;
    }

    public Rentee SearchMember(String MemberID) {
        Rentee memberFound = null;

        for (int i = 0; i < Members.size(); i++)
        {
            if (MemberID.equals(Members.get(i).getID()))
            {
                memberFound = Members.get(i);

                //msg = "MemberID\t| Name\n-------------------------------------------\n" + Members.get(i).getID() + "\n" + Members.get(i).getName() + "\n\n" + "Comics Loaned:\n" + Members.get(i).getComicsTitle() + "\n\n\nTotal Rental Per Day: $" + String.format("%.2f", Members.get(i).getComicsPrice());               
            }
        }

        return memberFound;

    }

    public String calculateTotalEarning() {
        double totalEarning = 0;

        for (int i = 0; i < Members.size(); i++)
        {

            totalEarning += Members.get(i).getComicsPrice();

        }

        String Totalmsg = "Earning Per Day:\n------------------\n\nThere are " + Members.size() + " Rentees in total.\n\nAverage earning per day based on number of rentees is $" + String.format("%.2f", totalEarning / Members.size()) + ".\n\nTotal earning per day is $" + String.format("%.2f", totalEarning) + ".";

        return Totalmsg;
    }

    public void AddComic(String ISBN, String Title, int Pages, double Price, String Language, String comicType) {

        if (comicType.equals("Manga"))
        {
            Manga manga = new Manga(ISBN, Title, Pages, Price, Language);
            Comics.add(manga);
        } else
        {
            Comic comic = new Comic(ISBN, Title, Pages, Price);
            Comics.add(comic);
        }
        RW.WriteComic(Comics);
    }

    public boolean CheckExistComic(String ISBN) {
        boolean DeleteISBN = false;
        int indexDel;
        for (int i = 0; i < Comics.size(); i++)
        {
            if (ISBN.equals(Comics.get(i).getISBN()))
            {

                DeleteISBN = true;
            }
        }
        return DeleteISBN;

    }

}
