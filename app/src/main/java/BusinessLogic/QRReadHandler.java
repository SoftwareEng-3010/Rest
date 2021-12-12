package BusinessLogic;

import android.util.Log;

import com.google.zxing.Result;

import org.json.JSONException;
import org.json.JSONObject;

import DataAccessLayer.RestDB;
import io.grpc.internal.JsonParser;

/**
 * This class handles a decoded read from QRCodeActivity
 */
public class QRReadHandler {

    private final String TAG = "QRReadHandler";
    private final String REST_ID_STRING = "restaurant_id";
    private final String BRANCH_ID_STRING = "branch_id";
    private final String TABLE_NUM_STRING = "table_num";

    private Result result;
    private RestDB restDB;

    public QRReadHandler(Result result){
        this.result = result;
        restDB = RestDB.getInstance();
    }

    public Result getResult() {
        return result;
    }


    public void handleRead(){
        JSONObject json = getJsonObject(result.getText());
        if(json == null){
            // todo: let the calling method know the qrcode is not valid
        }
        else{
            try{
                int restId = json.getInt(REST_ID_STRING);
                int branchId = json.getInt(BRANCH_ID_STRING);
                int tableNum = json.getInt(TABLE_NUM_STRING);
                List<Item> menu = restDB.getMenu(restId, branchId);
                // todo: start new activity to display menu
            }
            catch (Exception e){
                Log.e(TAG, e.getMessage());
            }
        }
    }

    private JSONObject getJsonObject(String jsonString){
        JSONObject json;
        try{
            json = new JSONObject(jsonString);
            return json;
        }
        catch (JSONException e){
            Log.e(TAG, e.getMessage());
            return null;
        }
    }
}
