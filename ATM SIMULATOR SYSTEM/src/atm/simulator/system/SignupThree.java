package atm.simulator.system;

import com.encryption.RSAEncryption;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.KeyPair;
import java.security.PublicKey;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.Base64;
import java.util.Random;


public class SignupThree extends JFrame implements ActionListener {

    JRadioButton r1, r2, r3, r4;
    JCheckBox c1, c2, c3, c4, c5, c6, c7;
    JButton submit, cancel;
    String formno;

    SignupThree(String formno) {
        this.formno = formno;
        setLayout(null);

        JLabel l1 = new JLabel("Trang 3: Chi tiết bổ sung");
        l1.setFont(new Font("Raleway", Font.BOLD, 22));
        l1.setBounds(280, 40, 400, 40);
        add(l1);

        JLabel type = new JLabel("Loại tài khoản: ");
        type.setFont(new Font("Raleway", Font.BOLD, 22));
        type.setBounds(100, 140, 200, 30);
        add(type);

        r1 = new JRadioButton("Tài khoản tiết kiệm");
        r1.setFont(new Font("Raleway", Font.BOLD, 16));
        r1.setBackground(Color.WHITE);
        r1.setBounds(100, 180, 250, 20);
        add(r1);

        r2 = new JRadioButton("Tài khoản thanh toán");
        r2.setFont(new Font("Raleway", Font.BOLD, 16));
        r2.setBackground(Color.WHITE);
        r2.setBounds(350, 180, 250, 20);
        add(r2);

        r3 = new JRadioButton("Tài khoản tín dụng");
        r3.setFont(new Font("Raleway", Font.BOLD, 16));
        r3.setBackground(Color.WHITE);
        r3.setBounds(100, 220, 250, 20);
        add(r3);

        r4 = new JRadioButton("Tài khoản cho vay");
        r4.setFont(new Font("Raleway", Font.BOLD, 16));
        r4.setBackground(Color.WHITE);
        r4.setBounds(350, 220, 260, 20);
        add(r4);

        ButtonGroup groupaccount = new ButtonGroup();
        groupaccount.add(r1);
        groupaccount.add(r2);
        groupaccount.add(r3);
        groupaccount.add(r4);

        JLabel card = new JLabel("STK: ");
        card.setFont(new Font("Raleway", Font.BOLD, 22));
        card.setBounds(100, 300, 200, 30);
        add(card);

        JLabel cnumber = new JLabel("XXXX-XXXX-XXXX-4134");
        cnumber.setFont(new Font("Raleway", Font.BOLD, 22));
        cnumber.setBounds(330, 300, 300, 30);
        add(cnumber);

        JLabel carddetail = new JLabel("Vui lòng nhập đủ 16 số");
        carddetail.setFont(new Font("Raleway", Font.BOLD, 12));
        carddetail.setBounds(100, 330, 300, 20);
        add(carddetail);

        JLabel pin = new JLabel("PIN: ");
        pin.setFont(new Font("Raleway", Font.BOLD, 22));
        pin.setBounds(100, 370, 200, 30);
        add(pin);

        JLabel pnumber = new JLabel("XXXXXX");
        pnumber.setFont(new Font("Raleway", Font.BOLD, 22));
        pnumber.setBounds(330, 370, 300, 30);
        add(pnumber);

        JLabel pindetail = new JLabel("Vui lòng nhập đủ 6 số");
        pindetail.setFont(new Font("Raleway", Font.BOLD, 12));
        pindetail.setBounds(100, 400, 300, 20);
        add(pindetail);

        JLabel services = new JLabel("Dịch vụ cần thiết: ");
        services.setFont(new Font("Raleway", Font.BOLD, 22));
        services.setBounds(100, 450, 400, 30);
        add(services);

        c1 = new JCheckBox("ATM CARD");
        c1.setBackground(Color.WHITE);
        c1.setFont(new Font("Raleway", Font.BOLD, 16));
        c1.setBounds(100, 500, 200, 30);
        add(c1);

        c2 = new JCheckBox("Internet Banking");
        c2.setBackground(Color.WHITE);
        c2.setFont(new Font("Raleway", Font.BOLD, 16));
        c2.setBounds(350, 500, 200, 30);
        add(c2);

        c3 = new JCheckBox("Mobile Banking");
        c3.setBackground(Color.WHITE);
        c3.setFont(new Font("Raleway", Font.BOLD, 16));
        c3.setBounds(100, 550, 200, 30);
        add(c3);

        c4 = new JCheckBox("Email & sms banking");
        c4.setBackground(Color.WHITE);
        c4.setFont(new Font("Raleway", Font.BOLD, 16));
        c4.setBounds(350, 550, 230, 30);
        add(c4);

        c5 = new JCheckBox("Cheque Book");
        c5.setBackground(Color.WHITE);
        c5.setFont(new Font("Raleway", Font.BOLD, 16));
        c5.setBounds(100, 600, 200, 30);
        add(c5);

        c6 = new JCheckBox("E-Statement");
        c6.setBackground(Color.WHITE);
        c6.setFont(new Font("Raleway", Font.BOLD, 16));
        c6.setBounds(350, 600, 250, 30);
        add(c6);

        c7 = new JCheckBox("Tôi cam kết hoàn toàn chịu trách nhiệm toàn bộ lời khai trên ");
        c7.setBackground(Color.WHITE);
        c7.setFont(new Font("Raleway", Font.BOLD, 12));
        c7.setBounds(100, 680, 600, 30);
        add(c7);

        submit = new JButton("Xác nhận");
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.setOpaque(true);
        submit.setBorderPainted(false);
        submit.setFont(new Font("Raleway", Font.BOLD, 14));
        submit.setBounds(250, 720, 120, 30);
        submit.addActionListener(this);
        add(submit);

        cancel = new JButton("Huỷ");
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.setOpaque(true);
        cancel.setBorderPainted(false);
        cancel.setFont(new Font("Raleway", Font.BOLD, 14));
        cancel.setBounds(420, 720, 100, 30);
        cancel.addActionListener(this);
        add(cancel);

        getContentPane().setBackground(Color.WHITE);

        setSize(850, 820);
        setLocation(350, 0);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit) {
            String accountType = null;
            if (r1.isSelected()) {
                accountType = "Tài khoản tiết kiệm";
            } else if (r2.isSelected()) {
                accountType = "Tài khoản thanh toán";
            } else if (r3.isSelected()) {
                accountType = "Tài khoản tín dụng";
            } else if (r4.isSelected()) {
                accountType = "Tài khoản cho vay";
            }

            Random ran = new Random();
            String cardNumber = "" + Math.abs((ran.nextLong() % 90000000L) + 970422000000000L);
            String pinNumber = "" + Math.abs((ran.nextLong() % 900000L) + 100000L);

            String facility = "";
            if (c1.isSelected()) {
                facility = facility + "ATM Card,";
            }
            if (c2.isSelected()) {
                facility = facility + "Internet Banking,";
            }
            if (c3.isSelected()) {
                facility = facility + "Mobile Banking,";
            }
            if (c4.isSelected()) {
                facility = facility + "EMAIL & SMS Alerts,";
            }
            if (c5.isSelected()) {
                facility = facility + "Cheque Book,";
            }
            if (c6.isSelected()) {
                facility = facility + "E-Statement,";
            }

            // Loại bỏ dấu phẩy cuối cùng
            if (facility.endsWith(",")) {
                facility = facility.substring(0, facility.length() - 1);
            }

            int balance = 0;
            int wrong_attempts = 0;
            Timestamp lockedUntil = null;

            try {
                if (accountType.equals("")) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn loại tài khoản");
                } else {

                    Conn conn = new Conn();

                    String encodedPin = RSAKeyManager.hash(pinNumber);

                    // Chèn dữ liệu vào câu lệnh SQL sử dụng prepared statement
                    String query1 = "insert into signupthree values(?, ?, ?, ?, ?)";
                    String query2 = "insert into login values(?, ?, ?, ?, ?)";
                    String query3 = "insert into bank_account values(?, ?, ?)";
                    PreparedStatement ps1 = conn.c.prepareStatement(query1);
                    ps1.setString(1, formno);
                    ps1.setString(2, accountType);
                    ps1.setString(3, cardNumber);
                    ps1.setString(4, encodedPin);
                    ps1.setString(5, facility);
                    ps1.executeUpdate();

                    PreparedStatement ps2 = conn.c.prepareStatement(query2);
                    ps2.setString(1, formno);
                    ps2.setString(2, cardNumber);
                    ps2.setString(3, encodedPin);
                    ps2.setInt(4, wrong_attempts);
                    ps2.setTimestamp(5, lockedUntil);
                    ps2.executeUpdate();

                    PreparedStatement ps3 = conn.c.prepareStatement(query3);
                    ps3.setString(1, cardNumber);
                    ps3.setString(2, encodedPin);
                    ps3.setInt(3, balance);
                    ps3.executeUpdate();

                    JOptionPane.showMessageDialog(null, "STK:" + cardNumber + "\n Pin:" + pinNumber);
                    System.out.println("Card: " + cardNumber);
                    System.out.println("Pin: " + pinNumber);
                    setVisible(false);
                    new Login().setVisible(true);
                }

            } catch (Exception e) {
                System.out.println(e);
            }

        } else if (ae.getSource() == cancel) {
            setVisible(false);
            new Login().setVisible(true);
        }
    }

    public static void main(String args[]) {
        new SignupThree("").setVisible(true);

    }
}