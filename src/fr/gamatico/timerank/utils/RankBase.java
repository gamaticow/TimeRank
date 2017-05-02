package fr.gamatico.timerank.utils;

import org.bukkit.permissions.PermissibleBase;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.ServerOperator;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Gamatico
 * @Date 09/04/2017
 */
public class RankBase extends PermissibleBase {

    private List<String> perms = new ArrayList<>();

    public RankBase(final boolean op, List<String> p) {
        super(new ServerOperator() {
            @Override
            public boolean isOp() {
                return op;
            }

            public void setOp(boolean paramBoolean) {
            }
        });

        for(String permission : p){
            this.perms.add(permission.toLowerCase());
        }
    }

    public boolean hasPermission(String inName) {
        System.out.println("pemr node : " + inName);

        if(perms.contains(inName.toLowerCase())){
            return true;
        }

        if(perms.contains("*")) return true;

        String[] sn = inName.toLowerCase().split(".");

        for(String perm : perms){
            if(perm.contains("*")){
                String[] sp = perm.split(".");
                if(sn.length >=  sp.length){
                    for(int i = 0; i < sp.length; i++){
                        if(sp[i].equalsIgnoreCase("*")){
                            return true;
                        }else if(!sp[i].equalsIgnoreCase(sn[i])){
                            break;
                        }
                    }
                }
            }
        }

        return false;
    }

    public boolean hasPermission(Permission perm) {
        return hasPermission(perm.getName());
    }
}
