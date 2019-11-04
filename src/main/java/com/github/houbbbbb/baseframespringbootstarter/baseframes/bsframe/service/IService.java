package com.github.houbbbbb.baseframespringbootstarter.baseframes.bsframe.service;

import java.util.List;

/**
 * service模板接口
 * Author hbw
 */
public interface IService {

    <T> T queryOne(T t);

    <T> List<T> queryAll(T t);

    <T> Long queryCount(T t);

    <T> void saveOne(T t);

    <T> void saveAll(List<T> ts);

    <T> void remove(T t);

    <T> void modify(T t);

}
