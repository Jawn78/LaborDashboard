
package bls;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Response implements Serializable
{

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("responseTime")
    @Expose
    private Integer responseTime;
    @SerializedName("message")
    @Expose
    private List<String> message;
    @SerializedName("Results")
    @Expose
    private Results results;
    private final static long serialVersionUID = 3974281992423308361L;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Response withStatus(String status) {
        this.status = status;
        return this;
    }

    public Integer getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(Integer responseTime) {
        this.responseTime = responseTime;
    }

    public Response withResponseTime(Integer responseTime) {
        this.responseTime = responseTime;
        return this;
    }

    public List<String> getMessage() {
        return message;
    }

    public void setMessage(List<String> message) {
        this.message = message;
    }

    public Response withMessage(List<String> message) {
        this.message = message;
        return this;
    }

    public Results getResults() {
        return results;
    }

    public void setResults(Results results) {
        this.results = results;
    }

    public Response withResults(Results results) {
        this.results = results;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Response.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("status");
        sb.append('=');
        sb.append(((this.status == null)?"<null>":this.status));
        sb.append(',');
        sb.append("responseTime");
        sb.append('=');
        sb.append(((this.responseTime == null)?"<null>":this.responseTime));
        sb.append(',');
        sb.append("message");
        sb.append('=');
        sb.append(((this.message == null)?"<null>":this.message));
        sb.append(',');
        sb.append("results");
        sb.append('=');
        sb.append(((this.results == null)?"<null>":this.results));
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
        result = ((result* 31)+((this.message == null)? 0 :this.message.hashCode()));
        result = ((result* 31)+((this.results == null)? 0 :this.results.hashCode()));
        result = ((result* 31)+((this.responseTime == null)? 0 :this.responseTime.hashCode()));
        result = ((result* 31)+((this.status == null)? 0 :this.status.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Response) == false) {
            return false;
        }
        Response rhs = ((Response) other);
        return (((((this.message == rhs.message)||((this.message!= null)&&this.message.equals(rhs.message)))&&((this.results == rhs.results)||((this.results!= null)&&this.results.equals(rhs.results))))&&((this.responseTime == rhs.responseTime)||((this.responseTime!= null)&&this.responseTime.equals(rhs.responseTime))))&&((this.status == rhs.status)||((this.status!= null)&&this.status.equals(rhs.status))));
    }

}
