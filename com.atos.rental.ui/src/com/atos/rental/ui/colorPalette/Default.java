package com.atos.rental.ui.colorPalette;

import org.eclipse.jface.resource.ColorRegistry;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

import com.atos.rental.ui.RentalUIActivator;
import com.atos.rental.ui.perf.PreferenceInitializer;
import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.Rental;
import com.opcoach.training.rental.RentalObject;

public class Default implements IColorProvider {

	public Default() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Color getForeground(Object element) {
		Color result = null;
		if (element instanceof Customer) {
			result = getAColor(RentalUIActivator.getDefault()
					.getPreferenceStore()
					.getString(PreferenceInitializer.PREF_CUSTOMER));
		} else if (element instanceof RentalObject) {
			result = getAColor(RentalUIActivator.getDefault()
					.getPreferenceStore()
					.getString(PreferenceInitializer.PREF_OBJECTS));
		} else if (element instanceof Rental) {
			result = getAColor(RentalUIActivator.getDefault()
					.getPreferenceStore()
					.getString(PreferenceInitializer.PREF_RENTAL));
		}
		return result;
	}
	private Color getAColor(String rgbKey) {
		ColorRegistry colorRegistry = JFaceResources.getColorRegistry();

		Color col = colorRegistry.get(rgbKey);
		if (col == null) {
			colorRegistry.put(rgbKey, StringConverter.asRGB(rgbKey));
			col = colorRegistry.get(rgbKey);
		}
		return col;
	}
	@Override
	public Color getBackground(Object element) {
		return Display.getCurrent().getSystemColor(SWT.COLOR_WHITE);
	}

}
