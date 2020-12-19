package com.jyoti.demo;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.codec.binary.StringUtils;

public class Test {
    public static void main(String[] args) {

        // Mention the Recipient's email address
        String to = "fromaddress@gmail.com";

        // Mention the Sender's email address
        String from = "jrjr89@live.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", "localhost");
        properties.put("mail.smtp.port", "465");

        // Get the Session object and pass username and password
        Session session = Session.getInstance(properties);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress("abc@example.com"));

            // Set Subject: header field
            message.setSubject("This is the Subject Line!");

            // Now set the actual message
            message.setText("This is actual message");

            System.out.println("sending...");
            // Send message
            //Transport.send(message);
            System.out.println(Arrays.toString(message.getRecipients(RecipientType.TO)));
            List<Address> recipients = new CopyOnWriteArrayList<Address>(message.getRecipients(RecipientType.TO));
            if (recipients != null && !recipients.isEmpty()) {
                for (Address ad : recipients) {
                    String address = ad.toString();
                    System.out.println(address);
                    if (address.equals("abc@example.com")) {
                        boolean isRemoved = recipients.remove(ad);
                        System.out.println(isRemoved);
                        Address [] x = new Address[recipients.size()];
                        x = recipients.toArray(x);
                        message.setRecipients(javax.mail.Message.RecipientType.TO, x);
                        message.saveChanges();
                        break;
                    }
                }
            }
            System.out.println(Arrays.toString(message.getRecipients(RecipientType.TO)));
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
