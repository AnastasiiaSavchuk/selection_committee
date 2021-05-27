package util;

import domain.Faculty;
import org.apache.log4j.Logger;
import org.bouncycastle.util.encoders.Hex;

import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

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

    public static void chooseSortedType(String filter, List<Faculty> listToSort) {
        if (listToSort == null || listToSort.isEmpty()) {
            return;
        }
        switch (filter) {
            case "nameAsc":
                listToSort.sort(Faculty.COMPARE_BY_NAME);
                break;
            case "nameDsc":
                listToSort.sort(Collections.reverseOrder(Faculty.COMPARE_BY_NAME));
                break;
            case "budgetAsc":
                listToSort.sort(Faculty.COMPARE_BY_BUDGET_QTY);
                break;
            case "budgetDsc":
                listToSort.sort(Collections.reverseOrder(Faculty.COMPARE_BY_BUDGET_QTY));
                break;
            case "totalAsc":
                listToSort.sort(Faculty.COMPARE_BY_TOTAL_QTY);
                break;
            case "totalDsc":
                listToSort.sort(Collections.reverseOrder(Faculty.COMPARE_BY_TOTAL_QTY));
                break;
            default:
                break;
        }
    }
}
