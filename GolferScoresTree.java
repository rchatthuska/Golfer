import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GolferScoresTree {
   public static void main(String[] args) {
      TreeBag<Golfer> golferTree = new TreeBag();
      Scanner scan = new Scanner(System.in);

      String name;
      try {
         FileInputStream inputStream = new FileInputStream("golferinfo.txt");
         Scanner scanner = new Scanner(inputStream);

         while(scanner.hasNextLine()) {
            name = scanner.nextLine();
            String[] parts = name.split("\\s+");
            String lastName = parts[0];
            int numberOfRounds = Integer.parseInt(parts[1]);
            double averageScore = Double.parseDouble(parts[2]);
            Golfer golfer = new Golfer(lastName, numberOfRounds, averageScore);
            golferTree.add(golfer);
         }

         scanner.close();
         inputStream.close();
      } catch (IOException var14) {
         System.out.println("An error occurred while reading the file: " + var14.getMessage());
      }

      System.out.println("Menu choices: ");
      System.out.println("1.\tDisplay listing to screen of all golfers stats(ordered by lastname)");
      System.out.println("2.\tDisplay the golfers in current tree format(Use displayAsTree )");
      System.out.println("3.\tFind and display one individual golfers stats");
      System.out.println("4.\tUpdate an individual golfers stats(by adding an additional score)");
      System.out.println("5.\tAdd a new golfer to the Database");
      System.out.println("6.\tRemove a golfer from the Database");
      System.out.println("7.\tQuit and update datafile");
      System.out.println("");
      boolean flag = true;

      while(flag) {
         try {
            System.out.print("Enter a number: ");
            int number = scan.nextInt();
            if (number == 1) {
               System.out.println("all golfers stats(ordered by lastname)");
               golferTree.display();
            } else if (number == 2) {
               System.out.println("the golfers in current tree format");
               golferTree.displayAsTree();
               System.out.println();
            } else {
               Golfer golfer6;
               Golfer golfer7;
               if (number == 3) {
                  scan.nextLine();
                  System.out.println("Please enter the last name of the golfer whose information you wish to display.  ");
                  name = scan.nextLine();
                  golfer6 = new Golfer(name);
                  if (golferTree.retrieve(golfer6) == null) {
                     System.out.println("name not found");
                  } else {
                     System.out.println("name found");
                     golfer7 = (Golfer)golferTree.retrieve(golfer6);
                     System.out.println(golfer7.toString());
                  }

                  System.out.println();
               } else if (number == 4) {
                  scan.nextLine();
                  System.out.println("Please enter the last name of the golfer whose information you wish to update.  ");
                  name = scan.nextLine();
                  golfer6 = new Golfer(name);
                  if (golferTree.retrieve(golfer6) == null) {
                     System.out.println("name not found");
                  } else {
                     System.out.println("name found");
                     golfer7 = (Golfer)golferTree.retrieve(golfer6);
                     System.out.println("please enter the addtional score you wish toa add");
                     double score = scan.nextDouble();
                     golfer7.addScore(score);
                     System.out.println("your score added succesfully");
                  }

                  System.out.println();
               } else if (number == 5) {
                  scan.nextLine();
                  System.out.println("please enter the lastname of the golfer: ");
                  name = scan.nextLine();
                  System.out.println("please enter the number of rounds: ");
                  int rounds = scan.nextInt();
                  System.out.println("please enther average score: ");
                  double averageScore = scan.nextDouble();
                  Golfer<Double> golfer5 = new Golfer(name, rounds, averageScore);
                  golferTree.add(golfer5);
                  System.out.println("your new golfer added succesfully");
                  System.out.println();
               } else if (number == 6) {
                  scan.nextLine();
                  System.out.println("Please enter the last name of the golfer whose information you wish to remove.  ");
                  name = scan.nextLine();
                  golfer6 = new Golfer(name);
                  if (golferTree.retrieve(golfer6) == null) {
                     System.out.println("name not found");
                  } else {
                     System.out.println("name found");
                     golfer7 = (Golfer)golferTree.retrieve(golfer6);
                     if (golferTree.remove(golfer7)) {
                        System.out.println("your new golfer deleted succesfully");
                     } else {
                        System.out.println("your new golfer deletion was not succesfull");
                     }
                  }

                  System.out.println();
               } else if (number == 7) {
                  System.out.println("Good Bye");
                  System.out.println();
                  flag = false;
               } else {
                  System.out.println("You entered wrong input");
                  System.out.println();
               }
            }
         } catch (InputMismatchException var13) {
            System.out.println("Invalid input. Please try again.");
            scan.next();
         }
      }

      try {
         File file = new File("golferinfo.txt");
         FileOutputStream fos = new FileOutputStream(file);
         PrintStream ps = new PrintStream(fos);
         System.setOut(ps);
         golferTree.display();
         fos.close();
      } catch (IOException var12) {
         var12.printStackTrace();
      }

   }
}
