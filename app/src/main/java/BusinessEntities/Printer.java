package BusinessEntities;

import java.util.Collection;

public class Printer<T> {

    public void print(Collection<T> collection){

        for (T i : collection) {
            i.toString();
        }
    }
}
