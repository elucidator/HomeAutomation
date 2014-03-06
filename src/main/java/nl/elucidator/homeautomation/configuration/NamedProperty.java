package nl.elucidator.homeautomation.configuration;

import javax.enterprise.util.Nonbinding;
import javax.inject.Qualifier;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by pieter on 3/5/14.
 */
@Retention(RUNTIME)
@Target({FIELD, METHOD})
@Qualifier
public @interface NamedProperty {
    /**
     * Bundle key
     *
     * @return a valid bundle key or ""
     */
    @Nonbinding String key() default "";

    /**
     * Is it a mandatory property
     *
     * @return true if mandator
     */
    @Nonbinding boolean mandatory() default false;

    /**
     * Default value if not provided
     *
     * @return default value or ""
     */
    @Nonbinding String defaultValue() default "";
}
