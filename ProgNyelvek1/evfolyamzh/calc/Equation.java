package calc;

import calc.Evaluable;
import calc.Sheet;
import java.lang.IllegalArgumentException;
import calc.util.SheetException;
import static calc.util.CellName.isCellNameValid;
import static calc.Sheet.constructIntFromOperandStr;

public class Equation implements Evaluable{

    private String operand1;
    private String operand2;
    private Character operator;

    public Equation(String cellName) throws IllegalArgumentException {
        if(!cellName.contains("+") && !cellName.contains("-") && !cellName.contains("*") && !cellName.contains("/")){
            throw new IllegalArgumentException("Wrong operator");
        }
        for(int i = 0 ; i < cellName.length();i++){
            if(cellName.charAt(i) == '+'){
                operator = '+';
                String[] op = cellName.split("\\+");
                    operand1 = op[0];
                    operand2 = op[1];
                
                break;
            }
            if(cellName.charAt(i) == '-'){
                operator = '-';
                String[] op = cellName.split("\\-");
                    operand1 = op[0];
                    operand2 = op[1];
                break;
            }
            if(cellName.charAt(i) == '*'){
                operator = '*';
                String[] op = cellName.split("\\*");
                    operand1 = op[0];
                    operand2 = op[1];

                break;
            }
            if(cellName.charAt(i) == '/'){
                operator = '/';
                String[] op = cellName.split("\\/");
                    operand1 = op[0];
                    operand2 = op[1];
                break;
            }
        }


    }

    @Override
    public final int eval(Sheet s) throws SheetException{ 
        int i = constructIntFromOperandStr(operand1,s);
        int j = constructIntFromOperandStr(operand2,s);
        switch(operator){
            case '+':
            return i+j;
            case '-':
                if(i-j < 0){
                    throw new ArithmeticException("Operator error");
                }else{
                    return i-j;
                }              
            case '*':
            return i*j;
            case '/':
                if(j == 0){
                    throw new ArithmeticException("Operator error");
                }else{
                    return i/j;
                } 
            default:
            return 0;
        }
    }
}