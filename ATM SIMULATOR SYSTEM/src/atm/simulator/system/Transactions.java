package atm.simulator.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Transactions extends JFrame implements ActionListener {
    JButton deposit, transfer, ministatement, pinchange, fastcash, balanceinquiry, withdrawal, exit;
    String cardNumber;

    Transactions(String cardNumber) {
        this.cardNumber = cardNumber;
        setLayout(null);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 1054, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);

        JLabel text = new JLabel("Vui lòng chọn hình thức giao dịch.");
        text.setBounds(200, 300, 700, 35);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 16));
        image.add(text);

        deposit = new JButton("Tiền gửi");
        deposit.setBounds(170, 415, 150, 30);
        deposit.addActionListener(this);
        image.add(deposit);

        transfer = new JButton("Chuyển tiền");
        transfer.setBounds(355, 415, 150, 30);
        transfer.addActionListener(this);
        image.add(transfer);

        fastcash = new JButton("Rút tiền ");
        fastcash.setBounds(170, 450, 150, 30);
        fastcash.addActionListener(this);
        image.add(fastcash);

        ministatement = new JButton("In sao kê");
        ministatement.setBounds(355, 450, 150, 30);
        ministatement.addActionListener(this);
        image.add(ministatement);

        pinchange = new JButton("Đổi mã pin");
        pinchange.setBounds(170, 485, 150, 30);
        pinchange.addActionListener(this);
        image.add(pinchange);

        balanceinquiry = new JButton("Tra số dư");
        balanceinquiry.setBounds(355, 485, 150, 30);
        balanceinquiry.addActionListener(this);
        image.add(balanceinquiry);

        exit = new JButton("Thoát");
        exit.setBounds(355, 520, 150, 30);
        exit.addActionListener(this);
        image.add(exit);


        setSize(900, 900);
        setLocation(300, 0);
        setUndecorated(true);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == exit) {
            System.exit(0);
        } else if (ae.getSource() == deposit) {
            setVisible(false);
            new Deposit(cardNumber).setVisible(true);
        } else if (ae.getSource() == transfer) {
            setVisible(false);
            new Transfer(cardNumber).setVisible(true);
        } else if (ae.getSource() == fastcash) {
            setVisible(false);
            new FastCash(cardNumber).setVisible(true);
        } else if (ae.getSource() == balanceinquiry) {
            setVisible(false);
            new BalancEnquiry(cardNumber).setVisible(true);
        } else if (ae.getSource() == pinchange) {
            setVisible(false);
            new PinChange(cardNumber).setVisible(true);
        } else if (ae.getSource() == ministatement) {
            setVisible(false);
            new MiniStatement(cardNumber).setVisible(true);
        } else if (ae.getSource() == ministatement) {
            setVisible(false);
            new MiniStatement(cardNumber).setVisible(true);
        }
    }

    public static void main(String args[]) {

        new Transactions("").setVisible(true);
    }
}
