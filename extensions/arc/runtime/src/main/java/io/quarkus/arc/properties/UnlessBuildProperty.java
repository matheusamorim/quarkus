package io.quarkus.arc.properties;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * When applied to a bean class or producer method (or field), the bean will only be enabled
 * if the Quarkus build time property does not match the provided value.
 * <p>
 * By default, the bean is not enabled when the build time property is not defined at all, but this behavior is configurable
 * via the {#code enableIfMissing} property.
 * <p>
 * This annotation is repeatable. A bean will only be enabled if all of the conditions defined by the
 * {@link UnlessBuildProperty} annotations are satisfied.
 */
@Repeatable(UnlessBuildProperty.List.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.TYPE, ElementType.FIELD })
public @interface UnlessBuildProperty {

    /**
     * Name of the build time property to check
     */
    String name();

    /**
     * The bean is enabled if the build time property (specified by {@code name}) does not match this value.
     */
    String stringValue();

    /**
     * Determines if the bean is enabled when the property name specified by {@code name} has not been specified at all
     */
    boolean enableIfMissing() default false;

    @Retention(RetentionPolicy.RUNTIME)
    @Target({ ElementType.METHOD, ElementType.TYPE, ElementType.FIELD })
    @interface List {

        UnlessBuildProperty[] value();

    }
}
