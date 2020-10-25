package com.add.ad.domain.base;

public interface Mapper<T,E> {
    public E mapFrom(T from);
}
