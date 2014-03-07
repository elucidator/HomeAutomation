package nl.elucidator.homeautomation.configuration;

import javax.enterprise.util.Nonbinding;
import javax.inject.Qualifier;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Annotation for Properties that are injected by the CDI container
 */
@Retention(RUNTIME)
@Target({FIELD, METHOD})
@Qualifier
public @interface NamedProperty {
    /**
     * key or name of the property
     *
     * @return a valid key or ""
     */
    @Nonbinding String key() default "";

    /**
     * Is it a mandatory property. When key is not found in the configuration it
     * throws a {{@link java.lang.IllegalArgumentException}}
     *
     * @return true if mandatory
     */
    @Nonbinding boolean mandatory() default false;

    /**
     * Default value if not provided
     *
     * @return default value or ""
     */
    @Nonbinding String defaultValue() default "";
}
