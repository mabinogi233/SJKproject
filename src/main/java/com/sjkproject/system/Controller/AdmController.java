package com.sjkproject.system.Controller;


import com.sjkproject.system.ORM.*;
import com.sjkproject.system.Services.AdmServices;
import com.sjkproject.system.Services.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/Adm")
public class AdmController {

    @Autowired
    @Qualifier("admServices")
    private AdmServices admServices;

    //获取员工管理主页面
    @RequestMapping("/list")
    public String getMain(ModelMap map){
        map.addAttribute("person",admServices.selectAllPerson());
        //操作提示标志,0为无修改，1为操作成功，2为操作失败
        map.addAttribute("flag",0);
        return "admMain";
    }

    //点击基本信息修改
    @RequestMapping("/updatePerson")
    public String getUpDatePerson(ModelMap map, @RequestParam("Yid")int Yid){
        map.addAttribute("person",admServices.selectPersonById(Yid));
        return "personEdit";
    }

    //接受post表单，修改员工基本信息
    @RequestMapping("/updatePersonPost")
    public String upDatePerson(ModelMap map, @ModelAttribute Person person){
        if(admServices.updatePerson(person)){
            //修改成功
            map.addAttribute("person",admServices.selectAllPerson());
            //操作提示标志,0为无修改，1为操作成功，2为操作失败
            map.addAttribute("flag",1);
            return "admMain";
        }else{
            //修改失败
            map.addAttribute("person",admServices.selectAllPerson());
            //操作提示标志,0为无修改，1为操作成功，2为操作失败
            map.addAttribute("flag",2);
            return "admMain";
        }
    }

    //点击系统账号信息修改
    @RequestMapping("/updateAccount")
    public String getUpDateAccount(ModelMap map, @RequestParam("Yid")int Yid){
        map.addAttribute("account",admServices.selectAccountByYid(Yid));
        map.addAttribute("YID",Yid);
        return "accountEdit";
    }

    //接受post表单，修改系统账号信息
    @RequestMapping("/updateAccountPost")
    public String upDatePerson(ModelMap map, @ModelAttribute Account account){
        if(admServices.updateAccount(account)){
            //修改成功
            map.addAttribute("person",admServices.selectAllPerson());
            //操作提示标志,0为无修改，1为操作成功，2为操作失败
            map.addAttribute("flag",1);
            return "admMain";
        }else{
            //修改失败
            map.addAttribute("person",admServices.selectAllPerson());
            //操作提示标志,0为无修改，1为操作成功，2为操作失败
            map.addAttribute("flag",2);
            return "admMain";
        }
    }

    //考勤管理
    @RequestMapping("/attendanceOP")
    public String attendanceOp(ModelMap map,@RequestParam("Yid")int Yid){
        map.addAttribute("attendances",admServices.selectAttendanceByYID(Yid));
        map.addAttribute("YID",Yid);
        return "attendanceEdit";
    }

    //点击添加考勤信息
    @RequestMapping("/addAttendance")
    public String addAttendance(ModelMap map,@RequestParam("Yid")int Yid){
        Attendance attendance = new Attendance();
        attendance.setYid(Yid);
        //设置时间
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = new Date();
        String str = dateFormat.format(date);
        attendance.setADate(str);
        attendance.setInformation("");
        map.addAttribute("attendance",attendance);
        return "addAttendance";
    }

    //添加考勤信息的post表单
    @RequestMapping("/addAttendancePost")
    public String addAttendancePost(ModelMap map,@ModelAttribute Attendance attendance){
        if(admServices.insertAttendance(attendance)){
            //修改成功
            map.addAttribute("person",admServices.selectAllPerson());
            //操作提示标志,0为无修改，1为操作成功，2为操作失败
            map.addAttribute("flag",1);
            return "admMain";
        }else{
            //修改失败
            map.addAttribute("person",admServices.selectAllPerson());
            //操作提示标志,0为无修改，1为操作成功，2为操作失败
            map.addAttribute("flag",2);
            return "admMain";
        }
    }

    //薪资管理
    @RequestMapping("/salaryOp")
    public String salaryOp(ModelMap map,@RequestParam("Yid")int Yid){
        map.addAttribute("salaries",admServices.selectSalaryByYID(Yid));
        map.addAttribute("YID",Yid);
        return "salaryEdit";
    }

    //点击添加薪资信息
    @RequestMapping("/addSalary")
    public String addSalary(ModelMap map,@RequestParam("Yid")int Yid){
        Salary salary = new Salary();
        salary.setYid(Yid);
        //设置时间
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = new Date();
        String str = dateFormat.format(date);
        salary.setSDate(str);
        map.addAttribute("salary",salary);
        return "addSalary";
    }

