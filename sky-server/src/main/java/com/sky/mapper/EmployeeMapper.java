package com.sky.mapper;

import com.sky.entity.Employee;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface EmployeeMapper {

    /**
     * 根据用户名查询员工
     * @param username
     * @return
     */
    @Select("select * from employee where username = #{username}")
    Employee getByUsername(String username);


    /** x
     * 添加员工
     * @param employee
     * @return
     */
    @Insert("insert into employee (id, name, username, password, phone, sex, id_number, status, create_time, update_time, create_user, update_user) " +
            "values" +
            "(#{id},#{name},#{username},#{password},#{phone},#{sex},#{idNumber},#{status},#{createTime},#{updateTime},#{createUser},#{updateUser})"
    )
//    @Insert("insert into employee(name,username,password,phone,sex,id_number,create_time,update_time,create_user,update_user,status)" +
//            " values(#{name},#{username},#{password},#{phone},#{sex},#{idNumber},#{createTime},#{updateTime},#{createUser},#{updateUser},#{status})")
    void insert(Employee employee);



}
