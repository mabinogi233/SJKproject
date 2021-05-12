package com.sjkproject.system.Services;


import com.sjkproject.system.JDBCUtils.JDBCMapper;
import com.sjkproject.system.JDBCUtils.jdbcTemplate;
import com.sjkproject.system.ORM.Attendance;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AttendanceService implements AttendanceServiceInterface{

    private static String url= JDBCMapper.getUrl();
    private static String SQLusername=JDBCMapper.getSQLusername();
    private static String SQLpassword=JDBCMapper.getSQLpassword();

    @Override
    public List<Attendance> selectAttendByYID(int Yid) {
        String sql = "select * from attendance where Yid="+String.valueOf(Yid)+";";
        //执行查询
        jdbcTemplate jdbc = new jdbcTemplate() {
            @Override
            protected Object runSql(ResultSet set) {
                boolean isNull = true;
                List<Attendance> attendances = new ArrayList<>();
                try {
                    while (set.next()) {
                        isNull = false;
                        Attendance attendance = new Attendance();
                        attendance.setYid(set.getInt("Yid"));
                        attendance.setADate(set.getString("ADate"));
                        attendance.setInformation(set.getString("Information"));
                        attendances.add(attendance);
                    }
                }catch (SQLException e){
                    e.printStackTrace();
                }
                if(isNull) {
                    return null;
                }else {
                    return attendances;
                }
            }
        };
        Object lists = jdbc.run(url,SQLusername,SQLpassword,sql,"select");
        if(lists==null){
            return null;
        }else {
            try {
                return (List<Attendance>) lists;
            }catch (Exception e){
                return null;
            }
        }
    }

    @Override
    public void delete(int Yid) {
        String sql = "delete from attendance where Yid="+Yid+";";
        jdbcTemplate jdbc=new jdbcTemplate() {};
        jdbc.run(url,SQLusername,SQLpassword,sql,"delete");
    }

    @Override
    public void insert(Attendance attendance) {
        String sql = "insert into attendance(Yid,ADate,Information) values (" +
                String.valueOf(attendance.getYid()) +",'"+
                attendance.getADate()+"','"+
                attendance.getInformation() + "')";
        jdbcTemplate jdbc=new jdbcTemplate(){};
        jdbc.run(url,SQLusername,SQLpassword,sql,"insert");
    }
}
