package com.leqcar.instalmentbilling.domain.service;

public interface InstalmentProcessor<T> {

	void process(T t);
}
