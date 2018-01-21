package com.demo.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;


public class RetryUtils implements TestRule {
    private static Logger logger = LoggerFactory.getLogger(RetryUtils.class);
    private int retryCount;

    public RetryUtils(int retryCount) {
        this.retryCount = retryCount;
    }

    @Override
    public Statement apply(Statement base, Description description) {
        return statement(base, description);
    }

    private Statement statement(final Statement base, final Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                Throwable caughtThrowable = null;

                // implement retry logic here
                for (int i = 0; i < retryCount; i++) {
                    try {
                        base.evaluate();
                        return;
                    } catch (Throwable t) {
                        caughtThrowable = t;
                        logger.info("{}.{}: run {} failed", description.getClassName(),
                                description.getMethodName(), i+1);
                    }
                }

                logger.info("{}.{}: giving up after {} failures", description.getClassName(),
                        description.getMethodName(), retryCount);

                assert caughtThrowable != null;
                throw caughtThrowable;
            }
        };
    }
}
