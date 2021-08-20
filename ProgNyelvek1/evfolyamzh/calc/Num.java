package calc;

import calc.Evaluable;
import calc.Sheet;
import calc.util.SheetException;
import java.lang.IllegalArgumentException;

public class Num implements Evaluable{
    private int value;
    public Num(int arg){
        if(arg < 0){
            throw new IllegalArgumentException("Negative number.");
        }
        else{
            value = arg;
        }
    }
    @Override
    public final int eval(Sheet s) throws SheetException{
        return value;
    }
}