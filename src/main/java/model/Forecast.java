package model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;


@Entity
@Table(name = "weather")
public class Forecast {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @JsonIgnore
    @Column(name = "date")
    private LocalDateTime date;

    @JsonIgnore
    @Column(name = "request")
    private String request;

    @JsonIgnore
    @Column(name = "resource")
    private String resouce;

    @Column(name = "city")
    private String city;

    @Column(name = "weather")
    private String weather;

    @Column(name = "temp")
    private String temp;

    @Column(name = "liketemp")
    private String likeTemp;

    @Column(name = "windspeed")
    private String windSpeed;

    @Column(name = "winddeg")
    private String windDeg;

    @Column(name = "clouds")
    private String clouds;

    @Column(name = "pressure")
    private String pressure;

    @Column(name = "humidity")
    private String humidity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getResouce() {
        return resouce;
    }

    public void setResouce(String resouce) {
        this.resouce = resouce;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getLikeTemp() {
        return likeTemp;
    }

    public void setLikeTemp(String likeTemp) {
        this.likeTemp = likeTemp;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getWindDeg() {
        return windDeg;
    }

    public void setWindDeg(String windDeg) {
        this.windDeg = windDeg;
    }

    public String getClouds() {
        return clouds;
    }

    public void setClouds(String clouds) {
        this.clouds = clouds;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    @Override
    public String toString() {
        return "weather{" +
                "id=" + id +
                ", date=" + date +
                ", request='" + request + '\'' +
                ", resouce='" + resouce + '\'' +
                ", city='" + city + '\'' +
                ", weather='" + weather + '\'' +
                ", temp='" + temp + '\'' +
                ", likeTemp='" + likeTemp + '\'' +
                ", windSpeed='" + windSpeed + '\'' +
                ", windDeg='" + windDeg + '\'' +
                ", clouds='" + clouds + '\'' +
                ", pressure='" + pressure + '\'' +
                ", humidity='" + humidity + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Forecast forecast = (Forecast) o;
        return Objects.equals(date, forecast.date) &&
                Objects.equals(request, forecast.request) &&
                Objects.equals(resouce, forecast.resouce) &&
                Objects.equals(city, forecast.city) &&
                Objects.equals(weather, forecast.weather) &&
                Objects.equals(temp, forecast.temp) &&
                Objects.equals(likeTemp, forecast.likeTemp) &&
                Objects.equals(windSpeed, forecast.windSpeed) &&
                Objects.equals(windDeg, forecast.windDeg) &&
                Objects.equals(clouds, forecast.clouds) &&
                Objects.equals(pressure, forecast.pressure) &&
                Objects.equals(humidity, forecast.humidity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, request, resouce, city, weather, temp, likeTemp, windSpeed, windDeg, clouds, pressure, humidity);
    }
}

