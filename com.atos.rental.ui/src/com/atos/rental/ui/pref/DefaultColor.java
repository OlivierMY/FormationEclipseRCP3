package com.atos.rental.ui.pref;

import org.eclipse.jface.preference.ColorFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.atos.rental.ui.RentalUIActivator;

public class DefaultColor extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	public DefaultColor() {
		super(GRID);
		setPreferenceStore(RentalUIActivator.getDefault().getPreferenceStore());
		setDescription("Parametrages des couleurs de Rental...");
		
	}

	@Override
	public void init(IWorkbench workbench) {
		
		
	}

	@Override
	protected void createFieldEditors() {
		addField(new ColorFieldEditor("PREF_CUSTOMER", "Customers : ", getFieldEditorParent()));
		addField(new ColorFieldEditor("PREF_RENTAL", "Rentals : ", getFieldEditorParent()));
		addField(new ColorFieldEditor("PREF_OBJECTS", "Ojbects : ", getFieldEditorParent()));
		
	}
	
}
