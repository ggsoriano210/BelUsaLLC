package com.BelUsa.utility;

import com.BelUsa.managers.FileReaderManager;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.Properties;

public class EmailUtil {


    //**********************************************************************************************
    //Method Name:sendEmailWithAttachReport
    //Description: Method is used to send the Email with attch report in it
    //Input Arguments:1.File->reportFile
    //                2.String->currSuite
    //Return: NA
    //Created By :
    //Created On :
    //Reviewed By:
    //Reviewed On:
    //**********************************************************************************************
    public static void sendEmailWithAttachReport(File reportFile, String currSuite) {

        //Setting Properties and Session for message
        Properties props = new Properties();

        try //try to send through 365 email
        {
            props.put("mail.smtp.host", FileReaderManager.getInstance().getEmailReader().getEmailHost());
            props.put("mail.smtp.starttls.enable", FileReaderManager.getInstance().getEmailReader().getEmailAuthentication());
            props.put("mail.smtp.auth", FileReaderManager.getInstance().getEmailReader().getEmailAuthentication());
            props.put("mail.smtp.port", FileReaderManager.getInstance().getEmailReader().getEmailPort());
            props.put("mail.smtp.connectiontimeout", "20000"); //set timeout, Exception if Connection to server is not made
            props.put("mail.smtp.timeout", "20000"); //set timeout, Exception if handshake is not made

            sendMessage(reportFile, currSuite, props); //Email With Content

        } catch (MessagingException eMessage) { // if send message through 365 fails, send via mailrelay which does not need authentication
            try {

                props.clear();//clear properties that were already added
                props = System.getProperties();
                props.put("mail.smtp.host", "mailrelay.gmail.com");
                props.put("mail.smtp.port", "25");

                sendMessage(reportFile, currSuite, props); //Email With Content

            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        }
    }

    //**********************************************************************************************
    //Method Name:sendEmailWithSnapshot
    //Description: Method is used to send the Email with attch report in it
    //Input Arguments:1.File->reportFile
    //                2.String->currSuite
    //Return: NA
    //Created By :
    //Created On :
    //Reviewed By:
    //Reviewed On:
    //**********************************************************************************************
    public static void sendEmailWithSnapshot(File reportFile, String currSuite) {

        //Setting Properties and Session for message
        Properties props = new Properties();

        try //try to send through 365 email
        {
            props.put("mail.smtp.host", FileReaderManager.getInstance().getEmailReader().getEmailHost());
            props.put("mail.smtp.starttls.enable", FileReaderManager.getInstance().getEmailReader().getEmailAuthentication());
            props.put("mail.smtp.auth", FileReaderManager.getInstance().getEmailReader().getEmailAuthentication());
            props.put("mail.smtp.port", FileReaderManager.getInstance().getEmailReader().getEmailPort());
            props.put("mail.smtp.connectiontimeout", "20000"); //set timeout, Exception if Connection to server is not made
            props.put("mail.smtp.timeout", "20000"); //set timeout, Exception if handshake is not made

            sendMessage(reportFile, currSuite, props); //Email With Content

        } catch (MessagingException eMessage) { // if send message through 365 fails, send via mailrelay which does not need authentication
            try {

                props.clear();//clear properties that were already added
                props = System.getProperties();
                props.put("mail.smtp.host", "mailrelay.gmail.com");
                props.put("mail.smtp.port", "25");

                sendMessage(reportFile, currSuite, props); //Email With Content

            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void sendMessage(File reportFile, String currSuite, Properties props) throws MessagingException
    {
        Session session;

        if (props.get("mail.smtp.host").equals("smtp.office365.com")) //depending on properties smtp.host, set authentication.
        {
            session = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(FileReaderManager.getInstance().getEmailReader().getOutLookUserName(), FileReaderManager.getInstance().getEmailReader().getOutLookPassword());
                        }
                    });
        }
        else{
            session  = Session.getInstance(props);
        }

        //Output for email , debugging purposes
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        // Output for session on Console, debugging purposes
        session.setDebug(true);
        session.setDebugOut(ps);

        // Create object of MimeMessage class
        Message message = new MimeMessage(session);

        // Set the from address
        message.setFrom(new InternetAddress(FileReaderManager.getInstance().getEmailReader().getOutLookUserName()));

        // Set the recipient address
        message.setRecipients(Message.RecipientType.TO,InternetAddress.parse("gsoriano210@gmail.com"));


        if(currSuite.equalsIgnoreCase("HeartBeat")){
            // Add the subject link
            message.setSubject("Failure Observed on Heart Beat at: " + reportFile.getName().replace(".png",""));
        }else{
            // Add the subject link
            message.setSubject("QA Automation Report Currently Completed "+ currSuite);
        }

        // Create object to add multimedia type content
        BodyPart messageBodyPart1 = new MimeBodyPart();

        // Set the body of email
        messageBodyPart1.setText("This is message body");

        // Create another object to add another content
        MimeBodyPart messageBodyPart2 = new MimeBodyPart();

        // Create data source and pass the filename
        DataSource source = new FileDataSource(reportFile);

        // set the handler
        messageBodyPart2.setDataHandler(new DataHandler(source));

        // set the file
        messageBodyPart2.setFileName(reportFile.getName());

        // Create object of MimeMultipart class
        Multipart multipart = new MimeMultipart();

        // add body part 1
        multipart.addBodyPart(messageBodyPart2);

        // add body part 2
        multipart.addBodyPart(messageBodyPart1);

        // set the content
        message.setContent(multipart);

        // finally send the email
        Transport.send(message);
    }
}