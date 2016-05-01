package pl.mg.liaison;

import pl.mg.liaison.filter.Filter;
import pl.mg.liaison.filter.Filters;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Created by Maciej on 10.04.2016.
 */
final class FilterProcessor {

    private final List<FilterContainer> filters;
    private Map<Method,Boolean> cache = new HashMap<Method,Boolean>();

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

}
