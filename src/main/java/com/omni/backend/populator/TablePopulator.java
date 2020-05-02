package com.omni.backend.populator;

import com.omni.aurora.core.populator.Populator;
import com.omni.backend.dto.ResponseData;
import com.omni.backend.dto.TableData;
import com.omni.backend.model.EntityEntryModel;
import com.omni.backend.model.EntityModel;
import com.omni.backend.model.TableConfigModel;
import com.omni.backend.parameter.RestParameter;
import com.omni.backend.parameter.TableParameter;
import com.omni.backend.repository.EntityEntryRepository;
import com.omni.backend.service.FarmService;
import com.omni.backend.strategy.GroupEntryEntitiesStrategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TablePopulator implements Populator<TableParameter, TableData> {
    private final FarmService farmService;
    private final GroupEntryEntitiesStrategy groupEntryEntitiesStrategy;
    private final EntityEntryRepository entityEntryModelRepository;

    @Override
    public TableData populate(final TableParameter source, final TableData target) {
        Assert.notNull(source, "source");
        Assert.notNull(target, "target");

        final TableConfigModel config = source.getTableConfig();
        final EntityModel entity = source.getEntity();
        final String token = source.getToken();
        final List<String> entryCodes = config.getEntries();
        final List<EntityEntryModel> entries = this.entityEntryModelRepository.findByCode(entryCodes);

        target.setDisplayPagination(config.isDisplayPagination());
        this.setMainResult(entity, token, target, entries);
        this.setSlaveResults(entries, target);
        return target;
    }

    private void setMainResult(final EntityModel entity, final String token,
                               final TableData target, final List<EntityEntryModel> entries) {
        Assert.notNull(entity, "entity");
        Assert.notNull(target, "target");

        final Iterable<EntityEntryModel> attributes = this.entityEntryModelRepository.findAll();
        final RestParameter parameter = new RestParameter();
        parameter.setEntity(entity);
        parameter.setToken(token);

        try {
            final ResponseEntity<ResponseData> result = this.farmService.search(parameter);
            if (result.getStatusCode().equals(HttpStatus.OK)) {
                ArrayList products = (ArrayList) result.getBody().get_embedded().values().iterator().next();
                products.forEach(product -> {
                    ArrayList targetEntries = new ArrayList();
                    attributes.forEach(entry -> {
                        Object value = ((LinkedHashMap<String, Object>) product).get(entry.getCode());
                        targetEntries.add(value);
                    });
                    target.getValues().add(targetEntries);
                });
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    private void setSlaveResults(final List<EntityEntryModel> source, final TableData target) {
        Assert.notNull(source, "source");
        Assert.notNull(target, "target");
        if (!CollectionUtils.isEmpty(source)) {
            Map<String, Set<String>> mapping = this.groupEntryEntitiesStrategy.convert(source);
        }
    }
}