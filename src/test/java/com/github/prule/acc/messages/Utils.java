package com.github.prule.acc.messages;

public class Utils {
    /**
     * Removes spaces if they've been added for readability and then returns a bytearray representation of the hex string.
     */
    public static byte[] hexStringToByteArray(String in) {
        String s = in.replaceAll(" ", ""); // remove spaces
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            int digit1 = Character.digit(s.charAt(i), 16);
            int digit2 = Character.digit(s.charAt(i + 1), 16);
            data[i / 2] = (byte) ((digit1 << 4) + digit2);
        }
        return data;
    }
}
