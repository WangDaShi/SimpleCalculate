package loatr.expression;

/**
 * 表示一个算术表达式
 */
public interface Expression {

//    Expression create(String exp);

    /**
     * 返回该表达式的值，用float类型表示，具体由实现了此接口的类实现
     * @return 表达式的值
     */
    float cal();

}
