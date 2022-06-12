package View;

import checkLogin.CheckLogin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditPass extends JFrame {
    private JTextField oldPass;
    private JTextField newPass1;
    private JButton OKButton;
    private JTextField newPass2;
    private JPanel EditPass;

    public EditPass() {
        this.setContentPane(EditPass);
        this.setVisible(true);
        this.setSize(500, 300);
        this.setLocationRelativeTo(null);
        JFrame jframe = this;

        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String old = oldPass.getText();
                String newP1 = newPass1.getText();
                String newP2 = newPass2.getText();
                jframe.setVisible(false);
                CheckLogin passEdit = new CheckLogin();
                passEdit.EditPass(old, newP1, newP2);
                JOptionPane.showConfirmDialog(jframe, "Pass was changed, please log in again");
                new SuperMarketSystemLogin();
            }
        });
    }
}

