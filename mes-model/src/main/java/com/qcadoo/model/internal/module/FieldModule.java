package com.qcadoo.model.internal.module;

import com.qcadoo.model.api.DataDefinitionService;
import com.qcadoo.plugin.api.PluginState;
import com.qcadoo.plugin.internal.api.Module;

public class FieldModule implements Module {

    private final String pluginIdentifier;

    private final String modelName;

    private final String fieldName;

    private final DataDefinitionService dataDefinitionService;

    public FieldModule(final String pluginIdentifier, final String modelName, final String fieldName,
            final DataDefinitionService dataDefinitionService) {
        this.pluginIdentifier = pluginIdentifier;
        this.modelName = modelName;
        this.fieldName = fieldName;
        this.dataDefinitionService = dataDefinitionService;
    }

    @Override
    public void init(final PluginState state) {
        if (!PluginState.ENABLED.equals(state)) {
            disable();
        }
    }

    @Override
    public void enable() {
        // TODO
        // włącza pole w dataDefinitionService
    }

    @Override
    public void disable() {
        // TODO
        // wyłącza pole w dataDefinitionService

    }

}
