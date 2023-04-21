package atm.simulator.system;
import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;
import java.text.ParseException;
import java.util.*;
import com.toedter.calendar.JDateChooser;
import java.awt.event.*;
public class SignupTwo extends JFrame implements ActionListener {


    JTextField cicTextField, taxTextField, wherecicTextField;

    JButton next;
    JRadioButton syes, sno, eyes, eno;
    JComboBox religion, nationality, occupation, education, income;
    JDateChooser dataChooser;
    String formno;
    SignupTwo(String formno){

        this.formno = formno;
        setLayout(null);

        setTitle("NEW ACCOUNT APPLICATION FORM - PAGE 2");

        JLabel additionalDetails = new JLabel("Trang 2: Thông tin bổ sung");
        additionalDetails.setFont(new Font("Raleway", Font.BOLD, 22));
        additionalDetails.setBounds(290, 80, 400, 30);
        add(additionalDetails);

        JLabel Religion = new JLabel("Tôn giáo: ");
        Religion.setFont(new Font("Raleway", Font.BOLD, 20));
        Religion.setBounds(100, 140, 130, 30);
        add(Religion);

        String valReligion[]= {"Đạo Phật","Đạo Hindu", "Đạo Hồi", "Đạo Do Thái", "Đạo chúa", "Khác"};
        religion = new JComboBox(valReligion);
        religion.setBounds(300, 140, 400, 30);
        religion.setBackground(Color.WHITE);
        add(religion);



        JLabel Nationality = new JLabel("Quốc tịch: ");
        Nationality.setFont(new Font("Raleway", Font.BOLD, 20));
        Nationality.setBounds(100, 190, 200, 30);
        add(Nationality);

        String valNationality[]= {"Afghanistan", "Albania", "Algeria", "Andorra", "Angola", "Antigua and Barbuda", "Argentina", "Armenia", "Australia",
                "Austria", "Azerbaijan", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium","Belize", "Benin", "Bhutan", "Bolivia", "Bosnia and Herzegovina",
                "Botswana", "Brazil", "Brunei", "Bulgaria", "Burkina Faso", "Burundi", "Cambodia","Cameroon", "Canada", "Chad", "Chile", "China", "Colombia", "Comoros",
                "Congo", "Costa Rica", "Denmark", "Djibouti", "Dominica", "East Timor", "Ecuador","Egypt", "El Salvador", "England", "Eritrea", "Estonia", "Ethiopia", "Fiji",
                "Finland", "France", "Gabon", "Gambia", "Georgia", "Germany", "Greece","Haiti", "Honduras", "Hong Kong", "Hungary", "India", "Indonesia", "Iran",
                "Iraq", "Ireland", "Israel", "Italy", "Ivory Coast", "Jamaica", "Japan","Kazakhstan", "Kenya", "Kiribati", "Laos", "Latvia", "Lebanon", "Lesotho",
                "Liberia", "Libya", "Liechtenstein", "Luxembourg", "Macao", "Macedonia", "Madagascar","Malawi", "Mexico", "Moldova", "Monaco", "Namibia", "Nauru", "Nepal",
                "New Zealand", "Nicaragua", "Niger", "Nigeria", "North Korea", "Northern Ireland", "Pakistan","Palestine", "Paraguay", "Peru", "Poland", "Portugal", "Puerto Rico", "Qatar",
                "Romania", "Russia", "Rwanda", "Samoa", "Saudi Arabia", "Scotland", "Singapore","South Korea", "Spain", "Sri Lanka", "Switzerland", "Taiwan", "The Czech Republic", "The Philippines",
                "The Unites States of America (USA)", "Turkey", "Uruguay", "Uzbekistan", "Vanuatu", "Vatican City", "Venezuela","Vietnam", "Wales", "Yemen", "Zambia", "Zimbabwe",
        };

        nationality = new JComboBox(valNationality);
        nationality.setBounds(300, 190, 400, 30);
        nationality.setBackground(Color.WHITE);
        add(nationality);

        JLabel Income = new JLabel("Thu nhập: ");
        Income.setFont(new Font("Raleway", Font.BOLD, 20));
        Income.setBounds(100, 240, 200, 30);
        add(Income);

        String valCard[]= {"Không","<1.50.000","<2.50.000","<5.00.000",">=10.00.000","Trên 10.00.000"};
        income = new JComboBox(valCard);
        income.setBounds(300, 240, 400, 30);
        income.setBackground(Color.WHITE);
        add(income);

        JLabel Education = new JLabel("Học vấn: ");
        Education.setFont(new Font("Raleway", Font.BOLD, 20));
        Education.setBounds(100, 315, 200, 30);
        add(Education);

        String educationValues[]= {"Không trình độ","12/12", "Đại học", "Cao Đẳng", "Tiến Sĩ", "Khác"};
        education = new JComboBox(educationValues);
        education.setBounds(300, 315, 400, 30);
        education.setBackground(Color.WHITE);
        add(education);

        JLabel Occupation = new JLabel("Nghề nghiệp: ");
        Occupation.setFont(new Font("Raleway", Font.BOLD, 20));
        Occupation.setBounds(100, 390, 200, 30);
        add(Occupation);

        String occupationValues[]= {"Nhân viên","Tự kinh doanh", "Doanh nhân", "Sinh viên", "Nghỉ hưu", "Khác"};
        occupation = new JComboBox(occupationValues);
        occupation.setBounds(300, 390, 400, 30);
        occupation.setBackground(Color.WHITE);
        add(occupation);

        JLabel TAX = new JLabel("Mã số thuế: ");
        TAX.setFont(new Font("Raleway", Font.BOLD, 20));
        TAX.setBounds(100, 440, 200, 30);
        add(TAX);

        taxTextField = new JTextField();
        taxTextField.setFont(new Font("Raleway", Font.BOLD, 14));
        taxTextField.setBounds(300, 440, 400, 30);
        add(taxTextField);

        JLabel CIC = new JLabel("CCCD: ");
        CIC.setFont(new Font("Raleway", Font.BOLD, 20));
        CIC.setBounds(100, 490, 200, 30);
        add(CIC);

        cicTextField = new JTextField();
        cicTextField.setFont(new Font("Raleway", Font.BOLD, 14));
        cicTextField.setBounds(300, 490, 400, 30);
        add(cicTextField);

        JLabel datecic = new JLabel("Ngày cấp: ");
        datecic.setFont(new Font("Raleway", Font.BOLD, 20));
        datecic.setBounds(100, 540, 200, 30);
        add(datecic);

        dataChooser = new JDateChooser();
        dataChooser.setBounds(300, 540, 400, 30);
        dataChooser.setForeground(new Color(105, 105, 105));
        add(dataChooser);

        JLabel wherecic = new JLabel("Nơi cấp: ");
        wherecic.setFont(new Font("Raleway", Font.BOLD, 20));
        wherecic.setBounds(100, 590, 200, 30);
        add(wherecic);

        wherecicTextField = new JTextField();
        wherecicTextField.setFont(new Font("Raleway", Font.BOLD, 14));
        wherecicTextField.setBounds(300, 590, 400, 30);
        add(wherecicTextField);

        JLabel Senior = new JLabel("Người cao tuổi: ");
        Senior.setFont(new Font("Raleway", Font.BOLD, 20));
        Senior.setBounds(100, 640, 200, 30);
        add(Senior);

        syes = new JRadioButton("Phải");
        syes.setBounds(300,640,120,30);
        add(syes);

        sno = new JRadioButton("Không");
        sno.setBounds(450, 640, 100, 30);
        add(sno);


        ButtonGroup seniorgroup = new ButtonGroup();
        seniorgroup.add(syes);
        seniorgroup.add(sno);


        JLabel existaccount = new JLabel("TK Ngân Hàng:");
        existaccount.setFont(new Font("Raleway", Font.BOLD, 20));
        existaccount.setBounds(100, 690, 330, 30);
        add(existaccount);

        eyes = new JRadioButton("Có");
        eyes.setBounds(300,690,120,30);
        add(eyes);

        eno = new JRadioButton("Chưa");
        eno.setBounds(450, 690, 100, 30);
        add(eno);


        ButtonGroup existinggroup = new ButtonGroup();
        existinggroup.add(eyes);
        existinggroup.add(eno);

        next = new JButton("Tiếp theo");
        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);
        next.setOpaque(true);
        next.setBorderPainted(false);
        next.setFont(new Font("Raleway",Font.BOLD, 14));
        next.setBounds(620,740,120,30);
        next.addActionListener(this);
        add(next);

