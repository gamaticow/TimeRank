package fr.gamatico.timerank;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Gamatico
 * @Date 09/04/2017
 */
public class Rank {

    private static List<Rank> rankList = new ArrayList<>();

    private String name;
    private int time;
    private List<String> permission;

    public Rank(String name, int time, List<String> permission){
        this.name = name;
        this.time = time;
        this.permission = permission;
    }

    public String getName(){
        return name;
    }

    public int getNeededTime(){
        return time;
    }

    public List<String> getPermissions(){
        return permission;
    }

    public void register(){
        rankList.add(this);
    }

    public static void clearRank(){
        rankList.clear();
    }

    public static List<Rank> getRanks(){
        return rankList;
    }

    public static void listRank(){
        for(Rank r : rankList){
            System.out.println("Rank : " + r.name + " / timeNeeded : " + r.time + " / permissions : " + r.permission);
        }
    }

}
