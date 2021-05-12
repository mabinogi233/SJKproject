package com.sjkproject.system.JDBCUtils;



public class JDBCMapper {
    //连接数据库的信息
    private static String url="jdbc:mysql://124.71.239.172:3306/SJKproject2?characterEncoding=utf8&useSSL=false&serverTimezone=UTC&rewriteBatchedStatements=true";
    private static String SQLusername="root";
    private static String SQLpassword="Liuwenze0501";

    public static String getUrl() {
        return url;
    }

    public static String getSQLpassword() {
        return SQLpassword;
    }

    public static String getSQLusername() {
        return SQLusername;
    }

    public static void setUrl(String url) {
        JDBCMapper.url = url;
    }

    public static void setSQLpassword(String SQLpassword) {
        JDBCMapper.SQLpassword = SQLpassword;
    }

    public static void setSQLusername(String SQLusername) {
        JDBCMapper.SQLusername = SQLusername;
    }
}
