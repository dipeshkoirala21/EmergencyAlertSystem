
package dipesh.com.emergencyalertsystem.Call;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Number {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("eId")
    @Expose
    private Integer eId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("contact")
    @Expose
    private String contact;
    @SerializedName("service_name")
    @Expose
    private String serviceName;
    @SerializedName("address")
    @Expose
    private String address;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Number() {
    }

    /**
     * 
     * @param id
     * @param title
     * @param address
     * @param eId
     * @param contact
     * @param serviceName
     */
    public Number(Integer id, Integer eId, String title, String contact, String serviceName, String address) {
        super();
        this.id = id;
        this.eId = eId;
        this.title = title;
        this.contact = contact;
        this.serviceName = serviceName;
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEId() {
        return eId;
    }

    public void setEId(Integer eId) {
        this.eId = eId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
