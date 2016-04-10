package pl.mg.liaison.testing;

import pl.mg.liaison.Liaison;
import pl.mg.liaison.Filter;
import pl.mg.liaison.LiaisonContext;
/**
 * Created by Maciej on 10.04.2016.
 */
@Filter(returning = {String.class,int.class})
public class MyLiaison implements Liaison {

    @Override
    public Object call(LiaisonContext context) throws Throwable {
        System.out.println("Liaison works!");
        System.out.println("Method returns: "+context.getReturnType().toString());
        return context.call();
    }
}
