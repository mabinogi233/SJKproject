package com.sjkproject.system.Services;


import com.sjkproject.system.ORM.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class AdmServices {
    //属性

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

    //查询类方法
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
     * 查询全部员工的基本信息
     * @return
     */
    public List<Person> selectAllPerson(){
        try{
            return personService.selectAll();
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
     * 查询全部部门的基本信息
     * @return
     */
    public List<Section> selectAllSection(){
        try{
            return sectionService.selectAll();
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

    //修改类方法
    /**
     * 添加新员工
     * @param person
     * @param Jurisdiction 员工身份，可选"普通员工"与"管理员"
     * @return 返回操作是否成功
     */
    public boolean insertPerson(Person person,String Jurisdiction){
        try {
            //存在此用户
            if (personService.selectOnePersonById(person.getYid()) != null) {
                return false;
            }
            //不存在此部门
            if(sectionService.selectOneById(person.getBid())==null){
                return false;
            }
            //存在此系统账户
            if (loginService.selectOneByYid(person.getYid()) != null) {
                return false;
            }
            //添加新员工
            personService.insert(person);
            //添加账户
            Account account = new Account();
            account.setYid(person.getYid());
            account.setJurisdiction(Jurisdiction);
            account.setPassword(String.valueOf(person.getYid()));
            account.setUserId(String.valueOf(person.getYid()));
            loginService.insert(account);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    /**
     * 为某员工添加考勤记录，返回操作是否成功
     * @param attendance
     * @return
     */
    public boolean insertAttendance(Attendance attendance){
        try {
            //员工不存在
            if (personService.selectOnePersonById(attendance.getYid()) == null) {
                return false;
            }
            attendanceService.insert(attendance);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    /**
     * 为某员工添加薪资记录,返回操作是否成功
     * @param salary
     * @return
     */
    public boolean insertSalary(Salary salary){
        try {
            //员工不存在
            if (personService.selectOnePersonById(salary.getYid()) == null) {
                return false;
            }
            salaryService.insert(salary);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    /**
     * 添加新部门,返回操作是否成功
     * @param section
     * @return
     */
    public boolean insertSection(Section section){
        try {
            //存在相同id部门
            if (sectionService.selectOneById(section.getBid())!=null) {
                return false;
            }
            sectionService.insert(section);
            return true;
        }catch (Exception e){
            return false;
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

    /**
     * 更新部门基本信息,返回操作是否成功
     * @param section
     * @return
     */
    public boolean updateSection(Section section){
        try{
            //部门不存在
            if(sectionService.selectOneById(section.getBid())==null){
                return false;
            }
            sectionService.update(section);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    //删除类方法
    /**
     * 删除Bid=Bid的部门的基本信息,返回操作是否成功
     * @param Bid
     * @return
     */
    public boolean delete(int Bid){
        try{
            //保证此部门下没有员工
            for(Person person:personService.selectAll()){
                if(person.getBid()==Bid){
                    return false;
                }
            }
            sectionService.delete(Bid);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除Yid=Yid的员工的基本信息,返回操作是否成功
     * @param Yid
     * @return
     */
    public boolean deletePerson(int Yid){
        try {
            //已经不存在此用户了
            if (personService.selectOnePersonById(Yid) == null) {
                return false;
            }
            //删除薪资记录、考勤记录、账号信息
            salaryService.delete(Yid);
            attendanceService.delete(Yid);
            loginService.delete(Yid);
            //删除此用户
            personService.delete(Yid);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 生成独一的YID
     * @return
     */
    public int createUniqueYId(){
        Random random = new Random();
        int id;
        do {
            id = 0;
            for (int i = 0; i < 8; i++) {
                //0~9随机 8位
                if(i==7) {
                    //最高位不为0
                    id += (random.nextInt(9)+1) * (int) Math.pow(10, i);
                }else {
                    id += random.nextInt(10) * (int) Math.pow(10, i);
                }
            }
        }while (personService.selectOnePersonById(id)!=null);
        return id;
    }

    /**
     * 生成独一的BID
     * @return
     */
    public int createUniqueBId(){
        Random random = new Random();
        int id;
        do {
            id = 0;
            for (int i = 0; i < 8; i++) {
                //0~9随机 8位
                if(i==7) {
                    //最高位不为0
                    id += (random.nextInt(9)+1) * (int) Math.pow(10, i);
                }else {
                    id += random.nextInt(10) * (int) Math.pow(10, i);
                }
            }
        }while (sectionService.selectOneById(id)!=null);
        return id;
    }
}
