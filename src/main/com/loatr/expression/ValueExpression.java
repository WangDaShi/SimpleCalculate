package loatr.expression;

/**
 * 单值表达式
 */
public class ValueExpression implements Expression {

    private float value;

    public ValueExpression(String exp){
        exp = exp.trim();
        // 直接调用Float自带的方法来把字符串转换成对应的数字
        value = Float.valueOf(exp);
    }

    @Override
    public float cal() {
        return value;
    }
}
