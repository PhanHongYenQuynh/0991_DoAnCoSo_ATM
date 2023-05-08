package atm.simulator.system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Conn {

    Connection c;
    Statement s;

    public Conn() {
        try {
            c = DriverManager.getConnection("jdbc:mysql:///atmsimulatorsystem", "root", "123456aA@");
            s = c.createStatement();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

}


