package data_models.SeriesRequest;

public class SeriesRequestWrapper {
    private String url;
    private SeriesRequest seriesRequest;

    // Getters and setters
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public SeriesRequest getSeriesRequest() {
        return seriesRequest;
    }

    public void setSeriesRequest(SeriesRequest seriesRequest) {
        this.seriesRequest = seriesRequest;
    }
}
