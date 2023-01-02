package CA1;

import java.util.ArrayList;
import javax.swing.*;

/**
 * M.Rajkaran 2109039 DIT/FT/1B/02
 *
 */
public class RentalSystem {

    private Rentee[] Members = new Rentee[4];
    private int option;
    private ArrayList<Comic> Comics = new ArrayList<Comic>();

    public RentalSystem() {

        //new Comics
        Comic C1 = new Comic("978-0785199618", "One Indian Girl", 20, 15.0);
        Comic C2 = new Comic("978-0785190219", "The Seven Husbands ", 20, 15.0);
        Comic C3 = new Comic("978-0785198956", "The After Series", 20, 15.0);
        Comic C4 = new Comic("978-0785156598", "My Little Epiphanies", 20, 15.0);

        //Adding books to Comic array
        Comics.add(C1);
        Comics.add(C2);
        Comics.add(C3);
        Comics.add(C4);

        // Rented books
        Comic[] allComics = Comics.toArray(new Comic[Comics.size()]);

        Comic[] comicLoanedby1 =
        {
            allComics[0], allComics[1], allComics[3]
        };
        Comic[] comicLoanedby2 =
        {
            allComics[1], allComics[2]
        };
        Comic[] comicLoanedby3 =
        {
            allComics[0], allComics[2]
        };
        Comic[] comicLoanedby4 =
        {
            allComics[1], allComics[3]
        };

        //Creating members and adding them to members array
        Members[0] = new Rentee("M2109039", "Rajkaran", comicLoanedby1);
        Members[1] = new Rentee("M2209039", "Anil", comicLoanedby2);
        Members[2] = new Rentee("M2309039", "Amai", comicLoanedby3);
        Members[3] = new Rentee("M2409039", "Potta Surya", comicLoanedby4);

    }

