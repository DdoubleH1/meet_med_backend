package com.hoangdh.doctor_app.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface PermissionsAllowed {
	String value() default "";

	String[] permissions() default {};

	DecisionStrategy decisionStrategy() default DecisionStrategy.ANY;

	enum DecisionStrategy {
		ALL,
		ANY
	}
}
