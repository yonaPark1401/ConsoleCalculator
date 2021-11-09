import java.util.Locale;
import java.util.Scanner;

public class consoleCalculator {
    static Scanner input = new Scanner(System.in);
    static int checkInt, checkChar;
    public static void main(String[] args)
    {
        int index; // char[index] = symbol +,-,* or /
        String expr; // expression from user
        int num1, num2;

        int choice=1;
        String choiceStr;
        while(choice!=0)
        {
            System.out.println("Choose the option:");
            System.out.println("1 - Calculate an expression");
            System.out.println("0 - Exit");
            choiceStr = input.nextLine();
            choice = Integer.parseInt(choiceStr);

            if(choice==1)
            {
                expr = getString();
                index=checkExpr(expr);

                if(index==0) continue;
                else
                {
                    checkInt = 0;
                    checkChar= 0;

                    num1 = getNum(expr, index, 0);
                    num2 = getNum(expr, expr.length()-(index+1), index+1);

                    if(num1==-1)
                    {
                        System.out.println("The first number is out of range");
                        continue;
                    }
                    else if(num2==-1)
                    {
                        System.out.println("The second number is out of range");
                        continue;
                    }
                    else if(num2==-2)
                    {
                        System.out.println("The values must be of the same type");
                        continue;
                    }
                    else  System.out.println("The result is: " + calc(expr,index,num1,num2));
                }
            }
            else if(choice!=0) System.out.println("Invalid input. Try again");
        }
    }
    public static String getString()
    {
        String expr;
        System.out.println("Enter the expression");
        System.out.println("Example: 5+6 or V+X ");
        System.out.println("Note: Numbers must be in range of 0-10");

        expr = input.nextLine();
        expr = expr.replaceAll("\\s","");
        return expr;
    }

    //check if the mathematical expression is valid
    public static int checkExpr(String expr)
    {

        for(int i=0; i<expr.length();i++)
        {
            if(expr.charAt(i)=='+' || expr.charAt(i)=='-' || expr.charAt(i)=='*' || expr.charAt(i)=='/')
            {
                if(i==0)
                {
                    System.out.println("Invalid expression");
                    break;
                }
                else return i;
            }

            else if (i==expr.length()-1)
                System.out.println("Invalid expression");

        }
        return 0;
    }

    //convert string into int value -> num1, num2
    public static Integer getNum(String expr, int indexArr, int indexExp)
    {
        char[] valArr = new char[indexArr];

        for(int i=0; i<indexArr; i++, indexExp++)
            valArr[i]=expr.charAt(indexExp);

        String num = new String(valArr);
        int val;
        if(isNumeric(num))
        {
            if(checkChar==1) return -2;
            else
            {
                val = Integer.parseInt(num);
                checkInt++;
            }
        }
        else
        {
            if(checkInt==1) return -2;
            else
            {
                val = numConv(num);
                checkChar++;
            }

        }

        if(val>=0.0 && val<=10.0) return val;
        else System.out.println("Invalid input");

        return -1;
    }

    //check is a string consisting of numbers ; is it possible to convert to a number directly
    public static boolean isNumeric(String str)
    {
        try
        {
            int d = Integer.parseInt(str);
        }
        catch (NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }

    //convert Roman numbers into Arabic
    public static Integer numConv(String str)
    {
        int num;
        str = str.toUpperCase(Locale.ROOT);
        switch(str)
        {
            case "I":
                num=1;
                break;
            case "II":
                num=2;
                break;
            case "III":
                num=3;
                break;
            case "IV":
                num=4;
                break;
            case "V":
                num=5;
                break;
            case "VI":
                num=6;
                break;
            case "VII":
                num=7;
                break;
            case "VIII":
                num=8;
                break;
            case "IX":
                num=9;
                break;
            case "X":
                num=10;
                break;
            default:
                num=-1;
                break;
        }
        return num;
    }

    //method to calculate the expression
    public static double calc(String expr, int index, double num1, double num2)
    {
        char sym = expr.charAt(index);
        switch(sym)
        {
            case '+': return num1+num2;
            case '-': return num1-num2;
            case '*': return num1*num2;
            case '/': return num1/num2;
        }
        return 0;
    }
}
