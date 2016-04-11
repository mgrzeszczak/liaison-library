package pl.mg.liaison.filter;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Maciej on 11.04.2016.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Annotations {

    /**
     * Specifies which annotation types are allowed
     * VoidAnnotation.class means method has no annotations
     * @return
     */
    Class<? extends Annotation>[] allow() default {};

    /**
     * Specifies which annotation types are not allowed
     * VoidAnnotation.class means method has no annotations
     * @return
     */
    Class<? extends Annotation>[] disallow() default {};

}
