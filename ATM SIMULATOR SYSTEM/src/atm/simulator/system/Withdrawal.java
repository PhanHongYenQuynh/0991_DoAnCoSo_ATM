package atm.simulator.system;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.Date;


public class Withdrawal extends JFrame implements ActionListener {

    JTextField amount;
    JButton withdrawl, back;
    String cardnumber;

    Withdrawal(String cardnumber) {
        this.cardnumber = cardnumber;
        setLayout(null);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 1054, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);

        JLabel text = new JLabel("Vui lòng nhập số tiền muốn rút.");
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 16));
        text.setBounds(200, 300, 550, 26);
        image.add(text);

        amount = new JTextField();
        amount.setFont(new Font("Raleway", Font.BOLD, 14));
        amount.setBounds(170, 350, 320, 20);
        // Đặt bộ lọc cho JTextField để chỉ cho phép nhập số và dấu chấm
        ((AbstractDocument) amount.getDocument()).setDocumentFilter(new NumberFilter());
        image.add(amount);

        withdrawl = new JButton("Đồng ý");
        withdrawl.setBounds(355, 485, 150, 30);
        withdrawl.addActionListener(this);
        image.add(withdrawl);

        back = new JButton("Quay về");
        back.setBounds(355, 520, 150, 30);
        back.addActionListener(this);
        image.add(back);

        setSize(900, 900);
        setLocation(300, 0);
        setUndecorated(true);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            String number = amount.getText().replace(".", ""); // Loại bỏ dấu chấm trước khi lưu vào cơ sở dữ liệu
            Date date = new Date();

            if (ae.getSource() == withdrawl) {
                if (number.equals("")) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập số tiền quý khách muốn rút!.");
                } else if (Integer.parseInt(number) <= 0) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập số tiền hợp lệ!.");
                } else {
                    Conn conn = new Conn();

                    // Kiểm tra số dư
                    PreparedStatement ps = conn.c.prepareStatement("SELECT balance FROM bank_account WHERE acc_no = ?");
                    ps.setString(1, cardnumber);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        int currentBalance = rs.getInt("balance");
                        if (currentBalance < Integer.parseInt(number)) {
                            JOptionPane.showMessageDialog(null, "Giao dịch thất bại!");
                            return;
                        }
                    }

                    int amountInt = Integer.parseInt(number);
                    int fee = 6000; // Phí rút tiền
                    int totalAmount = amountInt + fee;

                    DecimalFormat decimalFormat = new DecimalFormat("#,###");
                    String formattedNumber = decimalFormat.format(totalAmount);

                    JOptionPane.showMessageDialog(null, "Số tiền đã rút là: " + formattedNumber);

                    // update account balance in bank_account table
                    ps = conn.c.prepareStatement("SELECT balance FROM bank_account WHERE acc_no = ?");
                    ps.setString(1, cardnumber);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        int currentBalance = rs.getInt("balance");
                        int newBalance = currentBalance - totalAmount;
                        ps = conn.c.prepareStatement("UPDATE bank_account SET balance = ? WHERE acc_no = ?");
                        ps.setInt(1, newBalance);
                        ps.setString(2, cardnumber);
                        ps.executeUpdate();
                    }

                    ps = conn.c.prepareStatement("INSERT INTO atm VALUES (?, ?, ?, ?)");
                    ps.setString(1, cardnumber);
                    ps.setString(2, date.toString());
                    ps.setString(3, "Rút tiền");
                    ps.setString(4, number);
                    ps.executeUpdate();

                    setVisible(false);
                    new Transactions(cardnumber).setVisible(true);
                }
            } else if (ae.getSource() == back) {
                setVisible(false);
                new Transactions(cardnumber).setVisible(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error: " + e);
        }
    }

    public static void main(String args[]) {
        new Withdrawal("").setVisible(true);
    }
}
