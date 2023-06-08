package atm.simulator.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.Date;

public class FastCash extends JFrame implements ActionListener {
    JButton onehundred, twohundred, fivehundred, onemillion, twomillion, fivemillion, other, back;
    String cardnumber;
    DecimalFormat decimalFormat = new DecimalFormat("#,### VNĐ");

    FastCash(String cardnumber) {
        this.cardnumber = cardnumber;
        setLayout(null);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 1054, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);

        JLabel text = new JLabel("Vui lòng quý khách chọn mệnh giá.");
        text.setBounds(200, 300, 700, 35);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 16));
        image.add(text);

        onehundred = new  JButton(decimalFormat.format(100000));
        onehundred.setBounds(170, 415, 150, 30);
        onehundred.addActionListener(this);
        image.add(onehundred);

        twohundred = new JButton(decimalFormat.format(200000));
        twohundred.setBounds(355, 415, 150, 30);
        twohundred.addActionListener(this);
        image.add(twohundred);

        fivehundred = new JButton(decimalFormat.format(500000));
        fivehundred.setBounds(170, 450, 150, 30);
        fivehundred.addActionListener(this);
        image.add(fivehundred);

        onemillion =  new JButton(decimalFormat.format(1000000));
        onemillion.setBounds(355, 450, 150, 30);
        onemillion.addActionListener(this);
        image.add(onemillion);

        twomillion = new JButton(decimalFormat.format(2000000));
        twomillion.setBounds(170, 485, 150, 30);
        twomillion.addActionListener(this);
        image.add(twomillion);

        fivemillion = new JButton(decimalFormat.format(5000000));
        fivemillion.setBounds(355, 485, 150, 30);
        fivemillion.addActionListener(this);
        image.add(fivemillion);

        other = new JButton("Khác");
        other.setBounds(170, 520, 150, 30);
        other.addActionListener(this);
        image.add(other);

        back = new JButton("Quay lại");
        back.setBounds(355, 525, 150, 30);
        back.addActionListener(this);
        image.add(back);


        setSize(900, 900);
        setLocation(300, 0);
        setUndecorated(true);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == back) {
            setVisible(false);
            new Transactions(cardnumber).setVisible(true);
        } else if (ae.getSource() == other) {
            setVisible(false);
            new Withdrawal(cardnumber).setVisible(true);
        } else {
            String amount = ((JButton) ae.getSource()).getText().substring(0, ((JButton) ae.getSource()).getText().length() - 4);
            amount = amount.replace(".", ""); // Loại bỏ dấu chấm từ chuỗi số tiền
            Conn conn = new Conn();
            try {
                // Kiểm tra số dư
                PreparedStatement ps = conn.c.prepareStatement("SELECT balance FROM bank_account WHERE acc_no = ?");
                ps.setString(1, cardnumber);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    int currentBalance = rs.getInt("balance");
                    if (currentBalance < Integer.parseInt(amount)) {
                        JOptionPane.showMessageDialog(null, "Giao dịch thất bại!");
                        return;
                    }
                }
                int amountInt = Integer.parseInt(amount);
                int fee = 6000; // Phí rút tiền (6,000 VNĐ)
                int totalAmount = amountInt + fee; // Tổng số tiền bao gồm phí
                Date date = new Date();
                DecimalFormat decimalFormat = new DecimalFormat("#,### VNĐ");
                String formattedNumber = decimalFormat.format(totalAmount);
                JOptionPane.showMessageDialog(null, "Số tiền đã rút là: " + formattedNumber);
                ps = conn.c.prepareStatement("SELECT balance FROM bank_account WHERE acc_no = ?");
                ps.setString(1, cardnumber);
                rs = ps.executeQuery();
                if (rs.next()) {
                    int currentBalance = rs.getInt("balance");
                    int newBalance = currentBalance - totalAmount; // Trừ tổng số tiền bao gồm phí
                    ps = conn.c.prepareStatement("UPDATE bank_account SET balance = ? WHERE acc_no = ?");
                    ps.setInt(1, newBalance);
                    ps.setString(2, cardnumber);
                    ps.executeUpdate();
                }
                String query = "INSERT INTO atm VALUES(?, ?, ?, ?)";
                ps = conn.c.prepareStatement(query);
                ps.setString(1, cardnumber);
                ps.setString(2, date.toString());
                ps.setString(3, "Rút tiền");
                ps.setString(4, amount);
                ps.executeUpdate();
                setVisible(false);
                new Transactions(cardnumber).setVisible(true);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public static void main(String args[]) {

        new FastCash("").setVisible(true);
    }
}