    public void StartMenu() {

        String opt;

        // start timer
        long startTime = System.nanoTime();

        do
        {

            try
            {

                opt = JOptionPane.showInputDialog(null,
                        "Enter your option:\n1. Display all comics\n2. Seach Comic by ISBN-13\n3. Search Rentee by MemberID\n4. Print Earning Statistic\n5. Administrator\n6. Exit");

                // If user presses cancel, program will stop
                if (opt == null)
                {
                    break;
                }

                option = Integer.parseInt(opt);
                // to ensure input is integer

            } catch (NumberFormatException e)
            {

                JOptionPane.showMessageDialog(null, "Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);

                continue;
            }

            // validating option entered
            if (option > 6 || option <= 0)
            {

                JOptionPane.showMessageDialog(null, "Invalid option! Please enter in the range from 1 to 5", "Error", JOptionPane.ERROR_MESSAGE);

            } else

            {
                switch (option)

                {
                    case 1:

                        String ComicsMsg = "ISBN-13\t\t Title\t\t| Pages\t| Price/Day\t| Deposit\n---------------------------------------------------------------------------------------------------------------------------------------------------------";

                        for (int i = 0; i < Comics.size(); i++)
                        {

                            ComicsMsg += "\n" + Comics.get(i).getISBN() + "\t| " + Comics.get(i).getTitle() + "\t| " + Comics.get(i).getPages() + "\t| " + Comics.get(i).getRentalFee() + "\t| " + Comics.get(i).getDepositFee();

                        }

                        JOptionPane.showMessageDialog(null, new JTextArea(ComicsMsg, 4, 5), "All Comics", JOptionPane.INFORMATION_MESSAGE);

                        break;

                    case 2:

                        String SearchISBN = JOptionPane.showInputDialog(null, "Enter ISBN-13 to search:");

                        // If user presses cancel, will go back to main menu
                        if (SearchISBN == null)
                        {
                            break;
                        }

                        String ISBNmsg = "Cannot find the Comic \"" + SearchISBN + "\"!!";

                        boolean ISBNer = true;

                        for (int i = 0; i < Comics.size(); i++)
                        {
                            if (SearchISBN.equals(Comics.get(i).getISBN()))
                            {

                                ISBNmsg = Comics.get(i).getTitle() + "\n\nStock purchased at $" + Comics.get(i).getPrice() + ".\nCost $" + Comics.get(i).getRentalFee() + " per day to rent." + "\nRequire deposit of $" + Comics.get(i).getDepositFee();

                                ISBNer = false;
                            }
                        }

                        //if else statement to decide which messagedialog will be sent
                        if (!ISBNer)
                        {

                            //if book is found
                            JOptionPane.showMessageDialog(null,
                                    ISBNmsg,
                                    "Message",
                                    JOptionPane.INFORMATION_MESSAGE);
                        } else
                        {
                            // if book is not found
                            JOptionPane.showMessageDialog(null, ISBNmsg, "Info", JOptionPane.ERROR_MESSAGE);
                        }

                        break;

                    case 3:

                        String SearchMembID = JOptionPane.showInputDialog(null, "Enter MemberID to search:");

                        // If user presses cancel, will go back to main menu
                        if (SearchMembID == null)
                        {
                            break;
                        }

                        String msg = "Cannot find the Member \"" + SearchMembID + "\"!!";

                        boolean errorNo = true;

                        for (int i = 0; i < Members.length; i++)
                        {
                            if (SearchMembID.equals(Members[i].getID()))
                            {
                                msg = "MemberID\t| Name\n-------------------------------------------\n" + Members[i].getID() + "\n" + Members[i].getName() + "\n\n" + "Comics Loaned:\n" + Members[i].getComicsTitle() + "\n\n\nTotal Rental Per Day: $" + String.format("%.2f", Members[i].getComicsPrice());

                                errorNo = false;
                            }

                        }

                        // To choose which message to output
                        if (!errorNo)
                        {

                            JOptionPane.showMessageDialog(null, msg, "Info", JOptionPane.INFORMATION_MESSAGE);

                        } else
                        {

                            JOptionPane.showMessageDialog(null, msg, "Info", JOptionPane.ERROR_MESSAGE);

                        }

                        break;

                    case 4:

                        double totalEarning = 0;

                        for (int i = 0; i < Members.length; i++)
                        {

                            totalEarning += Members[i].getComicsPrice();

                        }

                        String Totalmsg = "Earning Per Day:\n------------------\n\nThere are " + Members.length + " Rentees in total.\n\nAverage earning per day based on number of rentees is $" + String.format("%.2f", totalEarning / Members.length) + ".\n\nTotal earning per day is $" + String.format("%.2f", totalEarning) + ".";

                        JOptionPane.showMessageDialog(null, Totalmsg, "Info", JOptionPane.INFORMATION_MESSAGE);

                        break;

                    case 5:
                        String Admin = JOptionPane.showInputDialog(null, "Password: ");

                        // If user presses cancel, will go back to main menu
                        if (Admin == null)
                        {
                            break;
                        }

                        //Admin password
                        String password = "Admin";

                        int AdOp = 0;

                        if (Admin.equals(password))
                        {

                            do
                            {

                                try
                                {
                                    String AO = JOptionPane.showInputDialog(null, "Enter your optionL\n1. Add a new book\n2. Delete an existing book\n3.Exit");

                                    // if user selects cancel or cross button, program returns to main menu
                                    if (AO == null)
                                    {
                                        break;
                                    }

                                    AdOp = Integer.parseInt(AO);
                                } catch (NumberFormatException e)
                                {
                                    JOptionPane.showMessageDialog(null, "Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                                    continue;
                                }

                                if (AdOp > 3 || AdOp <= 0)
                                {
                                    JOptionPane.showMessageDialog(null, "Invalid option! Please enter in the range from 1 to 3", "Error", JOptionPane.ERROR_MESSAGE);
                                } else
                                {
                                    switch (AdOp)
                                    {
                                        case 1:

                                            //ISBN
                                            String ISBN;
                                            do
                                            {
                                                ISBN = JOptionPane.showInputDialog(null, "Enter ISBN-13:", "Add Book", JOptionPane.INFORMATION_MESSAGE);
                                                //check if input is empty
                                                if (ISBN.trim().isEmpty())
                                                {
                                                    JOptionPane.showMessageDialog(null, "Please enter an ISBN.", "Error", JOptionPane.ERROR_MESSAGE);
                                                }

                                            } while (ISBN.trim().isEmpty());

                                            // to check if ISBN exists
                                            boolean doesExist = false;
                                            for (int i = 0; i < Comics.size(); i++)
                                            {
                                                if (Comics.get(i).getISBN().equals(ISBN))
                                                {
                                                    doesExist = true;
                                                    break;
                                                }
                                            }

                                            if (doesExist)
                                            {
                                                JOptionPane.showMessageDialog(null, "A Comic with that ISBN already exists!", "Error", JOptionPane.ERROR_MESSAGE);
                                                break;
                                            }

                                            // Title
                                            String Title;
                                            do
                                            {
                                                Title = JOptionPane.showInputDialog(null, "Enter title of the book:", "Add Book", JOptionPane.INFORMATION_MESSAGE);
                                                //check if input is empty
                                                if (Title.trim().isEmpty())
                                                {
                                                    JOptionPane.showMessageDialog(null, "Please enter a Title.", "Error", JOptionPane.ERROR_MESSAGE);
                                                }
                                            } while (Title.trim().isEmpty());

                                            int Pages = 0;
                                            boolean validInput = false;

                                            while (!validInput)
                                            {
                                                try
                                                {
                                                    String Pg = JOptionPane.showInputDialog(null, "Enter the number of pages:", "Add Book", JOptionPane.INFORMATION_MESSAGE);
                                                    Pages = Integer.parseInt(Pg);
                                                    validInput = true;
                                                } catch (NumberFormatException e)
                                                {
                                                    JOptionPane.showMessageDialog(null, "Please enter a valid number of pages");
                                                }
                                            }
                                            double Price = 0;
                                            validInput = false;

                                            while (!validInput)
                                            {
                                                try
                                                {
                                                    String Pr = JOptionPane.showInputDialog(null, "Enter the price of the book:", "Add Book", JOptionPane.INFORMATION_MESSAGE);
                                                    Price = Double.parseDouble(Pr);
                                                    validInput = true;

                                                } catch (NumberFormatException e)
                                                {
                                                    JOptionPane.showMessageDialog(null, "Please enter a valid price");
                                                }
                                            }

                                            //Adding new comic
                                            Comic newComic = new Comic(ISBN, Title, Pages, Price);
                                            Comics.add(newComic);

                                            break;

                                        case 2:

                                            //Searching through comic array with ISBN
                                            String ISBNDel = JOptionPane.showInputDialog(null, "Enter ISBN-13:", "Remove Book", JOptionPane.INFORMATION_MESSAGE);

                                            if (ISBNDel == null)
                                            {
                                                break;
                                            }

                                            ISBNmsg = "Cannot find the Comic \"" + ISBNDel + "\"!!";
                                            boolean DeleteISBN = true;
                                            int indexDel;
                                            for (int i = 0; i < Comics.size(); i++)
                                            {
                                                if (ISBNDel.equals(Comics.get(i).getISBN()))
                                                {
                                                    indexDel = i;
                                                    ISBNmsg = "Book of ISBN \'" + ISBNDel + "\' has been deleted";
                                                    //deleting comic
                                                    Comics.remove(Comics.get(i));

                                                }
                                            }

                                            if (!DeleteISBN)
                                            {
                                                JOptionPane.showMessageDialog(null, ISBNmsg, "Info", JOptionPane.ERROR_MESSAGE);

                                            } else
                                            {
                                                JOptionPane.showMessageDialog(null, ISBNmsg, "Message", JOptionPane.INFORMATION_MESSAGE);
                                            }

                                            break;

                                    }
                                }

                            } while (AdOp != 3);

                        } else
                        {
                            JOptionPane.showMessageDialog(null, "Wrong password!", "Access Denied", JOptionPane.ERROR_MESSAGE);
                            break;
                        }

                        break;

                }

            }
        } while (option != 6);

        //to calculate end time
        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;
        long minutes = elapsedTime / 60000000000L;
        long seconds = (elapsedTime % 60000000000L) / 1000000000L;

        JOptionPane.showMessageDialog(null, "Thank you for using Comic Rental.\nWe look forward to serve you in the near future.\n\nYou spent a total of " + minutes + " minutes and " + seconds + " seconds in the program.", "Message", JOptionPane.INFORMATION_MESSAGE);

    }

}
