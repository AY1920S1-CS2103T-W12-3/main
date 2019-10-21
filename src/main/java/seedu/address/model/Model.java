package seedu.address.model;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.transaction.Budget;
import seedu.address.model.transaction.SplitTransaction;
import seedu.address.model.transaction.Transaction;

/**
 * The API of the Model component.
 */
public interface Model {
    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Transaction> PREDICATE_SHOW_ALL_TRANSACTIONS = unused -> true;

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
     */
    void setTransactions(List<Transaction> transactionHistory);

    /**
     * Returns true if a transaction with the same identity as {@code transaction} exists in the bank account.
     */
    boolean hasTransaction(Transaction transaction);

    /**
     * Deletes the given transaction.
     * The transaction must exist in the bank account.
     */
    void deleteTransaction(Transaction transaction);

    /**
     * Replaces the given transaction {@code target} with {@code editedTransaction}.
     * {@code target} must exist in the bank account.
     * The transaction identity of {@code editedTransaction} must not be
     * the same as another existing transaction in the bank account.
     */
    void setTransaction(Transaction target, Transaction editedTransaction);

    /**
     * Adds the given transaction.
     * {@code transaction} must not already exist in the bank account.
     */
    void addTransaction(Transaction transaction);

    /**
     * Adds the given budget.
     * {@code budget} must not already exist in the bank account.
     */
    void addBudget(Budget budget);

    /**
     * Returns an unmodifiable view of the filtered transaction list
     */
    ObservableList<Transaction> getFilteredTransactionList();

    /**
     * Updates the filter of the filtered transaction list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredTransactionList(Predicate<Transaction> predicate);

    void addSplit(SplitTransaction transaction);

    /** Returns an unmodifiable view of the filtered budget list
     */
    ObservableList<Budget> getFilteredBudgetList();

}
