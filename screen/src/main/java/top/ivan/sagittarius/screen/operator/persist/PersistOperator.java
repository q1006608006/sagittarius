package top.ivan.sagittarius.screen.operator.persist;

import top.ivan.sagittarius.screen.Seed;
import top.ivan.sagittarius.screen.task.UnExceptMessageException;

public interface PersistOperator {
    void process(Seed item) throws UnExceptMessageException;
    default boolean validate(Seed item) {return true;}
}
