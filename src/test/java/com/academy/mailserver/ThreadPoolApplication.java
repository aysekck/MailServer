package com.academy.mailserver;

import com.academy.mailserver.service.EmailSendService;
import com.academy.mailserver.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ThreadPoolApplication {

    public static void main(String[] args) {

        EmailSendService emailSendService = EmailSendService.getInstance();

        int nop = Runtime.getRuntime().availableProcessors();
        ExecutorService executor = Executors.newFixedThreadPool( 5);

        List<Callable<String>> callables = new ArrayList<>();

        for(int i = 0; i < 20; i++) {
            callables.add(() -> emailSendService.sendEmail(Util.getFakeEmail()));
        }

        try {
            List<Future<String>> futures = executor.invokeAll( callables);

            for( Future<String> future: futures){
                String res = future.get();
                System.out.println(res);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        executor.shutdown();

    }

}
