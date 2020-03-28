package com.omni.backend.populator;

import com.omni.aurora.core.populator.Populator;
import com.omni.backend.dto.TableActionData;
import com.omni.backend.model.TableConfigActionModel;
import org.springframework.stereotype.Service;

@Service
public class TableActionPopulator implements Populator<TableConfigActionModel, TableActionData> {

    @Override
    public TableActionData populate(final TableConfigActionModel source, final TableActionData target) {
        target.setBtnType(source.getBtnType());
        target.setText(source.getText());
        target.setIcon(source.getIcon());
        target.setLink(source.getLink());
        return target;
    }
}
