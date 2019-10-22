package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.transaction.BankAccountOperation;

/**
 * Adds an income to the bank account.
 */
public class InCommand extends Command {

    public static final String COMMAND_WORD = "in";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a transaction to the bank account.\n"
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_AMOUNT + "AMOUNT "
            + PREFIX_DATE + "DATE "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "milk "
            + PREFIX_AMOUNT + "100 "
            + PREFIX_DATE + "24022019 "
            + PREFIX_TAG + "friends "
            + PREFIX_TAG + "owesMoney";

    public static final String MESSAGE_SUCCESS = "New transaction added: %1$s";

    private final BankAccountOperation transaction;

    public InCommand(BankAccountOperation transaction) {
        requireNonNull(transaction);
        this.transaction = transaction;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        model.handleOperation(transaction);
        model.commitBankAccount();
        return new CommandResult(String.format(MESSAGE_SUCCESS, transaction));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof InCommand) {
            InCommand inCommand = (InCommand) obj;
            return transaction.equals(inCommand.transaction);
        } else {
            return false;
        }
    }
}
