package atm.simulator.system;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import com.toedter.calendar.JDateChooser;
import java.awt.event.*;
public class SignupOne extends JFrame implements ActionListener {

    long random;
    JTextField nameTextField, fnameTextField, mnameTextField, emailTextField, addressTextField,
                cityTextField, wardTextField, pinTextField;

    JButton next;
    JRadioButton male, female, other, married, unmarried;
    JDateChooser dataChooser;
    SignupOne(){

        setLayout(null);
        Random ran = new Random();
        random = Math.abs((ran.nextLong() % 9000L) + 1000L);

        JLabel formno = new JLabel("APPLICATION FORM NO. " + random);
        formno.setFont(new Font("Raleway", Font.BOLD, 38));
        formno.setBounds(140, 20, 600, 40);
        add(formno);

        JLabel personalDetails = new JLabel("Trang 1: Thông tin khách hàng");
        personalDetails.setFont(new Font("Raleway", Font.BOLD, 22));
        personalDetails.setBounds(290, 80, 400, 30);
        add(personalDetails);

        JLabel name = new JLabel("Họ tên: ");
        name.setFont(new Font("Raleway", Font.BOLD, 20));
        name.setBounds(100, 140, 100, 30);
        add(name);

        nameTextField = new JTextField();
        nameTextField.setFont(new Font("Raleway", Font.BOLD, 14));
        nameTextField.setBounds(300, 140, 400, 30);
        add(nameTextField);

        JLabel fname = new JLabel("Tên cha: ");
        fname.setFont(new Font("Raleway", Font.BOLD, 20));
        fname.setBounds(100, 190, 200, 30);
        add(fname);

        fnameTextField = new JTextField();
        fnameTextField.setFont(new Font("Raleway", Font.BOLD, 14));
        fnameTextField.setBounds(300, 190, 400, 30);
        add(fnameTextField);

        JLabel mname = new JLabel("Tên mẹ: ");
        mname.setFont(new Font("Raleway", Font.BOLD, 20));
        mname.setBounds(100, 240, 200, 30);
        add(mname);

        mnameTextField = new JTextField();
        mnameTextField.setFont(new Font("Raleway", Font.BOLD, 14));
        mnameTextField.setBounds(300, 240, 400, 30);
        add(mnameTextField);

        JLabel dob = new JLabel("Ngày sinh: ");
        dob.setFont(new Font("Raleway", Font.BOLD, 20));
        dob.setBounds(100, 290, 200, 30);
        add(dob);

         dataChooser = new JDateChooser();
         dataChooser.setBounds(300, 290, 400, 30);
         dataChooser.setForeground(new Color(105, 105, 105));
         add(dataChooser);

        JLabel gender = new JLabel("Giới tính: ");
        gender.setFont(new Font("Raleway", Font.BOLD, 20));
        gender.setBounds(100, 340, 200, 30);
        add(gender);

        male = new JRadioButton("Nam");
        male.setBounds(300,340,120,30);
        add(male);

        female = new JRadioButton("Nữ");
        female.setBounds(450, 340, 120, 30);
        add(female);

        ButtonGroup gendergroup = new ButtonGroup();
        gendergroup.add(male);
        gendergroup.add(female);

        JLabel email = new JLabel("Email: ");
        email.setFont(new Font("Raleway", Font.BOLD, 20));
        email.setBounds(100, 390, 200, 30);
        add(email);

        emailTextField = new JTextField();
        emailTextField.setFont(new Font("Raleway", Font.BOLD, 14));
        emailTextField.setBounds(300, 390, 400, 30);
        add(emailTextField);

        JLabel marital = new JLabel("Hôn nhân: ");
        marital.setFont(new Font("Raleway", Font.BOLD, 20));
        marital.setBounds(100, 440, 200, 30);
        add(marital);

        married = new JRadioButton("Đã kết hôn");
        married.setBounds(300,440,150,30);
        add(married);

        unmarried = new JRadioButton("Chưa kết hôn");
        unmarried.setBounds(450, 440, 150, 30);
        add(unmarried);

        other = new JRadioButton("Khách");
        other.setBounds(630, 440, 100, 30);
        add(other);

        ButtonGroup maritalgroup = new ButtonGroup();
        maritalgroup.add(married);
        maritalgroup.add(unmarried);
        maritalgroup.add(other);

        JLabel address = new JLabel("Địa chỉ: ");
        address.setFont(new Font("Raleway", Font.BOLD, 20));
        address.setBounds(100, 490, 200, 30);
        add(address);

        addressTextField = new JTextField();
        addressTextField.setFont(new Font("Raleway", Font.BOLD, 14));
        addressTextField.setBounds(300, 490, 400, 30);
        add(addressTextField);

        JLabel ward = new JLabel("Phường: ");
        ward.setFont(new Font("Raleway", Font.BOLD, 20));
        ward.setBounds(100, 540, 200, 30);
        add(ward);

        wardTextField = new JTextField();
        wardTextField.setFont(new Font("Raleway", Font.BOLD, 14));
        wardTextField.setBounds(300, 540, 400, 30);
        add(wardTextField);

        JLabel city = new JLabel("Tỉnh/Thành Phố: ");
        city.setFont(new Font("Raleway", Font.BOLD, 20));
        city.setBounds(100, 590, 200, 30);
        add(city);

        cityTextField = new JTextField();
        cityTextField.setFont(new Font("Raleway", Font.BOLD, 14));
        cityTextField.setBounds(300, 590, 400, 30);
        add(cityTextField);

        JLabel pincode = new JLabel("Post Code: ");
        pincode.setFont(new Font("Raleway", Font.BOLD, 20));
        pincode.setBounds(100, 640, 200, 30);
        add(pincode);

        pinTextField = new JTextField();
        pinTextField.setFont(new Font("Raleway", Font.BOLD, 14));
        pinTextField.setBounds(300, 640, 400, 30);
        add(pinTextField);

        next = new JButton("Tiếp theo");
        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);
        next.setOpaque(true);
        next.setBorderPainted(false);
        next.setFont(new Font("Raleway",Font.BOLD, 14));
        next.setBounds(620,690,120,30);
        next.addActionListener(this);
        add(next);

