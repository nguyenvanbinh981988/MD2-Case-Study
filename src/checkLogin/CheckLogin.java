package checkLogin;

import StaffManage.ReadAndWriteStaffList;
import StaffManage.Staff;

import java.util.ArrayList;
import java.util.List;

public class CheckLogin {
    ReadAndWriteStaffList readAndWriteStaffList = new ReadAndWriteStaffList();

    List<Staff> staffs = new ArrayList<>();

    static public Staff user;

    static public int count = 0;
    public Staff accountCheck(String account, String pass) {
        readAndWriteStaffList.readFileStaffs(staffs);
        boolean check = true;
        for (int i = 0; i < staffs.size(); i++) {
            if (account.equals(staffs.get(i).getId())) {
                if (pass.equals(staffs.get(i).getPass())) {
                    user = staffs.get(i);
                    check = false;
                    break;
                }
            }
        }
        if (check){count++;}
        return user;
    }
    //case 4:
    public void EditPass(String oldPass,String newPass1, String newPass2  ) {
        readAndWriteStaffList.readFileStaffs(staffs);
        if (newPass1.equals(newPass2)) {
            for (int i = 0; i < staffs.size(); i++) {
                if (user.getId().equals(staffs.get(i).getId())) {
                    if (oldPass.equals(staffs.get(i).getPass())) {
                        staffs.get(i).setPass(newPass1);
                        break;
                    }
                }
            }
        }
        readAndWriteStaffList.writeFileStaffs(staffs);
    }
}
