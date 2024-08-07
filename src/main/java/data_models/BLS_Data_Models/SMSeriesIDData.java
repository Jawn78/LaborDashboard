package data_models.BLS_Data_Models;

import java.util.HashMap;
import java.util.Map;

public class SMSeriesIDData {
    private String seriesID;
    private String prefix;
    private String seasonalAdjustmentCode;
    private String seasonalAdjustmentText;
    private String stateCode;
    private String stateName;
    private String areaCode;
    private String areaName;
    private String supersectorCode;
    private String supersectorName;
    private String industryCode;
    private String industryName;
    private String dataTypeCode;
    private String dataTypeText;
    private Map<String, Double> monthlyValues;
    // Constructors
    public SMSeriesIDData() {
        this.monthlyValues = new HashMap<>();
    }

    public String getSeriesID() {
        return seriesID;
    }

    public void setSeriesID(String seriesID) {
        this.seriesID = seriesID;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSeasonalAdjustmentText() {
        return seasonalAdjustmentText;
    }

    public void setSeasonalAdjustmentText(String seasonalAdjustmentText) {
        this.seasonalAdjustmentText = seasonalAdjustmentText;
    }

    public String getSeasonalAdjustmentCode() {
        return seasonalAdjustmentCode;
    }

    public void setSeasonalAdjustmentCode(String seasonalAdjustmentCode) {
        this.seasonalAdjustmentCode = seasonalAdjustmentCode;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getSupersectorCode() {
        return supersectorCode;
    }

    public void setSupersectorCode(String supersectorCode) {
        this.supersectorCode = supersectorCode;
    }

    public String getSupersectorName() {
        return supersectorName;
    }

    public void setSupersectorName(String supersectorName) {
        this.supersectorName = supersectorName;
    }

    public String getIndustryCode() {
        return industryCode;
    }

    public void setIndustryCode(String industryCode) {
        this.industryCode = industryCode;
    }

    public String getIndustryName() {
        return industryName;
    }

    public void setIndustryName(String industryName) {
        this.industryName = industryName;
    }

    public String getDataTypeCode() {
        return dataTypeCode;
    }

    public void setDataTypeCode(String dataTypeCode) {
        this.dataTypeCode = dataTypeCode;
    }

    public String getDataTypeText() {
        return dataTypeText;
    }

    public void setDataTypeText(String dataTypeText) {
        this.dataTypeText = dataTypeText;
    }

    public Map<String, Double> getMonthlyValues() {
        return monthlyValues;
    }

    public void setMonthlyValues(Map<String, Double> monthlyValues) {
        this.monthlyValues = monthlyValues;
    }

    public void addMonthlyValue(String month, Double value) {
        this.monthlyValues.put(month, value);
    }

    // Unique key to identify each instance for aggregation
    public String generateKey() {
        return seriesID + "-" + dataTypeCode; // Modify as necessary
    }

    // toString method
    @Override
    public String toString() {
        return "SMSeriesIDData{" +
                "prefix='" + prefix + '\'' +
                ", seasonalAdjustmentCode='" + seasonalAdjustmentCode + '\'' +
                ", stateCode='" + stateCode + '\'' +
                ", stateName='" + stateName + '\'' +
                ", areaCode='" + areaCode + '\'' +
                ", areaName='" + areaName + '\'' +
                ", supersectorCode='" + supersectorCode + '\'' +
                ", supersectorName='" + supersectorName + '\'' +
                ", industryCode='" + industryCode + '\'' +
                ", industryName='" + industryName + '\'' +
                ", dataTypeCode='" + dataTypeCode + '\'' +
                ", dataTypeText='" + dataTypeText + '\'' +
                '}';
    }

}
