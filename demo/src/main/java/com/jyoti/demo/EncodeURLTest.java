package com.jyoti.demo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;

public class EncodeURLTest {

    public static void main(String[] args) {
        EncodeURLTest enc = new EncodeURLTest();
        //enc.encode();
        //enc.decode("f0szy,NZSJyR63ogixfsaZFdYS0R,0u8to7E,jTokfkB");
        //enc.decode("kTvXm3P3QHaKwjDZyfdTWvn+js4TLEXVpg3TiJiK5lQA");
        enc.decode("VleEEbSTQOOx9cHmcrh2lZAQTktkbUyiiJdNFpkFCmEB");
    }

    private void decode(String encoded) {
        byte[] buffer = decodeFSSafeBase64(encoded);
        DataInputStream dis = null;
        try {
            dis = new DataInputStream(new ByteArrayInputStream(buffer));
            long msb, lsb;
            msb = dis.readLong();
            lsb = dis.readLong();
            String item = new UUID(msb, lsb).toString();
            msb = dis.readLong();
            lsb = dis.readLong();
            String container = new UUID(msb, lsb).toString();
            boolean isShare = dis.readBoolean();
            System.out.println("item : " + item);
            System.out.println("container : " + container);
            System.out.println("isShare : " + isShare);
        } catch (Exception e) {
            
        } finally {
            try {
                if (dis != null) {
                    dis.close();
                }
            } catch (Exception e) {
            }
        }
        
    }

    public String encode(String itemUuid, String containerUuid, boolean isShare) {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        DataOutputStream dos = null;
        try {
            dos = new DataOutputStream(buffer);
            UUID ui = UUID.fromString(itemUuid);
            dos.writeLong(ui.getMostSignificantBits());
            dos.writeLong(ui.getLeastSignificantBits());
            UUID uc = UUID.fromString(containerUuid);
            dos.writeLong(uc.getMostSignificantBits());
            dos.writeLong(uc.getLeastSignificantBits());
            dos.writeBoolean(isShare);
        } catch (Exception e) {
           
        } finally {
            try {
                if (dos != null) {
                    dos.close();
                }
            } catch (Exception e) {
            }
        }
        return encodeFSSafeBase64(buffer.toByteArray());
    }

    private String encodeFSSafeBase64(byte[] data) {
        byte[] encoded = Base64.encodeBase64(data);
        for (int i = 0; i < encoded.length; i++) {
            if (encoded[i] == (byte) '/')
                encoded[i] = (byte) ',';
        }
        try {
            return new String(encoded, "utf-8");
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    public static byte[] decodeFSSafeBase64(String str) {
        byte[] bytes = null;
        try {
            bytes = str.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
        }
        for (int i = 0; i < bytes.length; i++) {
            if (bytes[i] == (byte) ',')
                bytes[i] = (byte) '/';
        }
        return Base64.decodeBase64(bytes);
    }
}
