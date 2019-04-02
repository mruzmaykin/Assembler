import java.io.*;
import java.util.Scanner;
public class Assembler {
    String ptr = "0010";
    int counter;
    Assembler(){
        counter = 0;
    }
    void readFile(String filePath) {
        if (isASM(filePath)) {
            File inputFile = new File(filePath);
            String oFileName = filePath.substring(0, filePath.length()-3) + "hack";
            File outputFile = new File(oFileName);
            BufferedWriter writer = null;
            try {
                writer = new BufferedWriter(new FileWriter(outputFile));
            } catch (IOException e) {
                e.printStackTrace();
            }String line;
            try {
                Scanner lineScanner = new Scanner(inputFile);
                while (lineScanner.hasNextLine()) {
                    line = lineScanner.nextLine();
                    if (isLabel(line)){
                        String result = "";
                        line = line.substring(1,line.length()-1);
                        if(!Variables.labels.containsKey(line)){
                            result = Integer.toBinaryString(counter);
                            result = Variables.formatHex(result);
                            result = result.trim();
                            System.out.println("resultato:" + result + "\nline:" + line);
                            Variables.labels.put(line,result);
                        }
                    }
                    else if (!isComment(line)) {
                        line = formatLine(line);
                        System.out.println(line);
                        //System.out.println("counter " + counter);
                        writer.write(getHexCode(line,counter) + "\n");
                        counter++;
                    }
                }
                writer.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        else System.out.println("Wrong file format.");
    }


    String getHexCode(String line, int counter){
        if(line.startsWith("@")) {
            // its A-instruction
            String result = "";
            line = line.substring(1);
            if (isInteger(line)) {
                result = Integer.toBinaryString(Integer.parseInt(line));
                result = Variables.formatHex(result);
            }
            else if (!Variables.labels.containsKey(line)){
                if (Variables.predefinedSymbols.containsKey(line)){
                    result = Variables.predefinedSymbols.get(line);
                }
                else {
                    if (Variables.variables.containsKey(line)) {
                        result = Variables.variables.get(line);
                    } else {
                        Variables.variables.put(line, ptr);
                        result = ptr;
                        incCount();
                        //result = Variables.hexToBin(result);
                    }
                }
                result = Variables.hexToBin(result);
                result = Variables.formatHex(result);
            }
            else
                result = Variables.labels.get(line);
            return result;
        }
        else
            {
                StringBuilder result = new StringBuilder();
                // its C-instruction
                result.append("111");
                String dest = "null";
                String comp = "";
                String jump = "null";
                int ptr = 0;
                if (line.contains("=")) {
                    String[] tokens = line.split("=");
                    dest = tokens[0];
                    line = tokens[1];
                }
                if (line.contains(";")) {
                    String[] tokens = line.split(";");
                    line = tokens[0];
                    jump = tokens[1];
                }
                comp = line;
                if (comp.contains("M")) {
                    comp = comp.replace('M', 'A');
                    result.append("1 ");
                } else
                    result.append("0 ");
                result.append(Variables.comp.get(comp));
                result.append(Variables.dest.get(dest));
                result.append(Variables.jump.get(jump));
//            System.out.println(Variables.comp.get(comp));
//            System.out.println("dest = " + dest + " comp = " + comp + " jump = " + jump);
                System.out.println(result.toString());
                return result.toString();
            }
    }
    void incCount(){
        int val = Integer.parseInt(ptr, 16) + 1;
        ptr = Integer.toHexString(val).toUpperCase();
        while (ptr.length() < 4) {
            ptr = "0" + ptr;
        }
    }
    Boolean isInteger(String line){
            try
            { int i = Integer.parseInt(line); return true; }

            catch(NumberFormatException er)
            {
                return false;
            }
    }
    Boolean isComment(String line){
        if (line.startsWith("//") || line.isEmpty())
            return true;
        return false;
    }
    Boolean isLabel(String line){
        if (line.startsWith("("))
            return true;
        return false;
    }
    Boolean isASM(String filename){
        if(filename.substring(filename.length()-4).equals(".asm"))
            return true;
        return false;
    }
    String getFileName(String filePath){
        String [] tokens = filePath.split(".");
        String filename = tokens[0];
        tokens = filename.split("/");
        return tokens[tokens.length-1];
    }


    String formatLine(String line){
        String formattedLine = "";
        line = line.trim();
        String strs[] = line.split("\\s+");
        for (String str:strs) {
            if (str.startsWith("//"))
                break;
            formattedLine+=str;
        }
        return formattedLine;
    }

}
