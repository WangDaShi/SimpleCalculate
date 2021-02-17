package loatr.expression;

import loatr.ExpressionBuilder;

/**
 * 二元表达式的父类
 */
public abstract class AbstractBiExpression implements Expression{

    private Expression left;// 运算符左边的表达式
    private Expression right;// 运算符右边的表达式

    public AbstractBiExpression(Expression left,Expression right){
        this.left = left;
        this.right = right;
    }

    @Override
    public float cal() {
        return calculate(left.cal(),right.cal());
    }

    abstract float calculate(float left,float right);

}