        getContentPane().setBackground(Color.WHITE);

        setSize(850, 800);
        setLocation(350, 10);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){
        String formno = "" + random; //long
        String name = nameTextField.getText(); //setText
        String fname = fnameTextField.getText();
        String mname = mnameTextField.getText();
        String dob = ((JTextField)dataChooser.getDateEditor().getUiComponent()).getText();
        String gender = null;
        if(male.isSelected()){
            gender = "Nam";

        }else if(female.isSelected()){
            gender = "Nữ";
        }
        String email = emailTextField.getText();

        String marital = null;
        if(married.isSelected()){
            marital = "Đã kết hôn";
        } else if (unmarried.isSelected()){
            marital = "Chưa kết hôn";

        } else if (other.isSelected()){
            marital = "Khách";
        }

        String address = addressTextField.getText();
        String ward = wardTextField.getText();
        String city = cityTextField.getText();
        String pin = pinTextField.getText();

        try {

            if(name.equals("")){
                JOptionPane.showMessageDialog(null, "Vui lòng không bỏ trống họ tên");
            } else if (fname.equals("")) {
                JOptionPane.showMessageDialog(null, "Vui lòng không bỏ trống tên cha");
            } else if (mname.equals("")) {
                JOptionPane.showMessageDialog(null, "Vui lòng không bỏ trống tên mẹ");
            }else if (mname.equals("")) {
                JOptionPane.showMessageDialog(null, "Vui lòng không bỏ trống tên mẹ");
            }else if (dob.equals("")) {
                JOptionPane.showMessageDialog(null, "Vui lòng không bỏ trống ngày sinh");
            }else if (address.equals("")) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập địa chỉ");
            }else if (ward.equals("")) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập tên phường");
            }else if (city.equals("")) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập tên Tỉnh/Tp");
            }

            else{
                Conn c = new Conn();
                String query="insert into signup values('"+formno+"', '"+name+"', '"+fname+"', '"+mname+"','"+dob+"', '"+gender+"', '"+email+"', '"+marital+"', '"+address+"', '"+ward+"', '"+pin+"', '"+city+"')";
                c.s.executeUpdate(query);

                setVisible(false);
                new SignupTwo(formno).setVisible(true);
            }

        }catch (Exception e){
            System.out.println(e);
        }


    }
    public static void main(String args[]){
        new SignupOne();
    }
}

