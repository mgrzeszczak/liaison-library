package pl.mg.liaison;


/**
 * Created by Maciej on 10.04.2016.
 */
public interface Liaison {

    Object call(LiaisonContext context) throws Throwable;

}
