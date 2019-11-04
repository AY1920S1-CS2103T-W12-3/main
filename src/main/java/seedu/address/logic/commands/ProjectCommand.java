package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.comparator.DateComparator;
import seedu.address.model.Model;
import seedu.address.model.Projection;
import seedu.address.model.transaction.BankAccountOperation;
import seedu.address.model.util.Date;

/**
 * Projects user's future balance based on income/outflow history
 */
public class ProjectCommand extends Command {

    public static final String COMMAND_WORD = "project";
    public static final String MESSAGE_INVALID_DATE = "Date must be set in the future";
    public static final String MESSAGE_VOID_TRANSACTION_HISTORY =
            "Transaction history is currently empty. It is impossible to cast a projection.";
    public static final String SMALL_SAMPLE_SIZE =
            "Projection is based on a small sample size, and may be limited in its accuracy";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Project future balance based on past income/outflow.\n"
        + "Parameters: "
        + PREFIX_DATE + "DATE\n"
        + "Example: " + COMMAND_WORD + " "
        + PREFIX_DATE + "12122103 09:00";
    public static final String MESSAGE_WARNING = "[WARNING] %1$s";
    public static final String MESSAGE_SUCCESS = "Projected balance: %1$s\n";

    private static final int RECOMMENDED_MINIMUM_TRANSACTIONS = 4;

    public final Date date;

    public ProjectCommand(Date date) {
        requireNonNull(date);
        this.date = date;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        ObservableList<BankAccountOperation> transactionHistory =
            model.getBankAccount().getSortedTransactionHistory(new DateComparator());

        if (transactionHistory.isEmpty()) {
            throw new CommandException(MESSAGE_VOID_TRANSACTION_HISTORY);
        }

        Projection projection = new Projection(transactionHistory, date, model.getFilteredBudgetList());

        if (transactionHistory.size() < RECOMMENDED_MINIMUM_TRANSACTIONS) {
            return new CommandResult(String.format(MESSAGE_SUCCESS, projection.toString()),
                    String.format(MESSAGE_WARNING, SMALL_SAMPLE_SIZE));
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, projection.toString()));
    }
}
