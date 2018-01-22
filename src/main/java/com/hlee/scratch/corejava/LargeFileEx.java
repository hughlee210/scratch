package com.hlee.scratch.corejava;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class LargeFileEx {

    public static void main(String[] args) {
        createLargeCsv();

    }

    static void createLargeCsv() {
        
//        Voucher Batch Id, Voucher Code, Control Code, Status, Use Count
//        SCEJ-XX-S0000035-00017862,H4H5-A9N4-8K75,SCEJ-XX-S0000035-00017862-000001,inactive,1

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("/Users/hlee1/tmp/large_data.txt"))) {
            String content = "Voucher Batch Id, Voucher Code, Control Code, Status, Use Count\n";
            bw.write(content);
            int size = 2000000;
            for (int i = 0; i < size; i++) {
                bw.write("SCEJ-XX-S0000035-00017862,H4H5-A9N4-8K75,SCEJ-XX-S0000035-00017862-000001,inactive,1\n");
            }

            // no need to close it.
            // bw.close();
            System.out.println("Done");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
