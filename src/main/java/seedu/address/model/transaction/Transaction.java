package seedu.address.model.transaction;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.Date;

/**
 * API of Transaction.
 */
public abstract class Transaction {

    protected Amount amount;
    protected Date date;
    protected Person peopleInvolved;

    // Data fields
    private final Set<Tag> tags = new HashSet<>();

    public Transaction(Amount amount, Date date) {
        this.amount = amount;
        this.date = date;
    }

    public Transaction(Amount amount, Date date, Set<Tag> tags) {
        this(amount, date);
        this.tags.addAll(tags);
    }

    public Transaction(Amount amount, Date date, Person personInvolved) {
        this(amount, date);
        this.peopleInvolved = personInvolved;
    }

    public Transaction(Amount amount, Date date, Set<Tag> tags, Person personInvolved) {
        this(amount, date);
        this.tags.addAll(tags);
        this.peopleInvolved = personInvolved;
    }


    public Amount getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }

    public Person getPeopleInvolved() {
        return peopleInvolved;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public abstract Amount handleBalance(Amount balance);

    /**
     * Returns true if both persons of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSameTransaction(Transaction otherTransaction) {
        if (otherTransaction == this) {
            return true;
        }

        return otherTransaction != null
                && otherTransaction.getAmount().equals(getAmount())
                && otherTransaction.getDate().equals(getDate())
                && otherTransaction.getPeopleInvolved().equals(getPeopleInvolved());
    }

    @Override
    public abstract boolean equals(Object obj);

}
