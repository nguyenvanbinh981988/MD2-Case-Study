package SuperMarketManager;

import KeyboardManage.ValiDateKeyBoard;
import Menu.AccountMenu;
import ImportProductManage.ImportProductManage;
import SellProductManage.SellProductManage;
import StaffManage.ReadAndWriteStaffList;
import StaffManage.Staff;
import StaffManage.StaffManage;
import java.util.ArrayList;
import java.util.List;

public class SuperMarketManager {

    ValiDateKeyBoard valiDateKeyBoard = new ValiDateKeyBoard();
    ReadAndWriteStaffList readAndWriteStaffList = new ReadAndWriteStaffList();
    AccountMenu accountMenu = new AccountMenu();

    List<Staff> staffs = new ArrayList<>();

    {
        readAndWriteStaffList.readFileStaffs(staffs);
    }

    static public Staff user;


    public void login() {
        user = accountMenu.accountMenu();
        if (user != null) {
            superMarketManagerMenu();
        }

    }


    public int decentralization() {
        int auth = 0;
        for (int i = 0; i < staffs.size(); i++) {
            if (user.getId().equals(staffs.get(i).getId())) {
                if (user.getPass().equals(staffs.get(i).getPass())) {
                    if (staffs.get(i).getPosition().equals("manager")) {
                        auth = 3;
                        System.out.println("you only choice 1 or 2 or 4 or 5 or 6");
                    } else if (staffs.get(i).getPosition().equals("leader")) {
                        auth = 2;
                        System.out.println("you only choice 2 or 3 or 4 or 5 or 6");
                    } else {
                        auth = 1;
                        System.out.println("you only choice 3 or 4 or 5 or 6");
                    }
                }
            }
        }
        return auth;
    }

    public void superMarketManagerMenu() {
        int position = decentralization();
        System.out.println("superMarketManagerMenu:");
        System.out.println("1. StaffManage");
        System.out.println("2. ImportProductManage");
        System.out.println("3. SellProductManage");
        System.out.println("4. Edit Pass");
        System.out.println("5. Logout");
        System.out.println("6. Exit");

        int choice = valiDateKeyBoard.ValiDateChoiceSPM();
        switch (choice) {
            case 1:
                if (position == 3) {
                    StaffManage staffManage = new StaffManage();
                    while (true) {
                        staffManage.staffManageMenu();
                    }
                } else {
                    System.out.println("you do not have permission to access this item, please choice again");
                    superMarketManagerMenu();
                }
                break;
            case 2:
                if (position == 3 || position == 2) {
                    ImportProductManage importProductManage = new ImportProductManage();
                    while (true) {
                        importProductManage.importProductMenu();
                    }
                } else {
                    System.out.println("you do not have permission to access this item, please choice again");
                    superMarketManagerMenu();
                }
                break;
            case 3:
                if (position == 1 || position==2) {
                    SellProductManage sellProductManage = new SellProductManage();
                    while (true) {
                        sellProductManage.sellProductMenu();
                    }
                } else {
                    System.out.println("you do not have permission to access this item, please choice again");
                    superMarketManagerMenu();
                }
                break;
            case 4:
                EditPass();
                break;
            case 5:
                login();
                break;
            case 6:
                System.exit(0);
        }
    }


    //case 4:
    public void EditPass() {
        readAndWriteStaffList.readFileStaffs(staffs);
        String oldPass = valiDateKeyBoard.importString("Old Account");
        String newPass1 = valiDateKeyBoard.importString("New Account");
        String newPass2 = valiDateKeyBoard.importString("New Account again");
        if (newPass1.equals(newPass2)) {
            for (int i = 0; i < staffs.size(); i++) {
                if (user.getId().equals(staffs.get(i).getId())) {
                    if (oldPass.equals(staffs.get(i).getPass())) {
                        staffs.get(i).setPass(newPass1);
                    }
                }
            }
        }
        readAndWriteStaffList.writeFileStaffs(staffs);
        System.out.println("Pass was changed, please log in again");
        login();
    }
}