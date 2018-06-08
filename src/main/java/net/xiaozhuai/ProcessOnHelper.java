package net.xiaozhuai;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Response;

import java.io.IOException;
import java.util.Random;

public class ProcessOnHelper {

    private OkHttpClient client = OkHttpHelper.newSession();

    public void init(String url) throws IOException {
        Response response = OkHttpHelper.get(client, url);
        response.close();
    }

    public boolean sign(String email) throws IOException {
        Response response = OkHttpHelper.post(client, "https://www.processon.com/signup/submit",
                new FormBody.Builder()
                        .add("email", email)
                        .add("pass", randomString(12))
                        .add("fullname", randomString(12))
                        .build()
        );
        String text = response.body().string();
        response.close();
        return text.contains("我们已经向您的邮箱");
    }

    public boolean verify(String verifyUrl) throws IOException {
        Response response = OkHttpHelper.get(new OkHttpClient.Builder().followRedirects(false).build(), verifyUrl);
        boolean suc = false;
        if (response.code() == 302) {
            suc = true;
        }
        response.close();
        return suc;
    }

    private static Random RANDOM = new Random(System.currentTimeMillis());

    private String randomString(int length) {
        StringBuffer buffer = new StringBuffer("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
        StringBuffer sb = new StringBuffer();
        int range = buffer.length();
        for (int i = 0; i < length; i++) {
            sb.append(buffer.charAt(RANDOM.nextInt(range)));
        }
        return sb.toString();
    }
}
