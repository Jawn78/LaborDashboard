package data_models.SeriesRequest;

import java.util.List;

public class SeriesRequest {
    private List<String> seriesid;
    private String startyear;
    private String endyear;
    private boolean catalog = false;
    private boolean calculations = false;
    private boolean annualaverage = false;
    private boolean aspects = false;
    private String registrationkey;

    // Getters and setters
    public List<String> getSeriesid() {
        return seriesid;
    }

    public void setSeriesid(List<String> seriesid) {
        this.seriesid = seriesid;
    }

    public String getStartyear() {
        return startyear;
    }

    public void setStartyear(String startyear) {
        this.startyear = startyear;
    }

    public String getEndyear() {
        return endyear;
    }

    public void setEndyear(String endyear) {
        this.endyear = endyear;
    }

    public boolean isCatalog() {
        return catalog;
    }

    public void setCatalog(boolean catalog) {
        this.catalog = catalog;
    }

    public boolean isCalculations() {
        return calculations;
    }

    public void setCalculations(boolean calculations) {
        this.calculations = calculations;
    }

    public boolean isAnnualaverage() {
        return annualaverage;
    }

    public void setAnnualaverage(boolean annualaverage) {
        this.annualaverage = annualaverage;
    }

    public boolean isAspects() {
        return aspects;
    }

    public void setAspects(boolean aspects) {
        this.aspects = aspects;
    }

    public String getRegistrationkey() {
        return registrationkey;
    }

    public void setRegistrationkey(String registrationkey) {
        this.registrationkey = registrationkey;
    }
}
