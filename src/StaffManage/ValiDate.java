package StaffManage;


import KeyboardManage.ValiDateKeyBoard;

public class ValiDate {
    ValiDateKeyBoard valiDateKeyBoard = new ValiDateKeyBoard();

    public String position() {
        System.out.println("choice position");
        System.out.println("1. manager");
        System.out.println("2. leader");
        System.out.println("3. staff");

        String pos = "";
        int choice = valiDateKeyBoard.importInt("choice");
        if (choice == 1 || choice == 2 || choice == 3) {
            switch (choice) {
                case 1:
                   pos  = "manager";
                    break;
                case 2:
                    pos = "leader";
                    break;
                case 3:
                    pos = "staff";
                    break;
            }
        } else {
            position();
        }
        return pos;
    }
}
