package calc;

import calc.util.SheetException;

public interface Evaluable{
    public abstract int eval(Sheet s) throws SheetException;
}