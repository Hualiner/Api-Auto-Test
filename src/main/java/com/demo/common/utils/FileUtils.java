package com.demo.common.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class FileUtils {

    /**
     * 读文件
     *
     * @param filePath 文件路径
     * @return 文件内容
     */
    public static String read(String filePath) {
        BufferedReader br = null;
        StringBuilder result = new StringBuilder();
        try {
            br = new BufferedReader(new FileReader(filePath));
            String s;
            while ((s = br.readLine()) != null) {
                result.append(System.lineSeparator()).append(s);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    throw new RuntimeException("关闭失败！");
                }
            }
        }
        return result.toString();
    }

    /**
     * 写文件
     *
     * @param text     文本
     * @param filePath 路径
     */
    public static void write(String text, String filePath) {
        write(text, filePath, false);
    }

    /**
     * 写文件
     *
     * @param text      文本
     * @param filePath  路径
     * @param isWriting 是否续写
     */
    public static void write(String text, String filePath, boolean isWriting) {
        FileWriter fw = null;
        try {
            fw = new FileWriter(filePath, isWriting);
            fw.write(text);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fw != null)
                try {
                    fw.close();
                } catch (IOException e) {
                    throw new RuntimeException("关闭失败！");
                }
        }
    }
}
