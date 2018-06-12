package com.aion.dashboard.utility;

@SuppressWarnings("Duplicates")
public class Utility {
    public static boolean validLong(String searchParam) {
        try {
            if(searchParam.length()>18 || !searchParam.matches("-?[0-9]+"))
                return false;
            else
                return true;
        }catch(Exception e) {
            return false;
        }
    }

    public static boolean validInt(String searchParam) {
        try {
            if(searchParam.length()>18 || !searchParam.matches("-?[0-9]+"))
                return false;
            else
                return true;
        }catch(Exception e) {
            return false;
        }
    }

    public static boolean validHex(String searchParam) {
        if(searchParam.length()!=64 || !searchParam.matches("-?[0-9a-fA-F]+"))
            return false;
        else
            return true;
    }
}
