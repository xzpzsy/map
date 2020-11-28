package cn.edu.seu.xzp.movie.object;

import java.util.Date;
import java.util.Calendar;

public class Record {

    private int id;
    private String city;
    private String province;
    private Date date;
    private String record;
    private String purpose;
    private String gourmet;

    private Calendar calendar = Calendar.getInstance();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }


    public Record() {

    }

    public String getGourmet() {
        return gourmet;
    }

    public void setGourmet(String gourmet) {
        this.gourmet = gourmet;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
        this.calendar.setTime(date);
    }
}
