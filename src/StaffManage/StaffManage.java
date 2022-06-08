package StaffManage;

import KeyboardManage.ValiDateKeyBoard;
import Menu.AccountMenu;
import SuperMarketManager.SuperMarketManager;
//import SuperMarketManager.SuperMarketManager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class StaffManage {
    Scanner input = new Scanner(System.in);

    ValiDateKeyBoard valiDateKeyBoard = new ValiDateKeyBoard();

    AccountMenu accountMenu = new AccountMenu();

//    SuperMarketManager superMarketManager = new SuperMarketManager();
    ReadAndWriteStaffList readAndWriteStaffList = new ReadAndWriteStaffList();

    ValiDate valiDate = new ValiDate();
    List<Staff> staffs = new ArrayList<>();

    {readAndWriteStaffList.readFileStaffs(staffs);}



    public void staffManageMenu(){
        System.out.println("staffManageMenu:");
        System.out.println("1. add staff");
        System.out.println("2. edit information off staff");
        System.out.println("3. show staffList");
        System.out.println("4. show payroll");
        System.out.println("5. comeBackSuperMarketManage");
        System.out.println("6. Exit");

        int choice = valiDateKeyBoard.importInt("choice");

        switch (choice){
            case 1:
                addStaff();
                break;
            case 2:
                editStaff();
                break;
            case 3:
                showStaffList();
                break;
            case 4:
                showPayroll();
                break;
            case 5:
                comeBackSuperMarketManage();
                break;
            case 6:
                Exit();
                break;
        }
    }

    //case 1:
    public void addStaff(){
        readAndWriteStaffList.readFileStaffs(staffs);
        System.out.println("Enter information of new staff:");
        staffs.add(creatStaff());
        readAndWriteStaffList.writeFileStaffs(staffs);
    }

    //case 2:
    public void editStaff(){
        readAndWriteStaffList.readFileStaffs(staffs);
        String id = valiDateKeyBoard.importString("ID of editStaff");
        for (int i = 0; i < staffs.size(); i++) {
                if (id.equals(staffs.get(i).getId())){
                    staffs.set(i,creatStaff());
                }else {
                System.out.println("there is this ID in List, please enter again");
                editStaff();
            }
            break;
        }
        readAndWriteStaffList.writeFileStaffs(staffs);
    }

    //case 3:
    public void showStaffList(){
        readAndWriteStaffList.readFileStaffs(staffs);
        System.out.println("This is StaffList");
        for (int i = 0; i < staffs.size(); i++) {
            System.out.println(staffs.get(i).toString());
        }
    }

    //case 4:
    public void showPayroll(){
        readAndWriteStaffList.readFileStaffs(staffs);
        System.out.println("This is PayrollList");
        for (int i = 0; i < staffs.size(); i++) {
            System.out.println(staffs.get(i).toStringPayRoll());
        }
    }

//    case 5:
    public void comeBackSuperMarketManage(){
        SuperMarketManager superMarketManager = new SuperMarketManager();
        superMarketManager.superMarketManagerMenu();
    }

    //case 6
    public void Exit(){
        System.exit(0);
    }


    public Staff creatStaff(){
        String id = valiDateKeyBoard.importString("ID");
        String name = valiDateKeyBoard.importString("Name");
        String position = valiDate.position();
        String address = valiDateKeyBoard.importString("Address");
        String gender = valiDateKeyBoard.importString("Gender");

        Date birth = valiDateKeyBoard.importDate("Birth");
        Date contractStartDay = valiDateKeyBoard.importDate("ContractStartDay");
        Date contractEndDay = valiDateKeyBoard.importDate("ContractEndDay");

        String cccd = valiDateKeyBoard.importString("Cccd");
        String bankCardID = valiDateKeyBoard.importString("bankCardID");
        String pass = valiDateKeyBoard.importString("Pass");

        double salary = valiDateKeyBoard.importDouble("Salary");
        double workday = valiDateKeyBoard.importDouble("Workday");
        double businessBonus = valiDateKeyBoard.importDouble("BusinessBonus");

        Staff staff = new Staff(id,name,position,address,gender,birth,contractStartDay,contractEndDay,cccd,bankCardID,pass,salary,workday,businessBonus);
        return staff;
    }

}
