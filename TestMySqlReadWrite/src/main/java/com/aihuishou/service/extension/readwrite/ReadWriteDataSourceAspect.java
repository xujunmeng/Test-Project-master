package com.aihuishou.service.extension.readwrite;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * Created by james on 2017/6/29.
 */
public class ReadWriteDataSourceAspect {

    public Object determineReadOrWriteDB(ProceedingJoinPoint pjp) throws Throwable {

        if (TransactionSynchronizationManager.isActualTransactionActive() &&
                TransactionSynchronizationManager.isCurrentTransactionReadOnly() != false) {

            ReadWriteDataSourceDecision.markWrite();

        } else {

            ReadWriteDataSourceDecision.markRead();

        }

        try {

            return pjp.proceed();

        } finally {

            ReadWriteDataSourceDecision.reset();

        }

    }

}
