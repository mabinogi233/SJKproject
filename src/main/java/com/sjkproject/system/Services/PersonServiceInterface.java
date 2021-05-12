package com.sjkproject.system.Services;

import com.sjkproject.system.ORM.Person;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PersonServiceInterface {
    /**
     * 查询全部员工的基本信息
     * @return
     */
    List<Person> selectAll();

    /**
     * 根据员工id查询用户基本信息
     * @param Yid
     * @return
     */
    Person selectOnePersonById(int Yid);

    /**
     * 更新员工基本信息
     * @param person
     */
    void update(Person person);

    /**
     * 删除Yid=Yid的员工的基本信息
     * @param Yid
     */
    void delete(int Yid);

    /**
     * 添加新员工
     * @param person
     */
    void insert(Person person);

}
