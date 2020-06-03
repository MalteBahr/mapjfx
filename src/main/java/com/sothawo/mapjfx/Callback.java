package com.sothawo.mapjfx;

@FunctionalInterface
public interface Callback<P> {
    void call(P p);
}
