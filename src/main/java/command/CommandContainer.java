package command;

import command.admin.AdminPageCommand;
import command.applicant.SignupDetailsCommand;
import command.common.LogoutCommand;
import command.common.NoCommand;
import command.outOfControl.*;
import org.apache.log4j.Logger;

import java.util.Map;
import java.util.TreeMap;

public class CommandContainer {
    private static final Logger logger = Logger.getLogger(CommandContainer.class);
    private static final Map<String, Command> commands = new TreeMap<>();

    static {
        //admin commands
        commands.put("adminPage", new AdminPageCommand());

        //user commands
        commands.put("signupDetails", new SignupDetailsCommand());

        //common commands
        commands.put("noCommand", new NoCommand());
        commands.put("logout", new LogoutCommand());

        //out of control commands
        commands.put("setLanguage", new SetLanguageCommand());
        commands.put("loginChoice", new LoginChoiceCommand());
        commands.put("login", new LoginCommand());
        commands.put("signupChoice", new SignupChoiceCommand());
        commands.put("signup", new SignupCommand());
        commands.put("getFaculties", new GetFacultiesCommand());
    }

    public static Command get(String commandName) {
        if (commandName == null || !commands.containsKey(commandName)) {
            logger.trace("Command not found, name --> " + commandName);
            return commands.get("noCommand");
        }
        return commands.get(commandName);
    }
}
