/**
 * ***************************************************************************
 * Copyright (c) 2010 Qcadoo Limited
 * Project: Qcadoo MES
 * Version: 1.4
 *
 * This file is part of Qcadoo.
 *
 * Qcadoo is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation; either version 3 of the License,
 * or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 * ***************************************************************************
 */
package com.qcadoo.mes.basic.listeners;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qcadoo.mes.basic.constants.BasicConstants;
import com.qcadoo.mes.basic.constants.ProductFields;
import com.qcadoo.mes.basic.imports.product.ProductCellBinderRegistry;
import com.qcadoo.mes.basic.imports.product.ProductXlsxImportService;
import com.qcadoo.mes.basic.imports.services.XlsxImportService;
import com.qcadoo.model.api.Entity;
import com.qcadoo.model.api.search.SearchCriterion;
import com.qcadoo.model.api.search.SearchRestrictions;
import com.qcadoo.view.api.ComponentState;
import com.qcadoo.view.api.ViewDefinitionState;

@Service
public class ProductsImportListeners {

    @Autowired
    private ProductXlsxImportService productXlsxImportService;

    @Autowired
    private ProductCellBinderRegistry productCellBinderRegistry;

    public void downloadImportSchema(final ViewDefinitionState view, final ComponentState state, final String[] args) {
        productXlsxImportService.downloadImportSchema(view, BasicConstants.PLUGIN_IDENTIFIER, BasicConstants.MODEL_PRODUCT,
                XlsxImportService.L_XLSX);
    }

    public void processImportFile(final ViewDefinitionState view, final ComponentState state, final String[] args)
            throws IOException {
        productXlsxImportService.processImportFile(view, productCellBinderRegistry.getCellBinderRegistry(), true,
                BasicConstants.PLUGIN_IDENTIFIER, BasicConstants.MODEL_PRODUCT,
                ProductsImportListeners::createRestrictionForProduct);
    }

    private static SearchCriterion createRestrictionForProduct(final Entity product) {
        return SearchRestrictions.eq(ProductFields.NUMBER, product.getStringField(ProductFields.NUMBER));
    }

    public void redirectToLogs(final ViewDefinitionState view, final ComponentState state, final String[] args) {
        productXlsxImportService.redirectToLogs(view, BasicConstants.MODEL_PRODUCT);
    }

    public void onInputChange(final ViewDefinitionState view, final ComponentState state, final String[] args) {
        productXlsxImportService.changeButtonsState(view, false);
    }

}
