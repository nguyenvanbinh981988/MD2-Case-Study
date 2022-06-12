package View;

import StaffManage.ReadAndWriteStaffList;
import StaffManage.Staff;
import checkLogin.CheckLogin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class SuperMarketManagerView extends JFrame {

    private JPanel superMarketManager;
    private JButton staffManage;
    private JButton importProductManage;
    private JButton sellProductManage;
    private JButton editPassButton;
    private JButton logoutButton;
    private JButton exitButton;

    ReadAndWriteStaffList readAndWriteStaffList = new ReadAndWriteStaffList();

    List<Staff> staffs = new ArrayList<>();

    {
        readAndWriteStaffList.readFileStaffs(staffs);
    }

    public SuperMarketManagerView() {
        this.setContentPane(superMarketManager);
        this.setVisible(true);
        this.setSize(500, 300);
        this.setLocationRelativeTo(null);
        JFrame jframe = this;

        staffManage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (CheckLogin.user.getPosition().equals("manager")) {
                    jframe.setVisible(false);
                    StaffManageView staffManageView = new StaffManageView();
                    staffManageView.setVisible(true);
                } else {
                    JOptionPane.showConfirmDialog(jframe, "you do not have permission to access this item, please choice again");
                    jframe.setVisible(false);
                    new SuperMarketManagerView();
                }
            }

        });

        importProductManage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ((CheckLogin.user.getPosition().equals("manager"))||(CheckLogin.user.getPosition().equals("leader"))) {
                    jframe.setVisible(false);
                    ImportProductManageView importProductManageView = new ImportProductManageView();
                    importProductManageView.setVisible(true);
                } else {
                    JOptionPane.showConfirmDialog(jframe, "you do not have permission to access this item, please choice again");
                    jframe.setVisible(false);
                    new SuperMarketManagerView();
                }
            }
        });

        sellProductManage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ((CheckLogin.user.getPosition().equals("leader"))||(CheckLogin.user.getPosition().equals("staff"))) {
                    jframe.setVisible(false);
                    SellProductManageView sellProductManageView = new SellProductManageView();
                    sellProductManageView.setVisible(true);
                } else {
                    JOptionPane.showConfirmDialog(jframe, "you do not have permission to access this item, please choice again");
                    jframe.setVisible(false);
                    new SuperMarketManagerView();
                }
            }
        });


        editPassButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    jframe.setVisible(false);
                    EditPass editPass = new EditPass();
                    editPass.setVisible(true);
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


}
