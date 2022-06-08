package ImportProductManage;

import KeyboardManage.ValiDateKeyBoard;
import SellProductManage.ReadAndWriteSellProductList;
import SellProductManage.SellProduct;
import SuperMarketManager.SuperMarketManager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ImportProductManage {
    Scanner input = new Scanner(System.in);
    List<ImportProduct> importProducts = new ArrayList<>();

    List<SellProduct> sellProducts = new ArrayList<>();
    ValiDateKeyBoard valiDateKeyBoard = new ValiDateKeyBoard();
    SuperMarketManager superMarketManager = new SuperMarketManager();
    ReadAndWriteImportProductList readAndWriteImportProductList = new ReadAndWriteImportProductList();

    ReadAndWriteSellProductList readAndWriteSellProductList = new ReadAndWriteSellProductList();

    {readAndWriteImportProductList.readFileImportProduct(importProducts);
    readAndWriteSellProductList.readFileSellProduct(sellProducts);}

    public void importProductMenu(){
        System.out.println("ProductManageMenu:");
        System.out.println("1. add ImportProduct");
        System.out.println("2. edit information off ImportProduct");
        System.out.println("3. show all of importImportProductList");
        System.out.println("4. show importImportProductList form.... to...");
        System.out.println("5. show ExpiryProductList");
        System.out.println("6. comeBackSuperMarketManage");
        System.out.println("7. Exit");

        int choice = Integer.parseInt(input.nextLine());
        switch (choice){
            case 1:
                addImportProduct();
                break;
            case 2:
                editInformationImportProduct();
                break;
            case 3:
                showImportProductList();
                break;
            case 4:
                showImportProductListFromTo();
                break;
            case 5:
                ExpiryProductList();
                break;
            case 6:
                comeBackSuperMarketManage();
                break;
            case 7:
                Exit();
                break;
        }
    }

    //case 1:
    public void addImportProduct(){
        readAndWriteImportProductList.readFileImportProduct(importProducts);
        editSellAmount();
        importProducts.add(creatProduct());
        readAndWriteImportProductList.writeImportProduct(importProducts);
    }
    //case 2:
    public void editInformationImportProduct(){
        readAndWriteImportProductList.readFileImportProduct(importProducts);
        String id = valiDateKeyBoard.importString("ID of editStaff");
        for (int i = 0; i < importProducts.size(); i++) {
            try {
                if (id.equals(importProducts.get(i).getId())){
                    creatProduct();
                    importProducts.set(i,creatProduct());
                    readAndWriteImportProductList.writeImportProduct(importProducts);
                }
            }
            catch (Exception e){
                System.out.println("there is this ID in List, please enter again");
                creatProduct();
            }
        }

    }
    //case 3:
    public void showImportProductList(){
        readAndWriteImportProductList.readFileImportProduct(importProducts);
        readAndWriteSellProductList.readFileSellProduct(sellProducts);
        editSellAmount();
        System.out.println("this is ImportProductList");
        for (ImportProduct IP: importProducts) {
            System.out.println(IP);
        }

    }

    //case 4:
    public void showImportProductListFromTo(){
        readAndWriteImportProductList.readFileImportProduct(importProducts);
        readAndWriteSellProductList.readFileSellProduct(sellProducts);
        editSellAmount();
        Date formDay = valiDateKeyBoard.importDate("from day");
        Date toDay = valiDateKeyBoard.importDate("to day");
        boolean check = false;
        for (int i = 0; i < importProducts.size(); i++) {
                if (formDay.compareTo(importProducts.get(i).getImportDate()) <= 0) {
                    if (toDay.compareTo(importProducts.get(i).getImportDate()) >= 0) {
                        System.out.println(importProducts.get(i).toString());
                        check = true;
                    }
                }
            }
        if(!check){
            System.out.println("there are not import product in choice time \n");
        }
        readAndWriteImportProductList.writeImportProduct(importProducts);
    }

    //case 5:
    public void ExpiryProductList(){
        readAndWriteImportProductList.readFileImportProduct(importProducts);
        readAndWriteSellProductList.readFileSellProduct(sellProducts);
        editSellAmount();
        Date expiryDay = new Date();
        boolean check = false;
        System.out.println("this is list off expiry product");
        for (int i = 0; i < importProducts.size(); i++) {
            if (expiryDay.compareTo(importProducts.get(i).getExpiry()) > 0) {
                if (importProducts.get(i).inventory() > 0) {
                    System.out.println(importProducts.get(i).toString());
                    check = true;
                }
            }
        }
        if(!check){
            System.out.println("there are not expiry product \n");
        }
    }

    //case 6:
    public void comeBackSuperMarketManage(){
        superMarketManager.superMarketManagerMenu();
    }


    //case 7:
    public void Exit(){
    System.exit(0);
    }



    public ImportProduct creatProduct() {
        System.out.println("Enter information of new Product:");
        String id = valiDateKeyBoard.importString("id");
        String name = valiDateKeyBoard.importString("name");
        String maker = valiDateKeyBoard.importString("maker");
        Date importDate = valiDateKeyBoard.importDate("importDate");
        Date expiry = valiDateKeyBoard.importDate("expiry");
        double importPrice = valiDateKeyBoard.importDouble("importPrice");
        double amount = valiDateKeyBoard.importDouble("amount");
        double sellPrice = valiDateKeyBoard.importDouble("sellPrice");
        double sellAmount = valiDateKeyBoard.importDouble("sellAmount");



        readAndWriteImportProductList.readFileImportProduct(importProducts);

        for (int i = 0; i <importProducts.size(); i++) {
            if (id.equals(importProducts.get(i).getId())){
                System.out.println("there is this Id in the importProductsList, please try by Id");
                id = valiDateKeyBoard.importString("Id");
                break;
            }
        }
        ImportProduct product = new ImportProduct(id,name,maker,importDate,expiry,importPrice,amount,sellPrice,sellAmount);
        return product;

    }

    public void editSellAmount(){
        for (int i = 0; i < importProducts.size(); i++) {
            double sum =0;
            for (int j = 0; j < sellProducts.size(); j++) {
                if (importProducts.get(i).getId().equals(sellProducts.get(j).getImportProduct().getId())){
                    sum +=  sellProducts.get(j).getSellAmount();
                }
            }
            importProducts.get(i).setSellAmount(sum);
        }
    }

}
