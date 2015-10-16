package com.silletti.coffiURL.utilities;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Utily for chipering strings.
 *
 */
public final class Chiper {

    private static MessageDigest md;
    private static Chiper      security;

    /**
     * Private constructor.
     */
    private Chiper() {
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {

        }
    }

    /**
     * Get the unique instance of the class.
     */
    private static void getInstance() {
        if (security == null) {
            security = new Chiper();
        }
    }

    /**
     * Chipers the string.
     *
     * @param s
     *            string to chiper.
     */
    public static String cipher(final String s) {
        getInstance();
        String result = null;
        try {
            byte[] code = md.digest(s.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder(2 * code.length);
            for (byte b : code) {
                sb.append(String.format("%02x", b & 0xff));
            }
            result = sb.toString();
        } catch (UnsupportedEncodingException e) {

        }
        return result;
    }
    
}
