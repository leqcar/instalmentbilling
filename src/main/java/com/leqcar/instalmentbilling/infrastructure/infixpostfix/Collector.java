package com.leqcar.instalmentbilling.infrastructure.infixpostfix;

import java.util.Collection;

public interface Collector<K, V> {
    Collection<V> get();

    V add(K key);
}
