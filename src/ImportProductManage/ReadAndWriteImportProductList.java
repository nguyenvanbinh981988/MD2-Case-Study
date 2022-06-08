package ImportProductManage;


import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ReadAndWriteImportProductList {
    public List<ImportProduct> readFileImportProduct(List<ImportProduct> importProducts){
        try {
            File file = new File("ImportProductList.csv");

            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

            String str = bufferedReader.readLine();
            importProducts.clear();
            while (str != null) {
                String[] arr = str.split(",");
                String id = arr[0];
                String name = arr[1];
                String maker = arr[2];
                Date importDate = new SimpleDateFormat("dd/MM/yyyy").parse(arr[3]);
                Date expiry = new SimpleDateFormat("dd/MM/yyyy").parse(arr[4]);
                double importPrice = Double.parseDouble(arr[5]);
                double amount = Double.parseDouble(arr[6]);
                double sellPrice = Double.parseDouble(arr[8]);
                double sellAmount = Double.parseDouble(arr[9]);

                ImportProduct product = new ImportProduct(id,name,maker,importDate,expiry,importPrice,amount,sellPrice,sellAmount);
                importProducts.add(product);
                str = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return importProducts;
    }




    public void writeImportProduct(List<ImportProduct> importProducts) {
        File file = new File("ImportProductList.csv");
        try {
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (ImportProduct st : importProducts) {
                bufferedWriter.write(st.toWriteProductList());
                bufferedWriter.newLine();
            }

            bufferedWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
