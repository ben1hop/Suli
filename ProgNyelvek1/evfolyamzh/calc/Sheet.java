package calc;

import calc.Evaluable;
import java.lang.IllegalArgumentException;
import java.lang.StringBuilder;
import calc.util.SheetException;

import static calc.util.CellName.colIndexes;
import static calc.util.CellName.getColIndexFromCellName;
import static calc.util.CellName.getRowIndexFromCellName;

public class Sheet{
    private Evaluable[][] tomb;

    public Sheet(int numOfRows , int numOfCols){
        if(numOfCols > colIndexes.length() || numOfCols < 0 || numOfCols < 0){
            throw new IllegalArgumentException("Sheet dimension exception");
        }else{
            tomb = new Evaluable[numOfRows][numOfCols];
        }
    }

    public void insertToSheet(String cellName , Evaluable ref){
        int col = 0;
        int row = 0;
        try{
            col = getColIndexFromCellName(cellName);
            row = getRowIndexFromCellName(cellName);
        }catch(SheetException e){
            e.printStackTrace();
        }
        if(row < tomb.length && col < tomb[0].length){
            tomb[row][col] = ref;
        }
        else{
            throw new IllegalArgumentException("Insert error");
        }
    }

    public Evaluable getFromSheet(String cellName){
        int col = 0;
        int row = 0;
        try{
            col = getColIndexFromCellName(cellName);
            row = getRowIndexFromCellName(cellName);
        }catch(SheetException e){
            e.printStackTrace();
        }
        if(row < tomb.length && col < tomb[0].length){
            return tomb[row][col];
        }
        else{
            throw new IllegalArgumentException("Insert error");
        }
    }

    public static int constructIntFromOperandStr(String operandStr, Sheet s) throws SheetException{
        if(Character.isUpperCase(operandStr.charAt(0))){
            return s.getFromSheet(operandStr).eval(s);
        }
        else{
            return Integer.parseInt(operandStr);
        }
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < tomb.length;i++){
            for(int j = 0 ; j < tomb[0].length;j++){
                if(tomb[i][j] == null){
                    sb.append("null");
                }else{
                    try{
                        sb.append(tomb[i][j].eval(this));
                    }catch(SheetException e){
                        e.printStackTrace();
                    }      
                }
                if(j != (tomb[0].length - 1) ){
                    sb.append(" ");
                }
                else{
                    if(i != (tomb.length -1)){
                        sb.append("\n");
                    }         
                }
            }
        }
        return sb.toString();
    }

}