package com.twtech.village_app;

/**
 * Created by twtech on 5/7/17.
 */

import java.io.File;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.activation.CommandMap;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.activation.MailcapCommandMap;
import javax.mail.BodyPart;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * Created by sushant on 30/11/16.
 */
public class EmailAPI extends javax.mail.Authenticator
{
    private String _user;
    private String _pass;
    private String _date;
    private String[] _to;
    private String _from;
    private String _port;
    private String _sport;
    private String _host;
    private String _subject;
    private String _body;
    private boolean _auth;
    private boolean _debuggable;
    private Multipart _multipart;
    public static int k=0;
    private Session session;
    public EmailAPI()
    {
        //_host = "smtp.gmail.com"; // default smtp server
        _host = "smtp.gmail.com";
        _port = "465"; // default smtp port
        _sport = "465"; // default socketfactory port

        //_user = "shivasrk8@gmail.com"; // username
        _user = "9100";
        _pass = "nandgaon123"; // password
        _from = "nnandgaon@gmail.com"; // email sent from
        _subject = "suggetions"; // email subject
        _body = "suggetions are :"; // email body
        //_to[0]="rahul.sharma8081@gmail.com";

        _debuggable = false; // debug mode on or off - default off
        _auth = true; // smtp authentication - default on

        _multipart = new MimeMultipart();

        // There is something wrong with MailCap, javamail can not find a handler for the multipart/mixed part, so this bit needs to be added.
        MailcapCommandMap mc = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
        mc.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
        mc.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
        mc.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
        mc.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
        mc.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
        CommandMap.setDefaultCommandMap(mc);
    }
    public EmailAPI(String user, String pass)
    {
        this();
        _user = user;
        _pass = pass;
    }
    public boolean send() throws Exception
    {
        Properties props = _setProperties();
        if(!_user.equals("") && !_pass.equals("") && _to.length > 0 && !_from.equals("") && !_subject.equals("") && !_body.equals("")) {
            Session session = Session.getInstance(props, this);
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(_from));
            InternetAddress[] addressTo = new InternetAddress[_to.length];
            for (int i = 0; i < _to.length; i++) {
                addressTo[i] = new InternetAddress(_to[i]);
            }
            msg.setRecipients(MimeMessage.RecipientType.TO, addressTo);

            msg.setSubject(_subject);
            // **************************************************************************
            msg.setSentDate(new Date(System.currentTimeMillis()));
            // **************************************************************************

            // setup message body
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(_body);
            _multipart.addBodyPart(messageBodyPart);

            // Put parts in message
            msg.setContent(_multipart);

            // send email
            Transport.send(msg);

            return true;
        } else {
            return false;
        }
    }
	/*
	 * this function call will set one file attachment by default
	 * and this function is used by SmsLog and CallLog services.
	 */

    public void addAttachment(String dirLocation) throws Exception
    {
        File file=new File(dirLocation);
        File[] listOfFiles=file.listFiles();
        int fileAttachmentLimit=1;
        for( int i=0;i<fileAttachmentLimit;i++)
        {
            String fileName=listOfFiles[i].getName();
            BodyPart messageBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(dirLocation+fileName);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(dirLocation+fileName);
            _multipart.addBodyPart(messageBodyPart);
        }
    }
    /*
     * this function call will send two attachments.
     * and this function is used by callrecording services.
     */
    public void addAttachment(String dirLocation, int fileAttachmentLimit, List attachmentFileNameList) throws Exception {

        ArrayList al=(ArrayList)attachmentFileNameList;

        if(fileAttachmentLimit > al.size())
        {
            fileAttachmentLimit = al.size();
        }
        for( int i=0;i<fileAttachmentLimit;i++)
        {
            String fileName=(String)al.get(i);
            BodyPart messageBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(dirLocation+fileName);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(dirLocation+fileName);
            _multipart.addBodyPart(messageBodyPart);
        }//for
    }//addAttachment

    @Override
    public PasswordAuthentication getPasswordAuthentication()
    {

        return new PasswordAuthentication(_user, _pass);
    }

    private Properties _setProperties() {
        Properties props = new Properties();

        props.put("mail.smtp.host", _host);

        if(_debuggable)
        {
            props.put("mail.debug", "true");
        }

        if(_auth) {
            props.put("mail.smtp.auth", "true");
        }

		/*
		props.put("mail.smtp.port", _port);
		props.put("mail.smtp.socketFactory.port", _sport);
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");
		*/
        props.put("mail.smtp.starttls.enable","true");    ////false
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.user", _user);  /////
        props.put("mail.smtp.password", _pass);  /////
        props.put("mail.store.protocol", "smtp");
        props.put("mail.smtp.host", _host);
        props.put("mail.smtp.port", "25"); /////  25

        return props;
    }
    // the getters and setters
    public String getBody()
    {
        return _body;
    }
    public void setBody(String _body)
    {
        this._body = _body;
    }
    public void setTo(String[] toArr)
    {
        this._to = toArr;
    }
    public void setFrom(String string)
    {
        this._from = string;
    }

    public void setSubject(String string) {
        this._subject = string;
    }
    public void setDate(String _date) {
        this._date = _date;
    }
    // more of tshe getters and setters �..

}

