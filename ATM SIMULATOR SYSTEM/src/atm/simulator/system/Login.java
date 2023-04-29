package atm.simulator.system;

import com.encryption.RSAEncryption;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Base64;

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

        /*getContentPane().setBackground(Color.WHITE);*/

        setSize(800, 450);
        setVisible(true);
        setLocation(350, 200);
    }

        // Code chưa mã hoá - không xoá

  /*  public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == clear) {
            cardTextField.setText("");
            pinTextField.setText("");
        } else if (ae.getSource() == login) {
            Conn conn = new Conn();
            String cardnumber = cardTextField.getText();
            String pinnumber = pinTextField.getText();
            String query = "SELECT * FROM login WHERE cardnumber = '" + cardnumber + "' AND pin = '" + pinnumber + "'";
            if (cardnumber.equals("") || pinnumber.equals("")) {
                JOptionPane.showMessageDialog(null, "Vui lòng điền thông tin đầy đủ!");
                return;
            }

            try {
                ResultSet rs = conn.s.executeQuery(query);
                if (rs.next()) {
                    Timestamp lockedUntil = rs.getTimestamp("locked_until");
                    if (lockedUntil != null && lockedUntil.after(new Timestamp(System.currentTimeMillis()))) {
                        JOptionPane.showMessageDialog(null, "Tài khoản của bạn đã bị khóa. Vui lòng liên hệ với số hotline để được hỗ trợ!");
                        return;
                    }
                    setVisible(false);
                    new Transactions(pinnumber).setVisible(true);
                } else {
                    // Lấy thông tin tài khoản từ database
                    query = "SELECT * FROM login WHERE cardnumber = '" + cardnumber + "'";
                    rs = conn.s.executeQuery(query);
                    rs.next();
                    int wrongAttempts = rs.getInt("wrong_attempts");
                    Timestamp lockedUntil = rs.getTimestamp("locked_until");

                    if (lockedUntil != null && lockedUntil.after(new Timestamp(System.currentTimeMillis()))) {
                        String cardnumberr = rs.getString("cardnumber");
                        String maskedCardnumber = (cardnumberr.substring(0, 4) + "XXXXXXXX" + cardnumberr.substring(12));
                        String messageBody = ("NGAN HANG HUETCH BANK\n ---XIN THONG BAO--- \nQuy khac co so tai khoan: " + maskedCardnumber + " đa bi khoa do nhap sai ma pin quá nhieu lan \n" + "Xin quy khac vui long thu lai sau 10 phut.");
                        SMS.sendSMS(messageBody);
                        JOptionPane.showMessageDialog(null, messageBody);
                        return;
                    } else if (wrongAttempts >= 5) {
                        // Khoá tài khoản vĩnh viễn nếu nhập sai quá nhiều lần
                        query = "UPDATE login SET locked_until = CURRENT_TIMESTAMP + INTERVAL 10 YEAR WHERE cardnumber = '" + cardnumber + "'";
                        conn.s.executeUpdate(query);

                        //Lấy số thẻ
                        query = "SELECT * FROM login WHERE cardnumber = '" + cardnumber + "'";
                        rs = conn.s.executeQuery(query);
                        rs.next();
                        String cardnumberr = rs.getString("cardnumber");
                        String maskedCardnumber = (cardnumberr.substring(0, 4) + "XXXXXXXX" + cardnumberr.substring(12));
                        String messageBody = "NGAN HANG HUETCH BANK\n ---XIN THONG BAO--- \nQuy khach co so tai khoan: " + maskedCardnumber + " đa bi khoa do nhap sai ma pin qua 5 lan \n" + "Xin qua khach vui lang lien he se hotline: 0976554323.";
                        SMS.sendSMS(messageBody);
                        JOptionPane.showMessageDialog(null, "Tài khoản của bạn đã bị khóa.\nVui lòng liên hệ với số hotline để được hỗ trợ!");
                        return;
                    } else if (wrongAttempts >= 3) {
                        // Khoá tài khoản trong 10 phút nếu nhập sai 3 lần
                        lockedUntil = new Timestamp(System.currentTimeMillis() + 10 * 60 * 1000);
                        query = "UPDATE login SET wrong_attempts = " + (wrongAttempts + 1) + ", locked_until = '" + lockedUntil + "' WHERE cardnumber = '" + cardnumber + "'";
                        conn.s.executeUpdate(query);
                        JOptionPane.showMessageDialog(null, "Quý khách đã nhập sai mã PIN!\nVui lòng kiểm tra và nhập lại đúng mã PIN.\nSố lần nhập sai: " + (wrongAttempts + 1));
                    } else {
                        // Cập nhật số lần nhập sai
                        query = "UPDATE login SET wrong_attempts = " + (wrongAttempts + 1) + " WHERE cardnumber = '" + cardnumber + "'";
                        conn.s.executeUpdate(query);
                        JOptionPane.showMessageDialog(null, "Quý khách đã nhập sai mã PIN!\nVui lòng kiểm tra và nhập lại đúng mã PIN.\nSố lần nhập sai: " + (wrongAttempts + 1));
                    }
                }
            } catch (SQLException e) {
                System.out.println("Tài khoản quý khách không tồn tại!.");
                System.out.println(e);
                 e.printStackTrace();
            }
        } else if (ae.getSource() == signup) {
            setVisible(false);
            new SignupOne().setVisible(true);
        }
    }*/

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == clear) {
            cardTextField.setText("");
            pinTextField.setText("");
        } else if (ae.getSource() == login) {
            Conn conn = new Conn();
            String cardnumber = cardTextField.getText();
            String cardStr = cardnumber.replaceAll("\\s+", "");
            String pinnumber = pinTextField.getText();
            String pinStr = pinnumber.replaceAll("\\s+", "");
            String query = "SELECT * FROM login WHERE cardnumber = ? AND pin = ?";

            if (cardStr.equals("") || pinStr.equals("")) {
                JOptionPane.showMessageDialog(null, "Vui lòng điền thông tin đầy đủ!");
                return;
            }

            try {
                // Mã hoá pinnumber trước khi truy vấn từ cơ sở dữ liệu
                KeyPair keyPair = RSAEncryption.generateKeyPair();
                PublicKey publicKey = keyPair.getPublic();
                byte[] pinHash = MessageDigest.getInstance("SHA-512").digest(pinnumber.getBytes());
                byte[] encryptedPin = RSAEncryption.encrypt(pinHash, publicKey);
                String encodedPin = Base64.getEncoder().encodeToString(encryptedPin);

                PreparedStatement ps = conn.c.prepareStatement(query);
                ps.setString(1, cardnumber);
                ps.setString(2, encodedPin);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    String encryptedPinFromDb = rs.getString("pin");
                    byte[] decodedPin = Base64.getDecoder().decode(encryptedPinFromDb);
                    byte[] decryptedPin = RSAEncryption.decrypt(decodedPin, keyPair.getPrivate());
                    if (MessageDigest.isEqual(pinHash, decryptedPin)) {
                        Timestamp lockedUntil = rs.getTimestamp("locked_until");
                        if (lockedUntil != null && lockedUntil.after(new Timestamp(System.currentTimeMillis()))) {
                            JOptionPane.showMessageDialog(null, "Tài khoản của bạn đã bị khóa. Vui lòng liên hệ với số hotline để được hỗ trợ!");
                            return;
                        }
                        setVisible(false);
                        new Transactions(pinnumber).setVisible(true);
                    }

                } else {
                    // Lấy thông tin tài khoản từ database
                    query = "SELECT * FROM login WHERE cardnumber = ?";
                    ps = conn.c.prepareStatement(query);
                    ps.setString(1, cardnumber);
                    rs = ps.executeQuery();
                    rs.next();
                    int wrongAttempts = rs.getInt("wrong_attempts");
                    Timestamp lockedUntil = rs.getTimestamp("locked_until");

                    if (lockedUntil != null && lockedUntil.after(new Timestamp(System.currentTimeMillis()))) {
                        String cardnumberr = rs.getString("cardnumber");
                        String maskedCardnumber = (cardnumberr.substring(0, 4) + "XXXXXXXX" + cardnumberr.substring(12));
                        String messageBody = ("NGAN HANG HUETCH BANK\n ---XIN THONG BAO--- \nQuy khac co so tai khoan: " + maskedCardnumber + " đa bi khoa do nhap sai ma pin quá nhieu lan \n" + "Xin quy khac vui long thu lai sau 10 phut.");
                        SMS.sendSMS(messageBody);
                        JOptionPane.showMessageDialog(null, messageBody);
                        return;
                    } else if (wrongAttempts >= 5) {
                        // Khoá tài khoản vĩnh viễn nếu nhập sai quá nhiều lần
                        query = "UPDATE login SET locked_until = CURRENT_TIMESTAMP + INTERVAL 10 YEAR WHERE cardnumber = ?";
                        ps = conn.c.prepareStatement(query);
                        ps.setString(1, cardnumber);
                        ps.executeUpdate();

                        //Lấy số thẻ
                        query = "SELECT * FROM login WHERE cardnumber = ?";
                        ps = conn.c.prepareStatement(query);
                        ps.setString(1, cardnumber);
                        rs = ps.executeQuery();
                        rs.next();
                        String cardnumberr = rs.getString("cardnumber");
                        String maskedCardnumber = (cardnumberr.substring(0, 4) + "XXXXXXXX" + cardnumberr.substring(12));
                        String messageBody = "NGAN HANG HUETCH BANK\n ---XIN THONG BAO--- \nQuy khách có số tài khoản: " + maskedCardnumber + " đã bị khóa do nhập sai mã PIN quá 5 lần \n" + "Vui lòng liên hệ với số hotline để được hỗ trợ: 0976554323.";
                        SMS.sendSMS(messageBody);
                        JOptionPane.showMessageDialog(null, "Tài khoản của bạn đã bị khóa.\nVui lòng liên hệ với số hotline để được hỗ trợ!");
                        return;

                    }else if (wrongAttempts >= 3) {
                        // Khoá tài khoản trong 10 phút nếu nhập sai 3 lần
                        lockedUntil = new Timestamp(System.currentTimeMillis() + 10 * 60 * 1000);
                        query = "UPDATE login SET wrong_attempts = ?, locked_until = ? WHERE cardnumber = ?";
                        ps = conn.c.prepareStatement(query);
                        ps.setInt(1, (wrongAttempts + 1));
                        ps.setTimestamp(2, lockedUntil);
                        ps.setString(3, cardnumber);
                        ps.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Quý khách đã nhập sai mã PIN!\nVui lòng kiểm tra và nhập lại đúng mã PIN.\nSố lần nhập sai: " + (wrongAttempts + 1));
                    } else {
                        // Cập nhật số lần nhập sai
                        query = "UPDATE login SET wrong_attempts = ? WHERE cardnumber = ?";
                        ps = conn.c.prepareStatement(query);
                        ps.setInt(1, (wrongAttempts + 1));
                        ps.setString(2, cardnumber);
                        ps.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Quý khách đã nhập sai mã PIN!\nVui lòng kiểm tra và nhập lại đúng mã PIN.\nSố lần nhập sai: " + (wrongAttempts + 1));
                    }
                }
            } catch (SQLException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException |
                     IllegalBlockSizeException | BadPaddingException e) {
                System.out.println("Tài khoản quý khách không tồn tại!.");
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

