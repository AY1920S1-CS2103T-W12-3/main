package seedu.address.model.transaction;

import seedu.address.model.person.UniquePersonList;
import seedu.address.model.util.Date;

public interface LedgerOperation {
    Amount handleBalance(Amount balance, UniquePersonList peopleInLedger);

    boolean isSameTransaction(BankAccountOperation transaction);

    Date getDate();

    Amount getAmount();
}
