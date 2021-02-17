package loatr;

import loatr.Calculator;

import java.io.Reader;
import java.io.StringReader;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * 程序主方法
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner= new Scanner(System.in);
        Calculator calculator = new Calculator();
        while(true){
            System.out.println("请输入公式：");
            String expression = scanner.nextLine();
            try{
                String result = calculator.cal(expression);
                System.out.println(result);
            }catch (RuntimeException ex){
                System.out.println("程序出错啦，详细信息：");
                ex.printStackTrace();
            }
        }
    }

}
