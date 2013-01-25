package com.fizzbuzz.model;

import java.util.Collection;

import com.google.common.base.Equivalence;
import com.google.common.base.Objects;

public class Models {

    // this is just a collection of static methods. Make the constructor private to prevent instantiation.
    private Models() {
    }

    // Given a collection of old things and a collection of new things, divide the new things into three categories:
    // things identical to an old thing, things which represent the same object as an old thing but which have been
    // modified, and things that don't match an old thing at all.
    // Note: this version doesn't detect things deleted from oldThings. See filterChanges2 (below) for that.

    public static <E> void filterChanges(
            final Collection<E> oldThings,
            final Collection<E> newThings,
            final Collection<E> sameThings,
            final Collection<E> addedThings,
            final Collection<E> changedThings,
            final Equivalence<E> equiv) {

        addedThings.addAll(newThings);
        for (E newThing : newThings) {
            for (E oldThing : oldThings) {
                if (Objects.equal(oldThing, newThing)) {
                    sameThings.add(newThing);
                    addedThings.remove(newThing);
                    break;
                }
                else if (equiv.equivalent(oldThing, newThing)) {
                    changedThings.add(newThing);
                    addedThings.remove(newThing);
                    break;
                }
            }
        }
    }

    // like filterChanges, but also detects old things that don't match any of the new things.
    public static <E> void filterChanges2(
            final Collection<E> oldThings,
            final Collection<E> newThings,
            final Collection<E> sameThings,
            final Collection<E> addedThings,
            final Collection<E> changedThings,
            final Collection<E> deletedThings,
            final Equivalence<E> equiv) {

        addedThings.addAll(newThings);
        for (E oldThing : oldThings) {
            boolean foundInNewThings = false;
            for (E newThing : newThings) {
                if (Objects.equal(oldThing, newThing)) {
                    foundInNewThings = true;
                    sameThings.add(newThing);
                    addedThings.remove(newThing);
                    break;
                }
                else if (equiv.equivalent(oldThing, newThing)) {
                    foundInNewThings = true;
                    changedThings.add(newThing);
                    addedThings.remove(newThing);
                    break;
                }
            }
            if (!foundInNewThings)
                deletedThings.add(oldThing);
        }
    }
}
