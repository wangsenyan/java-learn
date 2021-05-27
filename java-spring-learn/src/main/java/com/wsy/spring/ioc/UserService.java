package com.wsy.spring.ioc;

public class UserService {
    private UserDao dao;
    public void setDao(UserDao dao) {
        this.dao = dao;
    }
    public void execute(){
        System.out.println("真尼玛繁琐");
        dao.add();
    }
}

interface UserDao{
    void add();
}
class UserDaoImpl implements UserDao{
    public void add() {
        System.out.println("UserDao.add()");
    }
}
class UserFactory{
    static UserDao dao;
    public static UserDao getInstance(){
        if(dao == null){
            synchronized (UserFactory.class){
                if(dao == null){
                    dao = new UserDaoImpl();
                }
            }
        }
        return dao;
    }
    public static UserDao getDao(){
        try {
            Class<?> clazz = Class.forName("userDao");
            dao =  (UserDao) clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dao;
    }
}