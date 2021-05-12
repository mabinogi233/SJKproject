package com.sjkproject.system.Services;


import com.sjkproject.system.JDBCUtils.JDBCMapper;
import com.sjkproject.system.JDBCUtils.jdbcTemplate;
import com.sjkproject.system.ORM.Person;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService implements PersonServiceInterface{

    private static String url= JDBCMapper.getUrl();
    private static String SQLusername=JDBCMapper.getSQLusername();
    private static String SQLpassword=JDBCMapper.getSQLpassword();

    @Override
    public List<Person> selectAll() {
        String sql = "select * from person;";
        //执行查询
        jdbcTemplate jdbc = new jdbcTemplate() {
            @Override
            protected Object runSql(ResultSet set) {
                boolean isNull = true;
                List<Person> people = new ArrayList<>();
                try {
                    while (set.next()) {
                        isNull = false;
                        Person person = new Person();
                        person.setYid(set.getInt("Yid"));
                        person.setBid(set.getInt("Bid"));
                        person.setAge(set.getInt("Age"));
                        person.setIDCardNumber(set.getString("IDCardNumber"));
                        person.setPost(set.getString("Post"));
                        person.setSex(set.getString("Sex"));
                        person.setYName(set.getString("YName"));
                        people.add(person);
                    }
                }catch (SQLException e){
                    e.printStackTrace();
                }
                if(isNull) {
                    return null;
                }else {
                    return people;
                }
            }
        };
        Object lists = jdbc.run(url,SQLusername,SQLpassword,sql,"select");
        if(lists==null){
            return null;
        }else {
            try {
                return (List<Person>) lists;
            }catch (Exception e){
                return null;
            }
        }
    }

    @Override
    public Person selectOnePersonById(int Yid) {
        String sql = "select * from person where Yid="+String.valueOf(Yid)+";";
        //执行查询
        jdbcTemplate jdbc = new jdbcTemplate() {
            @Override
            protected Object runSql(ResultSet set) {
                boolean isNull = true;
                Person person = new Person();
                try {
                    while (set.next()) {
                        isNull = false;
                        person.setYid(set.getInt("Yid"));
                        person.setBid(set.getInt("Bid"));
                        person.setAge(set.getInt("Age"));
                        person.setIDCardNumber(set.getString("IDCardNumber"));
                        person.setPost(set.getString("Post"));
                        person.setSex(set.getString("Sex"));
                        person.setYName(set.getString("YName"));
                    }
                }catch (SQLException e){
                    e.printStackTrace();
                }
                if(isNull) {
                    return null;
                }else {
                    return person;
                }
            }
        };
        Object p = jdbc.run(url,SQLusername,SQLpassword,sql,"select");
        if(p==null){
            return null;
        }else {
            try {
                return (Person) p;
            }catch (Exception e){
                return null;
            }
        }
    }

    @Override
    public void update(Person person) {
        String sql = "update person set " +
                "Yid="+person.getYid()+","+
                "Bid="+person.getBid()+","+
                "Age="+person.getAge()+","+
                "YName='"+person.getYName()+"',"+
                "Sex='"+person.getSex()+"',"+
                "IDCardNumber='"+person.getIDCardNumber()+"',"+
                "Post='"+person.getPost()+"' "+
                "where Yid="+person.getYid()+"";
        jdbcTemplate jdbc=new jdbcTemplate() {};
        jdbc.run(url,SQLusername,SQLpassword,sql,"update");
    }

    @Override
    public void delete(int Yid) {
        String sql = "delete from person where Yid="+Yid+";";
        jdbcTemplate jdbc=new jdbcTemplate() {};
        jdbc.run(url,SQLusername,SQLpassword,sql,"delete");
    }

    @Override
    public void insert(Person person) {
        String sql = "insert into person(Yid,Bid,Age,YName,Sex,IDCardNumber,Post) values (" +
                String.valueOf(person.getYid()) +","+
                String.valueOf(person.getBid()) +","+
                String.valueOf(person.getAge()) +",'"+
                person.getYName()+"','"+
                person.getSex()+"','"+
                person.getIDCardNumber()+"','"+
                person.getPost() + "')";
        jdbcTemplate jdbc=new jdbcTemplate(){};
        jdbc.run(url,SQLusername,SQLpassword,sql,"insert");
    }
}
