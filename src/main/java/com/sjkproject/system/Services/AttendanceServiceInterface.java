package com.sjkproject.system.Services;


import com.sjkproject.system.ORM.Attendance;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AttendanceServiceInterface {

    /**
     * 查询某员工的全部考勤记录
     * @param Yid
     * @return
     */
    List<Attendance> selectAttendByYID(int Yid);

    /**
     * 删除某员工的全部考勤记录
     * @param Yid
     */
    void delete(int Yid);

    /**
     * 为某员工添加考勤记录
     * @param attendance
     */
    void insert(Attendance attendance);
}
