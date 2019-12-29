package spring.InstantiationAwareBeanPostProcessor;

import org.springframework.beans.factory.annotation.Value;

public class TestFb  {

    //XML方式 + properties文件注入值
    public String db_username;

    //@Configuration类方式+ properties文件注入值
    @Value("db.password")
    private  String db_password;

    public String getDb_password() {
        return db_password;
    }

    public void setDb_password(String db_password) {
        this.db_password = db_password;
    }

    public String getDb_username() {
        return db_username;
    }

    public void setDb_username(String db_username) {
        this.db_username = db_username;
    }

    public void dosomething() {
        System.out.print("执行了dosomething.......\n");
    }
}

