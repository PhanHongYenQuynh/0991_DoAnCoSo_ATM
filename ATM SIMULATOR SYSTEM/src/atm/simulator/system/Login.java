package atm.simulator.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {

    JButton login, signup, clear;
    JTextField cardTextField;
    JPasswordField pinTextField;
    Login(){
        setTitle("AUTOMATED TELLER MACHINE");
        setLayout(null);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/logo.png"));
        Image i2 = i1.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel label = new JLabel(i3);
        label.setBounds(70, 10, 100, 100);
        add(label);


        JLabel text = new JLabel("Kính Chào Quý Khách");
        text.setFont(new Font("Osward", Font.BOLD, 38));
        text.setBounds(200, 40, 430, 40);
        add(text);



        JLabel cardno = new JLabel("STK: ");
        cardno.setFont(new Font("Raleway", Font.BOLD, 28));
        cardno.setBounds(170, 150, 150, 30);
        add(cardno);

        cardTextField = new JTextField();
        cardTextField.setBounds(300, 150, 250, 30);
        cardTextField.setFont(new Font("Arial", Font.BOLD, 14));
        add(cardTextField);

        JLabel pin = new JLabel("PIN: ");
        pin.setFont(new Font("Raleway", Font.BOLD, 28));
        pin.setBounds(170, 220, 250, 30);
        add(pin);

        pinTextField = new JPasswordField();
        pinTextField.setBounds(300, 220, 250, 30);
        pinTextField.setFont(new Font("Arial", Font.BOLD, 14));
        add(pinTextField);

        login = new JButton("ĐĂNG NHẬP");
        login.setBounds(300, 300, 130, 30);
        login.setBackground(Color.ORANGE);
        login.setForeground(Color.BLACK);
        login.setOpaque(true);
        login.setBorderPainted(false);
        login.addActionListener(this);
        add(login);


        clear = new JButton("XOÁ");
        clear.setBounds(450, 300, 100, 30);
        clear.setBackground(Color.ORANGE);
        clear.setForeground(Color.BLACK);
        clear.setOpaque(true);
        clear.setBorderPainted(false);
        clear.addActionListener(this);
        add(clear);

        signup = new JButton("ĐĂNG KÝ");
        signup.setBounds(300, 350, 250, 30);
        signup.setBackground(Color.ORANGE);
        signup.setForeground(Color.BLACK);
        signup.setOpaque(true);
        signup.setBorderPainted(false);
        signup.addActionListener(this);
        add(signup);

        /*getContentPane().setBackground(Color.WHITE);*/

        setSize(800, 400);
        setVisible(true);
        setLocation(350, 200);
    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==clear){
            cardTextField.setText("");
            pinTextField.setText("");
        }else if(ae.getSource() == login){
            Conn conn = new Conn();
            String cardnumber = cardTextField.getText();
            String pinnumber = pinTextField.getText();
            String query = "select * from login where cardnumber = '"+cardnumber+"' and pin = '"+pinnumber+"'";
            try{
               ResultSet rs = conn.s.executeQuery(query);
               if(rs.next()){
                   setVisible(false);
                   new Transactions(pinnumber).setVisible(true);
               }else{
                   JOptionPane.showMessageDialog(null, "Quý khách đã nhập sai mã pin!" +
                           "\nVui lòng kiểm tra và nhập lại đúng mã pin.");
               }
            }catch(Exception e){
                System.out.println(e);
            }
        }else if(ae.getSource()==signup){
            setVisible(false);
            new SignupOne().setVisible(true);
        }
    }
    public static void main(String args[]){
        new Login();
    }
}

