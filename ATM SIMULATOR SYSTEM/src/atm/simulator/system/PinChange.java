package atm.simulator.system;

import com.encryption.RSAEncryption;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.KeyPair;
import java.security.PublicKey;
import java.sql.PreparedStatement;
import java.util.Base64;


public class PinChange extends JFrame implements ActionListener {

    JPasswordField pin, repin;
    JButton change, back;
    String pinnumber;

    PinChange(String pinnumber) {

        this.pinnumber = pinnumber;
        setLayout(null);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 1054, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);

        JLabel text = new JLabel("Vui lòng nhập mã pin.");
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 16));
        text.setBounds(250, 280, 500, 35);
        image.add(text);

        JLabel pintext = new JLabel("PIN MỚI: ");
        pintext.setForeground(Color.WHITE);
        pintext.setFont(new Font("System", Font.BOLD, 16));
        pintext.setBounds(165, 421, 180, 25);
        image.add(pintext);

        pin = new JPasswordField();
        pin.setFont(new Font("Raleway", Font.BOLD, 25));
        pin.setBounds(330, 421, 180, 25);
        image.add(pin);

        JLabel repintext = new JLabel("NHẬP LẠI: ");
        repintext.setForeground(Color.WHITE);
        repintext.setFont(new Font("System", Font.BOLD, 16));
        repintext.setBounds(165, 457, 180, 25);
        image.add(repintext);

        repin = new JPasswordField();
        repin.setFont(new Font("Raleway", Font.BOLD, 25));
        repin.setBounds(330, 457, 180, 25);
        image.add(repin);

        change = new JButton("Thay đổi");
        change.setBounds(355, 500, 150, 30);
        change.addActionListener(this);
        image.add(change);

        back = new JButton("Quay về");
        back.setBounds(155, 500, 150, 30);
        back.addActionListener(this);
        image.add(back);


        setSize(900, 900);
        setLocation(300, 0);
        setUndecorated(true);
        setVisible(true);
    }


    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == change) {
            try {
                String npin = pin.getText();
                String rpin = repin.getText();

                if (!npin.equals(rpin)) {
                    JOptionPane.showMessageDialog(null, "Quý khách nhập mã pin không trùng nhau. \n Xin vui lòng nhập lại!.");
                    return;
                }
                if (npin.equals("")) {
                    JOptionPane.showMessageDialog(null, "Vui lòng không trống mã pin!");
                    return;
                }

                if (npin.length() != 6) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng 6 số.");
                    return;
                }

                if (rpin.equals("")) {
                    JOptionPane.showMessageDialog(null, "Vui lòng không bỏ trống nhập lại mã pin!");
                    return;
                }

                Conn conn = new Conn();

                // Generate key pair
                KeyPair keyPair = RSAEncryption.generateKeyPair();
                PublicKey publicKey = keyPair.getPublic();

                // Mã hoá pinnumber trước khi chèn vào câu lệnh SQL
                byte[] pinHash = RSAEncryption.hash(npin);
                byte[] encryptedPin = RSAEncryption.encrypt(pinHash, publicKey);
                String encodedPin = Base64.getEncoder().encodeToString(encryptedPin);

                // Chèn dữ liệu vào câu lệnh SQL sử dụng prepared statement
                String query1 = "update atm set pin = '" + rpin + "' where pin = '" + pinnumber + "' ";
                String query2 = "update login set pin = '" + rpin + "' where pin = '" + pinnumber + "' ";
                String query3 = "update signupthree set pin = ? where pin = '" + pinnumber + "' ";
                String query4 = "update bank_account set pin = ? where pin = '" + pinnumber + "' ";

                try {
                    PreparedStatement ps1 = conn.c.prepareStatement(query1);
                    ps1.executeUpdate();
                    PreparedStatement ps2 = conn.c.prepareStatement(query2);
                    ps2.executeUpdate();
                    PreparedStatement ps3 = conn.c.prepareStatement(query3);
                    ps3.setString(1, encodedPin);
                    ps3.executeUpdate();
                    PreparedStatement ps4 = conn.c.prepareStatement(query4);
                    ps4.setString(1, pinnumber);
                    ps4.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Thay đổi mã pin thành công!");
                    setVisible(false);
                    new Transactions(rpin).setVisible(true);

                } catch (Exception e) {
                    System.out.println(e);
                }

            } catch (Exception e) {
                System.out.println(e);
            }
        } else {
            setVisible(false);
            new Transactions(pinnumber).setVisible(true);
        }

    }

    public static void main(String args[]) {
        new PinChange("").setVisible(true);
    }
}
