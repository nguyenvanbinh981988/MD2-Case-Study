package View;

import ImportProductManage.ImportProduct;
import ImportProductManage.ReadAndWriteImportProductList;
import SellProductManage.ReadAndWriteSellProductList;
import SellProductManage.SellProduct;
import StaffManage.ReadAndWriteStaffList;
import StaffManage.Staff;
import checkLogin.CheckLogin;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SellProductManageView extends JFrame {
    private JButton addSellProductButton;
    private JButton showAllOfImportProductListButton;
    private JButton editInformationOffSellProductButton;
    private JButton showSellListFormToButton;
    private JButton printBillButton;
    private JTable table1;
    private JButton comeBackSuperMarketManageButton;
    private JButton exitButton;
    private JButton logoutButton;
    private JPanel SellProductManage;
    private JTextField idSwing;
    private JTextField sellAmountSwing;
    private JButton deleteIDButton;
    private JButton findIDButton;
    private JTextField idFindSwing;
    private JButton showExpiryProductButton;
    private JTextField idDeleteSwing;

    private JTextField fromShowSellSwing;

    private JTextField toShowSellSwing;


    ReadAndWriteStaffList readAndWriteStaffList = new ReadAndWriteStaffList();
    ReadAndWriteImportProductList readAndWriteImportProductList = new ReadAndWriteImportProductList();
    ReadAndWriteSellProductList readAndWriteSellProductList = new ReadAndWriteSellProductList();
    List<Staff> staffs = new ArrayList<>();
    List<ImportProduct> importProducts = new ArrayList<>();
    List<SellProduct> sellProducts = new ArrayList<>();

    List<SellProduct> bills = new ArrayList<>();


    boolean checkSell;

    public SellProductManageView() {
        this.setContentPane(SellProductManage);
        this.setVisible(true);
        this.setSize(1500, 1000);
        this.setLocationRelativeTo(null);
        JFrame jframe = this;

        showExpiryProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                readAndWriteImportProductList.readFileImportProduct(importProducts);
                List<ImportProduct> expiryList = new ArrayList<>();
                Date expiryDay = new Date();
                boolean check = true;
                for (int i = 0; i < importProducts.size(); i++) {
                    if (expiryDay.compareTo(importProducts.get(i).getExpiry()) > 0) {
                        if (importProducts.get(i).inventory() > 0) {
                            expiryList.add(importProducts.get(i));
                            check = false;
                        }
                    }
                }
                if (check) {
                    JOptionPane.showConfirmDialog(jframe, "there are not expiry product");
                } else {
                    String[] title = {"Id", "Name", "maker", "ExpiryDay", "SellPrice", "Inventory"};
                    String[][] showProductList = new String[expiryList.size() + 1][];
                    showProductList[0] = title;
                    for (int i = 0; i < expiryList.size(); i++) {
                        ImportProduct expiryProduct = expiryList.get(i);
                        String b;
                        int g,l;
                        b = new SimpleDateFormat("dd/MM/yyyy").format(expiryProduct.getExpiry());
                        g = (int) expiryProduct.getSellPrice();
                        l = (int) expiryProduct.inventory();

                        showProductList[i+1] = new String[]{expiryProduct.getId() + "", expiryProduct.getName() + "", expiryProduct.getMaker(), b + "", g + "", l + ""};
                    }
                    DefaultTableModel model = new DefaultTableModel(showProductList, title);
                    table1.setModel(model);

                }
            }
        });


        showAllOfImportProductListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                readAndWriteImportProductList.readFileImportProduct(importProducts);
                readAndWriteStaffList.readFileStaffs(staffs);
                readAndWriteSellProductList.readFileSellProduct(sellProducts);

                List<ImportProduct> showList = new ArrayList<>();

                boolean check = true;

                for (int i = 0; i < importProducts.size(); i++) {
                    if (importProducts.get(i).inventory()>0) {
                                showList.add(importProducts.get(i));
                                check = false;
                            }
                        }
                        if (check) {
                            JOptionPane.showConfirmDialog(jframe, "there are not SellProduct in List");
                        } else {
                            String[] title = {"Id", "Name","Maker", "SellPrice", "Inventory"};
                            String[][] ShowProductList = new String[showList.size() + 1][];
                            ShowProductList[0] = title;
                            for (int j = 0; j < showList.size(); j++) {
                                ImportProduct showProduct = showList.get(j);
                                int g,l;
                                g = (int) showProduct.getSellPrice();
                                l = (int) showProduct.inventory();


                                ShowProductList[j +1] = new String[]{showProduct.getId() + "", showProduct.getName() + "",showProduct.getMaker(), g + "", l + ""};
                            }
                            DefaultTableModel model = new DefaultTableModel(ShowProductList, title);
                            table1.setModel(model);
                        }
                    }

        });


        addSellProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                readAndWriteImportProductList.readFileImportProduct(importProducts);
                readAndWriteStaffList.readFileStaffs(staffs);
                readAndWriteSellProductList.readFileSellProduct(sellProducts);

                String idStr = idSwing.getText();
                String id = valiString(idStr, "id");

                String sellAmountStr = sellAmountSwing.getText();
                double sellAmount = valiDouble(sellAmountStr, "sellAmount");

                boolean check = true;
                ImportProduct importProduct = null;

                List<SellProduct> showList = new ArrayList<>();

                for (int i = 0; i < importProducts.size(); i++) {
                    if (id.equals(importProducts.get(i).getId())) {
                        check = false;
                        if (sellAmount < importProducts.get(i).inventory()) {
                            importProduct = importProducts.get(i);
                        } else {
                            JOptionPane.showConfirmDialog(jframe, "This product is out of stock");
                        }
                    }
                }
                if (check) {
                    JOptionPane.showConfirmDialog(jframe, "there is not this Product in the ImportProductList");
                } else {
                    Date sellDay = new Date();
                    SellProduct sellProduct = new SellProduct(importProduct, sellDay, sellAmount, CheckLogin.user);
                    sellProducts.add(sellProduct);
                    bills.add(sellProduct);

                    editSellAmount();

                    readAndWriteSellProductList.writeSellProduct(sellProducts);
                    readAndWriteImportProductList.readFileImportProduct(importProducts);
                    readAndWriteStaffList.writeFileStaffs(staffs);

                    showBills();
                }
            }
        });


        editInformationOffSellProductButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed (ActionEvent e){
                    readAndWriteImportProductList.readFileImportProduct(importProducts);
                    readAndWriteStaffList.readFileStaffs(staffs);
                    readAndWriteSellProductList.readFileSellProduct(sellProducts);

                    String idStr = idSwing.getText();
                    String id = valiString(idStr, "id");

                    String sellAmountStr = sellAmountSwing.getText();
                    double sellAmount = valiDouble(sellAmountStr, "sellAmount");

                    boolean check = true;
                    ImportProduct importProduct = null;

                    for (int i = 0; i < bills.size(); i++) {
                        if (id.equals(bills.get(i).getImportProduct().getId())) {
                            check = false;
                            if (sellAmount < importProducts.get(i).inventory()) {
                                SellProduct billProduct = bills.get(i);
                                for (int j = 0; j < sellProducts.size(); j++) {
                                    if (billProduct.equals(sellProducts.get(j))) {
                                        sellProducts.get(i).setSellAmount(sellAmount);
                                        break;
                                    }
                                }
                                bills.get(i).setSellAmount(sellAmount);
                            } else {
                                JOptionPane.showConfirmDialog(jframe, "This product is out of stock");
                            }
                        }
                    }

                    if (check) {
                        JOptionPane.showConfirmDialog(jframe, "there is not this Product in the Bill");
                    } else {

                        editSellAmount();

                        readAndWriteSellProductList.writeSellProduct(sellProducts);
                        readAndWriteImportProductList.readFileImportProduct(importProducts);
                        readAndWriteStaffList.writeFileStaffs(staffs);

                    showBills();
                }
            }
        });


        printBillButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                readAndWriteImportProductList.readFileImportProduct(importProducts);
                readAndWriteStaffList.readFileStaffs(staffs);
                readAndWriteSellProductList.readFileSellProduct(sellProducts);

                bills.clear();

                readAndWriteImportProductList.writeImportProduct(importProducts);
                readAndWriteSellProductList.writeSellProduct(sellProducts);
                readAndWriteStaffList.writeFileStaffs(staffs);
                new SellProductManageView();
            }
        });


        showSellListFormToButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                readAndWriteImportProductList.readFileImportProduct(importProducts);
                readAndWriteStaffList.readFileStaffs(staffs);
                readAndWriteSellProductList.readFileSellProduct(sellProducts);

                String fromDayStr = fromShowSellSwing.getText();
                Date fromDay = valiDate(fromDayStr, "From");

                String toDayStr = toShowSellSwing.getText();
                Date toDay = valiDate(toDayStr, "to");

                List<SellProduct> showList = new ArrayList<>();
                boolean check = true;
                for (int i = 0; i < sellProducts.size(); i++) {
                    if (CheckLogin.user.getId().equals(sellProducts.get(i).getStaff().getId())) {
                        if (fromDay.compareTo(sellProducts.get(i).getSellDay()) <= 0) {
                            if (toDay.compareTo(sellProducts.get(i).getSellDay()) >= 0) {
                                showList.add(sellProducts.get(i));
                                check = false;
                            }
                        }
                    }
                }
                if (check) {
                    JOptionPane.showConfirmDialog(jframe, "there are not sellProduct in the List");
                } else {
                    String[] title = {"Id", "Name", "SellPrice", "SellAmount", "TotalSellPrice", "SellDay", "Staff"};
                    String[][] ShowProductList = new String[showList.size() + 2][];
                    ShowProductList[0] = title;
                    int totalSellMoney = 0;
                    for (int j = 0; j < showList.size(); j++) {
                        SellProduct showProduct = showList.get(j);
                        String a, l;
                        int g, h, k;
                        g = (int) showProduct.getImportProduct().getSellPrice();
                        h = (int) showProduct.getSellAmount();
                        k = (int) showProduct.totalSellPrice();
                        l = showProduct.getStaff().getId();
                        a = new SimpleDateFormat("dd/MM/yyyy").format(showProduct.getSellDay());

                        totalSellMoney += k;

                        ShowProductList[j + 1] = new String[]{showProduct.getImportProduct().getId() + "", showProduct.getImportProduct().getName() + "", g + "", h + "", k + "", a + "", l + ""};
                    }
                    String[] total = {"TOTAL", " ", " ", " ", totalSellMoney + "VND"};
                    ShowProductList[showList.size() + 1] = total;
                    DefaultTableModel model = new DefaultTableModel(ShowProductList, title);
                    table1.setModel(model);
                }
            }
        });

        deleteIDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                readAndWriteImportProductList.readFileImportProduct(importProducts);
                readAndWriteSellProductList.readFileSellProduct(sellProducts);
                readAndWriteStaffList.readFileStaffs(staffs);
                checkSell = true;
                String deleteIdStr = idDeleteSwing.getText();
                String deleteId = valiString(deleteIdStr, "Id");

                if (checkSell) {
                    for (int i = 0; i < bills.size(); i++) {
                        if (deleteId.equals(bills.get(i).getImportProduct().getId())) {
                            SellProduct billProduct = bills.get(i);
                            for (int j = 0; j < sellProducts.size(); j++) {
                                if (billProduct.equals(sellProducts.get(j))){
                                    sellProducts.remove(j);
                                }
                            }
                            bills.remove(i);
                        }
                    }
                    readAndWriteStaffList.writeFileStaffs(staffs);
                    readAndWriteImportProductList.writeImportProduct(importProducts);
                    readAndWriteSellProductList.writeSellProduct(sellProducts);
                    JOptionPane.showConfirmDialog(jframe, "Delete Id is successful");
                    showBills();
                } else {
                    JOptionPane.showConfirmDialog(jframe, "please edit wrongInformation");
                }
            }
        });


        findIDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<ImportProduct> ImportFind = new ArrayList<>();
                readAndWriteImportProductList.readFileImportProduct(importProducts);
                String idFindStr = idFindSwing.getText();
                String idFind = valiString(idFindStr, "id");
                boolean check = true;
                for (int i = 0; i < importProducts.size(); i++) {
                    if (importProducts.get(i).getId().contains(idFind)) {
                        ImportFind.add(importProducts.get(i));
                        check = false;
                    }
                }
                if (check) {
                    JOptionPane.showConfirmDialog(jframe, "there are not this product in List");
                } else {
                    String[] title = {"Id", "Name", "maker","ExpiryDay", "SellPrice", "Inventory"};
                    String[][] ShowProductList = new String[ImportFind.size() + 2][];
                    ShowProductList[0] = title;
                    for (int i = 0; i < ImportFind.size(); i++) {
                        ImportProduct ShowProduct = ImportFind.get(i);
                        String b;
                        int g, l;
                        b = new SimpleDateFormat("dd/MM/yyyy").format(ShowProduct.getExpiry());
                        g = (int) ShowProduct.getSellPrice();
                        l = (int) ShowProduct.inventory();

                        ShowProductList[i + 1] = new String[]{ShowProduct.getId() + "", ShowProduct.getName() + "", ShowProduct.getMaker(), b + "", g + "", l + ""};
                    }
                    DefaultTableModel model = new DefaultTableModel(ShowProductList, title);
                    table1.setModel(model);
                }
            }
        });

        comeBackSuperMarketManageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jframe.setVisible(false);
                new SuperMarketManagerView();
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jframe.setVisible(false);
                new SuperMarketSystemLogin();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });




    }




    public String valiString(String b, String c) {
        JFrame jframe = this;
        if (b.equals("")) {
            checkSell = false;
            JOptionPane.showConfirmDialog(jframe, c + " was not been entered, please try again");
        }
        return b;
    }


    public Date valiDate(String b, String c) {
        JFrame jframe = this;
        if (!b.equals("")) {
            try {
                Date a = new SimpleDateFormat("dd/MM/yyyy").parse(b);
                return a;
            } catch (Exception i) {
                JOptionPane.showConfirmDialog(jframe, c + " is wrong, please try again. ex: 25/10/2022");
                checkSell = false;
                return new Date();
            }
        } else {
            checkSell = false;
            JOptionPane.showConfirmDialog(jframe, c + " was not been entered, please try again. ex: 25/10/2022");
            return new Date();
        }
    }


    public Double valiDouble(String b, String c) {
        JFrame jframe = this;
        if (!b.equals("")) {
            try {
                double a = Double.parseDouble(b);
                return a;
            } catch (Exception i) {
                JOptionPane.showConfirmDialog(jframe, c + " is wrong, please try again. ex: 25/10/2022");
                checkSell = false;
                return 5.0;
            }
        } else {
            checkSell = false;
            JOptionPane.showConfirmDialog(jframe, c + " was not been entered, please try again. ex: 25/10/2022");
            return 5.0;
        }
    }


    public void editSellAmount(){
        readAndWriteImportProductList.readFileImportProduct(importProducts);
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
        readAndWriteImportProductList.writeImportProduct(importProducts);
    }

    public void showBills(){
        String[] title = {"Id", "Name", "SellPrice", "SellAmount", "TotalSellPrice"};
        String[][] ShowProductList = new String[bills.size() + 2][];
        ShowProductList[0] = title;
        int totalSellMoney = 0;
        for (int i = 0; i < bills.size(); i++) {
            SellProduct showProduct = bills.get(i);
            int g, h, k;
            g = (int) showProduct.getImportProduct().getSellPrice();
            h = (int) showProduct.getSellAmount();
            k = (int) showProduct.totalSellPrice();

            totalSellMoney += k;

            ShowProductList[i + 1] = new String[]{showProduct.getImportProduct().getId() + "", showProduct.getImportProduct().getName() + "", g + "", h + "", k + ""};
        }
        String[] total = {"TOTAL", " ", " ", " ", totalSellMoney + "VND", " "};
        ShowProductList[bills.size() + 1] = total;
        DefaultTableModel model = new DefaultTableModel(ShowProductList, title);
        table1.setModel(model);
    }
    }

