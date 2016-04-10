package pl.mg.liaison;

import java.lang.annotation.*;

/**
 * Created by Maciej on 10.04.2016.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Filter {

    Class<?>[] returning() default {};
    Class<?>[] notReturning() default {};

    boolean hasParameters() default true;

    Class<?>[] includeParameterTypes() default {};
    Class<?>[] parameterTypes() default {};
    Class<?>[] excludeParameterTypes() default {};

    String[] includeParameterNames() default {};
    String[] parameterNames() default {};
    String[] excludeParameterNames() default {};

    boolean hasAnnotations() default true;
    Class<? extends Annotation>[] includeAnnotations() default {};
    Class<? extends Annotation>[] annotations() default {};
    Class<? extends Annotation>[] excludeAnnotations() default {};

    String nameRegex() default ".*";

    boolean hasThrowables() default true;
    Class<? extends Throwable>[] includeThrowables() default {};
    Class<? extends Throwable>[] throwables() default {};
    Class<? extends Throwable>[] excludeThrowables() default {};

}
