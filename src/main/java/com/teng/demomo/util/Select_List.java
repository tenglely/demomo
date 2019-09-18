package com.teng.demomo.util;

import java.util.List;

/**
 * 用于比较list中是否有相似的内容
 */
public class Select_List {

    /**
     * 判断list中是否包含某字符串
     * 存在返回true
     * 不存在返回false
     * @param list
     * @param cc
     * @return
     */
    public Boolean find_list_false(List<String> list,String cc){
        for(int i=0;i<list.size();i++)
            if(list.get(i).indexOf(cc)!=-1)
                return true;
        return false;
    }
}
