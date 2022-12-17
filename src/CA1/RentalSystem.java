/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CA1;

import javax.swing.*;

/**
 *
 * @author rajka
 */
public class RentalSystem {

    private Comic[] Comics = new Comic[4];
    private Rentee[] Members = new Rentee[4];
    private int option;

    public RentalSystem() {

        Comic C1 = new Comic("978-0785199618", "One Indian Girl", 20, 15.0);
        Comic C2 = new Comic("978-0785199619", "The Seven Husbands ", 20, 15.0);
        Comic C3 = new Comic("978-0785199620", "The After Series", 20, 15.0);
        Comic C4 = new Comic("978-0785199621", "My Little Epiphanies", 20, 15.0);

        Comics[0] = C1;
        Comics[1] = C2;
        Comics[2] = C3;
        Comics[3] = C4;

        Comic[] comicLoanedby1 =
        {
            Comics[0], Comics[1],Comics[3]
        };
        Comic[] comicLoanedby2 =
        {
            Comics[1], Comics[2]
        };
        Comic[] comicLoanedby3 =
        {
            Comics[0], Comics[2]
        };
        Comic[] comicLoanedby4 =
        {
            Comics[1], Comics[3]
        };

        Members[0] = new Rentee("M2109039", "Rajkaran", comicLoanedby1);
        Members[1] = new Rentee("M2209039", "Dreamybull", comicLoanedby2);
        Members[2] = new Rentee("M2309039", "Mia", comicLoanedby3);
        Members[3] = new Rentee("M2409039", "Malkova", comicLoanedby4);

    }

    //need to reassign option
    public void StartMenu() {
        
            
        String opt;
        long startTime = System.nanoTime();
        
        do
        {

            try
            {

                opt = JOptionPane.showInputDialog(null,
                        "Enter your option:\n1. Display all comics\n2. Seach Comic by ISBN-13\n3. Search Rentee by MemberID\n4. Print Earning Statistic\n5. Exit");
                option = Integer.parseInt(opt);
            } catch (NumberFormatException e)
            {
                JOptionPane.showMessageDialog(null, "Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                continue;
            }


            if (option > 5 || option <= 0)
            {
                JOptionPane.showMessageDialog(null, "Invalid option! Please enter in the range from 1 to 5", "Error", JOptionPane.ERROR_MESSAGE);

            }
            else
            {
                switch (option)
                {
                    case 1:

                        String ComicsMsg = "ISBN-13\t\t Title\t\t| Pages\t| Price/Day\t| Deposit\n---------------------------------------------------------------------------------------------------------------------------------------------------------";

                        for (int i = 0; i < Comics.length; i++)
                        {

                            ComicsMsg += "\n" + Comics[i].getISBN() + "\t| " + Comics[i].getTitle() + "\t| " + Comics[i].getPages() + "\t| " + Comics[i].getRentalFee() + "\t| " + Comics[i].getDepositFee();

                        }

                        JOptionPane.showMessageDialog(null, new JTextArea(ComicsMsg, 4, 5), "All Comics", JOptionPane.INFORMATION_MESSAGE);

                        break;

                    case 2:
                        
                        String SearchISBN = JOptionPane.showInputDialog(null, "Enter ISBN-13 to search:");
                        if (SearchISBN == null)
                        {
                            break;
                        }
                        String ISBNmsg = "Cannot find the Comic \"" + SearchISBN + "\"!!";
                        boolean ISBNer = true;
                        for (int i = 0; i < Comics.length; i++)
                        {
                            if (SearchISBN.equals(Comics[i].getISBN()))
                            {

                                ISBNmsg = Comics[i].getTitle() + "\n\nStock purchased at $" + Comics[i].getPrice() + ".\nCost $" + Comics[i].getRentalFee() + " per day to rent." + "\nRequire deposit of $" + Comics[i].getDepositFee();
                                ISBNer = false;

                            }
                        }

                        if (!ISBNer)
                        {
                            JOptionPane.showMessageDialog(null,
                                    ISBNmsg,
                                    "Message",
                                    JOptionPane.INFORMATION_MESSAGE);
                        } else
                        {
                            JOptionPane.showMessageDialog(null, ISBNmsg, "Info", JOptionPane.ERROR_MESSAGE);
                        }

                        break;

                    case 3:

                        String SearchMembID = JOptionPane.showInputDialog(null, "Enter MemberID to search:");
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
                                msg = "MemberID\t| Name\n-------------------------------------------\n" + Members[i].getID() + "\n" + Members[i].getName() + "\n\n" + "Comics Loaned:\n" + Members[i].getComicsTitle() + "\n\n\nTotal Rental Per Day: $" + String.format("%.2f",Members[i].getComicsPrice());
                                errorNo = false;
                            }

                        }
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
                }

            }
        } while (option != 5);

        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;
        long minutes = elapsedTime / 60000000000L;
        long seconds = (elapsedTime % 60000000000L) / 1000000000L;

        JOptionPane.showMessageDialog(null, "Thank you for using Comic Rental.\nWe look forward to serve you in the near future.\n\nYou spent a total of " + minutes + " minutes and " + seconds + " seconds in the program.", "Message", JOptionPane.INFORMATION_MESSAGE);

    }

}
