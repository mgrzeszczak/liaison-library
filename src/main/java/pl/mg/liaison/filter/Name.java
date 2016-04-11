package pl.mg.liaison.filter;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Maciej on 11.04.2016.
 */

/**
 * Filter specifying name of the function
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Name {

    /**
     * Regular expression based on which methods are matched
     * @return
     */
    String regex();

}
