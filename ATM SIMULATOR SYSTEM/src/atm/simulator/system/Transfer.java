package atm.simulator.system;
import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.Date;


public class Transfer extends JFrame implements ActionListener {

    JTextField receiverAccNo, amount;
    JButton transfer, back;
    String cardnumber;

    Transfer(String cardnumber) {
        this.cardnumber = cardnumber;
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
        // Đặt bộ lọc cho JTextField để chỉ cho phép nhập số và dấu chấm
        ((AbstractDocument) amount.getDocument()).setDocumentFilter(new NumberFilter());
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


    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == back) {
            setVisible(false);
            new Transactions(cardnumber).setVisible(true);
        } else if (ae.getSource() == transfer) {
            String receiverAccNoText = receiverAccNo.getText();
            String transferAmount = amount.getText().replace(".", "");

            if (receiverAccNoText.equals("") || transferAmount.equals("")) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin chuyển khoản!");
            } else {
                try {
                    Conn conn = new Conn();

                    // Kiểm tra số dư tài khoản gửi
                    PreparedStatement ps = conn.c.prepareStatement("SELECT balance FROM bank_account WHERE acc_no = ?");
                    ps.setString(1, cardnumber);
                    ResultSet rs = ps.executeQuery();

                    if (rs.next()) {
                        int senderBalance = rs.getInt("balance");
                        int transferAmountInt = Integer.parseInt(transferAmount);
                        if (transferAmountInt <= 0) {
                            JOptionPane.showMessageDialog(null, "Số tiền chuyển khoản không hợp lệ! Vui lòng nhập số tiền hợp lệ.");
                        } else if (transferAmountInt > senderBalance) {
                            JOptionPane.showMessageDialog(null, "Số dư tài khoản không đủ để chuyển khoản!");
                        } else {
                            // Update số dư của tài khoản người nhận
                            ps = conn.c.prepareStatement("UPDATE bank_account SET balance=balance+? WHERE acc_no=?");
                            ps.setInt(1, transferAmountInt);
                            ps.setString(2, receiverAccNoText);
                            ps.executeUpdate();

                            // Update số dư của tài khoản gửi
                            ps = conn.c.prepareStatement("UPDATE bank_account SET balance=balance-? WHERE acc_no=?");
                            ps.setInt(1, transferAmountInt);
                            ps.setString(2, cardnumber);
                            ps.executeUpdate();

                            // Insert transaction into atm table
                            Date date = new Date();
                            String insertQuery = "insert into atm values(?, ?, ?, ?)";
                            ps = conn.c.prepareStatement(insertQuery);
                            ps.setString(1, cardnumber);
                            ps.setString(2, date.toString());
                            ps.setString(3, "Chuyển khoản");
                            ps.setString(4, transferAmount);
                            ps.executeUpdate();

                            // Display success message and go back to transactions page
                            int transferAmountIntCopy = transferAmountInt;
                            DecimalFormat decimalFormat = new DecimalFormat("#,### VNĐ");
                            String formattedAmount = decimalFormat.format(Integer.parseInt(transferAmount));
                            JOptionPane.showMessageDialog(null, "Số tiền đã chuyển là: " + formattedAmount);
                            setVisible(false);
                            new Transactions(cardnumber).setVisible(true);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Không tìm thấy tài khoản!");
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