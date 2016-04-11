package pl.mg.liaison.filter;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Maciej on 11.04.2016.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Throws {

    /**
     * Which throwable types to allow, empty means all are allowed
     * VoidThrowable.class means method doesn't throw any throwables
     * @return
     */
    Class<? extends Throwable>[] allow() default {};

    /**
     * Which throwable types to disallow, empty means none are disallowed
     * VoidThrowable.class means method doesn't throw any throwables
     * @return
     */
    Class<? extends Throwable>[] disallow() default {};

}
