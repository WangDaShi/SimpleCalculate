package loatr;

import loatr.expression.*;

import java.util.Stack;

public class ExpressionBuilder {

    /**
     * 将字符串构建为对应的表达式(树形结构)。例：
     * 1 + 2 -3 转化为一下的树形结构：
     *    -
     *   | |
     *  +   3
     * | |
     * 1 2
     * 基本思路：从左到右遍历字符串中的字符
     * 1. 遇到 + - 两个运算符就自动拆分成左右两个表达式，然后再递归调用此方法，并返回该表达式
     * 2. 遇到 * / 两个优先级更高的运算符，标记一下继续
     * 3. 遇到 ( 就调到对应的 ) ，继续循环
     * 4. 循环结束之后判断
     * @param exp
     * @return
     */
    public static Expression build(String exp){
        if(exp == null || exp.isBlank()){
            throw new RuntimeException("表达式为空");
        }
        exp = exp.trim();// 预处理一下，把开头和结尾的空格去掉
        int lastOpIndex = -1;
        char[] chars = exp.toCharArray();
        int i = 0;
        while(i < chars.length){
            char c = chars[i];
            if(c >= '0' && c <='9' || c == '.' || c == ' '){
                i++;
                continue;
            }
            if(c == '('){
                int end = findEndParenthesis(i,chars);
                if(end < 0){
                    throw new RuntimeException("()结构不完整");
                }
                i = end + 1;//跳过括号结束的位置
                continue;
            }
            if(c == '+' || c == '-'){
                if(i == chars.length -1){
                    throw new RuntimeException("表达式不能以" + c + "结尾");
                }
                Expression left = build(exp.substring(0,i));
                if(c == '+'){
                    Expression right = build(exp.substring(i + 1));
                    return new AddExpression(left,right);
                }else{
                    // - 号右边的表达式需要做一下转换 例： 1-2+3 可以拆成 (1) - (2-3)
                    String rightExp = extraSub(exp.substring(i+1));
                    Expression right = build(rightExp);
                    return new SubExpression(left,right);
                }
            }
            if(c == '*' || c == '/'){// 乘号和除号优先级高
                lastOpIndex = i;
                i++;
            }
        }
        if(lastOpIndex < 0){// 说明表达式里面没有* / 两个运算符
            if(exp.endsWith(")")){// 如果是括号表达式
                return build(exp.substring(1,exp.length()-1));// 剥掉表达式起始和结尾的()，递归继续解析
            }else{// 如果是单值表达式
                return new ValueExpression(exp);
            }
        }
        char lastOp = chars[lastOpIndex];
        // TODO 这部分代码与上面+ -号的处理代码相似，后续考虑抽象为通用代码
        if(lastOp == '*' || lastOp == '/'){
            Expression left = build(exp.substring(0,lastOpIndex));
            if(lastOp == '*'){
                Expression right = build(exp.substring(lastOpIndex + 1));
                return new MultiExpression(left,right);
            }else{
                // / 号右边的表达式需要做一下转换 例： 1/2*3 可以拆成 (1) / (2/3)
                String rightExp = extraDiv(exp.substring(lastOpIndex+1));
                Expression right = build(rightExp);
                return new DivExpression(left,right);
            }
        }else{
            throw new RuntimeException("未知错误");
        }
    }

    /**
     * 转换表达式，从表达式中提取出负号 -
     * @param rep
     * @return
     */
    public static String extraSub(String rep){
        StringBuilder builder = new StringBuilder();
        char[] chars = rep.toCharArray();
        int i = 0;
        while(i < chars.length){
            char c = chars[i];
            if(c == '('){// 括号内的操作符不用替换
                int k = findEndParenthesis(i,chars);
                builder.append(rep, i, k+1);
                i = k;
            }else if(c == '-'){
                builder.append('+');
            }else if(c == '+'){
                builder.append('-');
            }else{
                builder.append(c);
            }
            i ++;
        }
        return builder.toString();
    }
    /**
     * 转换表达式，从表达式中提取出除号 /
     * @param rep
     * @return
     */
    public static String extraDiv(String rep){
        StringBuilder builder = new StringBuilder();
        char[] chars = rep.toCharArray();
        int i = 0;
        while(i < chars.length){
            char c = chars[i];
            if(c == '('){
                int k = findEndParenthesis(i,chars);
                builder.append(rep, i, k+1);
                i = k;
            }else if(c == '*'){
                builder.append('/');
            }else if(c == '/'){
                builder.append('*');
            }else{
                builder.append(c);
            }
            i ++;
        }
        return builder.toString();
    }

    /**
     * 查找与指定左括号(匹配的右括号)
     * @param start 起始查找位置
     * @param chars 字符串
     * @return 从起始位置开始对应的右括号的位置，如果查找不多就返回 -1
     */
    public static int findEndParenthesis(int start,char[] chars){
        // 用栈保存嵌套的(
        Stack<Character> stack = new Stack<>();
        for(int i = start;i<chars.length;i++){
            char c = chars[i];
            if(c == '('){
                stack.push(c);
            }else if(c == ')'){
                stack.pop();
                if(stack.isEmpty()){
                    return i;
                }
            }
        }
        return -1;
    }
}
