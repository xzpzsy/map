package cn.edu.seu.xzp.movie.object;

public class Province {
    String province = "";
    String province_capital = "";
    String alias = "";
    String gourmet = "";
    String climate = "";
    String area = "";
    String link = "";
    String location = "";

    public String toString() {
        return String.format(" 省会:%s\n\n 别名:%s\n\n 所属地区:%s\n 地理位置:%s\n 气候条件:%s\n\n 美食:%s",
                province_capital,alias,area,location,climate, gourmet);
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getProvince_capital() {
        return province_capital;
    }

    public void setProvince_capital(String province_capital) {
        this.province_capital = province_capital;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getGourmet() {
        return gourmet;
    }

    public void setGourmet(String gourmet) {
        this.gourmet = gourmet;
    }

    public String getClimate() {
        return climate;
    }

    public void setClimate(String climate) {
        this.climate = climate;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
