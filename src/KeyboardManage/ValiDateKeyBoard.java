package KeyboardManage;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ValiDateKeyBoard {
    Scanner input = new Scanner(System.in);


    public int importInt(String a) {
        while (true) {
            try {
                System.out.println("Enter " + a);
               int b = Integer.parseInt(input.nextLine());
               return b;
            }
            catch (Exception e){
                System.out.println("Enter wrong, please try again by number");
                importInt(a);

            }
        }
    }

    public double importDouble(String a) {
        while (true) {
            try {
                System.out.println("Enter " + a);
                double b = Double.parseDouble(input.nextLine());
                return b;
            }
            catch (Exception e){
                System.out.println("Enter wrong, please try again. ex: 5.05");
                importDouble(a);

            }
        }
    }

    public Date importDate(String a) {
        while (true) {
            try {
                System.out.println("Enter " + a);
                Date b = new SimpleDateFormat("dd/MM/yyyy").parse(input.nextLine());
                return b;
            }
            catch (Exception e){
                System.out.println("Enter wrong, please try again. ex: 25/10/2022");
                importDate(a);
            }
        }
    }


    public String importString(String a) {
        while (true) {
            try {
                System.out.println("Enter " + a);
                String b = input.nextLine();
                return b;
            }
            catch (Exception e){
                System.out.println("Enter wrong, please try again.");
                importString(a);
            }
        }
    }

    public int ValiDateChoiceSPM(){
        int choice = importInt("you have to choice from 1 to 6");
        if (choice>0 && choice<7){
            return choice;
        } else {
            return importInt("you have to choice from 1 to 6");
        }
    }

    public int ValiDateChoiceSM(){
        int choice = importInt("you have to choice from 1 to 7");
        if (choice>0 && choice<8){
            return choice;
        } else {
            return importInt("you have to choice from 1 to 7");
        }
    }

    public int ValiDateChoiceIPM(){
        int choice = importInt("you have to choice from 1 to 9");
        if (choice>0 && choice<10){
            return choice;
        } else {
            return importInt("you have to choice from 1 to 9");
        }
    }

    public int ValiDateChoiceSellM(){
        int choice = importInt("you have to choice from 1 to 8");
        if (choice>0 && choice<9){
            return choice;
        } else {
            return importInt("you have to choice from 1 to 8");
        }
    }

}
