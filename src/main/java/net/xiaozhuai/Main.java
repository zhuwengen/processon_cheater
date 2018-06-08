package net.xiaozhuai;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    private static int totalSucTimes = 0;
    private static int totalFailTimes = 0;
    private final static int THREAD_POOL_SIZE = 32;
    private final static Object lock = new Object();


    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
        for (int i = 0; i < THREAD_POOL_SIZE; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            MailHelper mailHelper = new MailHelper();
                            ProcessOnHelper processOnHelper = new ProcessOnHelper();

                            mailHelper.refresh();
                            log_debug("email: " + mailHelper.email);

                            processOnHelper.init("https://www.processon.com/i/57d6182fe4b08cbf6cf59514");

                            if (!processOnHelper.sign(mailHelper.email)) {
                                log_debug("sign ProcessOn failed");
                                return;
                            }
                            log_debug("sign ProcessOn suc");

                            String mailUrl = null;
                            for (int i = 0; i < 50; i++) {
                                log_debug("try times: " + i);
                                mailUrl = mailHelper.refresh();
                                if (mailUrl != null) break;
                                Thread.sleep(1000);
                            }
                            if (mailUrl == null) {
                                log_debug("mail not found");
                                return;
                            }
                            log_debug("mailUrl: " + mailUrl);


                            String verifyUrl = mailHelper.getVerifyUrl(mailUrl);
                            if (verifyUrl == null) {
                                log_debug("verifyUrl not found");
                                return;
                            }
                            log_debug("verifyUrl: " + verifyUrl);

                            if (processOnHelper.verify(verifyUrl)) {
                                incSucTimes();
                            } else {
                                incFailTimes();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            incFailTimes();
                        }
                    }
                }
            });
        }
    }

    private static void log_debug(String line) {
//        System.out.println(line);
        System.out.flush();
    }

    private static void log_info(String line) {
        System.out.println(line);
        System.out.flush();
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
