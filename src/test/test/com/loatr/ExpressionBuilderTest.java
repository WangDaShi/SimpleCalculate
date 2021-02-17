package test.com.loatr;

import loatr.ExpressionBuilder;
import loatr.expression.Expression;

/**
 * ExpressionBuilder对应的测试类，此类中用来测试ExpressionBuilder中的方法
 */
public class ExpressionBuilderTest {

    public static void main(String[] args) {
        ExpressionBuilderTest test = new ExpressionBuilderTest();
        // 测试单值表达式
        test.testBuilder("1",1);
//        test.testBuilder("-1",-1);
        test.testBuilder(" 100 ",100);
        // 测试加法表达式
        test.testBuilder("1+2",3);
        // 测试空格
        test.testBuilder("1 + 2",3);
        // 测试减法
        test.testBuilder("1-2",-1);
        test.testBuilder("1+2+3",6);
        // 测试加法 减法 空格的混合表达式
        test.testBuilder("1 + 2  +3",6);
        test.testBuilder("1 + 2 -3 + 5",5);
        test.testBuilder("2 -3 - 5",-6);
        // 测试包含括号的表达式
        test.testBuilder("2 -(3 - 5)",4);
        test.testBuilder("2 -(3 -(1 +2 ) + (1+5))",-4);
        // 测试乘法
        test.testBuilder("2*3",6);
        test.testBuilder("2*3*4",24);
        test.testBuilder("2*(1+2)",6);
        test.testBuilder("2*(1+2)*(4-5) + 3",-3);
        // 测试除法
        test.testBuilder("4/2",2);
        test.testBuilder("4/2/2",1);
        // 测试混合表达式
        test.testBuilder("4/2*2",4);
        test.testBuilder("2*(1+2)/(4-2) + 3",6);
        test.testBuilder("(1+2)/(4-2) + 3",4.5f);
        // 测试小数
        test.testBuilder("4.1 + 0.1",4.2f);
        test.testBuilder("(4.1 + 0.1)*(0.3 * 2)",4.2f*0.6f);
        // 测试计算精度问题 double类型的0.1 + 0.2 = 0.30000000000004的问题
        test.testBuilder("0.1 + 0.2",0.3f);
//        test.testExtraSub("2-3","2+3");
//        test.testExtraSub("2-3+4","2+3-4");
//        test.testExtraSub("2-(3+4)","2+(3+4)");
    }

    /**
     * 测试builder方法
     * @param input 表达式字符串
     * @param expectResult 预期的计算结果
     */
    public void testBuilder(String input,float expectResult){
        Expression exp = ExpressionBuilder.build(input);
        float result = exp.cal();
        System.out.println("表达式" + input + "计算结果：" + result + ",预期结果：" + expectResult);
    }

    /**
     * 测试提取减法功能
     * @param input 表达式
     * @param expect 预期的提取之后的表达式
     */
    public void testExtraSub(String input,String expect){
        String newExp = ExpressionBuilder.extraSub(input);
        System.out.println("表达式" + input + "转换结果：" + newExp + ",预期结果：" + expect);
    }
}
