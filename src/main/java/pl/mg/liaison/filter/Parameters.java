package pl.mg.liaison.filter;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Maciej on 11.04.2016.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Parameters {

    /**
     * Specifies which parameter types are allowed
     * void.class means method takes no parameters
     * @return
     */
    Class<?>[] allow()  default {};

    /**
     * Specifies which parameter types are not allowed
     * void.class means method takes no parameters
     * @return
     */
    Class<?>[] disallow()  default {};

    /**
     * Specifies an exact list of parameters that must be matched ( in the same * order ). Ignored if empty. If specified, other fields of this
     * annotation are ignored.
     * @return
     */
    Class<?>[] exact() default {};

    int minCount() default 0;
    int maxCount() default Integer.MAX_VALUE;

}
