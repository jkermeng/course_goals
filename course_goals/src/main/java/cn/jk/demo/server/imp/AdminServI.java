package cn.jk.demo.server.imp;

import cn.jk.demo.pojo.Admin;

import java.util.List;

public interface AdminServI {

    public List<Admin> showAll();

    public Admin Login(Admin admin);

    public Integer add(Admin admin);
}
