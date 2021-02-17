package loatr.expression;

/**
 * 除法表达式
 */
public class DivExpression extends AbstractBiExpression{


    public DivExpression(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    float calculate(float left, float right) {
        return left / right;
    }

}
