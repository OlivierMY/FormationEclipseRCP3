package com.atos.rental.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.part.ViewPart;

import com.atos.rental.core.RentalCoreActivator;
import com.opcoach.training.rental.Rental;
import com.opcoach.training.rental.RentalAgency;

public class RentalPropertiesView extends ViewPart {

	private Label rentedObjectLabel;
	private Label customerLabel;
	private Label customerTitleLabel;
	private Label startDateLabel;
	private Label stopDateLabel;
	private Label startDateTitleLabel;
	private Label stopDateTitleLabel;

	public RentalPropertiesView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new GridLayout(1, false));
		
		Group infGroup = new Group(parent, SWT.NONE);
		infGroup.setText("Information");
		infGroup.setLayout(new GridLayout(2, false));
		
		rentedObjectLabel = new Label(infGroup, SWT.BORDER);
		GridData gd = new GridData();
		gd.horizontalSpan = 2;
		gd.horizontalAlignment = SWT.FILL;
		rentedObjectLabel.setLayoutData(gd);
		
		customerLabel = new Label(infGroup, SWT.BORDER);
		customerLabel.setLayoutData(gd);
		
		RentalAgency agency = RentalCoreActivator.getAgency();
		Rental r = agency.getRentals().get(0);
		
		setRental(r);
	}
	
	private void setRental(Rental r)
	{
		rentedObjectLabel.setText(r.getRentedObject().getName());
		customerLabel.setText(r.getCustomer().getDisplayName());
		//startDateLabel.setText(r.getStartDate().toString());
		//stopDateLabel.setText(r.getEndDate().toString());
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

}
