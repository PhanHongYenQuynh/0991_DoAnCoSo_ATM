package atm.simulator.system;

import com.twilio.SMS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class Login extends JFrame implements ActionListener {

    JButton login, signup, clear;
    JTextField cardTextField;
    JPasswordField pinTextField;

    /*  int numAttempts = 0;*/
    Login() {
        setTitle("AUTOMATED TELLER MACHINE");
        setLayout(null);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/logo.png"));
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
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


        setSize(800, 450);
        setVisible(true);
        setLocation(350, 200);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == clear) {
            cardTextField.setText("");
            pinTextField.setText("");
        } else if (ae.getSource() == login) {
            Conn conn = new Conn();
            String cardNumber = cardTextField.getText();
            String cardStr = cardNumber.replaceAll("\\s+", "");
            String pinNumber = pinTextField.getText();
            String pinStr = pinNumber.replaceAll("\\s+", "");

            if (cardStr.equals("") || pinStr.equals("")) {
                JOptionPane.showMessageDialog(null, "Vui lòng điền thông tin đầy đủ!");
                return;
            }

            try {
                PreparedStatement ps = conn.c.prepareStatement("SELECT * FROM login WHERE cardnumber = ?");
                ps.setString(1, cardNumber);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    int wrongAttempts = rs.getInt("wrong_attempts");
                    Timestamp lockedUntil = rs.getTimestamp("locked_until");
                    String maskedCardNumber = (cardNumber.substring(0, 4) + "XXXXXXXX" + cardNumber.substring(12));
                    String encryptedPin = rs.getString("pin");

                    if (RSAKeyManager.verify(pinNumber, encryptedPin)) {
                        if (lockedUntil != null && lockedUntil.after(new Timestamp(System.currentTimeMillis()))) {
                            JOptionPane.showMessageDialog(null, "Tài khoản của bạn đã bị khóa. Vui lòng liên hệ với số hotline để được hỗ trợ!");
                            return;
                        }

                        setVisible(false);
                        new Transactions(cardNumber).setVisible(true);
                    } else {
                        if (lockedUntil != null && lockedUntil.after(new Timestamp(System.currentTimeMillis()))) {
                            String messageBody = ("NGAN HANG HUETCH BANK\n ---XIN THONG BAO--- \nQuy khac co so tai khoan: " + maskedCardNumber + " đa bi khoa do nhap sai ma pin qua nhieu lan \n" + "Xin quy khac vui long thu lai sau 10 phut.");
                            SMS.sendSMS(messageBody);
                            JOptionPane.showMessageDialog(null, messageBody);
                        } else if (wrongAttempts >= 3) {
                            // Khoá tài khoản trong 10 phút nếu nhập sai 3 lần
                            ps = conn.c.prepareStatement("UPDATE login SET wrong_attempts = ?, locked_until = ? WHERE cardnumber = ?");
                            ps.setInt(1, (wrongAttempts + 1));
                            ps.setTimestamp(2, new Timestamp(System.currentTimeMillis() + 10 * 60 * 1000));
                            ps.setString(3, cardNumber);
                            ps.executeUpdate();
                            JOptionPane.showMessageDialog(null, "Quý khách đã nhập sai mã PIN!\nVui lòng kiểm tra và nhập lại đúng mã PIN.\nSố lần nhập sai: " + (wrongAttempts + 1));
                        } else if (wrongAttempts >= 5) {
                            // Khoá tài khoản vĩnh viễn nếu nhập sai quá nhiều lần
                            ps = conn.c.prepareStatement("UPDATE login SET locked_until = CURRENT_TIMESTAMP + INTERVAL 10 YEAR WHERE cardnumber = ?");
                            ps.setString(1, cardNumber);
                            ps.executeUpdate();

                            String messageBody = "NGAN HANG HUETCH BANK\n ---XIN THONG BAO--- \nQuy khach co so tai khoan: " + maskedCardNumber + " đa bi khaa do nhap sai ma PIN qua 5 lan \n" + "Vui lang lien he voi so hotline đe đuoc ho tro: 0976554323.";
                            SMS.sendSMS(messageBody);
                            JOptionPane.showMessageDialog(null, "Tài khoản của bạn đã bị khóa.\nVui lòng liên hệ với số hotline để được hỗ trợ!");
                        } else {
                            ps = conn.c.prepareStatement("UPDATE login SET wrong_attempts = ? WHERE cardnumber = ?");
                            ps.setInt(1, (wrongAttempts + 1));
                            ps.setString(2, cardNumber);
                            ps.executeUpdate();
                            JOptionPane.showMessageDialog(null, "Quý khách đã nhập sai mã PIN!\nVui lòng kiểm tra và nhập lại đúng mã PIN.\nSố lần nhập sai: " + (wrongAttempts + 1));
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Tài khoản quý khách không tồn tại!.");
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Lỗi. Vui lòng thử lại sau");
                System.out.println(e);
            }
        } else if (ae.getSource() == signup) {
            setVisible(false);
            new SignupOne().setVisible(true);
        }
    }

    public static void main(String args[]) {
        new Login();
    }
}

