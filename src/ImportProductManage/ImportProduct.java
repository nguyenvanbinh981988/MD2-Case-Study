package ImportProductManage;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ImportProduct {
    private String id;
    private String name;
    private String maker;
    private Date importDate;
    private Date expiry;
    private double importPrice;
    private double amount;
    private double sellPrice;
    private double sellAmount = 0;

    public ImportProduct() {
    }

    public ImportProduct( String id, String name, String maker, Date importDate, Date expiry, double importPrice, double amount, double sellPrice, double sellAmount) {
        this.id = id;
        this.name = name;
        this.maker = maker;
        this.importDate = importDate;
        this.expiry = expiry;
        this.importPrice = importPrice;
        this.amount = amount;
        this.sellPrice = sellPrice;
        this.sellAmount = sellAmount;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public Date getImportDate() {
        return importDate;
    }

    public void setImportDate(Date importDate) {
        this.importDate = importDate;
    }

    public Date getExpiry() {
        return expiry;
    }

    public void setExpiry(Date expiry) {
        this.expiry = expiry;
    }

    public double getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(double importPrice) {
        this.importPrice = importPrice;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double totalImportPrice() {
        return this.importPrice * this.amount;
    }

    public double getSellAmount() {
        return sellAmount;
    }

    public void setSellAmount(double sellAmount) {
        this.sellAmount = sellAmount;
    }

    public double inventory(){
        return this.amount-this.sellAmount;
    }

    public double totalSellPrice(){
      return   this.sellAmount*this.sellPrice;
    }

    @Override
    public String toString() {
        String strImportDate = new SimpleDateFormat("dd/MM/yyyy").format(importDate);
        String strExpiry = new SimpleDateFormat("dd/MM/yyyy").format(expiry);
        int intImportPrice = (int) importPrice;
        int intAmount = (int) amount;
        int intTotalImportPrice = (int) totalImportPrice();
        int intSellPrice = (int) sellPrice;
        int intSellAmount = (int) sellAmount;
        int intTotalSellPrice = (int) totalSellPrice();
        int intInventory = (int) inventory();

        return id +
                ", " + name +
                ", " + maker +
                ", " + strImportDate +
                ", " + strExpiry +
                ", " + intImportPrice +
                ", " + intAmount +
                ", " + intTotalImportPrice+
                ", " + intSellPrice +
                ", " + intSellAmount+
                ", " + intTotalSellPrice +
                ", " + intInventory ;
    }

    public String toWriteProductList() {
        String strImportDate = new SimpleDateFormat("dd/MM/yyyy").format(importDate);
        String strExpiry = new SimpleDateFormat("dd/MM/yyyy").format(expiry);
        int intImportPrice = (int) importPrice;
        int intAmount = (int) amount;
        int intTotalImportPrice = (int) totalImportPrice();
        int intSellPrice = (int) sellPrice;
        int intSellAmount = (int) sellAmount;
        int intInventory = (int) inventory();
        int intTotalSellPrice = (int) totalSellPrice();


        return        id +
                "," + name  +
                "," + maker +
                "," + strImportDate +
                "," + strExpiry +
                "," + intImportPrice +
                "," + intAmount +
                "," + intTotalImportPrice+
                "," + intSellPrice +
                "," + intSellAmount+
                "," + intTotalSellPrice +
                "," + intInventory;
    }
}
