package com.hoangdh.doctor_app.common.interfaces;

public abstract class StrategyFactory<T> {
	abstract public T getStrategy(String strategy);
}
