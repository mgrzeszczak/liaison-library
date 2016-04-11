package pl.mg.liaison.filter;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Maciej on 11.04.2016.
 */
/**
 * Filter specifying return type of the function
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Returns {

    /**
     * Which return types to allow, empty means all are allowed
     * void.class means nothing is returned
     * @return
     */
    Class<?>[] allow() default {};

    /**
     * Which return types to disallow, empty means none are disallowed
     * void.class means nothing is returned
     * @return
     */
    Class<?>[] disallow() default {};

}
