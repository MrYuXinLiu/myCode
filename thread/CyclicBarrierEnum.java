package com.example.thread;

public enum  CyclicBarrierEnum {
    ONE(1,"大娃"),TWO(2,"二娃"),THREE(3,"三娃"),FOUR(4,"四娃"),FIVE(5,"五娃"),SIX(6,"六娃"),SEVEN(7,"七娃")  ;


    private Integer id;
    private String message;

    public static CyclicBarrierEnum foreach(int index){

        for (CyclicBarrierEnum elemplment:CyclicBarrierEnum.values()) {
            if (elemplment.id == index){
                return elemplment;
            }
        }


        return null;
    }





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

    CyclicBarrierEnum(Integer id, String message) {
        this.id = id;
        this.message = message;
    }
}
