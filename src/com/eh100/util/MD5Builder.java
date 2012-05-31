package com.eh100.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;

public class MD5Builder {

    static Logger logger = Logger.getLogger(MD5Builder.class);

    static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8',
            '9', 'a', 'b', 'c', 'd', 'e', 'f' };

    /** */
    /**
         * 对文件全文生成MD5摘要
         * @param file   要加密的文件
         * @return MD5摘要砄1�7
         */
    public static String getMD5(File file) {
        FileInputStream fis = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");

            logger.info("MD5摘要长度＄1�7" + md.getDigestLength());
            fis = new FileInputStream(file);
            byte[] buffer = new byte[2048];
            int length = -1;
            logger.info("弄1�7始生成摘覄1�7");
            long s = System.currentTimeMillis();
            while ((length = fis.read(buffer)) != -1) {
                md.update(buffer, 0, length);
            }
            logger.info("摘要生成成功,总用旄1�7 " + (System.currentTimeMillis() - s)
                    + "ms");
            byte[] b = md.digest();
            return byteToHexString(b);
            // 16位加寄1�7
            // return buf.toString().substring(8, 24);
        } catch (Exception ex) {
            logger.error(ex);
            ex.printStackTrace();
            return null;
        } finally {
            try {
                fis.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /** */
    /**
         * 对一段String生成MD5加密信息
         * @param message 要加密的String
         * @return 生成的MD5信息
         */
    public static String getMD5(String message) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            logger.info("MD5摘要长度＄1�7" + md.getDigestLength());
            byte[] b = md.digest(message.getBytes());
            return byteToHexString(b);
        } catch (NoSuchAlgorithmException e) {
            logger.error(e);
            e.printStackTrace();
            return null;
        }
    }

    /** */
    /**
         * 把byte[]数组转换成十六进制字符串表示形式
         * @param tmp    要转换的byte[]
         * @return 十六进制字符串表示形弄1�7
         */
    private static String byteToHexString(byte[] tmp) {
        String s;

        char str[] = new char[16 * 2];

        int k = 0;
        for (int i = 0; i < 16; i++) {

            byte byte0 = tmp[i];
            str[k++] = hexDigits[byte0 >>> 4 & 0xf];

            str[k++] = hexDigits[byte0 & 0xf];
        }
        s = new String(str);
        return s;
    }

    public static void main(String[] arg) {
        System.out.print(MD5Builder.getMD5("222222"));
    }
}