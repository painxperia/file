package com.zpain.file.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {

    private T data;

    public static <T> Result<T> success(T t){
        return new Result<>(t);
    }

}
