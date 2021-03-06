package com.atos.rental.ui.view;

import java.text.SimpleDateFormat;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSource;
import org.eclipse.swt.dnd.DragSourceAdapter;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
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
import com.atos.rental.ui.Messages;
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
		infGroup.setText(Messages.RentalPropertiesView_0);
		infGroup.setLayout(new GridLayout(2, false));

		rentedObjectLabel = new Label(infGroup, SWT.NONE);
		GridData gd = new GridData();
		gd.horizontalSpan = 2;
		gd.horizontalAlignment = SWT.FILL;
		rentedObjectLabel.setLayoutData(gd);

		loueALabel = new Label(infGroup, SWT.NONE);
		loueALabel.setText(Messages.RentalPropertiesView_1);

		customerLabel = new Label(infGroup, SWT.NONE);

		grpDatesDeLocation = new Group(parent, SWT.NONE);
		grpDatesDeLocation.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER,
				true, false, 1, 1));
		grpDatesDeLocation.setText(Messages.RentalPropertiesView_2);
		GridLayout gl_grpDatesDeLocation = new GridLayout(2, false);
		gl_grpDatesDeLocation.horizontalSpacing = 2;
		grpDatesDeLocation.setLayout(gl_grpDatesDeLocation);

		duLabel = new Label(grpDatesDeLocation, SWT.NONE);
		duLabel.setText(Messages.RentalPropertiesView_3);

		dateStartLabel = new Label(grpDatesDeLocation, SWT.NONE);
		dateStartLabel.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true,
				false, 1, 1));
		dateStartLabel.setText(Messages.RentalPropertiesView_4);

		auLabel = new Label(grpDatesDeLocation, SWT.NONE);
		auLabel.setText(Messages.RentalPropertiesView_5);

		dateEndLabel = new Label(grpDatesDeLocation, SWT.NONE);
		dateEndLabel.setText(Messages.RentalPropertiesView_6);

		RentalAgency agency = RentalCoreActivator.getAgency();
		Rental r = agency.getRentals().get(0);

		setRental(r);
		setLabelAsDragSource(rentedObjectLabel);
	}

	private void setRental(Rental r) {
		rentedObjectLabel.setText(r.getRentedObject().getName());
		customerLabel.setText(r.getCustomer().getDisplayName());
		SimpleDateFormat sdp = new SimpleDateFormat(Messages.RentalPropertiesView_7);
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
	
	
	public void setLabelAsDragSource(final Label label) {
		DragSource source = new DragSource(label, DND.DROP_MOVE | DND.DROP_COPY);
		
		source.setTransfer(new Transfer[] {TextTransfer.getInstance()});
		
		source.addDragListener(new DragSourceAdapter() {
			@Override
			public void dragSetData(DragSourceEvent event) {
				if (TextTransfer.getInstance().isSupportedType(event.dataType)) {
					event.data = label.getText();
				}
			}
		});
	}

}
