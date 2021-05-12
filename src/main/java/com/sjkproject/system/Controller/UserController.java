package com.sjkproject.system.Controller;


import com.sjkproject.system.ORM.Account;
import com.sjkproject.system.ORM.Person;
import com.sjkproject.system.ORM.Section;
import com.sjkproject.system.Services.AdmServices;
import com.sjkproject.system.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @Autowired
    @Qualifier("admServices")
    private AdmServices admServices;

    //返回登陆界面
    @RequestMapping("/login")
    public String loginPage(ModelMap map){
        map.addAttribute("flag",0);
        return "login";
    }

    //根据登录用户权限返回页面
    @RequestMapping("/loginPost")
    public String login(ModelMap map,@RequestParam("Yid") int Yid,@RequestParam("Password")String password){
        System.out.println(Yid);
        Account account = userService.selectAccountByYid(Yid);
        if(account==null){
            //账户不存在
            map.addAttribute("flag",2);
            return "login";
        }else{
            if(account.getPassword().equals(password)){
                //登录成功
                if(account.getJurisdiction().equals("管理员")){
                    //管理员
                    map.addAttribute("person",admServices.selectAllPerson());
                    //操作提示标志,0为无修改，1为操作成功，2为操作失败
                    map.addAttribute("flag",0);
                    return "admMain";
                }else{
                    //普通员工
                    Person person = userService.selectPersonById(Yid);
                    Section section = userService.selectSectionById(person.getBid());
                    map.addAttribute("person",person);
                    map.addAttribute("account",account);
                    map.addAttribute("section",section);
                    map.addAttribute("flag",0);
                    return "userPerson";
                }
            }else{
                //密码错误
                map.addAttribute("flag",1);
                return "login";
            }
        }
    }

    @RequestMapping("/userInfo")
    public String getUserPage(ModelMap map,@RequestParam("Yid") int Yid){
        Person person = userService.selectPersonById(Yid);
        Section section = userService.selectSectionById(person.getBid());
        Account account = userService.selectAccountByYid(Yid);
        map.addAttribute("person",person);
        map.addAttribute("account",account);
        map.addAttribute("section",section);
        map.addAttribute("flag",0);
        return "userPerson";
    }

    //薪资查询界面
    @RequestMapping("/selectSalary")
    public String selectSalary(ModelMap map,@RequestParam("Yid") int Yid){
        map.addAttribute("salaries",userService.selectSalaryByYID(Yid));
        map.addAttribute("YID",Yid);
        return "userSalary";
    }


    //考勤查询页面
    @RequestMapping("/selectAttendance")
    public String selectAttendance(ModelMap map,@RequestParam("Yid") int Yid){
        map.addAttribute("attendance",userService.selectAttendanceByYID(Yid));
        map.addAttribute("YID",Yid);
        return "userAttendance";
    }

    //基本信息修改页面
    @RequestMapping("/updatePerson")
    public String updatePerson(ModelMap map,@RequestParam("Yid") int Yid){
        map.addAttribute("person",userService.selectPersonById(Yid));
        map.addAttribute("YID",Yid);
        return "userPersonInfo";
    }

    //修改基本信息
    @RequestMapping("/updatePersonPost")
    public String updatePersonPost(ModelMap map, @ModelAttribute Person person){
        if(userService.updatePerson(person)){
            //修改成功
            Section section = userService.selectSectionById(person.getBid());
            Account account = userService.selectAccountByYid(person.getYid());
            map.addAttribute("person",person);
            map.addAttribute("account",account);
            map.addAttribute("section",section);
            map.addAttribute("flag",1);
            return "userPerson";
        }else{
            //修改失败
            Section section = userService.selectSectionById(person.getBid());
            Account account = userService.selectAccountByYid(person.getYid());
            map.addAttribute("person",userService.selectPersonById(person.getYid()));
            map.addAttribute("account",account);
            map.addAttribute("section",section);
            map.addAttribute("flag",2);
            return "userPerson";
        }
    }

    //账户信息修改页面
    @RequestMapping("/updateAccount")
    public String updateAccount(ModelMap map,@RequestParam("Yid") int Yid){
        map.addAttribute("account",userService.selectAccountByYid(Yid));
        map.addAttribute("YID",Yid);
        return "userAccountInfo";
    }

    //修改账户信息
    @RequestMapping("/updateAccountPost")
    public String updateAccountPost(ModelMap map,@ModelAttribute Account account){
        if(userService.updateAccount(account)){
            //修改成功\
            Person person = userService.selectPersonById(account.getYid());
            Section section = userService.selectSectionById(person.getBid());
            map.addAttribute("person",person);
            map.addAttribute("account",account);
            map.addAttribute("section",section);
            map.addAttribute("flag",1);
            return "userPerson";
        }else{
            //修改失败
            Person person = userService.selectPersonById(account.getYid());
            Section section = userService.selectSectionById(person.getBid());
            map.addAttribute("person",person);
            map.addAttribute("account",userService.selectAccountByYid(account.getYid()));
            map.addAttribute("section",section);
            map.addAttribute("flag",2);
            return "userPerson";
        }
    }

}
