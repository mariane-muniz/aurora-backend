package com.omni.backend.dao.impl;

import com.omni.backend.dao.TableConfigDao;
import com.omni.backend.model.TableConfigModel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TableConfigDaoImpl implements TableConfigDao {
    private final EntityManager em;
    private final EntityManagerFactory factory;

    @Override
    public TableConfigModel getConfig(final String entityName) throws NoResultException {
        final String query = "SELECT e FROM TableConfigModel AS e WHERE e.entityName = :name";
        return (TableConfigModel) this.em.createQuery(query)
                .setParameter("name", entityName)
                .getSingleResult();
    }
}