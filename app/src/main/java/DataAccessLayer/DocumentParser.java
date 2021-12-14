package DataAccessLayer;

import android.util.Log;

import com.google.firebase.firestore.DocumentSnapshot;

import java.util.List;

import BusinessEntities.Restaurant;

public class DocumentParser {

    private static final String TAGStartParsing = "--- PARSING ---";
    private static final String TAGFinishParsing = "--- Finished PARSING ---";
    private static final String TAGDocSnap = "DocSnap: ";



    public static List<Restaurant> parseRestaurantFromDocs(List<DocumentSnapshot> documentSnapshots) {

//                for(DocumentSnapshot d : documentSnapshots){
//            Restaurant restaurant = d.toObject(Restaurant.class);
//            restaurants.add(restaurant);
//        }
        Restaurant restaurant;
        for (DocumentSnapshot ds : documentSnapshots) {
            Log.e(TAGDocSnap, ds.toString());
            Log.e(TAGDocSnap, ds.get("branches").toString());
            Log.e(TAGDocSnap, ds.get("id").toString());
            Log.e(TAGDocSnap, ds.get("name").toString());

        }
        return null;
    }
}
