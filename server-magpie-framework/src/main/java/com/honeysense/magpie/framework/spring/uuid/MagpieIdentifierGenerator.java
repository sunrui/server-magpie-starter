package com.honeysense.magpie.framework.spring.uuid;

import com.honeysense.magpie.framework.utils.MagpieSnowFlake;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class MagpieIdentifierGenerator implements IdentifierGenerator {
    @Autowired
    private MagpieSnowFlake magpieSnowFlake;

    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        return magpieSnowFlake.nextId();
    }
}
