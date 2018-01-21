package com.demo.common.entity;

import java.util.ArrayList;
import java.util.List;


public class TestCaseResult {
    private String className;
    private String methodName;
    private String description;
    private String spendTime;
    private String status;
    private List<String> throwableLog = new ArrayList<>();

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getDescription() {
        if (description != null) {
            return description;
        }
        description = "--";
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSpendTime() {
        return spendTime;
    }

    public void setSpendTime(String spendTime) {
        this.spendTime = spendTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getThrowableLog() {
        return throwableLog;
    }

    public void setThrowableLog(List<String> log) {
        this.throwableLog = log;
    }
}
