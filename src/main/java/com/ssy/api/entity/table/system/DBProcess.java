package com.ssy.api.entity.table.system;

/**
 * @Description 数据库进程
 * @Author sunshaoyu
 * @Date 2020年08月28日-14:36
 */
public class DBProcess {
    private String id;
    private String user;
    private String host;
    private String db;

    private String command;
    private String time;
    private String state;
    private String info;

    public DBProcess(String id, String user, String host, String db, String command, String time, String state, String info) {
        this.id = id;
        this.user = user;
        this.host = host;
        this.db = db;
        this.command = command;
        this.time = time;
        this.state = state;
        this.info = info;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getDb() {
        return db;
    }

    public void setDb(String db) {
        this.db = db;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "DBProcess{" +
                "id='" + id + '\'' +
                ", user='" + user + '\'' +
                ", host='" + host + '\'' +
                ", db='" + db + '\'' +
                ", command='" + command + '\'' +
                ", time='" + time + '\'' +
                ", state='" + state + '\'' +
                ", info='" + info + '\'' +
                '}';
    }
}
