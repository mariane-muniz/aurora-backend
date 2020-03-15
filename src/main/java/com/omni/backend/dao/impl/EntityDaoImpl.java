package com.omni.backend.dao.impl;

import com.omni.backend.dao.EntityDao;
import com.omni.backend.model.EntityModel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Optional;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EntityDaoImpl implements EntityDao {
    private final EntityManager em;
    private final EntityManagerFactory factory;

    @Override
    public Optional<EntityModel> get() {
        final String query = "SELECT e FROM EntityEntryModel AS e";
        return (Optional<EntityModel>) this.em.createQuery(query).getSingleResult();
    }
}