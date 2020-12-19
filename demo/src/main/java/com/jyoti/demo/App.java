package com.jyoti.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Hello world!
 *
 */
public class App 
{
    private static int i1 = 5;
    public static int i2 = 5;
    public int i3 = 5;
    private int i4 = 5;
    public static void main( String[] args )
    {
        /*HttpClient client = HttpClients.createDefault();
        String url = "http://id-bridge01.cloudid.ci.opal.synacor.com:4080/orgs/hughes/users/%s/authn";
        String targetURL = String.format(url, "ydasi");
        HttpPost post = new HttpPost(targetURL);
        String key = "c46ex264e0455gs5op224e9t142o7b34";
        post.addHeader("Authorization", "Basic "+key);*/
        /*String addr = "mailto:abc@example.com";
        if(addr.startsWith("mailto:")) {
            System.out.println(addr.substring(7, addr.length()));
        }
        List<Integer> lists = Arrays.asList(1, 2, 3, 4, 5);
        for (int i = 0; i < lists.size(); i++) {
            switch(i) {
                case 1:
                    System.out.println("ONE");
                    break;
                default:
                    System.out.println(lists.get(i));
                    break;
            }
        }*/
        List<String> list = new ArrayList<String>();
       /* list.add("a");
        list.add("b");
        list.add("c");*/
        System.out.println(String.join(",", list));
        System.out.println(System.getProperty("java.version"));
        System.out.println(System.getProperty("java.version").compareTo("1.8.0_171"));
        if (System.getProperty("java.version").compareTo("1.8.0_171") <= 0) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
        /*Properties prop = System.getProperties();
        System.out.println(prop);*/
        String base64Data = "jAAJBAp2MTQwRGV2aWNlAApTbWFydFBob25l";
        byte[] bytes = org.apache.commons.codec.binary.Base64.decodeBase64(base64Data.getBytes());
        System.out.println(bytes[0]);
        System.out.println((((float) bytes[0]) / 10.0));
        System.out.println(Byte.toUnsignedInt(bytes[0]));
        System.out.println((((float) Byte.toUnsignedInt(bytes[0])) / 10.0)); 
        System.out.println(Byte.toUnsignedInt(bytes[1]));
        String s = "150.136.239.36";
        System.out.println(s.replaceAll(".", ""));
        try {
            throw new Derived();
        } catch(Base b) {
            
        } /*catch(Derived d) {
            
        }*/ //CE
        String x = null;
        giveMeAString(x);
        System.out.println(x);// null
        int a = -1;
        System.out.println(a>>>29);
        System.out.println(a>>>30);
        System.out.println(a>>>31);
        String s1 = "                      ";
        
        System.out.println(s1.trim());
    }

    public static String giveMeAString(String x) {
        x = "Hello";
        return x;
    }
    public static class Inner {
        private static int i5 = 5;
        private static int getSum() {
            //return (i1 + i2 + i3 + i4 + i5); CE
            return 0;
        }
    }
}

class Base extends Exception {}
class Derived extends Base {}