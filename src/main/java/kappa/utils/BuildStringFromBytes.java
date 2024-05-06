package kappa.utils;

public class BuildStringFromBytes {
    // Constructor
    private BuildStringFromBytes() {
    }

    // Methods
    /**
     * Method to build a string from bytes
     * @param bytes bytes
     * @return string
     */
    public static String buildStringFromBytes(byte[] bytes){
        StringBuilder hexString = new StringBuilder();
        for(byte b : bytes){
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
