package edu.iu.habahram.weathermonitoring.model;

import org.springframework.stereotype.Component;

@Component
public class StatisticsDisplay
        implements Observer, DisplayElement{
    private float avgTemp;
    private float minTemp;
    private float maxTemp;

    private Subject weatherData;

    public StatisticsDisplay(Subject weatherData) {
        this.weatherData = weatherData;
    }

    @Override
    public String display() {
        String html = "";
        html += String.format("<div style=\"background-image: " +
                "url(/images/sky.webp); " +
                "height: 400px; " +
                "width: 647.2px;" +
                "display:flex;flex-wrap:wrap;justify-content:center;align-content:center;" +
                "\">");
        html += "<section>";
        html += String.format("<label>Avg Temp: %s</label><br />", avgTemp);
        html += String.format("<label>Max Temp: %s</label><br />", minTemp);
        html += String.format("<label>Min Temp: %s</label>", maxTemp);
        html += "</section>";
        html += "</div>";
        return html;
    }

    @Override
    public String name() {
        return "Statistics Display";
    }

    @Override
    public String id() {
        return "statistics";
    }

    @Override
    public void update(float avgTemp, float maxTemp, float minTemp) {
        this.avgTemp = avgTemp;
        this.maxTemp = maxTemp;
        this.minTemp = minTemp;
    }

    public void subscribe() {
        weatherData.registerObserver(this);
    }

    public void unsubscribe() {
        weatherData.removeObserver(this);
    }
}
