package atm.simulator.system;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SMS {
    public static final String ACCOUNT_SID = "NULL";
    public static final String AUTH_TOKEN = "NULL";
    public static final String TO_NUMBER = "+NULL";
    public static final String FROM_NUMBER = "+NULL";

    public static void sendSMS(String messageBody) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                        new PhoneNumber(TO_NUMBER),
                        new PhoneNumber(FROM_NUMBER),
                        messageBody)
                .create();
        System.out.println("Sent message: " + message.getSid());
    }
}


