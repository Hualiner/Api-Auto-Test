package com.demo.common.entity;


import java.util.List;


public class TestResult {
    private int testPass;
    private List<TestCaseResult> testResult;
    private String testName;
    private int testAll;
    private int testFail;
    private String beginTime;
    private String totalTime;
    private int testSkip;

    public int getTestPass() {
        return testPass;
    }

    public void setTestPass(int testPass) {
        this.testPass = testPass;
    }

    public List<TestCaseResult> getTestResult() {
        return testResult;
    }

    public void setTestResult(List<TestCaseResult> testResult) {
        this.testResult = testResult;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public int getTestAll() {
        return testAll;
    }

    public void setTestAll(int testAll) {
        this.testAll = testAll;
    }

    public int getTestFail() {
        return testFail;
    }

    public void setTestFail(int testFail) {
        this.testFail = testFail;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(String totalTime) {
        this.totalTime = totalTime;
    }

    public int getTestSkip() {
        return testSkip;
    }

    public void setTestSkip(int testSkip) {
        this.testSkip = testSkip;
    }
}
