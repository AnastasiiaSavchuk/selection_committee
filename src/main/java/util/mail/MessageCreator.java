package util.mail;


import domain.enums.ApplicationStatus;
import org.apache.log4j.Logger;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MessageCreator {
    private static final Logger logger = Logger.getLogger(MessageCreator.class);
    private static final Map<String, String> map = Collections.synchronizedMap(new HashMap<>());

    static {
        map.put("all.intro", "Hello, Dear");
        map.put("se.subject", "Successful enrollment");
        map.put("se.status", "You have been credited on a");
        map.put("se.faculty", "to the");
        map.put("use.subject", "Unsuccessful enrollment");
        map.put("use.text", "You were not enrolled in the ");
        map.put("all.end", "Best wishes University team");
    }

    private MessageCreator() {
    }

    public static void writeSuccessfulEnrollment(String recipientAddress, String firstName, String lastName,
                                                 String middleName, ApplicationStatus status, String facultyName) {
        String text = map.get("all.intro") + " " +
                lastName + " " + firstName + " " + middleName + "\n" +
                map.get("se.status") + " " + status + " " + map.get("se.faculty") + " : " + facultyName + "\n" +
                map.get("all.end");
        MailSender.sendMail(recipientAddress, map.get("se.subject"), text);
        logger.info("Create message to successful enrollment" + text);
    }

    public static void writeUnSuccessfullyEnrollment(String recipientAddress, String firstName, String lastName,
                                                     String middleName, String facultyName) {
        String text = map.get("all.intro") + " " +
                lastName + " " + firstName + " " + middleName + "\n" +
                map.get("use.text") + " " + facultyName + "\n" +
                map.get("all.end");
        MailSender.sendMail(recipientAddress, map.get("use.subject"), text);
        logger.info("Create message to unsuccessful enrollment" + text);
    }
}
