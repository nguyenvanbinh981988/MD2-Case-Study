package SellProductManage;

import ImportProductManage.ImportProduct;
import StaffManage.Staff;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SellProduct {
    private ImportProduct importProduct;
    private Date sellDay;
    private double sellAmount;
    private Staff staff;

    public SellProduct(ImportProduct importProduct, Date sellDay, double sellAmount, Staff staff) {
        this.importProduct = importProduct;
        this.sellDay = sellDay;
        this.sellAmount = sellAmount;
        this.staff = staff;
    }

    public ImportProduct getImportProduct() {
        return importProduct;
    }

    public void setImportProduct(ImportProduct importProduct) {
        this.importProduct = importProduct;
    }

    public Date getSellDay() {
        return sellDay;
    }

    public void setSellDay(Date sellDay) {
        this.sellDay = sellDay;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public double getSellAmount() {
        return sellAmount;
    }


    public void setSellAmount(double sellAmount) {
        this.sellAmount = sellAmount;
    }

    public double totalSellPrice() {
        return importProduct.getSellPrice() * sellAmount;
    }


    @Override
    public String toString() {
        String strSellDay = new SimpleDateFormat("dd/MM/yyyy").format(sellDay);
        int intSellAmount = (int) sellAmount;
        int intTotalSellPrice = (int) totalSellPrice();

        return "SellProduct{" +
                "id=" + importProduct.getId() +
                ", name=" + importProduct.getName() +
                ", sellAmount=" + sellAmount +
                ", totalSellPrice=" + totalSellPrice() +
                ", sellDay=" + strSellDay +
                ", staff=" + staff.getId() +
                '}';
    }


    public String toWriteSellProductList() {
        String strSellDay = new SimpleDateFormat("dd/MM/yyyy").format(sellDay);
        int intSellAmount = (int) sellAmount;
        int intTotalSellPrice = (int) totalSellPrice();

        return  importProduct.getId() +
                "," + importProduct.getName() +
                "," + importProduct.getSellPrice()+
                "," + sellAmount +
                "," + totalSellPrice() +
                "," + strSellDay +
                "," + staff.getId();
    }

    public String toWriteBill() {
        String strSellDay = new SimpleDateFormat("dd/MM/yyyy").format(sellDay);
        int intSellAmount = (int) sellAmount;
        int intTotalSellPrice = (int) totalSellPrice();

        return importProduct.getId() +
                "," + importProduct.getName() +
                "," + importProduct.getSellPrice() +
                "," + sellAmount +
                "," + totalSellPrice() ;
    }

    public String showBill() {
        String strSellDay = new SimpleDateFormat("dd/MM/yyyy").format(sellDay);
        int intSellAmount = (int) sellAmount;
        int intTotalSellPrice = (int) totalSellPrice();

        return importProduct.getId() +
                ", " + importProduct.getId() +
                ", " + importProduct.getSellPrice() +
                ", " + sellAmount +
                ", " + totalSellPrice();
    }
}


