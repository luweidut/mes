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
package com.qcadoo.mes.basic.hooks;

import java.util.Currency;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qcadoo.mes.basic.constants.CurrencyFields;
import com.qcadoo.mes.basic.constants.ParameterFields;
import com.qcadoo.mes.basic.util.CurrencyService;
import com.qcadoo.model.api.DataDefinition;
import com.qcadoo.model.api.Entity;

@Service
public class ParameterModelHooks {

    private static final String L_RELEASE_OF_MATERIALS = "releaseOfMaterials";
    private static final String L_RECEIPT_OF_PRODUCTS = "receiptOfProducts";

    @Autowired
    private CurrencyService currencyService;

    public void setDefaultValues(final DataDefinition parameterDD, final Entity parameter) {
        String defaultCurrencyAlphabeticCode = Currency.getInstance(Locale.getDefault()).getCurrencyCode();
        parameter.setField(CurrencyFields.CURRENCY, currencyService.getCurrencyByAlphabeticCode(defaultCurrencyAlphabeticCode));
        parameter.setField(ParameterFields.DASHBOARD_SHOW_FOR_PRODUCT, "01number");
        parameter.setField(ParameterFields.QUANTITY_MADE_ON_THE_BASIS_OF_DASHBOARD, "01approvedProduction");
        parameter.setField(ParameterFields.DASHBOARD_ORDER_SORTING, "01startDate");
        parameter.setField(L_RELEASE_OF_MATERIALS, "01onAcceptanceRegistrationRecord");
        parameter.setField(L_RECEIPT_OF_PRODUCTS, "01onAcceptanceRegistrationRecord");
    }

}
