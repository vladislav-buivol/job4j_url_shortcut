package ru.job4j.url.shortcut.dto.entity.link.statistic;

public class SiteStatistic {
    private String url;
    private long total;

    public SiteStatistic() {
    }

    public SiteStatistic(String url, long total) {
        this.total = total;
        this.url = url;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
