package io.github.f4pl0.config;

import lombok.SneakyThrows;

import java.lang.reflect.Field;

/**
 * The ConfigInjector is used to inject the IEXCloudConfig into the IEXHttpClient.
 * @hidden This class is not part of the public API. <b>DO NOT USE</b>.
 */
public class ConfigInjector {

    @SneakyThrows(IllegalAccessException.class)
    public static void injectIEXConfiguration(Object instance, IEXCloudConfig config) {
        Class<?> clazz = instance.getClass();

        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(IEXConfiguration.class)) {
                field.setAccessible(true);
                field.set(instance, config);
            }
        }
    }
}
