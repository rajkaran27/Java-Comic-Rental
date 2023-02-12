/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CA1;

import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author rajka
 */
public class ReadWriteFile {

    public void WriteComic(ArrayList<Comic> Comics) {
        try
        {
            PrintWriter pw = new PrintWriter(new FileWriter("comics.txt", false));

            for (int i = 0; i < Comics.size(); i++)
            {
                    Comic comic = Comics.get(i);
                    if (comic instanceof Manga)
                    {
                        Manga manga = (Manga) comic;

                        pw.println(comic.getISBN() + ";" + comic.getTitle() + ";" + comic.getPages() + ";" + comic.getPrice() + ";Manga;" + manga.getLanguage());
                    } else
                    {

                        pw.println(comic.getISBN() + ";" + comic.getTitle() + ";" + comic.getPages() + ";" + comic.getPrice() + ";Comic;EN");
                    }
                
            }

            pw.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }

    }
    
    
    public void ReadRentees(ArrayList<Rentee> Members, ArrayList<Comic> Comics) {
        try
        {
            BufferedReader br = new BufferedReader(new FileReader("rentees.txt"));
            String line = null;
            while ((line = br.readLine()) != null)
            {
                String[] memberDetails = line.split(";");
                String ID = memberDetails[0];
                String Name = memberDetails[1];
                String[] ISBNs = memberDetails[2].split("#");
                ArrayList<Comic> ComicsLoaned = new ArrayList<Comic>();
                for (String ISBN : ISBNs)
                {
                    for (Comic comic : Comics)
                    {
                        if (comic.getISBN().equals(ISBN))
                        {
                            ComicsLoaned.add(comic);
                            break;
                        }
                    }
                }
                Rentee rentee = new Rentee(ID, Name, ComicsLoaned.toArray(new Comic[ComicsLoaned.size()]));
                Members.add(rentee);
            }

        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void ReadComics(ArrayList<Comic> Comics) {

        try
        {
            BufferedReader br = new BufferedReader(new FileReader("comics.txt"));
            String line = null;

            while ((line = br.readLine()) != null)
            {
                String[] comicDetails = line.split(";");
                String ISBN = comicDetails[0];
                String Title = comicDetails[1];
                int Pages = Integer.parseInt(comicDetails[2]);
                double Price = Double.parseDouble(comicDetails[3]);
                String comicType = comicDetails[4];
                String Language = comicDetails[5];

                if (comicType.equals("Comic"))
                {
                    Comic comic = new Comic(ISBN, Title, Pages, Price);
                    Comics.add(comic);
                } else
                {
                    Manga manga = new Manga(ISBN, Title, Pages, Price, Language);
                    Comics.add(manga);
                }
            }

        } catch (IOException e)
        {
            e.printStackTrace();
        }

    }

}
