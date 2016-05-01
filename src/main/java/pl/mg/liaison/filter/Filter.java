package pl.mg.liaison.filter;

import java.lang.annotation.*;

/**
 * Created by Maciej on 10.04.2016.
 */

/**
 * Specifies a single set of rules that will filter the methods.
 * You can specify many filters in @Filters annotation, to target unrelated methods.
 * For example to target two specific methods, annotate your Liaison implementation
 * with @Filters containing two @Filter annotation with specified @Name.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Filter {

    // TODO: parameter type annotations, specify parameters

    Name name() default @Name(regex = ".*");
    Returns returns() default @Returns(allow = {},disallow = {});
    Throws _throws() default @Throws(allow = {},disallow = {});
    Parameters parameters() default @Parameters(allow = {},disallow = {});
    Annotations annotations() default @Annotations(allow = {},disallow = {});

}
