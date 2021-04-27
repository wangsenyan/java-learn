package com.wsy.basis.base;

import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

public class Enums {
    private static EnumSet<STAUTS> waitingStatuses =
            EnumSet.of(STAUTS.WAITING,STAUTS.TIME_WATING);
    private STAUTS stauts;
    public enum STAUTS{
        NEW,
        RUNABLE,
        WAITING,
        TIME_WATING,
        BLOCKED,
        TERMINATE;
    }
    public int getStatus(){
        switch (stauts){
            case NEW:
            case BLOCKED:
            case RUNABLE:
            case WAITING:
            case TIME_WATING:
            case TERMINATE:
            default:
                return 0;
        }
    }
    public static List<Enums> getAllWaiting(List<Enums> input){
        return input.stream().filter(
                (s)-> waitingStatuses.contains(s.getStatus()))
                .collect(Collectors.toList());
    }
}
enum SingletonConfiguration {
    INSTANCE;
    SingletonConfiguration(){

    }
}
