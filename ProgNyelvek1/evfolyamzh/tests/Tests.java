package tests;

import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import calc.Num;
import calc.Equation;
import calc.Sheet;

import java.lang.IllegalArgumentException;

public class Tests
{
    // isCelLNameValid()
    @Test
    public void containSpace(){
        assertFalse(calc.util.CellName.isCellNameValid("C 1"));
    }
    @Test
    public void containsLowercase(){
        assertFalse(calc.util.CellName.isCellNameValid("c1"));
    }
    @Test
    public void containsUppercase(){
        assertFalse(calc.util.CellName.isCellNameValid("C{"));
    }
    @Test
    public void containsLessTen(){
        assertTrue(calc.util.CellName.isCellNameValid("C8"));
    }
    @Test
    public void containsGreaterNine(){
        assertTrue(calc.util.CellName.isCellNameValid("C18"));
    }
    @Test
    public void negativeValue(){
        assertFalse(calc.util.CellName.isCellNameValid("C-1"));
    }

    // getRowIndexFromCellName()
    @Test
    public void greaterTen() throws Exception{
        assertEquals( 11 , calc.util.CellName.getRowIndexFromCellName("C11"));
    }
    @Test
    public void lessTen() throws Exception{
        assertEquals( 6 , calc.util.CellName.getRowIndexFromCellName("C6"));
    }

    // getColIndexFromCellName()
    @Test
    public void valueA() throws Exception{
        assertEquals( 0 , calc.util.CellName.getColIndexFromCellName("A6"));
    }
    @Test
    public void valueRandom() throws Exception{
        assertEquals( 9 , calc.util.CellName.getColIndexFromCellName("J6"));
    }




    // 2. resz
    @Test
    public void constructNum() throws Exception{
        Num a = new Num(23);
        assertEquals(23, a.eval(null));
    }

    @Test
    public void eq0() throws Exception{
        Equation eq = new Equation("C1+B1");
    }

    @Test(expected = IllegalArgumentException.class)
    public void eq1(){
        Equation eq= new Equation("C 1*D3");
    }

    @Test(expected = IllegalArgumentException.class)
    public void eq2(){
        Equation eq= new Equation("k10*C2");
    }

    @Test(expected = IllegalArgumentException.class)
    public void eq3(){
        Equation eq= new Equation("A1;B2");
    }


    // 3. resz
    @Test
    public void eq11() throws IllegalArgumentException{
        Sheet sheet = new Sheet(3,3);
        sheet.insertToSheet("A0" ,new Num(6));
        sheet.insertToSheet("A1" ,new Num(2));
        sheet.insertToSheet("A2" ,new Num(2));
        sheet.insertToSheet("B0" ,new Num(5));
        sheet.insertToSheet("B1" ,new Num(6));
        sheet.insertToSheet("B2" ,new Num(9));

        sheet.insertToSheet("C0" , new Equation("A0+B0"));
        sheet.insertToSheet("C1" , new Equation("A1+B1"));
        sheet.insertToSheet("C2" , new Equation("A2+B2"));

        System.out.println("\n"); // kell a junit logja miatt
        System.out.println(sheet.toString());
    }

    @Test
    public void eq12() throws IllegalArgumentException{
        Sheet sheet = new Sheet(3,4);
        sheet.insertToSheet("A0" ,new Num(6));
        sheet.insertToSheet("A1" ,new Num(2));
        sheet.insertToSheet("A2" ,new Num(2));
        sheet.insertToSheet("B0" ,new Num(5));
        sheet.insertToSheet("B1" ,new Num(6));
        sheet.insertToSheet("B2" ,new Num(9));

        sheet.insertToSheet("C0" , new Equation("A0+B0"));
        sheet.insertToSheet("C1" , new Equation("A1+B1"));
        sheet.insertToSheet("C2" , new Equation("A2+B2"));

        
        for(int i = 0 ; i < 3; i++){
            sheet.insertToSheet("D"+i , new Equation("C"+i+"/2"));
        }

        System.out.println("\n"); // kell a junit logja miatt
        System.out.println(sheet.toString());
    }

    @Test
    public void eq13(){
        Sheet sheet = new Sheet(3,3);
        sheet.insertToSheet("A0" ,new Num(6));
        sheet.insertToSheet("A1" ,new Num(2));
        sheet.insertToSheet("A2" ,new Num(2));
        sheet.insertToSheet("B0" ,new Num(5));
        sheet.insertToSheet("B1" ,new Num(6));
        sheet.insertToSheet("B2" ,new Num(9));

        for(int i = 0 ; i < 3; i++){
            sheet.insertToSheet("C"+i , new Equation("10*B"+i));
        }

        System.out.println("\n"); // kell a junit logja miatt
        System.out.println(sheet.toString());

    }

}