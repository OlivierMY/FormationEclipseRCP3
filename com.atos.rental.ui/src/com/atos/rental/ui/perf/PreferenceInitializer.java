package com.atos.rental.ui.perf;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.swt.graphics.RGB;

import com.atos.rental.ui.RentalUIActivator;

public class PreferenceInitializer extends AbstractPreferenceInitializer {

	public static final String PREF_OBJECTS = "PREF_OBJECTS";
	public static final String PREF_RENTAL = "PREF_RENTAL";
	public static final String PREF_CUSTOMER = "PREF_CUSTOMER";
	public static final String PREF_PALETTE = "PREF_PALETTE";

	public PreferenceInitializer() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initializeDefaultPreferences() {
		IPreferenceStore store = RentalUIActivator.getDefault().getPreferenceStore();

		store.setDefault(PREF_CUSTOMER, StringConverter.asString(new RGB(255,0,0)));
		store.setDefault(PREF_RENTAL,  StringConverter.asString(new RGB(0,255,0)));
		store.setDefault(PREF_OBJECTS,  StringConverter.asString(new RGB(0,0,255)));
		store.setDefault(PREF_PALETTE,  "com.atos.rental.ui.colorPalette.Default");
	}

}
