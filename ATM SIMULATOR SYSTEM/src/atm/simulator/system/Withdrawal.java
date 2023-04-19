package atm.simulator.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.sql.*;
import java.util.Date;

public class Withdrawal extends JFrame implements ActionListener {

    JTextField amount;
    JButton withdrawl, back;
    String pinnumber;
    Withdrawal(String pinnumber){
        this.pinnumber = pinnumber;
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

    public void actionPerformed(ActionEvent ae){
        try{
            String number = amount.getText();
            Date date = new Date();
            if(ae.getSource()==withdrawl){
                if(number.equals("")){
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập số tiền quý khách muốn rút!.");
                }else{
                    Conn conn = new Conn();
                    ResultSet rs = conn.s.executeQuery("select * from atm where pin = '"+pinnumber+"'");
                    int balance = 0;
                    while(rs.next()){
                        if(rs.getString("type").equals("Gửi tiền")){
                            balance += Integer.parseInt(rs.getString("amount"));
                        }else{
                            balance -= Integer.parseInt(rs.getString("amount"));
                        }
                    }
                    if(balance < Integer.parseInt(number)){
                        JOptionPane.showMessageDialog(null, "Vui lòng kiểm tra lại số dư tài khoản!.");
                        return;
                    }

                    conn.s.executeUpdate("insert into atm values('"+pinnumber+"', '"+date+"', 'Rút tiền', '"+number+"')");
                    JOptionPane.showMessageDialog(null, "Rút tiền thành công là: "+number+" VNĐ");

                    setVisible(false);
                    new Transactions(pinnumber).setVisible(true);
                }
            }else if(ae.getSource()==back){
                setVisible(false);
                new Transactions(pinnumber).setVisible(true);
            }
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("error: "+e);
        }

    }


    public static void main(String args[]){
        new Withdrawal("").setVisible(true);
    }
}
