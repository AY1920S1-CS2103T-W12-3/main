package seedu.address.model;

import java.util.Comparator;

import javafx.collections.ObservableList;
import seedu.address.model.transaction.Amount;
import seedu.address.model.transaction.LedgerOperation;

/**
 * Unmodifiable view of a ledger
 */
public interface ReadOnlyLedger {

    /**
     * Returns an unmodifiable view of the transaction list.
     * This list will not contain any duplicate transactions.
     */
    ObservableList<LedgerOperation> getLoanHistory();

    /**
     * Returns an unmodifiable view of the sorted transaction list.
     * This list will not contain any duplicate transactions
     */
    ObservableList<LedgerOperation> getSortedLoanHistory(Comparator<LedgerOperation> t);

    Amount getBalance();
}
