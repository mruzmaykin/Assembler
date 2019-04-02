import java.math.BigInteger;
import java.util.HashMap;

public class Variables {

    static HashMap<String, String> predefinedSymbols;
    static {
        predefinedSymbols = new HashMap<String, String>();
        predefinedSymbols.put("SP", "0000");
        predefinedSymbols.put("ARG", "0002");
        predefinedSymbols.put("THIS", "0003");
        predefinedSymbols.put("THAT", "0004");
        predefinedSymbols.put("R0", "0000");
        predefinedSymbols.put("R1", "0001");
        predefinedSymbols.put("R2", "0002");
        predefinedSymbols.put("R3", "0003");
        predefinedSymbols.put("R4", "0004");
        predefinedSymbols.put("R5", "0005");
        predefinedSymbols.put("R6", "0006");
        predefinedSymbols.put("R7", "0007");
        predefinedSymbols.put("R8", "0008");
        predefinedSymbols.put("R9", "0009");
        predefinedSymbols.put("R10", "000A");
        predefinedSymbols.put("R11", "000B");
        predefinedSymbols.put("R12", "000C");
        predefinedSymbols.put("R13", "000D");
        predefinedSymbols.put("R14", "000E");
        predefinedSymbols.put("R15", "000F");
        predefinedSymbols.put("SCREEN", "4000");
        predefinedSymbols.put("KBD", "6000");
    }
    static HashMap<String, String> variables;
    static {
        variables = new HashMap<String,String>();
    }
    static HashMap<String, String> comp;
    static {
        comp = new HashMap<String, String>();
        comp.put("0","1010 10");
        comp.put("1","1111 11");
        comp.put("-1","1110 10");
        comp.put("D","0011 00");
        comp.put("A","1100 00");
        comp.put("!D","0011 01");
        comp.put("!A","1100 01");
        comp.put("-D","0011 11");
        comp.put("-A","1100 11");
        comp.put("D+1","0111 11");
        comp.put("A+1","1101 11");
        comp.put("D-1","0011 10");
        comp.put("A-1","1100 10");
        comp.put("D+A","0000 10");
        comp.put("D-A","0100 11");
        comp.put("A-D","0001 11");
        comp.put("D&A","0000 00");
        comp.put("D|A","0101 01");
    }

    static HashMap<String, String> dest;
    static {
        dest = new HashMap<String, String>();
        dest.put("null", "00 0");
        dest.put("M", "00 1");
        dest.put("D", "01 0");
        dest.put("MD", "01 1");
        dest.put("A", "10 0");
        dest.put("AM", "10 1");
        dest.put("AD", "11 0");
        dest.put("AMD", "11 1");
    }
    static HashMap<String, String> jump;
    static {
        jump = new HashMap<String, String>();
        jump.put("null", "000");
        jump.put("JGT", "001");
        jump.put("JEQ", "010");
        jump.put("JGE", "011");
        jump.put("JLT", "100");
        jump.put("JNE", "101");
        jump.put("JLE", "110");
        jump.put("JMP", "111");
    }
    static String hexToBin(String s) {
        System.out.println("TRouble string: " + s.trim());
        return  new BigInteger(s.trim(), 16).toString(2);
    }
    static String formatHex(String s){
        StringBuilder result = new StringBuilder();
        result.append(s);
        while(result.length() < 16)
            result.insert(0,"0");
        int ptr = 4;
        for(int i = 0; i < 4; i++){
            result.insert(ptr+5*i, " ");
        }
        return result.toString();
    }
    static HashMap<String, String> labels;
    static {
        labels = new HashMap<String, String>();
    }
}
