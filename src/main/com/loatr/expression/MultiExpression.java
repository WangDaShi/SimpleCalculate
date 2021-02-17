package loatr.expression;

/**
 * 乘法表达式
 */
public class MultiExpression extends AbstractBiExpression{


    public MultiExpression(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    float calculate(float left, float right) {
        return left * right;
    }

}
