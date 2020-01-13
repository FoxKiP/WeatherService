package model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Forecast {
    private String city;
    private String weather;
    private String temp;
    private String likeTemp;
    private String windSpeed;
    private String windDir;
    private String clouds;
    private String pressure;
    private String humidity;
    @JsonIgnore
    private Request request = new Request();

    public String getClouds() {
        return clouds;
    }

    public void setClouds(String clouds) {
        this.clouds = clouds;
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

    public String getWindDir() {
        return windDir;
    }

    public void setWindDir(String windDir) {
        this.windDir = windDir;
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

    public void setRequest(String city, String resource) {
        request.setCity(city);
        request.setResource(resource);
    }

    public String[] getRequest() {
        return new String[] {request.getCity(), request.getResource()};
    }

    @Override
    public String toString() {
        return "Forecast{" +
                "city='" + city + '\'' +
                ", weather='" + weather + '\'' +
                ", temp='" + temp + '\'' +
                ", likeTemp='" + likeTemp + '\'' +
                ", windSpeed='" + windSpeed + '\'' +
                ", windDeg='" + windDir + '\'' +
                ", clouds='" + clouds + '\'' +
                ", pressure='" + pressure + '\'' +
                ", humidity='" + humidity + '\'' +
                '}';
    }

    private static class Request {
        private String city;
        private String resource;

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getResource() {
            return resource;
        }

        public void setResource(String resource) {
            this.resource = resource;
        }
    }
}
