package com.wwq.nacos.domain.req;

import com.wwq.common.page.BaseReq;

public class GirlFriendReq extends BaseReq {
    private String age;
    private String name;

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
