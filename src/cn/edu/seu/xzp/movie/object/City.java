package cn.edu.seu.xzp.movie.object;

public class City {
    int id;
    String city = "";
    String province = "";
    String alias = "";
    String link = "";
    String location = "";
    String gourmet = "";
    String dialect = "";

    public String toString() {
        return String.format(" 所属省份:%s\n\n 别名:%s\n\n 地理位置:%s\n\n 方言:%s\n\n 美食:%s",
                province,alias,location,dialect,gourmet);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGourmet() {
        return gourmet;
    }

    public void setGourmet(String gourmet) {
        this.gourmet = gourmet;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
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

    public String getDialect() {
        return dialect;
    }

    public void setDialect(String dialect) {
        this.dialect = dialect;
    }
}
