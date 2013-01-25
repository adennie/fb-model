package com.fizzbuzz.model;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.Serializable;
import java.util.Date;

import com.google.common.base.Equivalence;
import com.google.common.base.Objects;

public abstract class PersistentObject
        implements Serializable {
    private static final long serialVersionUID = 1L;

    private long mId;
    private Date mCreationDate;

    // zero-argument constructor needed for serialization
    public PersistentObject() {
    }

    // creates a dummy object with only an ID, suitable for storing in twig's keycache
    public PersistentObject(final long id) {
        mId = id;
    }

    // all fields provided. Used client-side when loading from SQLite DB and server-side when loading from datatore
    public PersistentObject(final long id,
            final Date creationDate) {
        mId = id;
        mCreationDate = checkNotNull(creationDate, "creationDate");
    }

    public long getId() {
        return mId;
    }

    public void setId(final long id) {
        mId = id;
    }

    public Date getCreationDate() {
        return mCreationDate;
    }

    public void setCreationDate(final Date creationDate) {
        mCreationDate = creationDate;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", getId())
                .add("creation date", getCreationDate())
                .toString();
    }

    @Override
    public boolean equals(final Object o) {
        if ((o == null) || (getClass() != o.getClass())) {
            return false;
        }

        PersistentObject other = (PersistentObject) o;
        // note: due to the way we store dates in SQLite, there is a loss of precision (milliseconds -> seconds) in the
        // date stored in SQLite, relative to the date we see in objects fetched over the wire from the server. So, when
        // comparing two identical objects, one coming from the server over the wire and one stored in SQLite, they will
        // seem to have different date values due to the loss of precision in the one read from SQLite. To work around
        // this, divide by 1000 before comparing.

        return Objects.equal(getId(), other.getId()) &&
                Objects.equal(getCreationDate().getTime() / 1000, other.getCreationDate().getTime() / 1000);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId(), getCreationDate());
    }

    public static class IdEquivalence<T extends PersistentObject>
            extends Equivalence<T> {

        @Override
        public boolean doEquivalent(final T a,
                final T b) {
            return Objects.equal(a.getId(), b.getId());
        }

        @Override
        public int doHash(final T item) {
            return Objects.hashCode(item.getId());
        }
    }

}