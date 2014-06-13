package com.atos.rental.ui.pref;

import java.util.Map;

import org.eclipse.jface.preference.ComboFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.atos.rental.ui.RentalUIActivator;
import com.atos.rental.ui.colorPalette.Palette;
import com.atos.rental.ui.perf.PreferenceInitializer;

public class PalettePref extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	public PalettePref() {
		super(GRID);
		setPreferenceStore(RentalUIActivator.getDefault().getPreferenceStore());
		setDescription("Parametrages des couleurs de Rental...");
		
	}

	@Override
	public void init(IWorkbench workbench) {
		
		
	}

	@Override
	protected void createFieldEditors() {
		Map<String,Palette> palettes = RentalUIActivator.getDefault().getPaletteManager();
		String[][] listPalette = new String [palettes.size()][2];
		int i=0;
		for (Palette p : palettes.values()) {
			listPalette[i][0] = p.getName();
			listPalette[i][1] = p.getId();
			i++;
		}
		
		addField(new ComboFieldEditor(PreferenceInitializer.PREF_PALETTE, "Palette : ", (String[][]) listPalette, getFieldEditorParent()));
		
	}
	
}
