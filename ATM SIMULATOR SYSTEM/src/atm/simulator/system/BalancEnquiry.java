package atm.simulator.system;

import com.encryption.RSAEncryption;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class BalancEnquiry extends JFrame implements ActionListener {
    JButton back;
    String cardnumber;

    BalancEnquiry(String cardnumber) {
        this.cardnumber = cardnumber;
        setLayout(null);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 1054, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);

        back = new JButton("Quay về");
        back.setBounds(355, 520, 150, 30);
        back.addActionListener(this);
        image.add(back);

        Conn conn = new Conn();
        int balance = 0;
        try {

            String query = "select balance from bank_account where acc_no = ?";
            PreparedStatement ps = conn.c.prepareStatement(query);
            ps.setString(1, cardnumber);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                balance = rs.getInt("balance");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        JLabel text = new JLabel("Số dư tài khoản của quý khách là:" + balance);
        text.setForeground(Color.WHITE);
        text.setBounds(170, 300, 400, 30);
        image.add(text);

        String amountText = numberToWords(balance);
        JLabel label = new JLabel(amountText);
        label.setForeground(Color.WHITE);
        label.setPreferredSize(new Dimension(400, 50));
        label.setBounds(170, 350, 520, 50);
        image.add(label);

        setSize(900, 900);
        setLocation(300, 0);
        setUndecorated(true);
        setVisible(true);
    }

    public static String numberToWords(int number) {
        if (number == 0) {
            return " đồng";
        }

        String[] units = {"", "một", "hai", "ba", "bốn", "năm", "sáu", "bảy", "tám", "chín", "mười",
                "mười một", "mười hai", "mười ba", "mười bốn", "mười lăm", "mười sáu", "mười bảy", "mười tám", "mười chín"};
        String[] tens = {"", "", "hai mươi", "ba mươi", "bốn mươi", "năm mươi", "sáu mươi", "bảy mươi", "tám mươi", "chín mươi"};

        if (number < 0) {
            return "âm " + numberToWords(Math.abs(number));
        }

        if (number < 20) {
            return units[number];
        }

        if (number < 100) {
            return tens[number / 10] + ((number % 10 != 0) ? " " : "") + units[number % 10];
        }

        if (number < 1000) {
            return units[number / 100] + " trăm" + ((number % 100 != 0) ? " " : "") + numberToWords(number % 100);
        }

        if (number < 1000000) {
            return numberToWords(number / 1000) + " nghìn" + ((number % 1000 != 0) ? " " : "") + numberToWords(number % 1000);
        }

        if (number < 1000000000) {
            return numberToWords(number / 1000000) + " triệu" + ((number % 1000000 != 0) ? " " : "") + numberToWords(number % 1000000);
        }

        return "số quá lớn";
    }

    public void actionPerformed(ActionEvent ae) {
        setVisible(false);
        new Transactions(cardnumber).setVisible(true);
    }

    public static void main(String args[]) {

        new BalancEnquiry("").setVisible(true);
    }
}
