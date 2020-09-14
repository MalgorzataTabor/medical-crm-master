package pl.sda.medicalcrm.service;

import com.mailjet.client.ClientOptions;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.MailjetResponse;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;
import com.mailjet.client.resource.Emailv31;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pl.sda.medicalcrm.entity.*;


@Component
public class EmailService {

    @Value("${spring.mail.username}")
    private String apiKey;

    @Value("${spring.mail.password}")
    private String apiSecret;

    @Value("${mail.from}")
    private String mailFrom;

    @Value("${mail.name}")
    private String mailName;

    public void sendEmail(Patient patient) throws MailjetException, MailjetSocketTimeoutException {
        String title = "Account has been created";
        String header = "<h3>Dear " + patient.getName() + "</a></h3>";
        String text = "Your new account has been created";
        MailjetClient client;
        MailjetRequest request;
        MailjetResponse response;
        client = new MailjetClient(apiKey, apiSecret,
                new ClientOptions("v3.1"));

        request = new MailjetRequest(Emailv31.resource)
                .property(Emailv31.MESSAGES, new JSONArray()
                        .put(new JSONObject()
                                .put(Emailv31.Message.FROM, new JSONObject()
                                        .put("Email", mailFrom)
                                        .put("Name", mailName))
                                .put(Emailv31.Message.TO, new JSONArray()
                                        .put(new JSONObject()
                                                .put("Email", patient.getUsername())
                                                .put("Name", patient.getName() + " " + patient.getSurname())))
                                .put(Emailv31.Message.SUBJECT, title)
                                .put(Emailv31.Message.HTMLPART, header + text)));
        response = client.post(request);
        System.out.println(response.getStatus());
        System.out.println(response.getData());
    }
}