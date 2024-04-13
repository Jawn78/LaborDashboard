
package bls;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Results implements Serializable
{

    @SerializedName("series")
    @Expose
    private List<Series> series;
    private final static long serialVersionUID = -5640526861550738617L;

    public List<Series> getSeries() {
        return series;
    }

    public void setSeries(List<Series> series) {
        this.series = series;
    }

    public Results withSeries(List<Series> series) {
        this.series = series;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Results.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("series");
        sb.append('=');
        sb.append(((this.series == null)?"<null>":this.series));
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
        result = ((result* 31)+((this.series == null)? 0 :this.series.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Results) == false) {
            return false;
        }
        Results rhs = ((Results) other);
        return ((this.series == rhs.series)||((this.series!= null)&&this.series.equals(rhs.series)));
    }

}
