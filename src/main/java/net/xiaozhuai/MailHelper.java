package net.xiaozhuai;

import okhttp3.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MailHelper {

    private OkHttpClient client = OkHttpHelper.newSession();
    public String email = null;

    public String refresh() throws IOException {
        Response response;
        if (email == null) {
            response = OkHttpHelper.get(client, "https://temp-mail.org/zh/option/refresh/");
        } else {
            response = OkHttpHelper.get(client, "https://temp-mail.org/zh/option/check/?_=" + System.currentTimeMillis());
        }

        String text = response.body().string();
        response.close();

        Document doc = Jsoup.parse(text);
        Elements elements;
        if (email == null) {
            elements = doc.select("input#mail");
            email = elements.first().attr("value");
        }

        elements = doc.select("table#mails tbody td a");
        if (elements.size() > 0) {
            return elements.first().attr("href");
        }
        return null;
    }

    public String getVerifyUrl(String mailUrl) throws IOException {
        Response response = OkHttpHelper.get(client, mailUrl);
        String text = response.body().string();
        response.close();
        Pattern pattern = Pattern.compile("https://www.processon.com/signup/verification/\\w+", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            return matcher.group();
        }
        return null;
    }

}
