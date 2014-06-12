package com.atos.rental.ui.view;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;

import com.atos.rental.core.RentalCoreActivator;
import com.atos.rental.ui.RentalUIActivator;
import com.atos.rental.ui.provider.RentalProvider;
import com.opcoach.training.rental.RentalAgency;

public class AgencyView extends ViewPart implements IPropertyChangeListener {

	private TreeViewer tv;

	public AgencyView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		tv = new TreeViewer(parent);

		tv.setContentProvider(new RentalProvider());
		tv.setLabelProvider(new RentalProvider());
		Collection<RentalAgency> agencies = new ArrayList<RentalAgency>();
		agencies.add(RentalCoreActivator.getAgency());

		tv.setInput(agencies);
		getSite().setSelectionProvider(tv);
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void init(IViewSite site) throws PartInitException {
		super.init(site);
		RentalUIActivator.getDefault().getPreferenceStore()
		  .addPropertyChangeListener(this); 
	}
	@Override
	public void dispose() {
		getSite().getPage().removePropertyChangeListener(this);
		super.dispose();
	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		tv.refresh();
	}

}
