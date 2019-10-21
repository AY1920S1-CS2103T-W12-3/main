package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.BankAccount;
import seedu.address.model.ReadOnlyBankAccount;
import seedu.address.model.transaction.Transaction;

/**
 * An Immutable BankAccount that is serializable to JSON format.
 */
@JsonRootName(value = "bankaccount")
class JsonSerializableBankAccount {

    public static final String MESSAGE_DUPLICATE_TRANSACTION = "Transactions list contains duplicate transaction(s).";

    private final List<JsonAdaptedTransaction> transactions = new ArrayList<>();
    private final List<JsonAdaptedBudget> budgets = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableBankAccount} with the given transactions.
     */
    @JsonCreator
    public JsonSerializableBankAccount(@JsonProperty("transactions") List<JsonAdaptedTransaction> transactions,
                                       @JsonProperty("budgets") List<JsonAdaptedBudget> budgets) {
        this.transactions.addAll(transactions);
        this.budgets.addAll(budgets);
    }

    /**
     * Converts a given {@code ReadOnlyBankAccount} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableBankAccount}.
     */
    public JsonSerializableBankAccount(ReadOnlyBankAccount source) {
        budgets
                .addAll(source.getBudgetHistory()
                        .stream()
                        .map(JsonAdaptedBudget::new)
                        .collect(Collectors.toList()));
        transactions
                .addAll(source.getTransactionHistory()
                        .stream()
                        .map(JsonAdaptedTransaction::new)
                        .collect(Collectors.toList()));
    }

    /**
     * Converts this bank account into the model's {@code BankAccount} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public BankAccount toModelType() throws IllegalValueException {
        BankAccount bankAccount = new BankAccount();
        for (JsonAdaptedTransaction jsonAdaptedTransaction : transactions) {
            Transaction txn = jsonAdaptedTransaction.toModelType();
            if (bankAccount.hasTransaction(txn)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_TRANSACTION);
            }
            bankAccount.addTransaction(txn);
        }
        return bankAccount;
    }

}
