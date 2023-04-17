package atm.simulator.system;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import com.toedter.calendar.JDateChooser;
import java.awt.event.*;
public class SignupTwo extends JFrame implements ActionListener {


    JTextField cicTextField, panTextField;

    JButton next;
    JRadioButton syes, sno, eyes, eno;
    JComboBox religion, nationality, occupation, education, card;
    String formo;
    SignupTwo(String formo){

        this.formo = formo;
        setLayout(null);

        setTitle("NEW ACCOUNT APPLICATION FORM - PAGE 2");

        JLabel additionalDetails = new JLabel("Page 2: Additional Details");
        additionalDetails.setFont(new Font("Raleway", Font.BOLD, 22));
        additionalDetails.setBounds(290, 80, 400, 30);
        add(additionalDetails);

        JLabel Religion = new JLabel("Religion: ");
        Religion.setFont(new Font("Raleway", Font.BOLD, 20));
        Religion.setBounds(100, 140, 100, 30);
        add(Religion);

        String valReligion[]= {"Buddhism","Hinduism", "Muslims", "Sikhism", "Christianity", "Other"};
        religion = new JComboBox(valReligion);
        religion.setBounds(300, 140, 400, 30);
        religion.setBackground(Color.WHITE);
        add(religion);



        JLabel Nationality = new JLabel("Nationality: ");
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

        JLabel Card = new JLabel("Card: ");
        Card.setFont(new Font("Raleway", Font.BOLD, 20));
        Card.setBounds(100, 240, 200, 30);
        add(Card);

        String valCard[]= {"Null", "Napas Card","Visa Debit", "MasterCard Debit", "Brand Link Card"};
        card = new JComboBox(valCard);
        card.setBounds(300, 240, 400, 30);
        card.setBackground(Color.WHITE);
        add(card);

        JLabel Education = new JLabel("Education: ");
        Education.setFont(new Font("Raleway", Font.BOLD, 20));
        Education.setBounds(100, 315, 200, 30);
        add(Education);

        String educationValues[]= {"Non-Graduation","Graduate", "Post-Graduation", "Doctrate", "Others"};
        education = new JComboBox(educationValues);
        education.setBounds(300, 315, 400, 30);
        education.setBackground(Color.WHITE);
        add(education);

        JLabel Occupation = new JLabel("Occupation: ");
        Occupation.setFont(new Font("Raleway", Font.BOLD, 20));
        Occupation.setBounds(100, 390, 200, 30);
        add(Occupation);

        String occupationValues[]= {"Salaried","Self-Employed", "Bussiness", "Student", "Retired", "Others"};
        occupation = new JComboBox(occupationValues);
        occupation.setBounds(300, 390, 400, 30);
        occupation.setBackground(Color.WHITE);
        add(occupation);

        JLabel PAN = new JLabel("PAN Number: ");
        PAN.setFont(new Font("Raleway", Font.BOLD, 20));
        PAN.setBounds(100, 440, 200, 30);
        add(PAN);

        panTextField = new JTextField();
        panTextField.setFont(new Font("Raleway", Font.BOLD, 14));
        panTextField.setBounds(300, 440, 400, 30);
        add(panTextField);

        JLabel CIC = new JLabel("CIC Number: ");
        CIC.setFont(new Font("Raleway", Font.BOLD, 20));
        CIC.setBounds(100, 490, 200, 30);
        add(CIC);

        cicTextField = new JTextField();
        cicTextField.setFont(new Font("Raleway", Font.BOLD, 14));
        cicTextField.setBounds(300, 490, 400, 30);
        add(cicTextField);

        JLabel Senior = new JLabel("Senior Citizen: ");
        Senior.setFont(new Font("Raleway", Font.BOLD, 20));
        Senior.setBounds(100, 540, 200, 30);
        add(Senior);

        syes = new JRadioButton("Yes");
        syes.setBounds(300,540,120,30);
        add(syes);

        sno = new JRadioButton("No");
        sno.setBounds(450, 540, 100, 30);
        add(sno);


        ButtonGroup seniorgroup = new ButtonGroup();
        seniorgroup.add(syes);
        seniorgroup.add(sno);


        JLabel pincode = new JLabel("Exisiting Account: ");
        pincode.setFont(new Font("Raleway", Font.BOLD, 20));
        pincode.setBounds(100, 590, 200, 30);
        add(pincode);

        eyes = new JRadioButton("Yes");
        eyes.setBounds(300,590,120,30);
        add(eyes);

        eno = new JRadioButton("No");
        eno.setBounds(450, 590, 100, 30);
        add(eno);


        ButtonGroup existinggroup = new ButtonGroup();
        existinggroup.add(eyes);
        existinggroup.add(eno);

        next = new JButton("Next");
        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);
        next.setOpaque(true);
        next.setBorderPainted(false);
        next.setFont(new Font("Raleway",Font.BOLD, 14));
        next.setBounds(620,660,80,30);
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
        String scard = (String) card.getSelectedItem();
        String seducation = (String) education.getSelectedItem();
        String soccupation = (String) occupation.getSelectedItem();
        String seniorcitizen = null;
        if(syes.isSelected()){
            seniorcitizen = "Yes";

        }else if(sno.isSelected()){
            seniorcitizen = "No";
        }

        String exisitingaccount = null;
        if(eyes.isSelected()){
            exisitingaccount = "Yes";
        } else if (eno.isSelected()){
            exisitingaccount = "No";
        }


        String pan = panTextField.getText();
        String cic = cicTextField.getText();

        try {

                Conn c = new Conn();
                String query="insert into signuptwo values('"+formo+"', '"+sreligion+"', '"+snationality+"', '"+scard+"', '"+seducation+"', '"+soccupation+"', '"+pan+"', '"+cic+"', '"+seniorcitizen+"', '"+exisitingaccount+"')";
                c.s.executeUpdate(query);

            } catch (Exception e){
            System.out.println(e);
        }


    }
    public static void main(String args[]){
        new SignupTwo("").setVisible(true);
    }
}

