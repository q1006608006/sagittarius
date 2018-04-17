package top.ivan.sagittarius.screen.operator.persist;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import top.ivan.sagittarius.screen.Seed;
import top.ivan.sagittarius.screen.context.PartsManager;
import top.ivan.sagittarius.screen.task.UnExceptMessageException;

import java.util.List;

public class DefaultPersist implements Persist {
    private static Logger logger = LogManager.getLogger(DefaultPersist.class);
    private PartsManager partsManager;

    public DefaultPersist(PartsManager partsManager) {
        this.partsManager = partsManager;
    }

    @Override
    public void process(Seed item, List<PersistWallet> wallets) throws UnExceptMessageException {
        if (null == wallets) {
            return;
        }
        for (PersistWallet wallet : wallets) {
            PersistOperator operator = partsManager.getPersistOperator(wallet.getProcessor());
            if (null == operator) {
                throw new NullPointerException("can not found operator: " + wallet.getProcessor());
            }
            try {
                if(operator.validate(item)) {
                    operator.process(item);
                }
            } catch (UnExceptMessageException e) {
                throw e;
            } catch (Throwable t) {
                logger.error(String.format("an error threw while process: %s with operator: %s",item,operator),t);
            }
        }
    }

}
