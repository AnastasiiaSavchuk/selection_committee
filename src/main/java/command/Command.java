package command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;

/**
 * Main abstract class for the Command pattern implementation.
 *
 * @author A.Savchuk.
 */
public abstract class Command implements Serializable {
    private static final long serialVersionUID = 845962147859625478L;

    /**
     * Execution method for command.
     *
     * @return Address to go once the command is executed.
     */
    public abstract String execute(HttpServletRequest request, HttpServletResponse response);

    @Override
    public final String toString() {
        return getClass().getSimpleName();
    }
}
