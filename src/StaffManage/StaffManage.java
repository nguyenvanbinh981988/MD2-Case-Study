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
        System.out.println("6. log out");
        System.out.println("7. Exit");

        int choice = valiDateKeyBoard.ValiDateChoiceSM();

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
                logout();
                break;
            case 7:
                System.exit(0);
        }
    }

    //case 1:
    public void addStaff(){
        readAndWriteStaffList.readFileStaffs(staffs);
        System.out.println("Enter information of new staff:");
        String id = valiDateKeyBoard.importString("id");
        for (int i = 0; i < staffs.size(); i++) {
            if (id.equals(staffs.get(i).getId())) {
                System.out.println("this is this id in list, please try again by other id");
                staffManageMenu();
            }
        }
        staffs.add(creatStaff(id));
        readAndWriteStaffList.writeFileStaffs(staffs);
    }

    //case 2:
    public void editStaff(){
        readAndWriteStaffList.readFileStaffs(staffs);
        String id = valiDateKeyBoard.importString("ID of editStaff");
        String newId = valiDateKeyBoard.importString("new id");

        if (id.equals(newId)){
        for (int i = 0; i < staffs.size(); i++) {
            if (id.equals(staffs.get(i).getId())) {
                staffs.set(i, creatStaff(newId));
            }
        }
        } else {
            for (int i = 0; i < staffs.size(); i++) {
                if (newId.equals(staffs.get(i).getId())) {
                    System.out.println("this is this id in list, please try again by other id");
                    staffManageMenu();
                }
            }
            for (int i = 0; i < staffs.size(); i++) {
                if (id.equals(staffs.get(i).getId())) {
                    staffs.set(i, creatStaff(newId));
                    staffManageMenu();
                }
            }
        }
        readAndWriteStaffList.writeFileStaffs(staffs);
        staffManageMenu();
    }

    //case 3:
    public void showStaffList(){
        readAndWriteStaffList.readFileStaffs(staffs);
        System.out.println("This is StaffList");
        System.out.println("Id; Name; Position; address; gender; Birth; ContractStartDay; ContractEndDay; cccd; bankCardID; pass; Salary; WorkDays; BusinessBonus; ActuallyIncome");
        for (int i = 0; i < staffs.size(); i++) {
            System.out.println(staffs.get(i).toString());
        }
    }

    //case 4:
    public void showPayroll(){
        readAndWriteStaffList.readFileStaffs(staffs);
        System.out.println("This is PayrollList");
        System.out.println("Id; Name; Position; Salary; WorkDays; BusinessBonus; ActuallyIncome");
        int totalPayRoll = 0;
        for (int i = 0; i < staffs.size(); i++) {
            if (staffs.get(i).getWorkday() != 0) {
                System.out.println(staffs.get(i).toStringPayRoll());
                totalPayRoll += staffs.get(i).actuallyIncome();
            }
        }
        System.out.println("totalPayRoll: " +totalPayRoll + " VND");
    }

//    case 5:
    public void comeBackSuperMarketManage(){
        SuperMarketManager superMarketManager = new SuperMarketManager();
        superMarketManager.superMarketManagerMenu();
    }

//    case 6:
    public void logout(){
        SuperMarketManager superMarketManager =new SuperMarketManager();
        superMarketManager.login();
    }


    public Staff creatStaff(String id){
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
