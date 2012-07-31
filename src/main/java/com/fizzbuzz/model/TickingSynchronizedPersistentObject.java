package com.fizzbuzz.model;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.Serializable;
import java.util.Date;

import com.google.common.base.Objects;

public class TickingSynchronizedPersistentObject
        extends SynchronizedObject
        implements Serializable {
    private static final long serialVersionUID = 1L;

    private TickStamp mTicker;

    // zero-argument constructor needed for serialization
    public TickingSynchronizedPersistentObject() {
        init(new TickStamp());
    }

    // creates a dummy object with only an ID, suitable for storing in twig's keycache
    public TickingSynchronizedPersistentObject(final long id) {
        super(id);
    }

    // all non-generated fields provided. used to create new objects prior to persisting them.
    // NOTE: no separate ctor needed for this case - no arg version suffices

    // all fields provided. Used client-side when loading from SQLite DB and server-side when
    // loading from datastore
    public TickingSynchronizedPersistentObject(final long id, final Date creationDate, final TickStamp tickStamp,
            final TickStamp ticker) {
        super(id, creationDate, tickStamp);
        init(ticker);
    }

    private void init(final TickStamp ticker) {
        setTicker(ticker);
    }

    public TickStamp getTicker() {
        return mTicker;
    }

    public void setTicker(final TickStamp ticker) {
        mTicker = checkNotNull(ticker, "ticker");
    }

    @Override
    public String toString() {
        return super.toString() + Objects.toStringHelper(this)

        .add("ticker", getTicker())
                .toString();
    }

    @Override
    public boolean equals(final Object o) {
        if ((o == null) || (getClass() != o.getClass())) {
            return false;
        }

        TickingSynchronizedPersistentObject other = (TickingSynchronizedPersistentObject) o;
        return super.equals(other) &&

        Objects.equal(getTicker(), other.getTicker());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), getTicker());
    }
}
