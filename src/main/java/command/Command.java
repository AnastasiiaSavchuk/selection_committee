package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

/**
 * Main abstract class for the Command pattern implementation.
 *
 * @author A.Savchuk.
 */
public abstract class Command implements Serializable {
    private static final long serialVersionUID = 5698447511235778123L;

    /**
     * Execution method for command.
     *
     * @return Address to go once the command is executed.
     */
    public abstract Object execute(HttpServletRequest request, HttpServletResponse response);

    @Override
    public final String toString() {
        return getClass().getSimpleName();
    }
}
