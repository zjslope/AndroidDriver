package com.example.slope.androiddriver.http;

import com.example.slope.androiddriver.basicclass.Car;

import java.util.List;

/**
 * Created by Slope on 2016/9/19.
 */
public interface DataResult {
    /**
     * 网络数据结果处理为对象
     * @param objects
     */
    void resultObject(String objects);

    /**
     * 网络数据处理结果为集合
     * @param list
     */
    void resultList(List<Car> list, int tye);
}
