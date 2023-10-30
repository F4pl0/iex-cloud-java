package io.github.f4pl0.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

/**
 * This annotation is used to inject the IEXConfiguration object into the class
 * @hidden This class is not part of the public API. <b>DO NOT USE</b>.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface IEXConfiguration {
}

