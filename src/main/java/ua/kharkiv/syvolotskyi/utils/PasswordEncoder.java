package ua.kharkiv.syvolotskyi.utils;


import org.apache.log4j.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncoder {
    private static final Logger LOG = Logger.getLogger(PasswordEncoder.class);

    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
    private static final String ALGORITHM = "MD5";

    private PasswordEncoder() {}

    public static String encode(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance(ALGORITHM);
            digest.update(password.getBytes());
            byte[] hash = digest.digest();
            return bytesToHex(hash);
        } catch (NoSuchAlgorithmException e) {
            LOG.error(e);
        }
        return null;
    }

    private static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }

}