        getContentPane().setBackground(Color.WHITE);

        setSize(850, 800);
        setLocation(350, 10);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){

        String sreligion = (String) religion.getSelectedItem();
        String snationality = (String) nationality.getSelectedItem();
        String sincome = (String) income.getSelectedItem();
        String seducation = (String) education.getSelectedItem();
        String soccupation = (String) occupation.getSelectedItem();
        String sdatecic = ((JTextField)dataChooser.getDateEditor().getUiComponent()).getText();

        String seniorcitizen = null;
        if(syes.isSelected()){
            seniorcitizen = "Phải";

        }else if(sno.isSelected()){
            seniorcitizen = "Không";
        }

        String exisitingaccount = null;
        if(eyes.isSelected()){
            exisitingaccount = "Có";
        } else if (eno.isSelected()){
            exisitingaccount = "Chưa";
        }

        String where = wherecicTextField.getText();
        String tax = taxTextField.getText();
        String cic = cicTextField.getText();

        try {
            if(tax.equals("")) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập mã số thuê.");
            } else if (cic.equals("")) {
                JOptionPane.showMessageDialog(null, "Vui lòng điền căn cước công dân. ");
            } else if (sdatecic.equals("")) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập ngày cấp căn cước công dân. ");
            } else if (where.equals("")) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập nơi cấp căn cước công dân. ");
            }else{
                Conn c = new Conn();
                String query="insert into signuptwo values('"+formno+"', '"+sreligion+"', '"+snationality+"', '"+sincome+"', '"+seducation+"', '"+soccupation+"', '"+tax+"', '"+cic+"', '"+sdatecic+"','"+where+"','"+seniorcitizen+"', '"+exisitingaccount+"')";
                c.s.executeUpdate(query);

                //Signup3 Object
                setVisible(false);
                new SignupThree(formno).setVisible(true);
            }


        } catch (Exception e){
            System.out.println(e);
        }

    }
    public static void main(String args[]){
        new SignupTwo("").setVisible(true);
    }
}

