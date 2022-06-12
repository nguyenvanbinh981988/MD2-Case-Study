package View;

import ImportProductManage.ImportProduct;
import ImportProductManage.ReadAndWriteImportProductList;
import SellProductManage.ReadAndWriteSellProductList;
import SellProductManage.SellProduct;
import StaffManage.ReadAndWriteStaffList;
import StaffManage.Staff;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ImportProductManageView extends JFrame {

    private JPanel ImportProductManageView;
    private JTable table1;
    private JTextField idImSwing;
    private JButton addImportProductButton;
    private JButton editImportProductButton;
    private JTextField nameImSwing;
    private JTextField makerImSwing;
    private JTextField importDateImSwing;
    private JTextField importAmountImSwing;
    private JTextField expryDayImSwing;
    private JTextField importPeiceImSwing;
    private JTextField sellPeiceImSwing;
    private JTextField sellAmountImSwing;
    private JButton exitButton;
    private JButton logOutButton;
    private JButton backSuperMarketManageButton;
    private JButton findFollowIDButton;
    private JTextField idFindSwing;
    private JButton showAllInportProductButton;
    private JButton showImportProducFollowDayButton;
    private JTextField fromShowImSwing;
    private JTextField toShowImSwing;
    private JButton showSellListFollowButton;
    private JTextField idShowSellSwing;
    private JTextField fromShowSellSwing;
    private JTextField toShowSellSwing;
    private JButton deleteIDButton;
    private JTextField iddeleteImSwing;
    private JTextField oldIdImSwing;
    private JButton showExpiryProductButton;

    ReadAndWriteImportProductList readAndWriteImportProductList = new ReadAndWriteImportProductList();
    ReadAndWriteSellProductList readAndWriteSellProductList = new ReadAndWriteSellProductList();
    ReadAndWriteStaffList readAndWriteStaffList = new ReadAndWriteStaffList();

    List<ImportProduct> importProducts = new ArrayList<>();
    List<SellProduct> sellProducts = new ArrayList<>();

    List<Staff> staffs = new ArrayList<>();

    String oldId, id, name, maker;

    Date importDate, expiry;

    double importPrice, importAmount, sellPrice, sellAmount;

    boolean checkAdd;

    public ImportProductManageView() {
        this.setContentPane(ImportProductManageView);
        this.setVisible(true);
        this.setSize(1500, 1000);
        this.setLocationRelativeTo(null);
        JFrame jframe = this;


        addImportProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkAdd = true;
                String idStr = idImSwing.getText();
                id = valiString(idStr, "id");

                String nameStr = nameImSwing.getText();
                name = valiString(nameStr, "name");

                String makerStr = makerImSwing.getText();
                makerStr = valiString(makerStr, "maker");


                String importDayStr = importDateImSwing.getText();
                importDate = valiDate(importDayStr, "importDate");


                String expiryStr = expryDayImSwing.getText();
                expiry = valiDate(expiryStr, "expiryDay");

                String importPriceStr = importPeiceImSwing.getText();
                importPrice = valiDouble(importPriceStr, "importPrice");

                String importAmountStr = importAmountImSwing.getText();
                importAmount = valiDouble(importAmountStr, "importAmount");

                String sellPriceStr = sellPeiceImSwing.getText();
                sellPrice = valiDouble(sellPriceStr, "sellPrice");

                String sellAmountStr = sellAmountImSwing.getText();
                sellAmount = valiDouble(sellAmountStr, "sellAmount");


                readAndWriteImportProductList.readFileImportProduct(importProducts);


                if (checkAdd) {
                    boolean check = true;
                    for (int i = 0; i < importProducts.size(); i++) {
                        if (id.equals(importProducts.get(i).getId())) {
                            check = false;
                            break;
                        }
                    }
                    if (!check) {
                        JOptionPane.showConfirmDialog(jframe, "there is this id in list, please try again by other id");
                    } else {
                        ImportProduct importProduct = new ImportProduct(id, name, maker, importDate, expiry, importPrice, importAmount, sellPrice, sellAmount);
                        importProducts.add(importProduct);
                        JOptionPane.showConfirmDialog(jframe, "AddImportProduct is successful");
                        jframe.setVisible(false);
                        new ImportProductManageView();
                    }
                } else {
                    JOptionPane.showConfirmDialog(jframe, "please edit wrongInformation");
                }
                readAndWriteImportProductList.writeImportProduct(importProducts);
            }
        });


        editImportProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkAdd = true;

                String oldIdStr = oldIdImSwing.getText();
                oldId = valiString(oldIdStr, "oldId");

                String idStr = idImSwing.getText();
                id = valiString(idStr, "id");

                String nameStr = nameImSwing.getText();
                name = valiString(nameStr, "name");

                String makerStr = makerImSwing.getText();
                makerStr = valiString(makerStr, "maker");


                String importDayStr = importDateImSwing.getText();
                importDate = valiDate(importDayStr, "importDate");


                String expiryStr = expryDayImSwing.getText();
                expiry = valiDate(expiryStr, "expiryDay");

                String importPriceStr = importPeiceImSwing.getText();
                importPrice = valiDouble(importPriceStr, "importPrice");

                String importAmountStr = importAmountImSwing.getText();
                importAmount = valiDouble(importAmountStr, "importAmount");

                String sellPriceStr = sellPeiceImSwing.getText();
                sellPrice = valiDouble(sellPriceStr, "sellPrice");

                String sellAmountStr = sellAmountImSwing.getText();
                sellAmount = valiDouble(sellAmountStr, "sellAmount");


                readAndWriteImportProductList.readFileImportProduct(importProducts);

                if (checkAdd) {
                    if (oldId.equals(id)) {
                        for (int i = 0; i < importProducts.size(); i++) {
                            if (id.equals(importProducts.get(i).getId())) {
                                importProducts.set(i, new ImportProduct(id, name, maker, importDate, expiry, importPrice, importAmount, sellPrice, sellAmount));
                                JOptionPane.showConfirmDialog(jframe, "EditImportProduct is successful");
                                jframe.setVisible(false);
                                new ImportProductManageView();
                            }
                        }
                    } else {
                        for (int i = 0; i < importProducts.size(); i++) {
                            if (id.equals(importProducts.get(i).getId())) {
                                JOptionPane.showConfirmDialog(jframe, "this is this id in list, please try again by other id");
                                jframe.setVisible(false);
                                new ImportProductManageView();
                            }
                        }
                        for (int i = 0; i < importProducts.size(); i++) {
                            if (oldId.equals(importProducts.get(i).getId())) {
                                importProducts.set(i, new ImportProduct(id, name, maker, importDate, expiry, importPrice, importAmount, sellPrice, sellAmount));
                                JOptionPane.showConfirmDialog(jframe, "EditStaff is successful");
                                jframe.setVisible(false);
                                new ImportProductManageView();
                            }
                        }
                    }
                } else {
                    JOptionPane.showConfirmDialog(jframe, "please edit wrongInformation");
                }
                readAndWriteImportProductList.writeImportProduct(importProducts);
            }
        });


        deleteIDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkAdd = true;
                String deleteIdStr = iddeleteImSwing.getText();
                String deleteId = valiString(deleteIdStr, "Id");
                readAndWriteImportProductList.readFileImportProduct(importProducts);

                if (checkAdd) {
                    for (int i = 0; i < importProducts.size(); i++) {
                        if (deleteId.equals(importProducts.get(i).getId())) {
                            importProducts.remove(i);
                        }
                    }
                    readAndWriteImportProductList.writeImportProduct(importProducts);
                    JOptionPane.showConfirmDialog(jframe, "Delete Id is successful");
                    jframe.setVisible(false);
                    new ImportProductManageView();
                } else {
                    JOptionPane.showConfirmDialog(jframe, "please edit wrongInformation");
                }
            }
        });


        showAllInportProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                readAndWriteImportProductList.readFileImportProduct(importProducts);
                editSellAmount();
                String[] title = {"Id", "Name", "maker", "ImportDate", "ExpiryDay", "ImportPrice", "ImportAmount", "TotalImportPrice", "SellPrice", "SellAmount", "TotalSellPrice", "Inventory"};
                String[][] importProductList = new String[importProducts.size() + 2][];
                importProductList[0] = title;
                int totalImportMoney = 0;
                int totalSellMoney = 0;
                for (int i = 0; i < importProducts.size(); i++) {
                    ImportProduct importProduct = importProducts.get(i);
                    String a, b;
                    int c, d, f, g, h, k, l;
                    a = new SimpleDateFormat("dd/MM/yyyy").format(importProduct.getImportDate());
                    b = new SimpleDateFormat("dd/MM/yyyy").format(importProduct.getExpiry());
                    c = (int) importProduct.getImportPrice();
                    d = (int) importProduct.getAmount();
                    f = (int) importProduct.totalImportPrice();
                    g = (int) importProduct.getSellPrice();
                    h = (int) importProduct.getSellAmount();
                    k = (int) importProduct.totalSellPrice();
                    l = (int) importProduct.inventory();
                    totalImportMoney += f;
                    totalSellMoney += k;

                    importProductList[i + 1] = new String[]{importProduct.getId() + "", importProduct.getName() + "", importProduct.getMaker(), a + "", b + "", c + "", d + "", f + "", g + "", h + "", k + "", l + ""};
                }
                String[] total = {"TOTAL", " ", " ", " ", " ", " ", " ", totalImportMoney + "VND", " ", " ", totalSellMoney + "VND", " "};
                importProductList[importProducts.size() + 1] = total;
                DefaultTableModel model = new DefaultTableModel(importProductList, title);
                table1.setModel(model);
            }
        });


        showExpiryProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                readAndWriteImportProductList.readFileImportProduct(importProducts);
                editSellAmount();
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
                    String[] title = {"Id", "Name", "maker", "ImportDate", "ExpiryDay", "ImportPrice", "ImportAmount", "TotalImportPrice", "SellPrice", "SellAmount", "TotalSellPrice", "Inventory"};
                    String[][] importProductList = new String[expiryList.size() + 2][];
                    importProductList[0] = title;
                    for (int i = 0; i < expiryList.size(); i++) {
                        ImportProduct expiryProduct = expiryList.get(i);
                        String a, b;
                        int c, d, f, g, h, k, l;
                        a = new SimpleDateFormat("dd/MM/yyyy").format(expiryProduct.getImportDate());
                        b = new SimpleDateFormat("dd/MM/yyyy").format(expiryProduct.getExpiry());
                        c = (int) expiryProduct.getImportPrice();
                        d = (int) expiryProduct.getAmount();
                        f = (int) expiryProduct.totalImportPrice();
                        g = (int) expiryProduct.getSellPrice();
                        h = (int) expiryProduct.getSellAmount();
                        k = (int) expiryProduct.totalSellPrice();
                        l = (int) expiryProduct.inventory();

                        importProductList[i + 1] = new String[]{expiryProduct.getId() + "", expiryProduct.getName() + "", expiryProduct.getMaker(), a + "", b + "", c + "", d + "", f + "", g + "", h + "", k + "", l + ""};
                    }
                    DefaultTableModel model = new DefaultTableModel(importProductList, title);
                    table1.setModel(model);

                }
            }
        });


        showImportProducFollowDayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                readAndWriteImportProductList.readFileImportProduct(importProducts);
                editSellAmount();
                String fromDayStr = fromShowImSwing.getText();
                Date fromDay = valiDate(fromDayStr, "From");

                String toDayStr = toShowImSwing.getText();
                Date toDay = valiDate(toDayStr, "to");

                List<ImportProduct> showList = new ArrayList<>();
                boolean check = true;
                for (int i = 0; i < importProducts.size(); i++) {
                    if (fromDay.compareTo(importProducts.get(i).getImportDate()) <= 0) {
                        if (toDay.compareTo(importProducts.get(i).getImportDate()) >= 0) {
                            showList.add(importProducts.get(i));
                            check = false;
                        }
                    }
                }
                if (check) {
                    JOptionPane.showConfirmDialog(jframe, "there are not import product in choice time");
                } else {
                    String[] title = {"Id", "Name", "maker", "ImportDate", "ExpiryDay", "ImportPrice", "ImportAmount", "TotalImportPrice", "SellPrice", "SellAmount", "TotalSellPrice", "Inventory"};
                    String[][] importProductList = new String[showList.size() + 2][];
                    importProductList[0] = title;
                    int totalImportMoney = 0;
                    int totalSellMoney = 0;
                    for (int i = 0; i < showList.size(); i++) {
                        ImportProduct showProduct = showList.get(i);
                        String a, b;
                        int c, d, f, g, h, k, l;
                        a = new SimpleDateFormat("dd/MM/yyyy").format(showProduct.getImportDate());
                        b = new SimpleDateFormat("dd/MM/yyyy").format(showProduct.getExpiry());
                        c = (int) showProduct.getImportPrice();
                        d = (int) showProduct.getAmount();
                        f = (int) showProduct.totalImportPrice();
                        g = (int) showProduct.getSellPrice();
                        h = (int) showProduct.getSellAmount();
                        k = (int) showProduct.totalSellPrice();
                        l = (int) showProduct.inventory();

                        totalImportMoney += f;
                        totalSellMoney += k;

                        importProductList[i + 1] = new String[]{showProduct.getId() + "", showProduct.getName() + "", showProduct.getMaker(), a + "", b + "", c + "", d + "", f + "", g + "", h + "", k + "", l + ""};
                    }
                    String[] total = {"TOTAL", " ", " ", " ", " ", " ", " ", totalImportMoney + "VND", " ", " ", totalSellMoney + "VND", " "};
                    importProductList[showList.size() + 1] = total;
                    DefaultTableModel model = new DefaultTableModel(importProductList, title);
                    table1.setModel(model);

                }
            }
        });

        showSellListFollowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                readAndWriteImportProductList.readFileImportProduct(importProducts);
                readAndWriteStaffList.readFileStaffs(staffs);
                readAndWriteSellProductList.readFileSellProduct(sellProducts);
                String idStr = idShowSellSwing.getText();
                String id = valiString(idStr, "id");

                String fromDayStr = fromShowSellSwing.getText();
                Date fromDay = valiDate(fromDayStr, "From");

                String toDayStr = toShowSellSwing.getText();
                Date toDay = valiDate(toDayStr, "to");

                List<SellProduct> showList = new ArrayList<>();
                boolean check = true;
                for (int i = 0; i < sellProducts.size(); i++) {
                    if (id.equals(sellProducts.get(i).getStaff().getId())) {
                        if (fromDay.compareTo(sellProducts.get(i).getSellDay()) <= 0) {
                            if (toDay.compareTo(sellProducts.get(i).getSellDay()) >= 0) {
                                    showList.add(sellProducts.get(i));
                                check = false;
                            }
                        }
                    }
                }
                if (check) {
                    JOptionPane.showConfirmDialog(jframe, "there are not import product in choice time");
                } else {
                    String[] title = {"Id", "Name", "maker", "ImportDate", "ExpiryDay", "ImportPrice", "ImportAmount", "TotalImportPrice", "SellPrice", "SellAmount", "TotalSellPrice", "Staff"};
                    String[][] ShowProductList = new String[showList.size() + 2][];
                    ShowProductList[0] = title;
                    int totalImportMoney = 0;
                    int totalSellMoney = 0;
                    for (int i = 0; i < showList.size(); i++) {
                        SellProduct showProduct = showList.get(i);
                        String a, b ,l;
                        int c, d, f, g, h, k;
                        a = new SimpleDateFormat("dd/MM/yyyy").format(showProduct.getImportProduct().getImportDate());
                        b = new SimpleDateFormat("dd/MM/yyyy").format(showProduct.getImportProduct().getExpiry());
                        c = (int) showProduct.getImportProduct().getImportPrice();
                        d = (int) showProduct.getImportProduct().getAmount();
                        f = (int) showProduct.getImportProduct().totalImportPrice();
                        g = (int) showProduct.getImportProduct().getSellPrice();
                        h = (int) showProduct.getSellAmount();
                        k = (int) showProduct.totalSellPrice();
                        l =  showProduct.getStaff().getId();

                        totalImportMoney += f;
                        totalSellMoney += k;

                        ShowProductList[i + 1] = new String[]{showProduct.getImportProduct().getId() + "", showProduct.getImportProduct().getName() + "", showProduct.getImportProduct().getMaker(), a + "", b + "", c + "", d + "", f + "", g + "", h + "", k + "", l + ""};
                    }
                    String[] total = {"TOTAL", " ", " ", " ", " ", " ", " ", totalImportMoney + "VND", " ", " ", totalSellMoney + "VND", " "};
                    ShowProductList[showList.size() + 1] = total;
                    DefaultTableModel model = new DefaultTableModel(ShowProductList, title);
                    table1.setModel(model);

                }
            }
        });

        findFollowIDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<ImportProduct> ImportFind = new ArrayList<>();
                readAndWriteImportProductList.readFileImportProduct(importProducts);
                editSellAmount();
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
                    String[] title = {"Id", "Name", "maker", "ImportDate", "ExpiryDay", "ImportPrice", "ImportAmount", "TotalImportPrice", "SellPrice", "SellAmount", "TotalSellPrice", "Inventory"};
                    String[][] ShowProductList = new String[ImportFind.size() + 2][];
                    ShowProductList[0] = title;
                    for (int i = 0; i < ImportFind.size(); i++) {
                        ImportProduct ShowProduct = ImportFind.get(i);
                        String a, b;
                        int c, d, f, g, h, k, l;
                        a = new SimpleDateFormat("dd/MM/yyyy").format(ShowProduct.getImportDate());
                        b = new SimpleDateFormat("dd/MM/yyyy").format(ShowProduct.getExpiry());
                        c = (int) ShowProduct.getImportPrice();
                        d = (int) ShowProduct.getAmount();
                        f = (int) ShowProduct.totalImportPrice();
                        g = (int) ShowProduct.getSellPrice();
                        h = (int) ShowProduct.getSellAmount();
                        k = (int) ShowProduct.totalSellPrice();
                        l = (int) ShowProduct.inventory();

                        ShowProductList[i + 1] = new String[]{ShowProduct.getId() + "", ShowProduct.getName() + "", ShowProduct.getMaker(), a + "", b + "", c + "", d + "", f + "", g + "", h + "", k + "", l + ""};
                    }
                    DefaultTableModel model = new DefaultTableModel(ShowProductList, title);
                    table1.setModel(model);
                }
            }
        });

        backSuperMarketManageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jframe.setVisible(false);
                new SuperMarketManagerView();
            }
        });

        logOutButton.addActionListener(new ActionListener() {
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
            checkAdd = false;
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
                checkAdd = false;
                return new Date();
            }
        } else {
            checkAdd = false;
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
                checkAdd = false;
                return 5.0;
            }
        } else {
            checkAdd = false;
            JOptionPane.showConfirmDialog(jframe, c + " was not been entered, please try again. ex: 25/10/2022");
            return 5.0;
        }
    }

    public void editSellAmount(){
        readAndWriteImportProductList.readFileImportProduct(importProducts);
        readAndWriteSellProductList.readFileSellProduct(sellProducts);
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
}

