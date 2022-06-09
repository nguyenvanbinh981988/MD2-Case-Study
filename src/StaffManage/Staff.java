package StaffManage;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Staff {
    private String id;
    private String name;
    private String position;
    private String address;
    private String gender;
    private Date birth;
    private Date contractStartDay;
    private Date contractEndDay;
    private String cccd;
    private String bankCardID;
    private String pass;
    private double salary;

    private double workday;

    private double businessBonus;

    public Staff() {
    }

    public Staff(String id, String name, String position, String address, String gender, Date birth, Date contractStartDay, Date contractEndDay, String cccd, String bankCardID, String pass, double salary, double workday, double businessBonus) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.address = address;
        this.gender = gender;
        this.birth = birth;
        this.contractStartDay = contractStartDay;
        this.contractEndDay = contractEndDay;
        this.cccd = cccd;
        this.bankCardID = bankCardID;
        this.pass = pass;
        this.salary = salary;
        this.workday = workday;
        this.businessBonus = businessBonus;
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Date getContractStartDay() {
        return contractStartDay;
    }

    public void setContractStartDay(Date contractStartDay) {
        this.contractStartDay = contractStartDay;
    }

    public Date getContractEndDay() {
        return contractEndDay;
    }

    public void setContractEndDay(Date contractEndDay) {
        this.contractEndDay = contractEndDay;
    }

    public String getCccd() {
        return cccd;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }

    public String getBankCardID() {
        return bankCardID;
    }

    public void setBankCardID(String bankCardID) {
        this.bankCardID = bankCardID;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getWorkday() {
        return workday;
    }

    public void setWorkday(double workday) {
        this.workday = workday;
    }

    public double getBusinessBonus() {
        return businessBonus;
    }

    public void setBusinessBonus(double businessBonus) {
        this.businessBonus = businessBonus;
    }

    public double actuallyIncome(){
        double actuallyReceived = (this.salary/200)*(this.workday*8)+this.businessBonus;
        return actuallyReceived;
    }

    @Override
    public String toString() {
        String strBirth = new SimpleDateFormat("dd/MM/yyyy").format(birth);
       String StrContractStartDay = new SimpleDateFormat("dd/MM/yyyy").format(contractStartDay);
        String StrContractEndDay = new SimpleDateFormat("dd/MM/yyyy").format(contractEndDay);
        int StrSalary = (int) salary;
        int StrBusinessBonus = (int) businessBonus;
        int StrActuallyIncome = (int) actuallyIncome();


        return  id +
                ", " + name +
                ", " + position +
                ", " + address +
                ", " + gender +
                ", " + strBirth +
                ", " + StrContractStartDay +
                ", " + StrContractEndDay +
                ", " + cccd +
                ", " + bankCardID +
                ", " + pass +
                ", " + StrSalary +
                ", " + workday +
                ", " + StrBusinessBonus +
                ", " + StrActuallyIncome;
    }

    public String toWriteStaffList() {
        String strBirth = new SimpleDateFormat("dd/MM/yyyy").format(birth);
        String StrContractStartDay = new SimpleDateFormat("dd/MM/yyyy").format(contractStartDay);
        String StrContractEndDay = new SimpleDateFormat("dd/MM/yyyy").format(contractEndDay);
        int StrSalary = (int) salary;
        int StrBusinessBonus = (int) businessBonus;
        int StrActuallyIncome = (int) actuallyIncome();

        return id +
                "," + name +
                "," + position  +
                "," + address  +
                "," + gender +
                "," + strBirth +
                "," + StrContractStartDay +
                "," + StrContractEndDay +
                "," + cccd +
                "," + bankCardID +
                "," + pass  +
                "," + StrSalary +
                "," + workday +
                "," + StrBusinessBonus +
                "," + StrActuallyIncome ;
    }

    public String toStringPayRoll() {
        String strBirth = new SimpleDateFormat("dd/MM/yyyy").format(birth);
        String StrContractStartDay = new SimpleDateFormat("dd/MM/yyyy").format(contractStartDay);
        String StrContractEndDay = new SimpleDateFormat("dd/MM/yyyy").format(contractEndDay);
        int StrSalary = (int) salary;
        int StrBusinessBonus = (int) businessBonus;
        int StrActuallyIncome = (int) actuallyIncome();


        return id +
                ", " + name +
                ", " + position  +
                ", " + StrSalary +
                ", " + workday +
                ", " + StrBusinessBonus +
                ", " + StrActuallyIncome;
    }

}
