package com.set.two.utils;

import java.util.concurrent.Executor;

public class MyAsyncTask implements Executor {
    private String resp;

    @Override
    public void execute(Runnable command) {
        command.run();
    }
}