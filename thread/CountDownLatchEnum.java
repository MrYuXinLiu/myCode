package com.example.thread;

import jdk.nashorn.internal.objects.annotations.Getter;

/**
 * @author Lxy
 */
public enum CountDownLatchEnum {
    ONE(1,"齐国"),TWO(2,"楚国"),THREE(3,"燕国"),FOUR(4,"赵国"),FIVE(5,"魏国"),SIX(6,"韩国"),SEVEN(7,"秦国");

    private Integer id;
    private String message;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    CountDownLatchEnum(Integer id, String message) {
        this.id = id;
        this.message = message;
    }

    public static CountDownLatchEnum foreach(int i){
        for (CountDownLatchEnum elempent:CountDownLatchEnum.values()) {
            if (elempent.getId()==i){
                return elempent;
            }
        }
        return null;
    }
}
