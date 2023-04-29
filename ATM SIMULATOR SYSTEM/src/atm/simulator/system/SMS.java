package atm.simulator.system;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class SMS {
    public static final String ACCOUNT_SID = "NULL";// Add after
    public static final String AUTH_TOKEN = "NULL";// Add after
    public static final String TO_NUMBER = "+NULL";// Add after
    public static final String FROM_NUMBER = "+NULL";// Add after

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


