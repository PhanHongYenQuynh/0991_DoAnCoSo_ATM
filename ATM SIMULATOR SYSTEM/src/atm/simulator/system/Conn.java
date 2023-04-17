package atm.simulator.system;

import java.sql.*;
import java.sql.DriverManager;

public class Conn {

    Connection c;
    Statement s;
    public Conn() {
        try{

            c = DriverManager.getConnection("jdbc:mysql:///atmsimulatorsystem", "root", "123456aA@");
            s = c.createStatement();
        }catch (Exception e){
            System.out.println(e);
        }
    }

}
