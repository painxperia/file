package com.zpain.file;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Component;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class User implements BeanNameAware {

    private Long id;

    @JSONField(name = "user_name")
    private String userName;

    public User(Long id) {
        this.id = id;
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("name:"+name);
    }
}
