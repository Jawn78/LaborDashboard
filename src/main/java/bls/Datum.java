
package bls;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum implements Serializable
{

    @SerializedName("year")
    @Expose
    private String year;
    @SerializedName("period")
    @Expose
    private String period;
    @SerializedName("periodName")
    @Expose
    private String periodName;
    @SerializedName("latest")
    @Expose
    private String latest;
    @SerializedName("value")
    @Expose
    private String value;
    @SerializedName("footnotes")
    @Expose
    private List<Footnote> footnotes;
    @Serial
    private final static long serialVersionUID = 2677139181736111300L;

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Datum withYear(String year) {
        this.year = year;
        return this;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public Datum withPeriod(String period) {
        this.period = period;
        return this;
    }

    public String getPeriodName() {
        return periodName;
    }

    public void setPeriodName(String periodName) {
        this.periodName = periodName;
    }

    public Datum withPeriodName(String periodName) {
        this.periodName = periodName;
        return this;
    }

    public String getLatest() {
        return latest;
    }

    public void setLatest(String latest) {
        this.latest = latest;
    }

    public Datum withLatest(String latest) {
        this.latest = latest;
        return this;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Datum withValue(String value) {
        this.value = value;
        return this;
    }

    public List<Footnote> getFootnotes() {
        return footnotes;
    }

    public void setFootnotes(List<Footnote> footnotes) {
        this.footnotes = footnotes;
    }

    public Datum withFootnotes(List<Footnote> footnotes) {
        this.footnotes = footnotes;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Datum.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("year");
        sb.append('=');
        sb.append(((this.year == null)?"<null>":this.year));
        sb.append(',');
        sb.append("period");
        sb.append('=');
        sb.append(((this.period == null)?"<null>":this.period));
        sb.append(',');
        sb.append("periodName");
        sb.append('=');
        sb.append(((this.periodName == null)?"<null>":this.periodName));
        sb.append(',');
        sb.append("latest");
        sb.append('=');
        sb.append(((this.latest == null)?"<null>":this.latest));
        sb.append(',');
        sb.append("value");
        sb.append('=');
        sb.append(((this.value == null)?"<null>":this.value));
        sb.append(',');
        sb.append("footnotes");
        sb.append('=');
        sb.append(((this.footnotes == null)?"<null>":this.footnotes));
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
        result = ((result* 31)+((this.period == null)? 0 :this.period.hashCode()));
        result = ((result* 31)+((this.year == null)? 0 :this.year.hashCode()));
        result = ((result* 31)+((this.periodName == null)? 0 :this.periodName.hashCode()));
        result = ((result* 31)+((this.value == null)? 0 :this.value.hashCode()));
        result = ((result* 31)+((this.footnotes == null)? 0 :this.footnotes.hashCode()));
        result = ((result* 31)+((this.latest == null)? 0 :this.latest.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Datum) == false) {
            return false;
        }
        Datum rhs = ((Datum) other);
        return (((((((this.period == rhs.period)||((this.period!= null)&&this.period.equals(rhs.period)))&&((this.year == rhs.year)||((this.year!= null)&&this.year.equals(rhs.year))))&&((this.periodName == rhs.periodName)||((this.periodName!= null)&&this.periodName.equals(rhs.periodName))))&&((this.value == rhs.value)||((this.value!= null)&&this.value.equals(rhs.value))))&&((this.footnotes == rhs.footnotes)||((this.footnotes!= null)&&this.footnotes.equals(rhs.footnotes))))&&((this.latest == rhs.latest)||((this.latest!= null)&&this.latest.equals(rhs.latest))));
    }

}
