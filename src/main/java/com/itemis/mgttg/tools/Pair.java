package com.itemis.mgttg.tools;

import java.util.Objects;

public class Pair<A,B> {
    private A left;
    private B right;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return left.equals(pair.left) && right.equals(pair.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right);
    }

    /**
     * Generates a new immutable Pair of two Objects
     * @param left First Object
     * @param right Second Object
     */
    public Pair(A left, B right){
        this.left=left;
        this.right=right;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "left=" + left +
                ", right=" + right +
                '}';
    }

    public A getLeft() {
        return left;
    }

    public B getRight() {
        return right;
    }
}
