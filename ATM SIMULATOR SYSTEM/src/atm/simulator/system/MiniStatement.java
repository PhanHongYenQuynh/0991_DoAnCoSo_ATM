package atm.simulator.system;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import java.awt.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;


public class MiniStatement extends JFrame implements ActionListener {

    String cardnumber;

    MiniStatement(String cardnumber) {
        this.cardnumber = cardnumber;
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


         try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from login where cardnumber = '" + cardnumber + "'");
            while (rs.next()) {
                card.setText("Card number:    " + rs.getString("cardnumber").substring(0, 4) + "XXXXXXXX" + rs.getString("cardnumber").substring(12));
            }
        } catch (Exception e) {
            System.out.println(e);
        }


        int balance = 0;
        Conn conn = new Conn();

        try {

            String query = "SELECT * FROM bank_account where acc_no = ?";
            PreparedStatement ps = conn.c.prepareStatement(query);
            ps.setString(1, cardnumber);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // Hiển thị số dư tài khoản
                balance = rs.getInt("balance");
                DecimalFormat decimalFormat = new DecimalFormat("#,### VNĐ");
                String formattedBalance = decimalFormat.format(balance);
                balancee.setText("Số dư tài khoản là: " + formattedBalance);

                // Hiển thị lịch sử giao dịch
                query = "SELECT * FROM atm where cardnumber = ?";
                ps = conn.c.prepareStatement(query);
                ps.setString(1, cardnumber);
                rs = ps.executeQuery();
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
        JLabel label = new JLabel("Số dư bằng chữ: \n" + amountText);
        label.setBounds(20, 800, 600, 30);
        add(label);

        mini.setBounds(20, 45, 400, 800);
        setSize(550, 1000);
        setLocation(20, 20);
        getContentPane().setBackground(Color.WHITE);
        setVisible(true);
        exportToPDF("ministatement.pdf");
    }

    public static String formatCurrency(int amount) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        return decimalFormat.format(amount);
    }



    public static String numberToWords(int number) {
        if (number == 0) {
            return " đồng";
        }

        String[] units = {"", "một", "hai", "ba", "bốn", "năm", "sáu", "bảy", "tám", "chín", "mười",
                "mười một", "mười hai", "mười ba", "mười bốn", "mười lăm", "mười sáu", "mười bảy", "mười tám", "mười chín"};
        String[] tens = {"", "", "hai mươi", "ba mươi", "bốn mươi", "năm mươi", "sáu mươi", "bảy mươi", "tám mươi", "chín mươi"};

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

    public void exportToPDF(String filePath) {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            com.itextpdf.text.Font font = FontFactory.getFont("STSong-Light", "UniGB-UCS2-H", BaseFont.EMBEDDED, 14, Font.BOLD, BaseColor.BLACK);

            Paragraph title = new Paragraph("Sao kê giao dịch", font);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            Conn conn = new Conn();
            String query = "SELECT * FROM atm where cardnumber = ?";
            PreparedStatement ps = conn.c.prepareStatement(query);
            ps.setString(1, cardnumber);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Paragraph paragraph = new Paragraph(rs.getString("date") + " - " + rs.getString("type") + " - " + formatCurrency(rs.getInt("amount")));
                document.add(paragraph);
            }

            document.close();

            System.out.println("Xuất file PDF thành công!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void actionPerformed(ActionEvent ae) {
        this.setVisible(false);
    }

    public static void main(String args[]) {
        new MiniStatement("").setVisible(true);
    }
}
