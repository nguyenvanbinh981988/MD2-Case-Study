package View;

import StaffManage.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.*;

public class StaffManageView extends JFrame {
    private JPanel StaffManageView;
    private JButton addStaffButton;
    private JButton editStaffButton;
    private JButton exitButton;
    private JButton showStaffListButton;
    private JButton showPayrollButton;
    private JButton logOutButton;
    private JButton comeBackSuperMarketManageButton;
    private JTable table1;
    private JTextField idswing;
    private JTextField positionSwing;
    private JTextField addressSwing;
    private JTextField birthSwing;
    private JTextField genderSwing;
    private JTextField contractStartDaySwing;
    private JTextField contractEndDaySwing;
    private JTextField cccdSwing;
    private JTextField bankCardIdSwing;
    private JTextField salarySwing;
    private JTextField passSwing;
    private JTextField workDaySwing;
    private JButton findButton;
    private JTextField findInforSwing;
    private JTextField businessBonusSwing;
    private JTextField oldIdSwing;
    private JTextField nameSwing;
    private JButton deleteIDButton;
    private JTextField DeleteIdSwing;

    List<Staff> staffs = new ArrayList<>();

    ReadAndWriteStaffList readAndWriteStaffList = new ReadAndWriteStaffList();

    public Date birth, contractStartDay,contractEndDay;

    public double salary,workDay,businessBonus;

    public String oldId,id, name , position,address,gender,cccd,bankCardID,pass;
    public boolean checkTT=true;



