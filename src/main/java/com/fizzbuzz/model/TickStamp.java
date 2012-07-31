package com.fizzbuzz.model;

import java.io.Serializable;

import com.google.common.base.Objects;

public class TickStamp
        implements Serializable, Comparable<TickStamp> {
    private static final long serialVersionUID = 1L;

    private long mTickValue;

    // zero-argument constructor needed for serialization
    public TickStamp() {
    }

    public TickStamp(final long tickValue) {
        mTickValue = tickValue;
    }

    public long getTickValue() {
        return mTickValue;
    }

    public void setTickValue(final long tickValue) {
        mTickValue = tickValue;
    }

    public TickStamp tick() {
        mTickValue++;
        return this;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("tickstamp", getTickValue())
                .toString();
    }

    @Override
    public boolean equals(final Object o) {
        if ((o == null) || (getClass() != o.getClass())) {
            return false;
        }

        TickStamp other = (TickStamp) o;
        return Objects.equal(getTickValue(), other.getTickValue());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getTickValue());
    }

    @Override
    public int compareTo(final TickStamp o) {
        if (mTickValue < o.getTickValue())
            return -1;
        else if (mTickValue > o.getTickValue())
            return 1;
        else
            return 0;
    }

}
