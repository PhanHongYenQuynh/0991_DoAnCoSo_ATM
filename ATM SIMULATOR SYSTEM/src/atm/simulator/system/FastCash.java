package atm.simulator.system;
/*import com.github.tiennv147.vnconvert.NumberToWords;*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.nio.file.FileAlreadyExistsException;
import java.sql.*;
import java.util.Date;

public class FastCash  extends JFrame implements ActionListener {
    JButton onehundred, twohundred, fivehundred, onemillion, twomillion, fivemillion, other, back;
    String pinnumber;
    FastCash(String pinnumber){
        this.pinnumber = pinnumber;
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

        onehundred = new JButton("100 VNĐ");
        onehundred.setBounds(170, 415, 150, 30);
        onehundred.addActionListener(this);
        image.add(onehundred);

        twohundred = new JButton("200 VNĐ");
        twohundred.setBounds(355, 415, 150, 30);
        twohundred.addActionListener(this);
        image.add(twohundred);

        fivehundred = new JButton("500 VNĐ");
        fivehundred.setBounds(170, 450, 150, 30);
        fivehundred.addActionListener(this);
        image.add(fivehundred);

        onemillion = new JButton("1000000 VNĐ");
        onemillion.setBounds(355, 450, 150, 30);
        onemillion.addActionListener(this);
        image.add(onemillion);

        twomillion = new JButton("2000000 VNĐ");
        twomillion.setBounds(170, 485, 150, 30);
        twomillion.addActionListener(this);
        image.add(twomillion);

        fivemillion = new JButton("5000000 VNĐ");
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
        setLocation(300,0);
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


    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==back){
            setVisible(false);
            new Transactions(pinnumber).setVisible(true);
        }else if (ae.getSource()==other) {
            setVisible(false);
            new Withdrawal(pinnumber).setVisible(true);
        }else{
            String amount = ((JButton)ae.getSource()).getText().substring(0, ((JButton)ae.getSource()).getText().length() - 4);;
            Conn conn = new Conn();
            try{
                ResultSet rs = conn.s.executeQuery("select * from atm where pin= '"+pinnumber+"'");
                int balance = 0;
                while (rs.next()) {
                    if (rs.getString("type").equals("Gửi tiền") || rs.getString("type").equals("Chuyển khoản")) {
                        balance += Integer.parseInt(rs.getString("amount"));
                    } else {
                        balance -= Integer.parseInt(rs.getString("amount"));
                    }
                }
                if (ae.getSource() != back && balance < Integer.parseInt(amount)) {
                    JOptionPane.showMessageDialog(null, "Vui lòng kiểm tra lại số dư tài khoản!.");
                    return;
                }


                    Date date = new Date();


                    int amountInt = Integer.parseInt(amount);
                    String amountText = numberToWords(amountInt);
                    JOptionPane.showMessageDialog(null, "Số tiền đã rút là: " + amountText);
                    rs = conn.s.executeQuery("select balance from bank_account where pin = '" + pinnumber + "'");
                    if (rs.next()) {
                        int currentBalance = rs.getInt("balance");
                        int newBalance = currentBalance - amountInt;
                        conn.s.executeUpdate("update bank_account set balance = " + newBalance + " where pin = '" + pinnumber + "'");
                    }
                    String query="insert into atm values('"+pinnumber+"', '"+date+"', 'Rút tiền', '"+amount+"')";
                    conn.s.executeUpdate(query);
                    setVisible(false);
                    new Transactions(pinnumber).setVisible(true);
            }catch(Exception e){
                System.out.println(e);
            }
        }
    }


    public static  void main(String args[]){

        new FastCash("").setVisible(true);
    }
}
