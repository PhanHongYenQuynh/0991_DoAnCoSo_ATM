package com.twilio;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class SMS {
    public static final String ACCOUNT_SID = "ACec36b89d26705fbb11b8f10ce75fb408";// Add after
    public static final String AUTH_TOKEN = "Null";// Add after
    public static final String TO_NUMBER = "+84986556949";// Add after
    public static final String FROM_NUMBER = "+16315576245";// Add after

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


