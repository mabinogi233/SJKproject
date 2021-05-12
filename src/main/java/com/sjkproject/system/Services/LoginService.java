package com.sjkproject.system.Services;


import com.sjkproject.system.JDBCUtils.JDBCMapper;
import com.sjkproject.system.JDBCUtils.jdbcTemplate;
import com.sjkproject.system.ORM.Account;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class LoginService implements LoginServiceInterface{

    private static String url= JDBCMapper.getUrl();
    private static String SQLusername=JDBCMapper.getSQLusername();
    private static String SQLpassword=JDBCMapper.getSQLpassword();

    @Override
    public Account selectOneByYid(int Yid) {
        String sql = "select * from account where Yid="+String.valueOf(Yid)+";";
        //执行查询
        jdbcTemplate jdbc = new jdbcTemplate() {
            @Override
            protected Object runSql(ResultSet set) {
                boolean isNull = true;
                Account account = new Account();
                try {
                    while (set.next()) {
                        isNull = false;
                        account.setYid(set.getInt("Yid"));
                        account.setUserId(set.getString("UserId"));
                        account.setPassword(set.getString("Password"));
                        account.setJurisdiction(set.getString("Jurisdiction"));
                    }
                }catch (SQLException e){
                    e.printStackTrace();
                }
                if(isNull) {
                    return null;
                }else {
                    return account;
                }
            }
        };
        Object p = jdbc.run(url,SQLusername,SQLpassword,sql,"select");
        if(p==null){
            return null;
        }else {
            try {
                return (Account) p;
            }catch (Exception e){
                return null;
            }
        }
    }

    @Override
    public void update(Account account) {
        String sql = "update account set " +
                "Yid="+account.getYid()+","+
                "UserId='"+account.getUserId()+"',"+
                "Password='"+account.getPassword()+"',"+
                "Jurisdiction='"+account.getJurisdiction()+"' "+
                "where Yid="+account.getYid()+";";
        jdbcTemplate jdbc=new jdbcTemplate() {};
        jdbc.run(url,SQLusername,SQLpassword,sql,"update");
    }

    @Override
    public void delete(int Yid) {
        String sql = "delete from account where Yid="+Yid+";";
        jdbcTemplate jdbc=new jdbcTemplate() {};
        jdbc.run(url,SQLusername,SQLpassword,sql,"delete");
    }

    @Override
    public void insert(Account account) {
        String sql = "insert into account(Yid,UserId,Password,Jurisdiction) values (" +
                String.valueOf(account.getYid()) +",'"+
                account.getUserId()+"','"+
                account.getPassword()+"','"+
                account.getJurisdiction() + "')";
        jdbcTemplate jdbc=new jdbcTemplate(){};
        jdbc.run(url,SQLusername,SQLpassword,sql,"insert");
    }

}
