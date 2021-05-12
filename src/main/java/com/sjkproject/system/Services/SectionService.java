package com.sjkproject.system.Services;


import com.sjkproject.system.JDBCUtils.JDBCMapper;
import com.sjkproject.system.JDBCUtils.jdbcTemplate;
import com.sjkproject.system.ORM.Section;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SectionService implements SectionServiceInterface{

    private static String url= JDBCMapper.getUrl();
    private static String SQLusername=JDBCMapper.getSQLusername();
    private static String SQLpassword=JDBCMapper.getSQLpassword();

    @Override
    public List<Section> selectAll() {
        String sql = "select * from section;";
        //执行查询
        jdbcTemplate jdbc = new jdbcTemplate() {
            @Override
            protected Object runSql(ResultSet set) {
                boolean isNull = true;
                List<Section> sections = new ArrayList<>();
                try {
                    while (set.next()) {
                        isNull = false;
                        Section section = new Section();
                        section.setBid(set.getInt("Bid"));
                        section.setBName(set.getString("BName"));
                        section.setLocation(set.getString("Location"));
                        sections.add(section);
                    }
                }catch (SQLException e){
                    e.printStackTrace();
                }
                if(isNull) {
                    return null;
                }else {
                    return sections;
                }
            }
        };
        Object lists = jdbc.run(url,SQLusername,SQLpassword,sql,"select");
        if(lists==null){
            return null;
        }else {
            try {
                return (List<Section>) lists;
            }catch (Exception e){
                return null;
            }
        }
    }

    @Override
    public Section selectOneById(int Bid) {
        String sql = "select * from section where Bid = "+String.valueOf(Bid)+";";
        //执行查询
        jdbcTemplate jdbc = new jdbcTemplate() {
            @Override
            protected Object runSql(ResultSet set) {
                boolean isNull = true;
                Section section = new Section();
                try {
                    while (set.next()) {
                        isNull = false;
                        section.setBid(set.getInt("Bid"));
                        section.setBName(set.getString("BName"));
                        section.setLocation(set.getString("Location"));
                    }
                }catch (SQLException e){
                    e.printStackTrace();
                }
                if(isNull) {
                    return null;
                }else {
                    return section;
                }
            }
        };
        Object s = jdbc.run(url,SQLusername,SQLpassword,sql,"select");
        if(s==null){
            return null;
        }else {
            try {
                return (Section) s;
            }catch (Exception e){
                return null;
            }
        }
    }

    @Override
    public void update(Section section) {
        String sql = "update section set " +
                "Bid="+section.getBid()+","+
                "BName='"+section.getBName()+"',"+
                "Location='"+section.getLocation()+"' "+
                "where Bid="+section.getBid()+";";
        jdbcTemplate jdbc=new jdbcTemplate() {};
        jdbc.run(url,SQLusername,SQLpassword,sql,"update");
    }

    @Override
    public void delete(int Bid) {
        String sql = "delete from section where Bid="+Bid+";";
        jdbcTemplate jdbc=new jdbcTemplate() {};
        jdbc.run(url,SQLusername,SQLpassword,sql,"delete");
    }

    @Override
    public void insert(Section section) {
        String sql = "insert into section(Bid,BName,Location) values (" +
                String.valueOf(section.getBid()) +",'"+
                section.getBName()+"','"+
                section.getLocation() + "')";
        jdbcTemplate jdbc=new jdbcTemplate(){};
        jdbc.run(url,SQLusername,SQLpassword,sql,"insert");
    }
}
