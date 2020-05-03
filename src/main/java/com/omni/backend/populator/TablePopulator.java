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
import java.util.stream.Collectors;

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
        final List<EntityEntryModel> entries = this.getEntries(config, entity);

        this.setFields(entries, target);
        this.setMainResult(entity, token, target, entries);
        this.setSlaveResults(entries, target);

        target.setDisplayPagination(config.isDisplayPagination());
        return target;
    }

    private void setFields(final List<EntityEntryModel> entries, final TableData target) {
        target.setFields(new ArrayList<>());
        entries.forEach(entry -> target.getFields().add(entry.getCode().split("_")[1]));
    }

    private List<EntityEntryModel> getEntries(final TableConfigModel config, final EntityModel entity) {
        if (config.getCode().equals("general")) {
            final String entityCode = entity.getCode();
            final List<EntityEntryModel> entries = this.entityEntryModelRepository.findByEntityCode(entityCode);
            return entries.stream().filter(entry -> entry.getCode().contains("code")).collect(Collectors.toList());
        }
        return this.entityEntryModelRepository.findByCode(config.getEntries());
    }

    private void setMainResult(
            final EntityModel entity,
            final String token,
            final TableData target,
            final List<EntityEntryModel> entries) {

        Assert.notNull(entity, "entity");
        Assert.notNull(target, "target");
        final RestParameter parameter = new RestParameter();
        parameter.setEntity(entity);
        parameter.setToken(token);
        try {
            final ResponseEntity<ResponseData> result = this.farmService.search(parameter);
            if (result.getStatusCode().equals(HttpStatus.OK)) {
                ArrayList products = (ArrayList) result.getBody().get_embedded().values().iterator().next();
                products.forEach(product -> {
                    ArrayList targetEntries = new ArrayList();
                    entries.forEach(entry -> {
                        final String entryCode = entry.getCode().split("_")[1];
                        Object value = ((LinkedHashMap<String, Object>) product).get(entryCode);
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