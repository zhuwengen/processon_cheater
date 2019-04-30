package net.xiaozhuai;

import javax.imageio.IIOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    private static int totalSucTimes = 0;
    private static int totalFailTimes = 0;
    private static int threadPoolSize = 1;
    private final static Object lock = new Object();
    private static String inviteUrl;


    public static void main(String[] args) {

        if (args.length == 0 || args.length > 2) {
            printUsage();
            return;
        }

        inviteUrl = args[0];

        if (args.length == 2) {
            try {
                threadPoolSize = Integer.parseInt(args[1]);
            } catch (Exception e) {
                printUsage();
                return;
            }
        }
        List<String> signupList = getSignup();
        for (int i = 0; i < signupList.size(); i++) {
                        try {
                            MailHelper mailHelper = new MailHelper();
                            ProcessOnHelper processOnHelper = new ProcessOnHelper();

                            String email = mailHelper.getMailAddr();
                            log_debug("email: " + email);

                            processOnHelper.init(inviteUrl);

                            if (!processOnHelper.sign(email,signupList.get(i))) {
                                log_debug("提交注册请求失败");
                                incFailTimes();
                                continue;
                            }else {
                                log_debug("提交注册请求成功");
                            }

                            String mailUrl = null;
                            for (int j = 0; j < 50; j++) {
                                log_debug("try times: " + j);
                                mailUrl = mailHelper.getVerifyMailUrl();
                                if (mailUrl != null) break;
                                Thread.sleep(1000);
                            }
                            if (mailUrl == null) {
                                log_debug("mail not found");
                                incFailTimes();
                                return;
                            }
                            log_debug("mailUrl: " + mailUrl);


                            String verifyUrl = mailHelper.getVerifyUrl(mailUrl);
                            if (verifyUrl == null) {
                                log_debug("verifyUrl not found");
                                incFailTimes();
                                return;
                            }else {
                                log_debug("verifyUrl: " + verifyUrl);
                            }

                            if (processOnHelper.verify(verifyUrl)) {
                                log_debug("验证成功");
                                incSucTimes();
                            } else {
                                log_debug("验证失败");
                                incFailTimes();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            incFailTimes();
                        }
        }
       /* ExecutorService executorService = Executors.newFixedThreadPool(threadPoolSize);
        for (int i = 0; i < threadPoolSize; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            MailHelper mailHelper = new MailHelper();
                            ProcessOnHelper processOnHelper = new ProcessOnHelper();

                            String email = mailHelper.getMailAddr();
                            log_debug("email: " + email);

                            processOnHelper.init(inviteUrl);

                            if (!processOnHelper.sign(email)) {
                                log_debug("sign ProcessOn failed");
                                incFailTimes();
                                return;
                            }
                            log_debug("sign ProcessOn suc");

                            String mailUrl = null;
                            for (int i = 0; i < 50; i++) {
                                log_debug("try times: " + i);
                                mailUrl = mailHelper.getVerifyMailUrl();
                                if (mailUrl != null) break;
                                Thread.sleep(1000);
                            }
                            if (mailUrl == null) {
                                log_debug("mail not found");
                                incFailTimes();
                                return;
                            }
                            log_debug("mailUrl: " + mailUrl);


                            String verifyUrl = mailHelper.getVerifyUrl(mailUrl);
                            if (verifyUrl == null) {
                                log_debug("verifyUrl not found");
                                incFailTimes();
                                return;
                            }
                            log_debug("verifyUrl: " + verifyUrl);

                            if (processOnHelper.verify(verifyUrl)) {
                                log_debug("verify suc");
                                incSucTimes();
                            } else {
                                log_debug("verify failed");
                                incFailTimes();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            incFailTimes();
                        }
                    }
                }
            });
        }*/
    }

    private static List<String> getSignup() {
        List<String> list = new ArrayList<>();
        try {
            FileReader file = new FileReader("F:\\processon\\signup.txt");
            BufferedReader reader = new BufferedReader(file);
            String str = null;
            while ((str=reader.readLine())!=null){
                list.add(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    private static void printUsage() {
        System.out.println("ProcessOn Cheater");
        System.out.println("Usage:");
        System.out.println("  java -jar processon_cheater.jar [inviteUrl]");
        System.out.println("  java -jar processon_cheater.jar [inviteUrl] [threadPoolSize]");
    }

    private static void log_debug(String line) {
        System.out.println(line);
    }

    private static void log_info(String line) {
        System.out.println(line);
    }

    private static void incSucTimes() {
        synchronized (lock) {
            totalSucTimes++;
            log_info("Suc: " + totalSucTimes + ", Fail: " + totalFailTimes);
        }
    }

    private static void incFailTimes() {
        synchronized (lock) {
            totalFailTimes++;
            log_info("Suc: " + totalSucTimes + ", Fail: " + totalFailTimes);
        }
    }
}
