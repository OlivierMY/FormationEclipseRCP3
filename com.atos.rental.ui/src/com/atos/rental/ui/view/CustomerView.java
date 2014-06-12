package com.atos.rental.ui.view;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;

import com.atos.rental.core.RentalCoreActivator;
import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.RentalAgency;

public class CustomerView extends ViewPart implements ISelectionListener {

	private Label lblAffnom;
	private Label lblAffprenom;

	public CustomerView() {
	}

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new GridLayout(2, false));

		Label lblNom = new Label(parent, SWT.NONE);
		lblNom.setText("Nom :");

		lblAffnom = new Label(parent, SWT.NONE);

		Label lblPrenom = new Label(parent, SWT.NONE);
		lblPrenom.setText("Prenom :");

		lblAffprenom = new Label(parent, SWT.NONE);

		RentalAgency agency = RentalCoreActivator.getAgency();
		Customer c = agency.getCustomers().get(0);

		setCustomer(c);

	}

	private void setCustomer(Customer cus) {
		lblAffnom.setText(cus.getFirstName());
		lblAffprenom.setText(cus.getLastName());
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
			Customer cus = (Customer) Platform.getAdapterManager().getAdapter(
					selected, Customer.class);

			if (cus != null) {
				setCustomer(cus);
			}
		}
	}
}
