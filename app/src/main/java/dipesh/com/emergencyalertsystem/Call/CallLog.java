
package dipesh.com.emergencyalertsystem.Call;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CallLog {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("numbers")
    @Expose
    private List<Number> numbers;

    /**
     * No args constructor for use in serialization
     * 
     */
    public CallLog() {
    }

    /**
     * 
     * @param title
     * @param numbers
     */
    public CallLog(String title, List<Number> numbers) {
        super();
        this.title = title;
        this.numbers = numbers;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Number> getNumbers() {
        return numbers;
    }

    public void setNumbers(List<Number> numbers) {
        this.numbers = numbers;
    }

}