    //添加薪资信息的post表单
    @RequestMapping("/addSalaryPost")
    public String addSalaryPost(ModelMap map,@ModelAttribute Salary salary){
        if(admServices.insertSalary(salary)){
            //修改成功
            map.addAttribute("person",admServices.selectAllPerson());
            //操作提示标志,0为无修改，1为操作成功，2为操作失败
            map.addAttribute("flag",1);
            return "admMain";
        }else{
            //修改失败
            map.addAttribute("person",admServices.selectAllPerson());
            //操作提示标志,0为无修改，1为操作成功，2为操作失败
            map.addAttribute("flag",2);
            return "admMain";
        }
    }

    //点击删除员工
    @RequestMapping("/deletePerson")
    public String deletePerson(ModelMap map,@RequestParam("Yid")int Yid){
        if(admServices.deletePerson(Yid)){
            //修改成功
            map.addAttribute("person",admServices.selectAllPerson());
            //操作提示标志,0为无修改，1为操作成功，2为操作失败
            map.addAttribute("flag",1);
            return "admMain";
        }else{
            //修改失败
            map.addAttribute("person",admServices.selectAllPerson());
            //操作提示标志,0为无修改，1为操作成功，2为操作失败
            map.addAttribute("flag",2);
            return "admMain";
        }
    }

    //点击添加用户
    @RequestMapping("/addPerson")
    public String addPerson(ModelMap map){
        if(admServices.selectAllSection()==null){
            //无部门
            map.addAttribute("noSection",true);
            Person person = new Person();
            person.setYid(admServices.createUniqueYId());
            map.addAttribute("person",person);
        }else{
            map.addAttribute("noSection",false);
            Person person = new Person();
            person.setYid(admServices.createUniqueYId());
            //默认部门
            person.setBid(admServices.selectAllSection().get(0).getBid());
            map.addAttribute("person",person);
        }
        return "addPerson";
    }

    //添加员工post表单处理
    @RequestMapping("/addPersonPost")
    public String addPersonPost(ModelMap map,@ModelAttribute Person person) {
        if(admServices.insertPerson(person,"普通员工")){
            //成功
            map.addAttribute("person",admServices.selectAllPerson());
            //操作提示标志,0为无修改，1为操作成功，2为操作失败
            map.addAttribute("flag",1);
            return "admMain";
        }else{
            //失败
            map.addAttribute("person",admServices.selectAllPerson());
            //操作提示标志,0为无修改，1为操作成功，2为操作失败
            map.addAttribute("flag",2);
            return "admMain";
        }
    }

    //部门管理页面
    @RequestMapping("/listSection")
    public String getSectionMain(ModelMap map){
        map.addAttribute("section",admServices.selectAllSection());
        //操作提示标志,0为无修改，1为操作成功，2为操作失败
        map.addAttribute("flag",0);
        return "sectionMain";
    }

    //点击部门信息修改
    @RequestMapping("/updateSection")
    public String getUpDateSection(ModelMap map, @RequestParam("Bid")int Bid){
        map.addAttribute("section",admServices.selectSectionById(Bid));
        return "sectionEdit";
    }

    //接受post表单，修改员工基本信息
    @RequestMapping("/updateSectionPost")
    public String upDateSection(ModelMap map, @ModelAttribute Section section){
        if(admServices.updateSection(section)){
            //修改成功
            map.addAttribute("section",admServices.selectAllSection());
            //操作提示标志,0为无修改，1为操作成功，2为操作失败
            map.addAttribute("flag",1);
            return "sectionMain";
        }else{
            //修改失败
            map.addAttribute("section",admServices.selectAllSection());
            //操作提示标志,0为无修改，1为操作成功，2为操作失败
            map.addAttribute("flag",2);
            return "sectionMain";
        }
    }
    //添加部门
    @RequestMapping("/addSection")
    public String addSection(ModelMap map) {
        Section section = new Section();
        section.setBid(admServices.createUniqueBId());
        map.addAttribute("section",section);
        return "addSection";
    }

    //添加员工post表单处理
    @RequestMapping("/addSectionPost")
    public String addSectionPost(ModelMap map,@ModelAttribute Section section) {
        if(admServices.insertSection(section)){
            //成功
            map.addAttribute("section",admServices.selectAllSection());
            //操作提示标志,0为无修改，1为操作成功，2为操作失败
            map.addAttribute("flag",1);
            return "sectionMain";
        }else{
            //失败
            map.addAttribute("section",admServices.selectAllSection());
            //操作提示标志,0为无修改，1为操作成功，2为操作失败
            map.addAttribute("flag",2);
            return "sectionMain";
        }
    }

    //删除部门
    @RequestMapping("/deleteSection")
    public String deleteSection(ModelMap map,@RequestParam("Bid")int Bid){
        if(admServices.delete(Bid)){
            //修改成功
            map.addAttribute("section",admServices.selectAllSection());
            //操作提示标志,0为无修改，1为操作成功，2为操作失败
            map.addAttribute("flag",1);
            return "sectionMain";
        }else{
            //修改失败
            map.addAttribute("section",admServices.selectAllSection());
            //操作提示标志,0为无修改，1为操作成功，2为操作失败
            map.addAttribute("flag",2);
            return "sectionMain";
        }
    }
}
