package com.sjkproject.system.Services;


import com.sjkproject.system.JDBCUtils.JDBCMapper;
import com.sjkproject.system.JDBCUtils.jdbcTemplate;
import com.sjkproject.system.ORM.Salary;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SalaryService implements SalaryServiceInterface {

    private static String url= JDBCMapper.getUrl();
    private static String SQLusername=JDBCMapper.getSQLusername();
    private static String SQLpassword=JDBCMapper.getSQLpassword();

    @Override
    public List<Salary> selectByYID(int Yid) {
        String sql = "select * from salary where Yid="+String.valueOf(Yid)+";";
        //执行查询
        jdbcTemplate jdbc = new jdbcTemplate() {
            @Override
            protected Object runSql(ResultSet set) {
                boolean isNull = true;
                List<Salary> salaries = new ArrayList<>();
                try {
                    while (set.next()) {
                        isNull = false;
                        Salary salary = new Salary();
                        salary.setYid(set.getInt("Yid"));
                        salary.setSDate(set.getString("SDate"));
                        salary.setBasePay(set.getDouble("BasePay"));
                        salary.setReward(set.getDouble("Reward"));
                        salaries.add(salary);
                    }
                }catch (SQLException e){
                    e.printStackTrace();
                }
                if(isNull) {
                    return null;
                }else {
                    return salaries;
                }
            }
        };
        Object lists = jdbc.run(url,SQLusername,SQLpassword,sql,"select");
        if(lists==null){
            return null;
        }else {
            try {
                return (List<Salary>) lists;
            }catch (Exception e){
                return null;
            }
        }
    }

    @Override
    public void delete(int Yid) {
        String sql = "delete from salary where Yid="+Yid+";";
        jdbcTemplate jdbc=new jdbcTemplate() {};
        jdbc.run(url,SQLusername,SQLpassword,sql,"delete");
    }

    @Override
    public void insert(Salary salary) {
        String sql = "insert into salary(Yid,SDate,BasePay,Reward) values (" +
                String.valueOf(salary.getYid()) +",'"+
                salary.getSDate()+"','"+
                salary.getBasePay()+"','"+
                salary.getReward() + "')";
        jdbcTemplate jdbc=new jdbcTemplate(){};
        jdbc.run(url,SQLusername,SQLpassword,sql,"insert");
    }
}
