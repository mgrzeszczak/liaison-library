package pl.mg.liaison.testing.parameters;

import pl.mg.liaison.Liaison;
import pl.mg.liaison.LiaisonContext;
import pl.mg.liaison.filter.Filter;
import pl.mg.liaison.filter.Filters;
import pl.mg.liaison.filter.Parameters;

import java.util.List;

/**
 * Created by maciej on 01.05.16.
 */
@Filters(value = {
        @Filter(parameters = @Parameters(minCount = 0,maxCount = 3,exact = {String.class,int.class,double.class})),
        @Filter(parameters = @Parameters(allow = {String.class})),
        @Filter(parameters = @Parameters(disallow = {String.class,List.class}))
})
public class LiaisonClass implements Liaison {
    @Override
    public Object call(LiaisonContext context) throws Throwable {
        System.out.println("Calling "+context.getMethodName());
        return context.call();
    }
}
