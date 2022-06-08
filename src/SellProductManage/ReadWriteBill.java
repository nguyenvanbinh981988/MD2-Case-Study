package SellProductManage;

import java.io.*;
import java.util.List;


public class ReadWriteBill {
    public void writeBill(List<SellProduct> bills) {
        File file = new File("Bill.csv");
        try {
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (SellProduct bi : bills) {
                bufferedWriter.write(bi.toWriteBill());
                bufferedWriter.newLine();
            }

            bufferedWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}