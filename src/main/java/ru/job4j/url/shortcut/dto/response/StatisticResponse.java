package ru.job4j.url.shortcut.dto.response;

import ru.job4j.url.shortcut.dto.entity.link.statistic.SiteStatistic;

import java.util.List;
import java.util.NoSuchElementException;

public class StatisticResponse implements Response {
    private List<SiteStatistic> statistic;

    public StatisticResponse(
            List<SiteStatistic> statistic) {
        this.statistic = statistic;
    }

    public StatisticResponse() {
    }

    public List<SiteStatistic> getStatistic() {
        return statistic;
    }

    public void setStatistic(
            List<SiteStatistic> statistic) {
        this.statistic = statistic;
    }

    public SiteStatistic findByUrl(String url) {
        for (SiteStatistic stat : statistic) {
            if (stat.getUrl().equals(url)) {
                return stat;
            }
        }
        throw new NoSuchElementException(String.format("Statistic for %s was not found", url));
    }
}
