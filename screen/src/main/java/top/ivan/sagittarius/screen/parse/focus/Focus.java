package top.ivan.sagittarius.screen.parse.focus;

import java.io.Serializable;

public interface Focus extends Serializable {
    String peek(String src, String target, String key) throws Exception;
}