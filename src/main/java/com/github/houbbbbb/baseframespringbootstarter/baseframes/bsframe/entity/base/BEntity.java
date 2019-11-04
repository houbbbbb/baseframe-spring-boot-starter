package com.github.houbbbbb.baseframespringbootstarter.baseframes.bsframe.entity.base;

import com.github.houbbbbb.baseframespringbootstarter.baseframes.bsframe.entity.IEntity;

/**
 * ClassName BEntity
 * Description 基本实体
 * Author hbw
 * Date 2019/7/12 11:09
 * Version 1.0
 **/
public abstract class BEntity implements IEntity {
    protected String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
