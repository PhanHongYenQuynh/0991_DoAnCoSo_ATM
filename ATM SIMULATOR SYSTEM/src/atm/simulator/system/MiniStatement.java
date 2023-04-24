package atm.simulator.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class MiniStatement extends JFrame  implements ActionListener{

    String pinnumber;
    MiniStatement(String pinnumber){
        this.pinnumber = pinnumber;
        setTitle("In Sao Kê");

        setLayout(null);

        JLabel mini = new JLabel();
        add(mini);

        JLabel bank = new JLabel("Hutech Bank");
        bank.setFont(new Font("Osward", Font.BOLD, 22));
        bank.setBounds(200, 30, 250, 40);
        add(bank);

        JLabel card = new JLabel();
        card.setBounds(20, 100, 300, 20);
        add(card);

        JLabel balancee = new JLabel();
        balancee.setBounds(20, 750, 300, 20);
        add(balancee);

        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from login where pin = '"+pinnumber+"'");
            while(rs.next()){
                card.setText("Card number:    " + rs.getString("cardnumber").substring(0, 4) + "XXXXXXXX" + rs.getString("cardnumber").substring(12));
            }
        }catch(Exception e){
            System.out.println(e);
        }

        int balance = 0;
        Conn conn = new Conn();

        try {
            ResultSet rs = conn.s.executeQuery("SELECT * FROM bank_account where pin = '"+pinnumber+"'");

            if (rs.next()) {
                // Hiển thị số dư tài khoản
                balance = rs.getInt("balance");
                balancee.setText("Số dư tài khoản là: " + balance + "VNĐ");

                // Hiển thị lịch sử giao dịch
                rs = conn.s.executeQuery("SELECT * FROM atm where pin = '"+pinnumber+"'");
                while (rs.next()) {
                    mini.setText(mini.getText() + "<html>" + rs.getString("date") + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
                            + rs.getString("type") + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
                            + rs.getString("amount") + "<br><br><html>");
                }
            } else {
                // Không tìm thấy tài khoản
                balancee.setText("Không tìm thấy tài khoản");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        String amountText = numberToWords(balance);
        JLabel label = new JLabel("Số dư bằng chữ: \n"+ amountText);
        label.setBounds(20, 800, 600, 30);
        add(label);

        mini.setBounds(20,45,400,800);
        setSize(550, 1000);
        setLocation(20, 20);
        getContentPane().setBackground(Color.WHITE);
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
    public void actionPerformed(ActionEvent ae){
        this.setVisible(false);
    }
    public static void main(String args[]){
        new MiniStatement("").setVisible(true);
    }
}
