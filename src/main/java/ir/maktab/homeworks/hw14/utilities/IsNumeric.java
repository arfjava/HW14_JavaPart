package ir.maktab.homeworks.hw14.utilities;

public class IsNumeric {
    public static boolean execute(String string){
        try {
            Long.parseLong(string);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
