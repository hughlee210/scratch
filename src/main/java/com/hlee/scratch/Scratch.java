package com.hlee.scratch;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Scratch {

    public static void main(String[] args) throws UnsupportedEncodingException {

        double result = (10 / 0.75) + 1;
        int resultInt = Double.valueOf(result).intValue();
        System.out.format("result = %f, int result = %d %n", result, resultInt);

        String key = "770A8A65DA156D24EE2A093277530142";
        String keyStrUTF16 = new String(key.getBytes("UTF-16"), "UTF-16");

        byte[] bytes = key.getBytes();
        byte[] bytesUTF8 = key.getBytes("UTF-8");
        byte[] bytesUTF16 = key.getBytes("UTF-16");

        System.out.format("key: %s, length: %d\n", key, key.length());
        System.out.format("key bytes      length: %d\n", bytes.length);
        System.out.format("key bytesUTF8  length: %d\n", bytesUTF8.length);
        System.out.format("key bytesUTF16 length: %d\n", bytesUTF16.length);
        System.out.format("keyStrUTF16 string: %s, length: %d\n", keyStrUTF16, keyStrUTF16.length());

        testStringRegex();

        testSplit();

        String name = "122_null";
        System.out.println("name: " + name);
        System.out.println("name replaced: " + name.replace("_null", "_None"));

        String testStr = "test 1 2 345_Purpose - this is test_ttt.txt";
        System.out.println("string                    : " + testStr);
        System.out.println("string white space removed: " + testStr.replaceAll(" ", ""));
        
        Map<String, String> map = new HashMap<>();
        map.put("key1", "val1");
        map.put("key2", "val2");
        System.out.println(map);
        
    }

    private static void testStringRegex() {

        String gmtOffset = "+00:00";
        String val = gmtOffset.replaceAll("\\+|\\-|:", "");
        System.out.println("gmtOffset: " + gmtOffset + ", number only: " + val);

        char sign = gmtOffset.charAt(0) == '-' ? '-' : '+';
        String hour = val.substring(0, 2);
        String minute = val.substring(2, 4);

        int hourNum = Integer.valueOf(sign + hour);
        int minNum = Integer.valueOf(sign + minute);

        System.out.println("hour=" + hourNum + ", minute=" + minNum);
    }

    private static void testSplit() {
        String str = "Voucher Batch Id, Voucher Code, Control Code, Status, Use Count";
        System.out.println("[" + str + "] split result length = " + str.split(",").length);
        
        str = "1,2,,4,";
        System.out.println("[" + str + "] split result length = " + str.split(",").length);

        StringTokenizer st = new StringTokenizer(str, ",");
        while (st.hasMoreTokens()) {
            System.out.println("token: " + st.nextToken());
        }
    }

}
