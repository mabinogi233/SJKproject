package com.sjkproject.system.Services;


import com.sjkproject.system.ORM.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    @Qualifier("attendanceService")
    private AttendanceServiceInterface attendanceService;

    @Autowired
    @Qualifier("loginService")
    private LoginServiceInterface loginService;

    @Autowired
    @Qualifier("personService")
    private PersonServiceInterface personService;

    @Autowired
    @Qualifier("salaryService")
    private SalaryServiceInterface salaryService;

    @Autowired
    @Qualifier("sectionService")
    private SectionServiceInterface sectionService;

    /**
     * 查询某员工的全部考勤记录
     * @param Yid
     * @return
     */
    public List<Attendance> selectAttendanceByYID(int Yid){
        try {
            return attendanceService.selectAttendByYID(Yid);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 查询某员工的全部薪资记录
     * @param Yid
     * @return
     */
    public List<Salary> selectSalaryByYID(int Yid){
        try{
            return salaryService.selectByYID(Yid);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据员工id查询用户基本信息
     * @param Yid
     * @return
     */
    public Person selectPersonById(int Yid){
        try{
            return personService.selectOnePersonById(Yid);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据部门id查询部门基本信息
     * @param Bid
     * @return
     */
    public Section selectSectionById(int Bid){
        try{
            return sectionService.selectOneById(Bid);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 查询员工的系统账户信息
     * @param Yid
     * @return
     */
    public Account selectAccountByYid(int Yid){
        try{
            return loginService.selectOneByYid(Yid);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 修改员工的系统账户信息,返回操作是否成功
     * @param account
     * @return
     */
    public boolean updateAccount(Account account){
        try{
            //员工不存在
            if(personService.selectOnePersonById(account.getYid())==null){
                return false;
            }
            //系统账户信息不存在
            if(loginService.selectOneByYid(account.getYid())==null){
                return false;
            }
            loginService.update(account);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 更新员工基本信息,返回操作是否成功
     * @param person
     * @return
     */
    public boolean updatePerson(Person person){
        try{
            //员工不存在
            if(personService.selectOnePersonById(person.getYid())==null){
                return false;
            }
            //保证满足外键约束
            if(sectionService.selectOneById(person.getBid())==null){
                return false;
            }
            personService.update(person);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

}
