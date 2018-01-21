package com.demo.common.runner;

import com.demo.common.GlobalVar;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;

public class ListenerRunner extends BlockJUnit4ClassRunner {

    public ListenerRunner(Class<?> cls) throws InitializationError {
        super(cls);
    }

    @Override
    public void run(RunNotifier notifier){
        notifier.removeListener(GlobalVar.listenerUtils);
        notifier.addListener(GlobalVar.listenerUtils);
        notifier.fireTestRunStarted(getDescription());
        super.run(notifier);
    }
}