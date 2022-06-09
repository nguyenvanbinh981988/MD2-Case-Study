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
        System.out.println("4. show ImportProductList form.... to...");
        System.out.println("5. show SellProductList form.... to... of Staff");
        System.out.println("6. show ExpiryProductList");
        System.out.println("7. comeBackSuperMarketManage");
        System.out.println("8. Logout");
        System.out.println("9. Exit");

        int choice = valiDateKeyBoard.ValiDateChoiceIPM();
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
                showSellProductListFromToStaff();
                break;
            case 6:
                ExpiryProductList();
                break;
            case 7:
                comeBackSuperMarketManage();
                break;
            case 8:
                logout();
                break;
            case 9:
                System.exit(0);
        }
    }

    //case 1:
    public void addImportProduct(){
        readAndWriteImportProductList.readFileImportProduct(importProducts);

        String id = valiDateKeyBoard.importString("id");
        for (int i = 0; i < importProducts.size(); i++) {
            if (id.equals(importProducts.get(i).getId())) {
                System.out.println("this is this id in list, please try again by other id");
                importProductMenu();
            }
        }
        editSellAmount();
        importProducts.add(creatProduct(id));
        readAndWriteImportProductList.writeImportProduct(importProducts);
    }
    //case 2:
    public void editInformationImportProduct(){
        readAndWriteImportProductList.readFileImportProduct(importProducts);
        String id = valiDateKeyBoard.importString("ID of editStaff");
        String newId = valiDateKeyBoard.importString("new id");

        if (id.equals(newId)){
            for (int i = 0; i < importProducts.size(); i++) {
                if (id.equals(importProducts.get(i).getId())) {
                    importProducts.set(i, creatProduct(newId));
                }
            }
        } else {
            for (int i = 0; i < importProducts.size(); i++) {
                if (newId.equals(importProducts.get(i).getId())) {
                    System.out.println("this is this id in list, please try again by other id");
                    importProductMenu();
                }
            }
            for (int i = 0; i < importProducts.size(); i++) {
                if (id.equals(importProducts.get(i).getId())) {
                    importProducts.set(i, creatProduct(newId));
                }
            }
            }
        readAndWriteImportProductList.writeImportProduct(importProducts);
        importProductMenu();
    }

    //case 3:
    public void showImportProductList(){
        readAndWriteImportProductList.readFileImportProduct(importProducts);
        readAndWriteSellProductList.readFileSellProduct(sellProducts);
        editSellAmount();
        System.out.println("this is ImportProductList");
        System.out.println("id; name; maker; importDate; expiry; importPrice; amount; totalImportPrice; sellPrice; SellAmount; sellPrice; Inventory");
        for (ImportProduct IP: importProducts) {
            System.out.println(IP);
        }
        readAndWriteImportProductList.writeImportProduct(importProducts);

    }

    //case 4:
    public void showImportProductListFromTo(){
        readAndWriteImportProductList.readFileImportProduct(importProducts);
        readAndWriteSellProductList.readFileSellProduct(sellProducts);
        editSellAmount();
        Date formDay = valiDateKeyBoard.importDate("from day");
        Date toDay = valiDateKeyBoard.importDate("to day");
        System.out.println("this is ImportProductList from "+formDay+" to "+toDay);
        System.out.println("id; name; maker; importDate; expiry; importPrice; amount; totalImportPrice; sellPrice; SellAmount; sellPrice; Inventory");
        boolean check = false;
        int totalImport = 0;
        int totalSell = 0;
        for (int i = 0; i < importProducts.size(); i++) {
                if (formDay.compareTo(importProducts.get(i).getImportDate()) <= 0) {
                    if (toDay.compareTo(importProducts.get(i).getImportDate()) >= 0) {
                        System.out.println(importProducts.get(i).toString());
                        check = true;
                        totalImport += importProducts.get(i).totalImportPrice();
                        totalSell += importProducts.get(i).totalSellPrice();
                    }
                }
            }
        if(!check){
            System.out.println("there are not import product in choice time \n");
        }
        readAndWriteImportProductList.writeImportProduct(importProducts);
        System.out.println("totalImport; totalSell ");
        System.out.println(totalSell + "VND,  "+totalSell+ "VND");
    }

//    case 5
    public void showSellProductListFromToStaff(){
        readAndWriteImportProductList.readFileImportProduct(importProducts);
        readAndWriteSellProductList.readFileSellProduct(sellProducts);
        editSellAmount();
        String id = valiDateKeyBoard.importString("Staff");
        Date formDay = valiDateKeyBoard.importDate("from day");
        Date toDay = valiDateKeyBoard.importDate("to day");
        System.out.println("this is ImportProductList from "+formDay+" to "+toDay);
        System.out.println("id; name; maker; importDate; expiry; importPrice; amount; totalImportPrice; sellPrice; SellAmount; sellPrice; Inventory");
        boolean check = false;
        int totalSell = 0;
        for (int i = 0; i < sellProducts.size(); i++) {
            if (id.equals(sellProducts.get(i).getStaff().getId())) {
                if (formDay.compareTo(sellProducts.get(i).getSellDay()) <= 0) {
                    if (toDay.compareTo(sellProducts.get(i).getSellDay()) >= 0) {
                        System.out.println(sellProducts.get(i).toString());
                        check = true;
                        totalSell += importProducts.get(i).totalSellPrice();
                    }
                }
            }
        }
        if(!check){
            System.out.println("there are not import product in choice time \n");
        }
        readAndWriteImportProductList.writeImportProduct(importProducts);
        System.out.println("totalSell: "+totalSell+ "VND");
    }




    //case 6:
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

    //case 7:
    public void comeBackSuperMarketManage(){
        superMarketManager.superMarketManagerMenu();
    }


    //case 8:
    public void logout(){
        SuperMarketManager superMarketManager =new SuperMarketManager();
        superMarketManager.login();
    }



    public ImportProduct creatProduct(String id) {
        System.out.println("Enter information of new Product:");
        String name = valiDateKeyBoard.importString("name");
        String maker = valiDateKeyBoard.importString("maker");
        Date importDate = valiDateKeyBoard.importDate("importDate");
        Date expiry = valiDateKeyBoard.importDate("expiry");
        double importPrice = valiDateKeyBoard.importDouble("importPrice");
        double amount = valiDateKeyBoard.importDouble("amount");
        double sellPrice = valiDateKeyBoard.importDouble("sellPrice");
        double sellAmount = valiDateKeyBoard.importDouble("sellAmount");

        readAndWriteImportProductList.readFileImportProduct(importProducts);

        ImportProduct product = new ImportProduct(id,name,maker,importDate,expiry,importPrice,amount,sellPrice,sellAmount);
        return product;

    }

    public void editSellAmount(){
        for (int i = 0; i < importProducts.size(); i++) {
            double sum =0;
            for (int j = 0; j < sellProducts.size(); j++) {
                if (importProducts.get(i).getId().equals(sellProducts.get(j).getImportProduct().getId())){
                    SellProduct ip = sellProducts.get(j);
                    double a = ip.getSellAmount();
                    sum += a ;
                }
            }
            importProducts.get(i).setSellAmount(sum);
        }
    }

}
