package com.ebanma.cloud.usertestall.domain.dto;

import com.ebanma.cloud.usertestall.util.InsertValidationGroup;
import com.ebanma.cloud.usertestall.util.UpdateValidationGroup;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.io.Serializable;

public class UserDTO implements Serializable  {


    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 6728497964686236776L;

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空",groups = InsertValidationGroup.class)
    private String username;

    /**
     * 用户密码
     */
    @NotBlank(message = "用户名不能为空",groups = InsertValidationGroup.class)
    @Length(min =6,max = 20,message = "密码长度不能少于6位，不能多于20位")
    private String password;

    /**
     * 邮箱
     */
    @NotEmpty(message = "邮箱不能为空",groups = InsertValidationGroup.class)
    @Email(message = "必须为有效的邮箱格式")
    private String email;

    /**
     * 年龄
     */
    @NotNull(message = "年龄不能为空！",groups = InsertValidationGroup.class)
    @Max(value = 100,message = "年龄不能大于100岁")
    @Min(value = 10,message = "年龄不能小于10岁")
    private Integer age;


    /**
     * 手机号
     */
    @NotBlank(message = "手机不能为空",groups = InsertValidationGroup.class)
    private String phone;

    /**
     * 版本号
     */
    @NotNull(message = "版本号不能为空",groups = UpdateValidationGroup.class)
    private Long version;



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

}