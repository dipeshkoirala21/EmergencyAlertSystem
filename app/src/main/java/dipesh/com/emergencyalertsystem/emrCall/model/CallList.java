
package dipesh.com.emergencyalertsystem.emrCall.model;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CallList implements Serializable {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("call_log")
    @Expose
    private List<CallLog> callLog = null;

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public List<CallLog> getCallLog() {
        return callLog;
    }

    public void setCallLog(List<CallLog> callLog) {
        this.callLog = callLog;
    }

}
