package View;

import checkLogin.CheckLogin;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SuperMarketSystemLogin extends JFrame {
    private JPanel LogIn;
    private JTextField textId;
    private JTextField textPass;
    private JButton logInButton;
    private JButton exitButton;


    public SuperMarketSystemLogin() {
        this.setContentPane(LogIn);
        this.setVisible(true);
        this.setSize(500, 300);
        this.setLocationRelativeTo(null);
        JFrame jframe = this;


        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String account = textId.getText();
                String pass = textPass.getText();
                CheckLogin accountMenu = new CheckLogin();
                if (accountMenu.accountCheck(account,pass) != null) {
                    jframe.setVisible(false);
                    new SuperMarketManagerView();
                } else {
                    if (CheckLogin.count == 5) {
                        JOptionPane.showConfirmDialog(jframe, "Account or Pass were been entered 5 times");
                        System.exit(0);
                    } else {
                        JOptionPane.showConfirmDialog(jframe, "Account or Pass are wrong, please try again");
                        jframe.setVisible(false);
                        new SuperMarketSystemLogin();
                    }
                }
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
