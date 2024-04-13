
package bls;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Series implements Serializable
{

    @SerializedName("seriesID")
    @Expose
    private String seriesID;
    @SerializedName("data")
    @Expose
    private List<Datum> data;
    private final static long serialVersionUID = 7873816151974195110L;

    public String getSeriesID() {
        return seriesID;
    }

    public void setSeriesID(String seriesID) {
        this.seriesID = seriesID;
    }

    public Series withSeriesID(String seriesID) {
        this.seriesID = seriesID;
        return this;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public Series withData(List<Datum> data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Series.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("seriesID");
        sb.append('=');
        sb.append(((this.seriesID == null)?"<null>":this.seriesID));
        sb.append(',');
        sb.append("data");
        sb.append('=');
        sb.append(((this.data == null)?"<null>":this.data));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.data == null)? 0 :this.data.hashCode()));
        result = ((result* 31)+((this.seriesID == null)? 0 :this.seriesID.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Series) == false) {
            return false;
        }
        Series rhs = ((Series) other);
        return (((this.data == rhs.data)||((this.data!= null)&&this.data.equals(rhs.data)))&&((this.seriesID == rhs.seriesID)||((this.seriesID!= null)&&this.seriesID.equals(rhs.seriesID))));
    }

}
