package atm.simulator.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Date;

public class Transfer extends JFrame implements ActionListener {

    JTextField receiverAccNo, amount;
    JButton transfer, back;
    String pinnumber;

    Transfer(String pinnumber) {
        this.pinnumber = pinnumber;
        setLayout(null);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 1054, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);

        JLabel text = new JLabel("Vui lòng nhập thông tin chuyển khoản.");
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 16));
        text.setBounds(180, 320, 550, 26);
        image.add(text);

        JLabel receiverAccNoLabel = new JLabel("STK:");
        receiverAccNoLabel.setForeground(Color.WHITE);
        receiverAccNoLabel.setFont(new Font("Raleway", Font.BOLD, 14));
        receiverAccNoLabel.setBounds(165, 421, 180, 25);
        image.add(receiverAccNoLabel);

        receiverAccNo = new JTextField();
        receiverAccNo.setFont(new Font("Raleway", Font.BOLD, 14));
        receiverAccNo.setBounds(280, 421, 230, 25);
        image.add(receiverAccNo);

        JLabel amountLabel = new JLabel("SỐ TIỀN:");
        amountLabel.setForeground(Color.WHITE);
        amountLabel.setFont(new Font("Raleway", Font.BOLD, 14));
        amountLabel.setBounds(165, 457, 180, 25);
        image.add(amountLabel);

        amount = new JTextField();
        amount.setFont(new Font("Raleway", Font.BOLD, 14));
        amount.setBounds(280, 457, 230, 25);
        image.add(amount);

        transfer = new JButton("Chuyển khoản");
        transfer.setBounds(355, 498, 150, 30);
        transfer.addActionListener(this);
        image.add(transfer);

        back = new JButton("Quay về");
        back.setBounds(355, 540, 150, 30);
        back.addActionListener(this);
        image.add(back);

        setSize(900, 900);
        setLocation(300, 0);
        setUndecorated(true);
        setVisible(true);
    }

    public static String numberToWords(int number) {
        if (number == 0) {
            return " đồng";
        }

        String[] units = { "", "một", "hai", "ba", "bốn", "năm", "sáu", "bảy", "tám", "chín", "mười",
                "mười một", "mười hai", "mười ba", "mười bốn", "mười lăm", "mười sáu", "mười bảy", "mười tám", "mười chín" };
        String[] tens = { "", "", "hai mươi", "ba mươi", "bốn mươi", "năm mươi", "sáu mươi", "bảy mươi", "tám mươi", "chín mươi" };

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
        if (ae.getSource() == back) {
            setVisible(false);
            new Transactions(pinnumber).setVisible(true);
        } else if (ae.getSource() == transfer) {
            String receiverAccNoText = receiverAccNo.getText();
            String transferAmount = amount.getText();

            if (receiverAccNoText.equals("") || transferAmount.equals("")) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin chuyển khoản!.");
            } else {
                try {
                    Conn conn = new Conn();
                    ResultSet rs = conn.s.executeQuery("SELECT balance FROM bank_account WHERE pin='" + pinnumber + "'");
                    if (rs.next()) {
                        int senderBalance = rs.getInt("balance");
                        int transferAmountInt = Integer.parseInt(transferAmount);
                        if (transferAmountInt <= 0) {
                            JOptionPane.showMessageDialog(null, "Số tiền chuyển khoản không hợp lệ! Vui lòng nhập số tiền hợp lệ.");
                        }else if (transferAmountInt > senderBalance) {
                            JOptionPane.showMessageDialog(null, "Số dư tài khoản không đủ để chuyển khoản!.");
                        } else {
                            // Update số dư của tài khoản người nhận
                            String updateReceiverQuery = "UPDATE bank_account SET balance=balance+" + transferAmountInt + " WHERE acc_no='" + receiverAccNoText + "'";
                            conn.s.executeUpdate(updateReceiverQuery);

                            // Update số dư của tài khoản gửi
                            String updateSenderQuery = "UPDATE bank_account SET balance=balance-" + transferAmountInt + " WHERE pin='" + pinnumber + "'";
                            conn.s.executeUpdate(updateSenderQuery);

                            // Insert transaction into atm table
                            Date date = new Date();
                            String insertQuery = "insert into atm values('" + pinnumber + "', '" + date + "', 'Chuyển khoản', '" + transferAmount + "')";
                            conn.s.executeUpdate(insertQuery);

                            // Display success message and go back to transactions page
                            int transferAmountIntCopy = transferAmountInt;
                            String transferAmountWords = numberToWords(transferAmountIntCopy);
                            JOptionPane.showMessageDialog(null, "Số tiền đã chuyển là: " + transferAmountWords);
                            setVisible(false);
                            new Transactions(pinnumber).setVisible(true);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Không tìm thấy tài khoản!.");
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }
    }


    public static void main(String args[]) {
        new Transfer("").setVisible(true);
    }
}