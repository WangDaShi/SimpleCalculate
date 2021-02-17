package loatr.expression;

/**
 * 减法表达式
 */
public class SubExpression extends AbstractBiExpression{


    public SubExpression(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    float calculate(float left, float right) {
        return left - right;
    }

}
