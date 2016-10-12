package com.example.slope.androiddriver.myserverhttp;

import com.example.slope.androiddriver.basicclass.Talk;
import com.example.slope.androiddriver.basicclass.User;


import java.util.List;

/**
 * Created by Slope on 2016/9/11.
 */
public interface MyDetaResult {
    /**
     * 网络数据结果处理为对象
     * @param objects
     */
    void mysrvserObject(String objects);
    /**
     * 网络数据处理结果为集合
     * @param list
     */
    void myserverList(List<User> list);

    void myserverListForTalk(List<Talk> parse);
}
