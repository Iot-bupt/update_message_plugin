package com.bupt.updateMessage.redis;

public interface queue<T>{
    // Set<String> TreeSet<>

    // @Cache
    T getData() ; // id +  type

    // @Cache
     void addData(T data);

    public T getUnSentData() ;

    public void pushLastdData(T data);
}
