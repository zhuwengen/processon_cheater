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

    public String getMailAddr() throws IOException {
        Response response = OkHttpHelper.get(client, "https://temp-mail.org/zh/option/refresh/");
        String text = response.body().string();
        response.close();
        Document doc = Jsoup.parse(text);
        Elements elements = doc.select("input#mail");
        return elements.first().attr("value");
    }

    public String getVerifyMailUrl() throws IOException {
        Response response = OkHttpHelper.get(client, "https://temp-mail.org/zh/option/check/?_=" + System.currentTimeMillis());
        String text = response.body().string();
        response.close();
        Document doc = Jsoup.parse(text);
        Elements elements = doc.select(".col-box a");
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
