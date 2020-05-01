package com.omni.backend.populator;

import com.omni.aurora.core.populator.Populator;
import com.omni.backend.dto.TableActionGroupData;
import com.omni.backend.model.TableConfigModel;
import com.omni.backend.model.TableGroupActionModel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TableActionGroupPopulator implements Populator<TableConfigModel, Set<TableActionGroupData>> {

    @Override
    public Set<TableActionGroupData> populate(final TableConfigModel source, final Set<TableActionGroupData> target) {
        final Set<TableGroupActionModel> actionGroups = source.getActionGroups();
//        if (!CollectionUtils.isEmpty(actionGroups)) {
//            actionGroups.stream().forEach(actionGroup -> {
//                final TableActionGroupData actionData = new TableActionGroupData();
//                actionData.setName(actionGroup.getName());
//                Set<TableConfigActionModel> actions = actionGroup.getActions();
//                if (!CollectionUtils.isEmpty(actions)) {
//                    actions.parallelStream().forEach(action -> {
//                        this.convertActions(action, actionData);
//                    });
//                }
//                target.add(actionData);
//            });
//        }
        return target;
    }

//    private void convertActions(final TableConfigActionModel source, final TableActionGroupData actionData) {
//        actionData.getActions().add(
//            this.tableActionPopulator.populate(source, new TableActionData())
//        );
//    }
}
