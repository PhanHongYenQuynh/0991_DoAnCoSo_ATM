package atm.simulator.system;

import com.encryption.RSAEncryption;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.KeyPair;
import java.security.PublicKey;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Base64;
import java.util.Date;

public class Deposit extends JFrame implements ActionListener {

    JTextField amount;
    JButton deposit, back;
    String pinnumber;

    Deposit(String pinnumber) {
        this.pinnumber = pinnumber;
        setLayout(null);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 1054, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);

        JLabel text = new JLabel("Vui lòng nhập số tiền muốn gửi.");
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 16));
        text.setBounds(200, 300, 550, 26);
        image.add(text);

        amount = new JTextField();
        amount.setFont(new Font("Raleway", Font.BOLD, 14));
        amount.setBounds(170, 350, 320, 20);
        image.add(amount);

        deposit = new JButton("Gửi tiền");
        deposit.setBounds(355, 485, 150, 30);
        deposit.addActionListener(this);
        image.add(deposit);

        back = new JButton("Quay về");
        back.setBounds(355, 520, 150, 30);
        back.addActionListener(this);
        image.add(back);

        setSize(900, 900);
        setLocation(300, 0);
        setUndecorated(true);
        setVisible(true);
    }

    // Code chưa mã hoá - không xoá
   /* public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == deposit) {
            String number = amount.getText();
            Date date = new Date();
            if (number.equals("")) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập số tiền quý khách muốn gửi!.");
            } else {
                // Kiểm tra số tiền nhập vào không phải số âm
                try {
                    int amountInt = Integer.parseInt(number);
                    if (amountInt <= 0) {
                        JOptionPane.showMessageDialog(null, "Số tiền gửi không hợp lệ!.");
                        return;
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Số tiền gửi không hợp lệ!.");
                    return;
                }

                try {
                    Conn conn = new Conn();
                    String acc_no = "";
                    String query = "select acc_no from bank_account where pin = '" + pinnumber + "'";
                    ResultSet rs = conn.s.executeQuery(query);
                    if (rs.next()) {
                        acc_no = rs.getString("acc_no");
                    }
                    String query1 = "insert into atm values('" + pinnumber + "', '" + date + "', 'Gửi tiền', '" + number + "')";
                    String query2 = "update bank_account set balance = balance + " + number + " where acc_no = '" + acc_no + "'";
                    conn.s.executeUpdate(query1);
                    conn.s.executeUpdate(query2);
                    JOptionPane.showMessageDialog(null, "Tiền gửi thành công là: " + number + " VNĐ");
                    setVisible(false);
                    new Transactions(pinnumber).setVisible(true);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        } else if (ae.getSource() == back) {
            setVisible(false);
            new Transactions(pinnumber).setVisible(true);
        }
    }*/

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == deposit) {
            String number = amount.getText();
            Date date = new Date();
            if (number.equals("")) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập số tiền quý khách muốn gửi!.");
            } else {
                // Kiểm tra số tiền nhập vào không phải số âm
                try {
                    int amountInt = Integer.parseInt(number);
                    if (amountInt <= 0) {
                        JOptionPane.showMessageDialog(null, "Số tiền gửi không hợp lệ!.");
                        return;
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Số tiền gửi không hợp lệ!.");
                    return;
                }

                try {
                    Conn conn = new Conn();
                    String acc_no = "";

                    // Mã hóa mã PIN mới
                    KeyPair keyPair = RSAEncryption.generateKeyPair();
                    PublicKey publicKey = keyPair.getPublic();
                    byte[] pinHash = RSAEncryption.hash(pinnumber);
                    byte[] encryptedPin = RSAEncryption.encrypt(pinHash, publicKey);
                    String encodedPin = Base64.getEncoder().encodeToString(encryptedPin);

                    // Truy vấn số tài khoản với mã PIN mới
                    String query = "select acc_no from bank_account where pin = ?";
                    PreparedStatement ps = conn.c.prepareStatement(query);
                    ps.setString(1, encodedPin);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        acc_no = rs.getString("acc_no");
                    }

                    // Cập nhật số tiền trong tài khoản và ghi nhật ký giao dịch với mã PIN mới
                    String query1 = "insert into atm values(?, ?, 'Gửi tiền', ?)";
                    String query2 = "update bank_account set balance = balance + ? where acc_no = ?";
                    PreparedStatement ps1 = conn.c.prepareStatement(query1);
                    PreparedStatement ps2 = conn.c.prepareStatement(query2);
                    ps1.setString(1, encodedPin);
                    ps1.setDate(2, new java.sql.Date(date.getTime()));
                    ps1.setInt(3, Integer.parseInt(number));
                    ps2.setInt(1, Integer.parseInt(number));
                    ps2.setString(2, acc_no);
                    ps1.executeUpdate();
                    ps2.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Tiền gửi thành công là: " + number + " VNĐ");
                    setVisible(false);
                    new Transactions(pinnumber).setVisible(true);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        } else if (ae.getSource() == back) {
            setVisible(false);
            new Transactions(pinnumber).setVisible(true);
        }
    }


    public static void main(String args[]) {
        new Deposit("").setVisible(true);
    }
}
