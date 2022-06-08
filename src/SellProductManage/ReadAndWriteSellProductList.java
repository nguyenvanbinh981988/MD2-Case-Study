package SellProductManage;


import ImportProductManage.ImportProduct;
import ImportProductManage.ReadAndWriteImportProductList;
import StaffManage.ReadAndWriteStaffList;
import StaffManage.Staff;


import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ReadAndWriteSellProductList {

    List<ImportProduct> importProducts = new ArrayList<>();

    List<Staff> staffs = new ArrayList<>();

    ReadAndWriteImportProductList readAndWriteImportProductList = new ReadAndWriteImportProductList();

    ReadAndWriteStaffList readAndWriteStaffList = new ReadAndWriteStaffList();

    {readAndWriteImportProductList.readFileImportProduct(importProducts);
     readAndWriteStaffList.readFileStaffs(staffs);}
    public List<SellProduct> readFileSellProduct(List<SellProduct> sellProducts){
        try {
            File file = new File("SellProductList.csv");

            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

            String str = bufferedReader.readLine();
            sellProducts.clear();

            while (str != null) {
                String[] arr = str.split(",");
                String productId = arr[0];
                double sellAmount = Double.parseDouble(arr[3]);
                Date sellDay = new SimpleDateFormat("dd/MM/yyyy").parse(arr[5]);
                String staffID = arr[6];
                ImportProduct importProduct = null;
                for (int i = 0; i < importProducts.size(); i++) {
                    if (productId.equals(importProducts.get(i).getId())) {
                        importProduct = importProducts.get(i);
                        break;
                    }
                }

                Staff staff = null;
                for (int i = 0; i < staffs.size(); i++) {
                    if (staffID.equals(staffs.get(i).getId())) {
                        staff = staffs.get(i);
                        break;
                    }
                }


                SellProduct sellproduct = new SellProduct(importProduct, sellDay, sellAmount, staff);
                sellProducts.add(sellproduct);
                str = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sellProducts;
    }




    public void writeSellProduct(List<SellProduct> sellProducts) {
        File file = new File("SellProductList.csv");
        try {
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (SellProduct st : sellProducts) {
                bufferedWriter.write(st.toWriteSellProductList());
                bufferedWriter.newLine();
            }

            bufferedWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
