package StaffManage;


import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class ReadAndWriteStaffList {
    public List<Staff> readFileStaffs(List<Staff> staffs){
        try {
            File file = new File("StaffList.csv");

            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

            String str = bufferedReader.readLine();
            staffs.clear();
            while (str != null) {
                String[] arr = str.split(",");
                 String id = arr[0];
                 String name = arr[1];
                 String position = arr[2];
                String address = arr[3];
                 String gender = arr[4];
                 Date birth = new SimpleDateFormat("dd/MM/yyyy").parse(arr[5]);
                 Date contractStartDay = new SimpleDateFormat("dd/MM/yyyy").parse(arr[6]);
                 Date contractEndDay = new SimpleDateFormat("dd/MM/yyyy").parse(arr[7]);
                 String cccd = arr[8];
                 String bankCardID = arr[9];
                 String pass = arr[10];
                double salary = Double.parseDouble(arr[11]);
                double workday = Double.parseDouble(arr[12]);
                double businessBonus = Double.parseDouble(arr[13]);

                Staff staff = new Staff(id,name,position,address,gender,birth,contractStartDay,contractEndDay,cccd,bankCardID,pass,salary,workday,businessBonus);
                staffs.add(staff);
                str = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return staffs;
    }




    public void writeFileStaffs(List<Staff> staffs) {
        File file = new File("StaffList.csv");
        try {
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (Staff st : staffs) {
                bufferedWriter.write(st.toWriteStaffList());
                bufferedWriter.newLine();
            }

            bufferedWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
