package com.zzc.elegantcommunity.model.issueactivity;

/**
 * Created by zhangzhengchao on 2018/3/29.
 */

public class BriefActivityRequestModel {

    private Long startNum;
    private Integer pageSize;



    public Long getStartNum() {
        return startNum;
    }

    public void setStartNum(Long startNum) {
        this.startNum = startNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }


}
