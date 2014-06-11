package com.atos.rental.ui.view;

import java.text.SimpleDateFormat;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;

import com.atos.rental.core.RentalCoreActivator;
import com.opcoach.training.rental.Rental;
import com.opcoach.training.rental.RentalAgency;

public class RentalPropertiesView extends ViewPart implements
		ISelectionListener {

	private Label rentedObjectLabel;
	private Label customerLabel;
	private Label loueALabel;
	private Group grpDatesDeLocation;
	private Label duLabel;
	private Label auLabel;
	private Label dateStartLabel;
	private Label dateEndLabel;

	public RentalPropertiesView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new GridLayout(1, false));

		Group infGroup = new Group(parent, SWT.NONE);
		infGroup.setText("Informations");
		infGroup.setLayout(new GridLayout(2, false));

		rentedObjectLabel = new Label(infGroup, SWT.NONE);
		GridData gd = new GridData();
		gd.horizontalSpan = 2;
		gd.horizontalAlignment = SWT.FILL;
		rentedObjectLabel.setLayoutData(gd);

		loueALabel = new Label(infGroup, SWT.NONE);
		loueALabel.setText("Lou\u00E9 \u00E0 :");

		customerLabel = new Label(infGroup, SWT.NONE);

		grpDatesDeLocation = new Group(parent, SWT.NONE);
		grpDatesDeLocation.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER,
				true, false, 1, 1));
		grpDatesDeLocation.setText("Dates de location");
		GridLayout gl_grpDatesDeLocation = new GridLayout(2, false);
		gl_grpDatesDeLocation.horizontalSpacing = 2;
		grpDatesDeLocation.setLayout(gl_grpDatesDeLocation);

		duLabel = new Label(grpDatesDeLocation, SWT.NONE);
		duLabel.setText("du :");

		dateStartLabel = new Label(grpDatesDeLocation, SWT.NONE);
		dateStartLabel.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true,
				false, 1, 1));
		dateStartLabel.setText("dateStart");

		auLabel = new Label(grpDatesDeLocation, SWT.NONE);
		auLabel.setText("au :");

		dateEndLabel = new Label(grpDatesDeLocation, SWT.NONE);
		dateEndLabel.setText("dateEnd");

		RentalAgency agency = RentalCoreActivator.getAgency();
		Rental r = agency.getRentals().get(0);

		setRental(r);
	}

	private void setRental(Rental r) {
		rentedObjectLabel.setText(r.getRentedObject().getName());
		customerLabel.setText(r.getCustomer().getDisplayName());
		SimpleDateFormat sdp = new SimpleDateFormat("dd/MM/yyyy");
		dateStartLabel.setText(sdp.format(r.getStartDate()));
		dateEndLabel.setText(sdp.format(r.getEndDate()));
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(IViewSite site) throws PartInitException {
		super.init(site);
		site.getPage().addSelectionListener(this);
	}

	@Override
	public void dispose() {
		getSite().getPage().removeSelectionListener(this);
		super.dispose();
	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		if (selection instanceof IStructuredSelection) {
			Object selected = ((IStructuredSelection) selection)
					.getFirstElement();
			if (selected instanceof Rental) {
				setRental((Rental) selected);
			}
		}
	}

}
