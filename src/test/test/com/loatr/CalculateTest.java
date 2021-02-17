package test.com.loatr;

import loatr.ExpressionBuilder;

import java.util.regex.Pattern;

/**
 * Calculate对应的测试类。用来写Calculate中方法对应的测试用例
 */
public class CalculateTest {

    public static void main(String[] args) {
        CalculateTest test = new CalculateTest();
//        test.testRegex();
        test.testFindEndParenthesis();
    }

    public void testRegex(){
        String expression = "(1+2)*2 + 3-4/2";
        String regex = "^([0-9]|\\+|=|-|\\*|/|\\(|\\)|\\s)*";
        boolean result = Pattern.matches(regex,expression);
        System.out.println(result);
    }

    public void testFindEndParenthesis(){
        String str = "((()(";
        int index = ExpressionBuilder.findEndParenthesis(1,str.toCharArray());
        System.out.println(index);
    }
}
