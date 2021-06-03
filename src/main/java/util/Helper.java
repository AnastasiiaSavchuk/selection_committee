package util;

import org.apache.log4j.Logger;
import org.bouncycastle.util.encoders.Hex;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

/**
 * Includes utils methods for the app
 *
 * @author A.Savchuk
 */
public final class Helper {
    private static final Logger logger = org.apache.log4j.Logger.getLogger(Helper.class);

    public static String getPasswordHash(String password) {
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException ex) {
            logger.error("Problem with file hash password: " + ex.getMessage());
        }
        byte[] hash = Objects.requireNonNull(digest).digest(password.getBytes(StandardCharsets.UTF_8));
        return new String(Hex.encode(hash));
    }
}
