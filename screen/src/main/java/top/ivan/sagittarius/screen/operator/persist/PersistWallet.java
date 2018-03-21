package top.ivan.sagittarius.screen.operator.persist;

import java.io.Serializable;

public class PersistWallet implements Serializable {
    private static final long serialVersionUID = -4471264573670964243L;
    private String processor;

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }
}
