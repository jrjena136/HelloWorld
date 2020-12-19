package com.jyoti.demo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Random;

/**
 * @author zimbra
 *
 */
public class BulkProv {

    /**
     * @param args
     */
    public static void main(String[] args) {

        String [] dept = {"Dev", "support", "qa", "tech writer"};
        String [] title = {"Engineer1", "Engineer2", "Sr Softawre Engineer", "Principal Software Engineer"};
        int x = 0;
        try {
           BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/jyotiranjan.jena/accts_generated.csv"));
           Random rand = new Random();
           for (int i =0; i < 5;i++) {
               long drand = (long)(rand.nextDouble()*100L);
               writer.write("ca " + "usera" + i + "@rdesai2.zdev.local" + " test123 fax 12346123"+ drand
                   +"  manager uid=user ou " + dept[x] + " title " + "\""+ title[x] + "\"" + " zimbraFeatureEWSEnabled" + " TRUE\n");
           }
            writer.close();
        } catch (Exception e) {

        }

    }

}

