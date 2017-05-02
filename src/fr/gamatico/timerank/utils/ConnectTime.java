package fr.gamatico.timerank.utils;

/**
 * @Author Gamatico
 * @Date 09/04/2017
 */
public class ConnectTime {

    private long milis;

    public ConnectTime(long milis){
        this.milis = milis;
    }

    public int getSecond(){
        return (int) milis / 1000;
    }
}
