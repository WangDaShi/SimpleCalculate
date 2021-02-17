package loatr;

import loatr.expression.Expression;

import java.util.regex.Pattern;


public class Calculator {

    // 正则表达式，判断表达式是否只包含以下字符：数字 . + - * / ( ) 和空格
    private static final String regex = "^([0-9]|\\.|\\+|=|-|\\*|/|\\(|\\)|\\s)*";

    /**
     * 传入字符串，返回对应的计算值
     * @param expression 表示表达式的字符串
     * @return 表达式的计算结果
     */
    public String cal(String expression){
        if(!checkExpress(expression)){
            return "输入参数不合法";
        }
        // 把字符串转换成树形数据结构
        Expression exp = ExpressionBuilder.build(expression);
        float val = exp.cal();//调用计算方法
        return val + "";
    }

    /**
     * 初步判断一下表达式是否合法
     * @param expression 用户输入的表达式
     * @return true 表示表达式合法
     */
    private static boolean checkExpress(String expression){
        return Pattern.matches(regex,expression);
    }

}
