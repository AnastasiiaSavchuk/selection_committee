package command;

import command.admin.*;
import command.applicant.*;
import command.common.ApplicantCommand;
import command.common.LogoutCommand;
import command.common.NoCommand;
import command.outOfControl.*;
import org.apache.log4j.Logger;

import java.util.Map;
import java.util.TreeMap;

/**
 * Holder for all commands.
 *
 * @author A.Savchuk.
 */
public class CommandContainer {
    private static final Logger logger = Logger.getLogger(CommandContainer.class);
    private static final Map<String, Command> commands = new TreeMap<>();

    static {
        //admin commands
        commands.put("adminPage", new AdminCommand());
        commands.put("facultyCreateChoice", new FacultyCreateChoiceCommand());
        commands.put("facultyCreate", new FacultyCreateCommand());
        commands.put("facultyUpdate", new FacultyUpdateCommand());
        commands.put("facultyDelete", new FacultyDeleteCommand());
        commands.put("getSubjects", new SubjectsCommand());
        commands.put("subjectCreateChoice", new SubjectCreateChoiceCommand());
        commands.put("subjectCreate", new SubjectCreateCommand());
        commands.put("subjectUpdate", new SubjectUpdateCommand());
        commands.put("subjectDelete", new SubjectDeleteCommand());
        commands.put("getApplicantById", new ApplicantByIdCommand());
        commands.put("applicantUpdateByAdmin", new ApplicantUpdateByAdminCommand());
        commands.put("generateStatement", new StatementGenerateCommand());
        commands.put("rollbackStatement", new StatementRollBackCommand());
        commands.put("sendStatement", new StatementSendToEmailCommand());

        //user commands
        commands.put("signupDetails", new SignupDetailsCommand());
        commands.put("saveCertificate", new SaveCertificateCommand());
        commands.put("applicantUpdate", new ApplicantUpdateCommand());
        commands.put("applyToTheFacultyCreateChoice", new ApplyToTheFacultyChoiceCommand());
        commands.put("applyToTheFaculty", new ApplyToTheFacultyCommand());

        //common commands
        commands.put("noCommand", new NoCommand());
        commands.put("logout", new LogoutCommand());
        commands.put("applicantPage", new ApplicantCommand());

        //out of control commands
        commands.put("setLanguage", new SetLanguageCommand());
        commands.put("loginChoice", new LoginChoiceCommand());
        commands.put("login", new LoginCommand());
        commands.put("signupChoice", new SignupChoiceCommand());
        commands.put("signup", new SignupCommand());
        commands.put("getFaculties", new FacultiesCommand());
        commands.put("getFacultyById", new FacultyByIdCommand());
    }

    public static Command get(String commandName) {
        if (commandName == null || !commands.containsKey(commandName)) {
            logger.trace("Command not found, name --> " + commandName);
            return commands.get("noCommand");
        }
        return commands.get(commandName);
    }
}
