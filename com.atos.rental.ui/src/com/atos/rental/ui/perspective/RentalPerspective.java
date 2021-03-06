package com.atos.rental.ui.perspective;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class RentalPerspective implements IPerspectiveFactory {
	
	public static final String PERSPECTIVE_ID = "com.atos.rental.ui.perspective.RentalPerspective";

	/**
	 * Creates the initial layout for a page.
	 */
	public void createInitialLayout(IPageLayout layout) {
		layout.setEditorAreaVisible(false);
		addFastViews(layout);
		addViewShortcuts(layout);
		addPerspectiveShortcuts(layout);
		layout.addView("com.atos.rental.ui.rentalProperties", IPageLayout.TOP, 0.5f, IPageLayout.ID_EDITOR_AREA);
		layout.addView("com.atos.rental.ui.Agency", IPageLayout.BOTTOM, 0.5f, "com.atos.rental.ui.rentalProperties");
		layout.addView("com.atos.rental.ui.CustomerView", IPageLayout.RIGHT, 0.5f, "com.atos.rental.ui.rentalProperties");
	}

	/**
	 * Add fast views to the perspective.
	 */
	private void addFastViews(IPageLayout layout) {
	}

	/**
	 * Add view shortcuts to the perspective.
	 */
	private void addViewShortcuts(IPageLayout layout) {
	}

	/**
	 * Add perspective shortcuts to the perspective.
	 */
	private void addPerspectiveShortcuts(IPageLayout layout) {
	}

}
