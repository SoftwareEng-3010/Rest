package BusinessEntities;

import android.content.Context;
import android.widget.Toast;

public class Printer<T> {

    public void print(T object, Context context){
        Toast.makeText(context, object.toString(), Toast.LENGTH_LONG).show();
    }
}