    public StaffManageView() {
        this.setContentPane(StaffManageView);
        this.setVisible(true);
        this.setSize(1500, 1000);
        this.setLocationRelativeTo(null);
        JFrame jframe = this;



        addStaffButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkTT = true;
                String idStr = idswing.getText();
                id = valiString(idStr,"id");

                String nameStr = nameSwing.getText();
                name = valiString(nameStr,"name");

                String positionStr = positionSwing.getText();
                position=valiPosition(positionStr,"Position");


                String addressStr = addressSwing.getText();
                address=valiString(addressStr,"address");


                String genderStr = genderSwing.getText();
                gender=valiString(genderStr,"gender");

                String birthStr = birthSwing.getText();
                valiDateBirth(birthStr, "birth");

                String contractStartDayStr = contractStartDaySwing.getText();
                valiDateContractStartDay(contractStartDayStr, "contractStartDay");

                String contractEndDayStr = contractEndDaySwing.getText();
                valiDateContractEndDay( contractEndDayStr, "contractEndDay");

                String cccdStr = cccdSwing.getText();
                cccd = valiString(cccdStr,"cccd");


                String bankCardIDStr = bankCardIdSwing.getText();
                bankCardID=valiString(bankCardIDStr,"bankCardId");


                String passStr = passSwing.getText();
                pass=valiString(passStr,"pass");


                String salaryStr = salarySwing.getText();
                valiDoubleSalary(salaryStr, "salary");

                String workdayStr = workDaySwing.getText();
                valiDoubleWorkDay(workdayStr, "workday");

                String businessBonusStr = businessBonusSwing.getText();
                valiDoubleBusinessBonus(businessBonusStr, "businessBonus");
                readAndWriteStaffList.readFileStaffs(staffs);


                if (checkTT) {
                    boolean check = true;
                    for (int i = 0; i < staffs.size(); i++) {
                        if (id.equals(staffs.get(i).getId())) {
                            check = false;
                            break;
                        }
                    }
                    if (!check) {
                        JOptionPane.showConfirmDialog(jframe, "there is this id in list, please try again by other id");
                    } else {
                        Staff staff = new Staff(id, name, position, address, gender, birth, contractStartDay, contractEndDay, cccd, bankCardID, pass, salary, workDay, businessBonus);
                        staffs.add(staff);
                            JOptionPane.showConfirmDialog(jframe, "AddStaff is successful");
                            jframe.setVisible(false);
                            new StaffManageView();
                    }
                    } else {
                    JOptionPane.showConfirmDialog(jframe, "please edit wrongInformation");
                }
                readAndWriteStaffList.writeFileStaffs(staffs);
            }

            });

        editStaffButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkTT=true;
                String oldIdStr = oldIdSwing.getText();
                oldId = valiString(oldIdStr,"oldId");

                String idStr = idswing.getText();
                id = valiString(idStr,"id");

                String nameStr = nameSwing.getText();
                name=valiString(nameStr,"name");

                String positionStr = positionSwing.getText();
                position=valiPosition(positionStr,"position");

                String addressStr = addressSwing.getText();
                address=valiString(addressStr,"address");

                String genderStr = genderSwing.getText();
                gender=valiString(genderStr,"gender");

                String birthStr = birthSwing.getText();
                valiDateBirth(birthStr,"birth");

                String contractStartDayStr = contractStartDaySwing.getText();
                valiDateContractStartDay(contractStartDayStr,"contractStartDay");

                String contractEndDayStr = contractEndDaySwing.getText();
                valiDateContractEndDay(contractEndDayStr,"contractEndDay");

                String cccdStr = cccdSwing.getText();
                cccd=valiString(cccdStr,"cccd");

                String bankCardIDStr = bankCardIdSwing.getText();
                bankCardID= valiString(bankCardIDStr,"bankCardID");

                String passStr = passSwing.getText();
                pass= valiString(passStr,"pass");

                String salaryStr = salarySwing.getText();
                valiDoubleSalary(salaryStr,"salary");

                String workdayStr = workDaySwing.getText();
                valiDoubleWorkDay(workdayStr,"workday");

                String businessBonusStr = businessBonusSwing.getText();
                valiDoubleBusinessBonus(businessBonusStr,"businessBonus");

                readAndWriteStaffList.readFileStaffs(staffs);

                if (checkTT) {
                    if (oldId.equals(id)) {
                        for (int i = 0; i < staffs.size(); i++) {
                            if (id.equals(staffs.get(i).getId())) {
                                staffs.set(i, new Staff(id, name, position, address, gender, birth, contractStartDay, contractEndDay, cccd, bankCardID, pass, salary, workDay, businessBonus));
                                JOptionPane.showConfirmDialog(jframe, "EditStaff is successful");
                                jframe.setVisible(false);
                                new StaffManageView();
                            }
                        }
                    } else {
                        for (int i = 0; i < staffs.size(); i++) {
                            if (id.equals(staffs.get(i).getId())) {
                                JOptionPane.showConfirmDialog(jframe, "this is this id in list, please try again by other id");
                                jframe.setVisible(false);
                                new StaffManageView();
                            }
                        }
                        for (int i = 0; i < staffs.size(); i++) {
                            if (oldId.equals(staffs.get(i).getId())) {
                                staffs.set(i, new Staff(id, name, position, address, gender, birth, contractStartDay, contractEndDay, cccd, bankCardID, pass, salary, workDay, businessBonus));
                                JOptionPane.showConfirmDialog(jframe, "EditStaff is successful");
                                jframe.setVisible(false);
                                new StaffManageView();
                            }
                        }
                    }
                } else {
                    JOptionPane.showConfirmDialog(jframe, "please edit wrongInformation");
                }
                readAndWriteStaffList.writeFileStaffs(staffs);
            }
        });

        deleteIDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkTT = true;
                String deleteIdStr = DeleteIdSwing.getText();
                String deleteId = valiString(deleteIdStr, "id");
                readAndWriteStaffList.readFileStaffs(staffs);

                if (checkTT) {
                    for (int i = 0; i < staffs.size(); i++) {
                        if (deleteId.equals(staffs.get(i).getId())) {
                            checkTT = false;
                            staffs.remove(i);
                        }
                    }

                    if (checkTT){
                        JOptionPane.showConfirmDialog(jframe, "there is not this ID, please check again");
                    } else {
                        readAndWriteStaffList.writeFileStaffs(staffs);
                        JOptionPane.showConfirmDialog(jframe, "Delete Id is successful");
                        jframe.setVisible(false);
                        new StaffManageView();
                    }
                } else {
                    JOptionPane.showConfirmDialog(jframe, "please edit wrongInformation");
                }
            }
        });


        showStaffListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                readAndWriteStaffList.readFileStaffs(staffs);
                String[] title = {"Id", "Name", "Position", "Address", "Gender", "Birth", "ContractStartDay", "ContractEndDay", "Cccd","BankCardID", "Pass", "Salary", "Workday", "BusinessBonus","ActuallyIncome"};
                String[][] staffList = new String[staffs.size()+1][];
                staffList[0]=title;
                for (int i = 0; i < staffs.size(); i++) {
                    Staff staff = staffs.get(i);
                    String a,b,c;
                    int d,f,g,h;
                    a = new SimpleDateFormat("dd/MM/yyyy").format(staff.getBirth());
                    b = new SimpleDateFormat("dd/MM/yyyy").format(staff.getContractStartDay());
                    c = new SimpleDateFormat("dd/MM/yyyy").format(staff.getContractEndDay());
                    d = (int) staff.getSalary();
                    f = (int) staff.getWorkday();
                    g = (int) staff.getBusinessBonus();
                    h = (int) staff.actuallyIncome();

                    staffList[i+1] = new String[]{staff.getId() + "", staff.getName(), staff.getPosition() + "", staff.getAddress() + "", staff.getGender() + "", a + "", b + "", c + "", staff.getCccd() + "", staff.getBankCardID() + "", staff.getPass() + "", d + "", f + "", g + "", h + " VND"};
                }
                DefaultTableModel model = new DefaultTableModel(staffList, title);
                table1.setModel(model);
            }
        });


        showPayrollButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                readAndWriteStaffList.readFileStaffs(staffs);
                String[] title = {"Id", "Name", "Position", "Salary", "WorkDays", "BusinessBonus", "ActuallyIncome"};
                String[][] staffList = new String[staffs.size()+1][];
                staffList[0]=title;
                for (int i = 0; i < staffs.size(); i++) {
                    Staff staff = staffs.get(i);
                    int d,f,g,h;
                    d = (int) staff.getSalary();
                    f = (int) staff.getWorkday();
                    g = (int) staff.getBusinessBonus();
                    h = (int) staff.actuallyIncome();
                    staffList[i+1] = new String[]{staff.getId() + "", staff.getName() + "", staff.getPosition() + "", d + "", f + "", g + "", h + " VND"};
                }
                DefaultTableModel model = new DefaultTableModel(staffList, title);
                table1.setModel(model);
            }
        });


        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Staff> staffsFind = new ArrayList<>();
                readAndWriteStaffList.readFileStaffs(staffs);
                String idFind = findInforSwing.getText();
                for (int i = 0; i < staffs.size(); i++) {
                    if (staffs.get(i).getId().contains(idFind)) {
                        staffsFind.add(staffs.get(i));
                    }
                }


                String[] title = {"Id", "Name", "Position", "Address", "Gender", "Birth", "ContractStartDay", "ContractEndDay", "Cccd,BankCardID", "Pass", "Salary", "Workday", "BusinessBonus","ActuallyIncome"};
                String[][] staffsFindList = new String[staffsFind.size()+1][];
                staffsFindList[0]=title;
                for (int i = 0; i < staffsFind.size(); i++) {
                    Staff staff = staffsFind.get(i);
                    String a,b,c;
                    int d,f,g,h;
                    a = new SimpleDateFormat("dd/MM/yyyy").format(staff.getBirth());
                    b = new SimpleDateFormat("dd/MM/yyyy").format(staff.getContractStartDay());
                    c = new SimpleDateFormat("dd/MM/yyyy").format(staff.getContractEndDay());
                    d = (int) staff.getSalary();
                    f = (int) staff.getWorkday();
                    g = (int) staff.getBusinessBonus();
                    h = (int) staff.actuallyIncome();
                    staffsFindList[i+1] = new String[]{staff.getId() + "", staff.getName(), staff.getPosition() + "", staff.getAddress() + "", staff.getGender() + "", a + "", b + "", c + "", staff.getCccd() + "", staff.getBankCardID() + "", staff.getPass() + "", d + "", f + "", g + "", h + " VND"};
                }

                DefaultTableModel model = new DefaultTableModel(staffsFindList, title);
                table1.setModel(model);
            }
        });

        comeBackSuperMarketManageButton.addActionListener(new ActionListener() {
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

    public void valiDateBirth(String b, String c) {
        JFrame jframe = this;
        if (!b.equals("")){
            try {
                birth = new SimpleDateFormat("dd/MM/yyyy").parse(b);
            } catch (Exception i) {
                JOptionPane.showConfirmDialog(jframe, c + " is wrong, please try again. ex: 25/10/2022");
                checkTT = false;
            }
    } else {
            checkTT = false;
            JOptionPane.showConfirmDialog(jframe, c + " was not been entered, please try again. ex: 25/10/2022");
        }
    }

    public void valiDateContractStartDay(String b, String c) {
        JFrame jframe = this;
        if (!b.equals("")){
            try {
                contractStartDay = new SimpleDateFormat("dd/MM/yyyy").parse(b);
            } catch (Exception i) {
                JOptionPane.showConfirmDialog(jframe, c + " is wrong, please try again. ex: 25/10/2022");
                checkTT = false;
            }
        } else {
            checkTT = false;
            JOptionPane.showConfirmDialog(jframe, c + " was not been entered, please try again. ex: 25/10/2022");
        }
    }

    public void valiDateContractEndDay(String b, String c) {
        JFrame jframe = this;
        if (!b.equals("")){
            try {
                contractEndDay = new SimpleDateFormat("dd/MM/yyyy").parse(b);
            } catch (Exception i) {
                JOptionPane.showConfirmDialog(jframe, c + " is wrong, please try again. ex: 25/10/2022");
                checkTT = false;
            }
        } else {
            checkTT = false;
            JOptionPane.showConfirmDialog(jframe, c + " was not been entered, please try again. ex: 25/10/2022");
        }
    }

    public void valiDoubleSalary(String b, String c) {
        JFrame jframe = this;
        if (!b.equals("")) {
            try {
                salary = Double.parseDouble(b);
            } catch (Exception i) {
                JOptionPane.showConfirmDialog(jframe, c + " is wrong, please try again. ex: 5.005 or 5");
                checkTT = false;
            }
        } else {
        checkTT = false;
            JOptionPane.showConfirmDialog(jframe, c + " was not been entered , please try again. ex: 5.005 or 5");
        }
    }

    public void valiDoubleWorkDay(String b, String c) {
        JFrame jframe = this;
        if (!b.equals("")) {
            try {
                workDay = Double.parseDouble(b);
            } catch (Exception i) {
                JOptionPane.showConfirmDialog(jframe, c + " is wrong, please try again. ex: 5.005 or 5");
                checkTT = false;
            }
        } else {
            checkTT = false;
            JOptionPane.showConfirmDialog(jframe, c + " was not been entered , please try again. ex: 5.005 or 5");
        }
    }

    public void valiDoubleBusinessBonus(String b, String c) {
        JFrame jframe = this;
        if (!b.equals("")) {
            try {
                businessBonus = Double.parseDouble(b);
            } catch (Exception i) {
                JOptionPane.showConfirmDialog(jframe, c + " is wrong, please try again. ex: 5.005 or 5");
                checkTT = false;
            }
        } else {
            checkTT = false;
            JOptionPane.showConfirmDialog(jframe, c + " was not been entered , please try again. ex: 5.005 or 5");
        }
    }
    public String valiString(String b, String c) {
        JFrame jframe = this;
        if (b.equals("")) {
            checkTT = false;
            JOptionPane.showConfirmDialog(jframe, c + " was not been entered, please try again");
        }
        return b;
    }

    public String valiPosition(String b, String c) {
        JFrame jframe = this;
        if (b.equals("")){
            JOptionPane.showConfirmDialog(jframe, c + " was not been entered, please try again");

        } else if (b.equals("manager")||b.equals("leader")||b.equals("staff")) {
            return b;
        } else {
            checkTT = false;
            JOptionPane.showConfirmDialog(jframe, c + " is wrong, please try again. please enter manager or leader or staff ");
        }
        return b;
    }
}
