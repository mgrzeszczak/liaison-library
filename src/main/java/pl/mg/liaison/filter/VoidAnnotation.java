package pl.mg.liaison.filter;

import java.lang.annotation.Annotation;

/**
 * Created by Maciej on 11.04.2016.
 */
public final class VoidAnnotation implements Annotation {

    private VoidAnnotation(){
        throw new AssertionError("No instances.");
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return VoidAnnotation.class;
    }

}
