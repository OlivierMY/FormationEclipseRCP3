package com.atos.rental.ui.view;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IPropertyListener;
import org.eclipse.ui.part.ViewPart;

import com.atos.rental.core.RentalCoreActivator;
import com.atos.rental.ui.RentalUIActivator;
import com.atos.rental.ui.provider.RentalProvider;
import com.opcoach.training.rental.RentalAgency;

public class AgencyView extends ViewPart {

	public AgencyView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		final TreeViewer tv = new TreeViewer(parent);

		tv.setContentProvider(new RentalProvider());
		tv.setLabelProvider(new RentalProvider());
		Collection<RentalAgency> agencies = new ArrayList<RentalAgency>();
		agencies.add(RentalCoreActivator.getAgency());

		tv.setInput(agencies);
		getSite().setSelectionProvider(tv);
		
		RentalUIActivator.getDefault().getPreferenceStore()
		  .addPropertyChangeListener(new IPropertyChangeListener() {
		    
		    @Override
		    public void propertyChange(PropertyChangeEvent event) {
		    	tv.refresh();
		    	
		    }
		  }); 
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

}
