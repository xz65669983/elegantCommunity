package com.zzc.elegantcommunity.model.issueactivity;

/**
 * Created by Administrator on 2017/12/31.
 */

public class ActivityDetialsModel {
    private String title;
    private String beginTime;
    private String endTime;
    private String city;
    private String mainAddress;
    private String content;
    private Integer cost;
    private String contactPhoneNo;

    private String county;
    private String  siteId;
    private String capacityLimit;
    private String participatorCondition;


    private String poster;
    private Integer sponsor;



    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public Integer getSponsor() {
        return sponsor;
    }

    public void setSponsor(Integer sponsor) {
        this.sponsor = sponsor;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getMainAddress() {
        return mainAddress;
    }

    public void setMainAddress(String mainAddress) {
        this.mainAddress = mainAddress;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public String getContactPhoneNo() {
        return contactPhoneNo;
    }

    public void setContactPhoneNo(String contactPhoneNo) {
        this.contactPhoneNo = contactPhoneNo;
    }
}
