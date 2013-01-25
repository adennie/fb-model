package com.fizzbuzz.model;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Date;

import com.google.common.base.Objects;

public abstract class SynchronizedObject
        extends PersistentObject {
    private static final long serialVersionUID = 1L;

    private TickStamp mTickStamp;

    // zero-argument constructor needed for serialization
    public SynchronizedObject() {
    }

    // creates a dummy object with only an ID, suitable for storing in twig's keycache
    public SynchronizedObject(final long id) {
        super(id);
        mTickStamp = new TickStamp();
    }

    // all fields provided. Used client-side when loading from SQLite DB and server-side when loading from datastore
    public SynchronizedObject(final long id,
            final Date creationDate,
            final TickStamp tickStamp) {
        super(id, creationDate);
        mTickStamp = checkNotNull(tickStamp, "tickStamp");
    }

    public TickStamp getTickStamp() {
        return mTickStamp;
    }

    public void setTickStamp(final TickStamp tickStamp) {
        mTickStamp = checkNotNull(tickStamp, "tickStamp");
    }

    @Override
    public String toString() {
        return super.toString() + Objects.toStringHelper(this)
                .add("tickstamp", getTickStamp())
                .toString();
    }

    @Override
    public boolean equals(final Object o) {
        if ((o == null) || (getClass() != o.getClass())) {
            return false;
        }

        SynchronizedObject other = (SynchronizedObject) o;
        return super.equals(other) &&
                Objects.equal(getTickStamp(), other.getTickStamp());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), getTickStamp());
    }

}