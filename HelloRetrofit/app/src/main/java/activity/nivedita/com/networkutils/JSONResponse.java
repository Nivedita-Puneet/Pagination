package activity.nivedita.com.networkutils;

import com.google.gson.annotations.SerializedName;

/**
 * Created by NEETU on 05-03-2018.
 */

/*Class which de serializes JSON response*/
public class JSONResponse {

    @SerializedName("status")
    public String status;

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    @SuppressWarnings({"unused", "used by Retrofit"})
    public JSONResponse() {
    }

    public JSONResponse(String status) {
        this.status = status;
    }

}
