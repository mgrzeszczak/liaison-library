package pl.mg.liaison.filter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Maciej on 11.04.2016.
 *
 * Provides classes implementing Liaison interface with instructions
 * which methods to modify.
 *
 * Consists of at least one filter, results of all filters are joined and
 * any method that is contained in such a set will be affected.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Filters {

    Filter[] value() default {@Filter()};

}
