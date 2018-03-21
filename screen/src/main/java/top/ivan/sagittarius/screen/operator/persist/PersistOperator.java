package top.ivan.sagittarius.screen.operator.persist;

import top.ivan.sagittarius.screen.Seed;

public interface PersistOperator {
    void process(Seed item);
    default boolean validate(Seed item) {return true;}
}
