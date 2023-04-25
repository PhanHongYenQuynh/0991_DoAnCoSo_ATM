package atm.simulator.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class PinChange extends JFrame implements ActionListener {

    JPasswordField pin, repin;
    JButton change, back;
    String pinnumber;

    PinChange(String pinnumber) {

        this.pinnumber = pinnumber;
        setLayout(null);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 1054, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);

        JLabel text = new JLabel("Vui lòng nhập mã pin.");
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 16));
        text.setBounds(250, 280, 500, 35);
        image.add(text);

        JLabel pintext = new JLabel("PIN MỚI: ");
        pintext.setForeground(Color.WHITE);
        pintext.setFont(new Font("System", Font.BOLD, 16));
        pintext.setBounds(165, 421, 180, 25);
        image.add(pintext);

        pin = new JPasswordField();
        pin.setFont(new Font("Raleway", Font.BOLD, 25));
        pin.setBounds(330, 421, 180, 25);
        image.add(pin);

        JLabel repintext = new JLabel("NHẬP LẠI: ");
        repintext.setForeground(Color.WHITE);
        repintext.setFont(new Font("System", Font.BOLD, 16));
        repintext.setBounds(165, 457, 180, 25);
        image.add(repintext);

        repin = new JPasswordField();
        repin.setFont(new Font("Raleway", Font.BOLD, 25));
        repin.setBounds(330, 457, 180, 25);
        image.add(repin);

        change = new JButton("Thay đổi");
        change.setBounds(355, 500, 150, 30);
        change.addActionListener(this);
        image.add(change);

        back = new JButton("Quay về");
        back.setBounds(155, 500, 150, 30);
        back.addActionListener(this);
        image.add(back);


        setSize(900, 900);
        setLocation(300, 0);
        setUndecorated(true);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == change) {
            try {
                String npin = pin.getText();
                String rpin = repin.getText();

                if (!npin.equals(rpin)) {
                    JOptionPane.showMessageDialog(null, "Quý khách nhập mã pin không trùng nhau. \n Xin vui lòng nhập lại!.");
                    return;
                }
                if (npin.equals("")) {
                    JOptionPane.showMessageDialog(null, "Vui lòng không trống mã pin!");
                    return;
                }

                if (npin.length() != 6) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng 6 số cho.");
                    return;
                }

                if (rpin.equals("")) {
                    JOptionPane.showMessageDialog(null, "Vui lòng không bỏ trống nhập lại mã pin!");
                    return;
                }

                Conn conn = new Conn();
                String query1 = "update atm set pin = '" + rpin + "' where pin = '" + pinnumber + "' ";
                String query2 = "update login set pin = '" + rpin + "' where pin = '" + pinnumber + "' ";
                String query3 = "update signupthree set pin = '" + rpin + "' where pin = '" + pinnumber + "' ";
                String query4 = "update bank_account set pin = '" + rpin + "' where pin = '" + pinnumber + "' ";

                conn.s.executeUpdate(query1);
                conn.s.executeUpdate(query2);
                conn.s.executeUpdate(query3);
                conn.s.executeUpdate(query4);

                JOptionPane.showMessageDialog(null, "Thay đổi mã pin thành công!");
                setVisible(false);
                new Transactions(rpin).setVisible(true);

            } catch (Exception e) {
                System.out.println(e);
            }
        } else {
            setVisible(false);
            new Transactions(pinnumber).setVisible(true);
        }

    }

    public static void main(String args[]) {
        new PinChange("").setVisible(true);
    }
}
