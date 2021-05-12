package com.sjkproject.system.Services;


import com.sjkproject.system.ORM.Account;
import org.springframework.stereotype.Component;

@Component
public interface LoginServiceInterface {
    /**
     * 查询员工的系统账户信息
     * @param Yid
     * @return
     */
    Account selectOneByYid(int Yid);

    /**
     * 修改员工的系统账户信息
     * @param account
     */
    void update(Account account);

    /**
     * 删除Yid=yid员工的系统账户信息
     * @param Yid
     */
    void delete(int Yid);

    /**
     * 添加账户信息
     * @param account
     */
    void insert(Account account);

}
