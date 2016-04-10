package pl.mg.liaison;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static com.sun.corba.se.impl.util.RepositoryId.cache;


/**
 * Created by Maciej on 10.04.2016.
 */
final class LiaisonInvocationHandler implements InvocationHandler {

    private Liaison liaison;
    private Class<? extends Liaison> liaisonClass;
    private Object instance;

    private Map<Method,Boolean> eligibilityCache = new HashMap<Method,Boolean>();
    private Map<Method,LiaisonContext> contextCache = new HashMap<Method, LiaisonContext>();
    // todo: cache!
    //private Map<Liaison,Method> cache = new HashMap<Liaison,Method>();

    private FilterProcessor filterProcessor;

    public LiaisonInvocationHandler(Liaison liaison, Class<? extends Liaison> liaisonClass, Object instance) {
        this.liaison = liaison;
        this.liaisonClass = liaisonClass;
        this.instance = instance;
        this.filterProcessor = new FilterProcessor(this.liaisonClass.getAnnotation(Filter.class));
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        LiaisonContext context;
        if (contextCache.containsKey(method)) context = contextCache.get(method);
        else {
            context = new LiaisonContextImpl(method,args,instance);
            contextCache.put(method,context);
        }
        boolean eligible = true;
        if (eligibilityCache.containsKey(method)){
            eligible = eligibilityCache.get(method);
        } else {
            eligible = filterProcessor.isEligible(method);
            cache.put(method,eligible);
        }
        if (eligible) return liaison.call(context);
        else return method.invoke(instance,args);
    }

}
