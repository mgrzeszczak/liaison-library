package pl.mg.liaison;

import pl.mg.liaison.filter.Filter;
import pl.mg.liaison.filter.Filters;
import pl.mg.liaison.filter.Name;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;
import java.util.regex.Pattern;
/**
 * Created by Maciej on 10.04.2016.
 */
final class FilterProcessor {

    private final List<FilterContainer> filters;
    private Map<Method,Boolean> cache = new HashMap<>();

    public FilterProcessor(final Filters filters) {
        this.filters = new ArrayList<FilterContainer>();
        for (Filter filter : filters.value()){
           this.filters.add(new FilterContainer(filter));
        }
    }

    public boolean eligible(Method method) {
        if (cache.containsKey(method)) return cache.get(method);
        boolean eligible = false;
        for (int i=0,size=filters.size();i<size;i++)
            if (filters.get(i).eligible(method)) {
                eligible = true;
                break;
            }
        cache.put(method,eligible);
        return eligible;
    }

    /*private final Filter filter;

    private final List<Class<?>> allowedReturnTypes;
    private final List<Class<?>> notAllowedReturnTypes;

    private final boolean hasParameters;
    private final List<Class<?>> allowedParameterTypes;
    private final List<Class<?>> notAllowedParameterTypes;
    private final List<Class<?>> specifiedParameterTypes;

    private final List<String> allowedParameterNames;
    private final List<String> notAllowedParameterNames;
    private final List<String> specifiedParameterNames;

    private final boolean hasAnnotations;
    private final List<Class<? extends Annotation>> allowedAnnotations;
    private final List<Class<? extends Annotation>> notAllowedAnnotations;
    private final List<Class<? extends Annotation>> specifiedAnnotations;

    private final boolean hasThrowables;
    private final List<Class<? extends Throwable>> allowedThrowables;
    private final List<Class<? extends Throwable>> notAllowedThrowables;
    private final List<Class<? extends Throwable>> specifiedThrowables;

    private final Pattern methodNamePattern;

    public FilterProcessor(Filter filter){
        this.filter = filter;
        this.hasParameters = filter.hasParameters();
        this.hasThrowables = filter.hasThrowables();
        this.hasAnnotations = filter.hasAnnotations();
        this.allowedReturnTypes = Arrays.asList(filter.returning());
        this.notAllowedReturnTypes = Arrays.asList(filter.notReturning());
        this.methodNamePattern = Pattern.compile(filter.nameRegex());

        this.allowedParameterNames = Arrays.asList(filter.includeParameterNames());
        this.notAllowedParameterNames = Arrays.asList(filter.excludeParameterNames());
        this.specifiedParameterNames = Arrays.asList(filter.parameterNames());

        this.allowedParameterTypes = Arrays.asList(filter.includeParameterTypes());
        this.notAllowedParameterTypes = Arrays.asList(filter.excludeParameterTypes());
        this.specifiedParameterTypes = Arrays.asList(filter.parameterTypes());

        this.allowedAnnotations = Arrays.asList(filter.includeAnnotations());
        this.notAllowedAnnotations = Arrays.asList(filter.excludeAnnotations());
        this.specifiedAnnotations = Arrays.asList(filter.annotations());

        this.allowedThrowables = Arrays.asList(filter.includeThrowables());
        this.notAllowedThrowables = Arrays.asList(filter.excludeThrowables());
        this.specifiedThrowables = Arrays.asList(filter.throwables());
    }

    public boolean isEligible(Method method){
        return runFilters(method);
    }

    private boolean runFilters(Method method) {
        boolean eligible = true;
        //eligible = eligible && filterHasAnnotations(method);
        //eligible = eligible && filterHasThrowables(method);
        //eligible = eligible && filterHasParameters(method);
        eligible = eligible && filterName(method);
        eligible = eligible && filterReturnType(method);
        eligible = eligible && filterParameterTypes(method);

        //eligible = eligible && filterParameterNames(method);
        eligible = eligible && filterAnnotations(method);
        eligible = eligible && filterThrowables(method);
        return eligible;
    }

    private boolean filterHasAnnotations(Method method){
        return method.getAnnotations().length==0;
    }
    private boolean filterHasThrowables(Method method){
        return method.getExceptionTypes().length==0;
    }
    private boolean filterHasParameters(Method method){
        return method.getParameterTypes().length==0;
    }

    private boolean filterName(Method method){
        String name = method.getName();
        return methodNamePattern.matcher(name).matches();
    }

    private boolean filterReturnType(Method method){
        Class<?> returnType = method.getReturnType();
        if (allowedReturnTypes.size()==0) return !notAllowedReturnTypes.contains(returnType);
        return allowedReturnTypes.contains(returnType) && !notAllowedReturnTypes.contains(returnType);
    }

    private boolean filterParameterTypes(Method method){
        List<Class<?>> parameterTypes = Arrays.asList(method.getParameterTypes());
        if (specifiedParameterTypes.size()!=0){
            return specifiedParameterTypes.size()==parameterTypes.size() && specifiedParameterTypes.containsAll(parameterTypes);
        }
        if (allowedParameterTypes.size()==0) {
            for(Class<?> parameterType : parameterTypes) if (notAllowedParameterTypes.contains(parameterType)){
                return false;
            }
            return true;
        }
        for (Class<?> parameterType : parameterTypes){
            if (notAllowedParameterTypes.contains(parameterType) || !allowedParameterTypes.contains(parameterType)) return false;
        }
        return true;
    }

    private boolean filterParameterNames(Method method){
        List<String> parameterNames = new ArrayList<String>();
        for(Parameter parameter : method.getParameters()) {
            parameterNames.add(parameter.getName());
            System.out.println(parameter.getName());
        }
        if (specifiedParameterNames.size()!=0){
            return specifiedParameterNames.size() == parameterNames.size() && specifiedParameterNames.containsAll(parameterNames);
        }
        if (allowedParameterNames.size()==0) {
            for(String parameterName : parameterNames) if (notAllowedParameterNames.contains(parameterName)){
                return false;
            }
            return true;
        }
        for (String parameterName : parameterNames){
            if (notAllowedParameterNames.contains(parameterName) || !allowedParameterNames.contains(parameterName)) return false;
        }
        return true;
    }

    private boolean filterAnnotations(Method method){
        List<Class<? extends Annotation>> annotations = new ArrayList<Class<? extends Annotation>>();
        for (Annotation annotation : method.getAnnotations()) annotations.add(annotation.getClass());
        if (specifiedAnnotations.size()!=0){
            return specifiedAnnotations.size() == annotations.size() && specifiedAnnotations.containsAll(annotations);
        }
        if (allowedAnnotations.size()==0){
            for (Class<? extends Annotation> annotation : annotations) if (notAllowedAnnotations.contains(annotation)){
                return false;
            }
            return true;
        }
        for (Class<? extends Annotation> annotation : annotations) {
            if (notAllowedAnnotations.contains(annotation) || !allowedAnnotations.contains(annotation)) return false;
        }
        return true;
    }

    // todo: fix throwables
    // todo: think about extending classes and superclasses regarding filter exclusion
    // todo: test all
    private boolean filterThrowables(Method method){
        List<Class<? extends Throwable>> throwables = (List<Class<? extends Throwable>>) Arrays.asList(method.getExceptionTypes());
        if (specifiedThrowables.size()!=0){
            return specifiedThrowables.contains(throwables);
        }
        if (allowedThrowables.size()==0){
            for (Class<? extends Throwable> throwable : throwables) if (notAllowedThrowables.contains(throwable)){
                return false;
            }
            return true;
        }
        for (Class<? extends Throwable> throwable : throwables) {
            if (notAllowedThrowables.contains(throwable) || !allowedThrowables.contains(throwable)) return false;
        }
        return true;
    }*/

}
