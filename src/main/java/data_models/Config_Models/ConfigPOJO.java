
package data_models.Config_Models;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ConfigPOJO implements Serializable
{

    @SerializedName("BLSApiKey")
    @Expose
    private String bLSApiKey;
    @SerializedName("UsePostgreSQL")
    @Expose
    private Boolean usePostgreSQL;
    @SerializedName("PostgreSQLConfig")
    @Expose
    private PostgreSQLConfig postgreSQLConfig;
    private final static long serialVersionUID = -1111425644240801265L;

    public ConfigPOJO(String blsApiKey, boolean usePostgreSQL, PostgreSQLConfig pgConfig) {
    }

    public String getBLSApiKey() {
        return bLSApiKey;
    }

    public void setBLSApiKey(String bLSApiKey) {
        this.bLSApiKey = bLSApiKey;
    }

    public Boolean getUsePostgreSQL() {
        return usePostgreSQL;
    }

    public void setUsePostgreSQL(Boolean usePostgreSQL) {
        this.usePostgreSQL = usePostgreSQL;
    }

    public PostgreSQLConfig getPostgreSQLConfig() {
        return postgreSQLConfig;
    }

    public void setPostgreSQLConfig(PostgreSQLConfig postgreSQLConfig) {
        this.postgreSQLConfig = postgreSQLConfig;
    }

}
