package com.academy.mailserver;

import com.academy.mailserver.service.EmailSendService;
import com.academy.mailserver.util.Util;

import java.util.concurrent.*;

public class SingleThreadApplication {

    public static void main(String[] args) {

//  Runnables, Callables and SingleThreadExecutor
//  Runnable: no parameters, no return value. cannot through an Exception
//  Callable: no parameters, is Generic, has a return value and can throw an Exception
//  Note: This application runs in the main thread

        // Example of a valid lambda
        Runnable runnable = () -> System.out.println("Hello World!");

        // Example of a valid lambda
        Callable<String> callable = ()-> "kJByfR".toLowerCase();    // This return a String

        // Thread pool with size 1
        // Every task that is 'added' to the executor runs sequentially
        ExecutorService executor = Executors.newSingleThreadExecutor();

        //Asynchronous execution in separate thread.
        executor.execute(runnable);

        // Asynchronous execution in separate thread.
        // 'result' wil hold the result of the lambda
        // being of type String
        Future<String> result =  executor.submit(callable);

        // Get the result
        try {
            // get() is blocking. If the task in not already finished it waits until it is finished.
            String resultString = result.get();
            System.out.println(resultString);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        // Send emails to backend using a Runnable and Callable
        EmailSendService emailSendService = EmailSendService.getInstance();
        Runnable runnable2 = () -> { emailSendService.sendEmail(Util.getFakeEmail());};
        Callable<String> callable2 = () -> emailSendService.sendEmail(Util.getFakeEmail());

        // Get the result
        // if the task is not yet finished, get() waits until it is finished.

        // Executing the Runnable
        executor.execute( runnable2);
        // This is also valid.
        // There is no return type but it is now possible to check if it is finished
        // or call get() to wait until it is finished
        Future<?> runnableFuture = executor.submit( runnable2);
        boolean isFinished = runnableFuture.isDone();

        // Execution of Callable
        Future<String> callableFuture = executor.submit( callable2);

        // Always do this!!!
        // The executor shuts down after all tasks are finished
        executor.shutdown();

    }
}
