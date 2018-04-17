package top.ivan.sagittarius.screen.task;

import top.ivan.sagittarius.screen.Seed;

import java.io.Serializable;

public interface Callback extends Serializable {
    void callback(Seed seed) throws UnExceptMessageException;
}
