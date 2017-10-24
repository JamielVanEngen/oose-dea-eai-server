package nl.han.oose.dea.jamielvanengen.domain;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User {
    private String user;
    private String password;

    public User() {
    }

    public User(String user, String password, String token) {
        this.user = user;
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public boolean authenticate(String password) {
        return this.password.equals(hash(password));
    }

    public String hash(String stringToHash) {
        byte[] hashedString = new byte[0];
        try {
            hashedString = getMessageDigest(stringToHash).digest();
        } catch (NoSuchAlgorithmException e) {
            // TODO: add a usefull catch (logging)
            e.printStackTrace();
        }
        return convertDecimalFormatToHexadecimal(hashedString);
    }

    private String convertDecimalFormatToHexadecimal(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i< bytes.length ;i++)
        {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }

    private MessageDigest getMessageDigest(String stringToHash) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(stringToHash.getBytes());
        return md;
    }
}
