package com.honeysense.magpie.spring.uuid;

import com.honeysense.magpie.config.MagpieConfig;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class MagpieIdentifierGenerator implements IdentifierGenerator {
    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        return MagpieConfig.snowFlake().nextId();
    }
}
