package com.sjkproject.system.Services;


import com.sjkproject.system.ORM.Salary;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public interface SalaryServiceInterface {

    /**
     * 查询某员工的全部薪资记录
     * @param Yid
     * @return
     */
    List<Salary> selectByYID(int Yid);

    /**
     * 删除某员工的全部薪资记录
     * @param Yid
     */
    void delete(int Yid);

    /**
     * 为某员工添加薪资记录
     * @param salary
     */
    void insert(Salary salary);
}
