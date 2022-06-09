package Menu;


import KeyboardManage.ValiDateKeyBoard;
import StaffManage.ReadAndWriteStaffList;
import StaffManage.Staff;

import java.util.ArrayList;
import java.util.List;

public class AccountMenu {
    List<Staff> staffs = new ArrayList<>();
    ReadAndWriteStaffList readAndWriteStaffList = new ReadAndWriteStaffList();
    ValiDateKeyBoard valiDateKeyBoard = new ValiDateKeyBoard();

    public Staff accountMenu() {
        readAndWriteStaffList.readFileStaffs(staffs);
        Staff user = new Staff();
        int count = 0;
        boolean check = true;
        while (check) {
            System.out.println("log in");
            String account = account();
            String pass = pass();
            for (int i = 0; i < staffs.size(); i++) {
                if (account.equals(staffs.get(i).getId())) {
                    if (pass.equals(staffs.get(i).getPass())) {
                        user = staffs.get(i);
                        System.out.println("hello "+ staffs.get(i).getId() +"\n log in successful \n");
                        check = false;
                        break;
                    }
                }
            }
            if (check){
                count++;
                if (count==5){
                    System.out.println("Account or Pass were been 5 times");
                    System.exit(0);
                }
                System.out.println("Account or Pass are wrong, please try again");
            }
        }
        return user;
    }



    public final String account() {
        String account = valiDateKeyBoard.importString("Account");
        return account;
    }

    public final String pass() {
        String pass = valiDateKeyBoard.importString("pass");
        return pass;
    }

}
