package loatr.expression;

/**
 * 加法表达式
 */
public class AddExpression extends AbstractBiExpression{

    public AddExpression(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    float calculate(float left, float right) {
        return left + right;
    }
}
