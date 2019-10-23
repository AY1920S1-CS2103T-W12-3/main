package seedu.address.model;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.transaction.BankAccountOperation;
import seedu.address.model.transaction.Budget;
import seedu.address.model.transaction.LedgerOperation;

/**
 * The API of the Model component.
 */
public interface Model {
    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<BankAccountOperation> PREDICATE_SHOW_ALL_TRANSACTIONS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' bank account file path.
     */
    Path getBankAccountFilePath();

    /**
     * Sets the user prefs' bank account file path.
     */
    void setBankAccountFilePath(Path bankAccountFilePath);

    /**
     * Replaces bank account data with the data in {@code bankAccount}.
     */
    void setBankAccount(ReadOnlyBankAccount bankAccount);

    /**
     * Returns the BankAccount
     */
    ReadOnlyBankAccount getBankAccount();

    /**
     * Returns true if the model has previous bank account states to restore.
     */
    boolean canUndoBankAccount();

    /**
     * Restores the model's bank account to its previous state.
     */
    void undoBankAccount();

    /**
     * Returns true if the model has undone bank account states to restore.
     */
    boolean canRedoBankAccount();

    /**
     * Restores the model's bank account to its previously undone state.
     */
    void redoBankAccount();

    /**
     * Saves the current bank account state for undo/redo.
     */
    void commitBankAccount();

    /**
     * Replaces the existing transaction history in the bank account
     * with {@code transactionHistory}.
     * @param transactionHistory
     */
    void setTransactions(List<BankAccountOperation> transactionHistory);

    /**
     * Returns true if a transaction with the same identity as {@code transaction} exists in the bank account.
     * @param transaction
     */
    boolean hasTransaction(BankAccountOperation transaction);

    /**
     * Deletes the given transaction.
     * The transaction must exist in the bank account.
     * @param transaction
     */
    void deleteTransaction(BankAccountOperation transaction);

    /**
     * Replaces the given transaction {@code target} with {@code editedTransaction}.
     * {@code target} must exist in the bank account.
     * The transaction identity of {@code editedTransaction} must not be
     * the same as another existing transaction in the bank account.
     */
    void setTransaction(BankAccountOperation transactionTarget, BankAccountOperation transactionEdit);

    /**
     * Adds the given transaction.
     * {@code transaction} must not already exist in the bank account.
     * @param operation
     */
    void handleOperation(BankAccountOperation operation);

    void handleOperation(LedgerOperation operation);

    /**
     * Adds the given budget.
     * {@code budget} must not already exist in the bank account.
     */
    void addBudget(Budget budget);

    /**
     * Adds the given budget.
     * {@code budget} must not already exist in the bank account.
     */
    void addTransaction(BankAccountOperation bankAccountOperation);

    /**
     * Returns an unmodifiable view of the filtered transaction list
     * @return
     */
    FilteredList<BankAccountOperation> getFilteredTransactionList();

    /**
     * Updates the filter of the filtered transaction list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredTransactionList(Predicate<BankAccountOperation> predicate);


    /** Returns an unmodifiable view of the filtered budget list
     */
    ObservableList<Budget> getFilteredBudgetList();

    /**
     * Deletes the given budget.
     * The budget must exist in the bank account.
     * @param Budget
     */
    void deleteBudget(Budget budgetToDelete);
}
