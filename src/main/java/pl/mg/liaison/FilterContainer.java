package pl.mg.liaison;

import pl.mg.liaison.filter.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by Maciej on 11.04.2016.
 */
final class FilterContainer {

    private final Name name;
    private final Parameters parameters;
    private final Returns returns;
    private final Throws _throws;
    private final Annotations annotations;

    private final Pattern pattern;

    private final List<Class<?>> allowedThrowables;
    private final List<Class<?>> disallowedThrowables;

    private final List<Class<?>> allowedAnnotations;
    private final List<Class<?>> disallowedAnnotations;

    private final List<Class<?>> allowedParameters;
    private final List<Class<?>> disallowedParameters;

    private final List<Class<?>> allowedReturnTypes;
    private final List<Class<?>> disallowedReturnTypes;


    public FilterContainer(final Filter filter){
        this.name = filter.name();
        this.parameters = filter.parameters();
        this._throws = filter._throws();
        this.returns = filter.returns();
        this.annotations = filter.annotations();
        this.pattern = Pattern.compile(name.regex());

        Class<? extends Throwable>[] allowT = _throws.allow();
        Class<? extends Throwable>[] disallowT = _throws.disallow();
        this.allowedThrowables = new ArrayList<Class<?>>(allowT.length);
        this.disallowedThrowables = new ArrayList<Class<?>>(disallowT.length);
        for (int i=0;i<allowT.length;i++) allowedThrowables.add(allowT[i]);
        for (int i=0;i<disallowT.length;i++) allowedThrowables.add(disallowT[i]);

        Class<? extends Annotation>[] allowA = annotations.allow();
        Class<? extends Annotation>[] disallowA = annotations.disallow();
        this.allowedAnnotations = new ArrayList<Class<?>>(allowA.length);
        this.disallowedAnnotations = new ArrayList<Class<?>>(disallowA.length);
        for (int i=0;i<allowA.length;i++) allowedAnnotations.add(allowA[i]);
        for (int i=0;i<disallowA.length;i++) disallowedAnnotations.add(disallowA[i]);

        this.allowedParameters = Arrays.asList(parameters.allow());
        this.disallowedParameters = Arrays.asList(parameters.disallow());
        this.allowedReturnTypes = Arrays.asList(returns.allow());
        this.disallowedReturnTypes = Arrays.asList(returns.disallow());
    }

    public boolean eligible(Method method){
        boolean eligible = filterName(method);
        eligible = eligible && filterReturns(method);
        eligible = eligible && filterThrowables(method);
        eligible = eligible && filterAnnotations(method);
        eligible = eligible && filterParameters(method);
        return eligible;
    }

    private boolean filterName(Method method){
        return pattern.matcher(method.getName()).matches();
    }
    private boolean filterReturns(Method method){
        Class<?> returnType = method.getReturnType();
        Class<?>[] allow = returns.allow();
        Class<?>[] disallow = returns.disallow();

        boolean eligible = false;
        if (allow.length!=0) {
            for (int i = 0, size = allow.length; i < size; i++)
                if (allow[i].equals(returnType)) {
                    eligible = true;
                    break;
                }
        }
        else eligible = true;
        if (eligible == false) return false;
        for (int i=0,size=disallow.length;i<size;i++){
            if (disallow[i].equals(returnType)){
                eligible = false;
                break;
            }
        }
        return eligible;
    }
    private boolean filterThrowables(Method method){
        Class<?>[] throwables = method.getExceptionTypes().length==0? new Class<?>[]{VoidThrowable.class} : method.getExceptionTypes();
        List<Class<?>> methodThrowables = Arrays.asList(throwables);
        boolean eligible = false;
        if (allowedThrowables.size()!=0){
            eligible = allowedThrowables.containsAll(methodThrowables);
        } else eligible = true;
        if (eligible==false) return false;
        if (disallowedThrowables.size()!=0){
            for (Class<?> c : disallowedThrowables){
                if (methodThrowables.contains(c)) return false;
            }
        }
        return eligible;
    }
    private boolean filterAnnotations(Method method){
        Class<?>[] annotations = method.getAnnotations().length==0? new Class<?>[]{VoidAnnotation.class} : method.getExceptionTypes();
        List<Class<?>> methodAnnotations = Arrays.asList(annotations);
        boolean eligible = false;
        if (allowedAnnotations.size()!=0){
            eligible = allowedAnnotations.containsAll(methodAnnotations);
        } else eligible = true;
        if (eligible==false) return false;
        if (disallowedAnnotations.size()!=0){
            for (Class<?> c : disallowedAnnotations){
                if (methodAnnotations.contains(c)) return false;
            }
        }
        return eligible;
    }
    private boolean filterParameters(Method method){
        Class<?>[] parameters = method.getParameterTypes().length==0? new Class<?>[]{void.class} : method.getExceptionTypes();
        List<Class<?>> methodParameters = Arrays.asList(parameters);

        if (method.getParameterTypes().length<this.parameters.minCount() || method.getParameterTypes().length>this.parameters.maxCount()) return false;

        boolean eligible = false;
        if (allowedParameters.size()!=0){
            eligible = allowedParameters.containsAll(methodParameters);
        } else eligible = true;
        if (eligible==false) return false;
        if (disallowedParameters.size()!=0){
            for (Class<?> c : disallowedParameters){
                if (methodParameters.contains(c)) return false;
            }
        }
        return eligible;
    }

}
