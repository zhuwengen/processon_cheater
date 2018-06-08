package net.xiaozhuai;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    private static int totalSucTimes = 0;
    private static int totalFailTimes = 0;
    private static int threadPoolSize = 32;
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

        ExecutorService executorService = Executors.newFixedThreadPool(threadPoolSize);
        for (int i = 0; i < threadPoolSize; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            MailHelper mailHelper = new MailHelper();
                            ProcessOnHelper processOnHelper = new ProcessOnHelper();

                            mailHelper.refresh();
                            log_debug("email: " + mailHelper.email);

                            processOnHelper.init(inviteUrl);

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

    private static void printUsage() {
        System.out.println("ProcessOn Cheater");
        System.out.println("Usage:");
        System.out.println("  java -jar processon_cheater.jar [inviteUrl]");
        System.out.println("  java -jar processon_cheater.jar [inviteUrl] [threadPoolSize]");
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
