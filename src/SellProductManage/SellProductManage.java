package SellProductManage;

import ImportProductManage.ImportProduct;
import ImportProductManage.ReadAndWriteImportProductList;
import KeyboardManage.ValiDateKeyBoard;
import StaffManage.ReadAndWriteStaffList;
import StaffManage.Staff;
import SuperMarketManager.SuperMarketManager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class SellProductManage {
    Scanner input = new Scanner(System.in);
    List<ImportProduct> importProducts = new ArrayList<>();
    List<SellProduct> sellProducts = new ArrayList<>();
    List<SellProduct> bills = new ArrayList<>();

    List<Staff> staffs = new ArrayList<>();
    ValiDateKeyBoard valiDateKeyBoard = new ValiDateKeyBoard();
    ReadAndWriteSellProductList readAndWriteSellProductList = new ReadAndWriteSellProductList();
    ReadAndWriteImportProductList readAndWriteImportProductList = new ReadAndWriteImportProductList();
    ReadWriteBill readWriteBill = new ReadWriteBill();
    ReadAndWriteStaffList readAndWriteStaffList = new ReadAndWriteStaffList();
    SuperMarketManager superMarketManager = new SuperMarketManager();

    {
        readAndWriteImportProductList.readFileImportProduct(importProducts);
        readAndWriteSellProductList.readFileSellProduct(sellProducts);
        readAndWriteStaffList.readFileStaffs(staffs);
    }

    SellProduct sellProduct;

    public void sellProductMenu() {
        System.out.println("ProductManageMenu:");
        System.out.println("1. add SellProduct");
        System.out.println("2. edit information off sellProduct");
        System.out.println("3. show All of sellProductList of me");
        System.out.println("4. show sellProductList form.... to... of me");
        System.out.println("5. showBill");
        System.out.println("6. comeBackSuperMarketManage");
        System.out.println("7. Logout");
        System.out.println("8. exit");

        int choice = valiDateKeyBoard.ValiDateChoiceSellM();

        switch (choice) {
            case 1:
                addSellProduct();
                break;
            case 2:
                editInformationSellProduct();
                break;
            case 3:
                showSellProductListOfMe();
                break;
            case 4:
                showSellProductListFromToOfMe();
                break;
            case 5:
                showBill();
                break;
            case 6:
                comeBackSuperMarketManage();
                break;
            case 7:
                logout();
                break;
            case 8:
                System.exit(0);
        }
    }

    //case 1:
    public void addSellProduct() {
        readAndWriteImportProductList.readFileImportProduct(importProducts);
        readAndWriteSellProductList.readFileSellProduct(sellProducts);

        Date sellDay = new Date();
        String id = valiDateKeyBoard.importString("Id of SellProduct");
        double sellAmount = valiDateKeyBoard.importDouble("sellAmount");
        boolean check = true;
        ImportProduct importProduct = null;
        for (int i = 0; i < importProducts.size(); i++) {
            if (id.equals(importProducts.get(i).getId())) {
                check = false;
                if (sellAmount < importProducts.get(i).inventory()) {
                    importProduct = importProducts.get(i);
                } else {
                    System.out.println("This product is out of stock");
                    sellProductMenu();
                }
            }
        }

            if (check) {
                System.out.println("there is not this Id in the ImportProductList");
                sellProductMenu();
            }
                sellProduct = new SellProduct(importProduct, sellDay, sellAmount, SuperMarketManager.user);
                sellProducts.add(sellProduct);
                bills.add(sellProduct);

                readAndWriteSellProductList.writeSellProduct(sellProducts);
                readWriteBill.writeBill(bills);
        }

    //case 2:
    public void editInformationSellProduct() {
        readAndWriteImportProductList.readFileImportProduct(importProducts);
        readAndWriteSellProductList.readFileSellProduct(sellProducts);

        System.out.println("1. Delete");
        System.out.println("2. Edit amount");
        int choice = valiDateKeyBoard.importInt("1 or 2");
        String id = valiDateKeyBoard.importString("Id");
        switch (choice) {
            case 1:
                for (int i = 0; i < bills.size(); i++) {
                    if (id.equals(bills.get(i).getImportProduct().getId())) {
                        for (int j = 0; j < sellProducts.size(); j++) {
                            if (id.equals(sellProducts.get(j).getImportProduct().getId())) {
                                sellProducts.get(j).setSellAmount(sellProducts.get(j).getSellAmount() - bills.get(i).getSellAmount());
                            }
                        }
                        bills.remove(i);
                    }
                }
            case 2:
                double amount = valiDateKeyBoard.importDouble("Amount");
                for (int i = 0; i < bills.size(); i++) {
                    if (id.equals(bills.get(i).getImportProduct().getId())) {
                        for (int j = 0; j < sellProducts.size(); j++) {
                            if (id.equals(sellProducts.get(j).getImportProduct().getId())) {
                                sellProducts.get(j).setSellAmount(sellProducts.get(j).getSellAmount() - bills.get(i).getSellAmount() + amount);
                            }
                        }
                        bills.get(i).setSellAmount(amount);
                    }
                }
        }
        readAndWriteSellProductList.writeSellProduct(sellProducts);
        readWriteBill.writeBill(bills);
        readAndWriteImportProductList.writeImportProduct(importProducts);
    }


    //case 3:
    public void showSellProductListOfMe() {
        readAndWriteSellProductList.readFileSellProduct(sellProducts);
        System.out.println("this is showSellProductList");
        System.out.println("Id, Name, sellAmount, totalSellPrice, SellDay, StaffId");
        int totalSell = 0;
        for (int i = 0; i < sellProducts.size(); i++) {
            if (SuperMarketManager.user.getId().equals(sellProducts.get(i).getStaff().getId())) {
                System.out.println(sellProducts.get(i).toString());
                totalSell += sellProducts.get(i).totalSellPrice();
            }
        }
        System.out.println("totalSell: " + totalSell);
    }

    //case 4:
    public void showSellProductListFromToOfMe() {
        readAndWriteSellProductList.writeSellProduct(sellProducts);
        Date formDay = valiDateKeyBoard.importDate("from day");
        Date toDay = valiDateKeyBoard.importDate("to day");
        System.out.println("this is showSellProductList from " + formDay + " to " + toDay);
        System.out.println("Id, Name, sellAmount, totalSellPrice, SellDay, StaffId");
        boolean check = false;
        int totalSell = 0;
        for (int i = 0; i < sellProducts.size(); i++) {
            if (formDay.compareTo(sellProducts.get(i).getSellDay()) <= 0) {
                if (toDay.compareTo(sellProducts.get(i).getSellDay()) >= 0) {
                    System.out.println(sellProducts.get(i).toString());
                    check = true;
                    totalSell += sellProducts.get(i).totalSellPrice();
                }
            }
        }
        if (!check) {
            System.out.println("there are not import product in choice time \n");
        }
        System.out.println("totalSell: " + totalSell +" VND");
        readAndWriteSellProductList.writeSellProduct(sellProducts);
    }

    //case 5:
    public void showBill() {
        Date date = new Date();
        System.out.println("this this bill_" + date);
        System.out.println("Id, name, Price, Amount, money");
        double total = 0;
        for (SellProduct sell : bills) {
            System.out.println(sell.showBill());
            total += sell.totalSellPrice();
        }
        System.out.println("total: " + total + " VND");
        readWriteBill.writeBill(bills);
        bills.clear();

    }

    //case 6:
    public void comeBackSuperMarketManage() {
        superMarketManager.superMarketManagerMenu();
    }

    // case 7:
    public void logout() {
        SuperMarketManager superMarketManager = new SuperMarketManager();
        superMarketManager.login();
    }
}
