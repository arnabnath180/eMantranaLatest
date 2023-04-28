package com.example.emantrana.IdGenerator;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.math.BigInteger;

public class DoctorGenerator implements IdentifierGenerator{
    private static final Long INITIAL_ID = Long.valueOf(1);
    private static final Long INCREMENT_BY = Long.valueOf(3);

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        // Get the last generated ID from the database
        Query query = session.createSQLQuery("SELECT MAX(id) FROM doctor");
        BigInteger lastId =(BigInteger)  query.uniqueResult();
        if(lastId != null) {
            int i = lastId.intValue();
            Long l = Long.valueOf(i);
            // Calculate the next ID
            Long nextId = lastId != null ? i + INCREMENT_BY : INITIAL_ID;
            return nextId;
        }
        else{
            return INITIAL_ID;
        }
    }
}
