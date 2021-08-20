package calc.util;

public class CellName{
    public final static String colIndexes = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static boolean isCellNameValid(String cellName){
        int digit;
        try {
            digit = Integer.parseInt(cellName.substring(1,cellName.length()));
        } catch (NumberFormatException e) {
            return false;
        }

        return colIndexes.contains(cellName.substring(0,1)) && digit >= 0;
    }

    public static int getColIndexFromCellName(String cellName) throws SheetException{
        if(!isCellNameValid(cellName)){
            throw new SheetException("Wrong Col index.");
        }
        else{
            return colIndexes.indexOf(cellName.substring(0,1));
        }
    }

    public static int getRowIndexFromCellName(String cellName) throws SheetException{
        if(!isCellNameValid(cellName)){
            throw new SheetException("Wrong Row index.");
        }
        else{
            return Integer.parseInt(cellName.substring(1,cellName.length()));
        }
    }
}