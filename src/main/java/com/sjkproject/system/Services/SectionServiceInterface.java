package com.sjkproject.system.Services;


import com.sjkproject.system.ORM.Section;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface SectionServiceInterface {

    /**
     * 查询全部部门的基本信息
     * @return
     */
    List<Section> selectAll();

    /**
     * 根据部门id查询部门基本信息
     * @param Bid
     * @return
     */
    Section selectOneById(int Bid);

    /**
     * 更新部门基本信息
     * @param section
     */
    void update(Section section);

    /**
     * 删除Bid=Bid的部门的基本信息
     * @param Bid
     */
    void delete(int Bid);

    /**
     * 添加新部门
     * @param section
     */
    void insert(Section section);

}
