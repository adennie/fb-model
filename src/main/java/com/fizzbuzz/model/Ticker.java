package com.fizzbuzz.model;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Objects;

public class Ticker
        implements Serializable {
    private static final long serialVersionUID = 1L;

    private final Logger mLogger = LoggerFactory.getLogger(LoggingManager.TAG);

    private TickStamp mTickStamp;

    public Ticker() {
        mTickStamp = new TickStamp();
    }

    public Ticker(final TickStamp tickStamp) {
        mTickStamp = tickStamp;
    }

    public TickStamp getTickStamp() {
        return mTickStamp;
    }

    public void setTickStamp(final TickStamp tickStamp) {
        mTickStamp = tickStamp;
    }

    public void setTickValue(final TickStamp tickValue) {
        mTickStamp = checkNotNull(tickValue, "tickValue");
    }

    public TickStamp tick() {
        TickStamp newTickStamp = mTickStamp.tick();
        mLogger.debug("TickStamp.tick: new tick value={}", newTickStamp.getTickValue());
        return newTickStamp;

    }

    // implement toString, equals, and hashCode
    @Override
    public String toString() {
        return super.toString() + Objects.toStringHelper(this)
                .add("tickStamp", getTickStamp())
                .toString();
    }

    @Override
    public boolean equals(final Object o) {
        if ((o == null) || (getClass() != o.getClass())) {
            return false;
        }

        Ticker other = (Ticker) o;
        return super.equals(other) &&
                Objects.equal(getTickStamp(), other.getTickStamp());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), getTickStamp());
    }
}
