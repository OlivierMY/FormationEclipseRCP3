package com.atos.rental.ui.provider;

import java.text.SimpleDateFormat;
import java.util.Collection;

import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;

import com.atos.rental.ui.IRentalUIConstants;
import com.atos.rental.ui.RentalUIActivator;
import com.atos.rental.ui.perf.PreferenceInitializer;
import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.Rental;
import com.opcoach.training.rental.RentalAgency;
import com.opcoach.training.rental.RentalObject;

public class RentalProvider extends LabelProvider implements
		ITreeContentProvider, IRentalUIConstants, IColorProvider {

	private static final Object[] EMPTY_RESULT = new Object[0];

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object[] getElements(Object inputElement) {
		Object[] result = null;

		if (inputElement instanceof Collection<?>) {
			result = ((Collection<?>) inputElement).toArray();
		} else if (inputElement instanceof Node) {
			result = ((Node) inputElement).getChildren();
		}
		return (result == null) ? EMPTY_RESULT : result;
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		Object[] result = null;

		if (parentElement instanceof RentalAgency) {
			result = new Node[] { new Node(CUSTOMER, parentElement),
					new Node(LOCATIONS, parentElement),
					new Node(RENTAL_ITEMS, parentElement) };
		} else if (parentElement instanceof Node) {
			result = ((Node) parentElement).getChildren();
		}

		return (result == null) ? EMPTY_RESULT : result;
	}

	@Override
	public Object getParent(Object element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		boolean result = false;
		if (element instanceof RentalAgency) {
			result = true;
		} else if (element instanceof Collection<?>) {
			result = true;
		} else if (element instanceof Node) {
			result = true;
		}

		return result;
	}

	public String getText(Object element) {
		String result = "null";
		if (element instanceof RentalAgency) {
			result = ((RentalAgency) element).getName();
		} else if (element instanceof Customer) {
			result = ((Customer) element).getFirstName() + " "
					+ ((Customer) element).getLastName();
		} else if (element instanceof Rental) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM");
			result = ((Rental) element).getRentedObject().getName() + " ["
					+ sdf.format(((Rental) element).getStartDate()) + "->"
					+ sdf.format(((Rental) element).getEndDate()) + "]";
		} else if (element instanceof RentalObject) {
			result = ((RentalObject) element).getName();
		} else if (element instanceof Node) {
			result = ((Node) element).toString();
		}

		return result;
	}

	class Node {
		private String label;
		private RentalAgency agency;

		public Node(String label, Object parentElement) {
			super();
			this.label = label;
			this.agency = (RentalAgency) parentElement;
		}

		public Object[] getChildren() {
			Object[] result = null;

			switch (label) {
			case IRentalUIConstants.CUSTOMER:
				result = agency.getCustomers().toArray();
				break;
			case IRentalUIConstants.LOCATIONS:
				result = agency.getRentals().toArray();
				break;
			case IRentalUIConstants.RENTAL_ITEMS:
				result = agency.getObjectsToRent().toArray();
				break;
			default:
				result = EMPTY_RESULT;
				break;
			}

			return result;
		}

		@Override
		public String toString() {
			return label;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result
					+ ((agency == null) ? 0 : agency.hashCode());
			result = prime * result + ((label == null) ? 0 : label.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (!(obj instanceof Node)) {
				return false;
			}
			Node other = (Node) obj;
			if (!getOuterType().equals(other.getOuterType())) {
				return false;
			}
			if (agency == null) {
				if (other.agency != null) {
					return false;
				}
			} else if (!agency.equals(other.agency)) {
				return false;
			}
			if (label == null) {
				if (other.label != null) {
					return false;
				}
			} else if (!label.equals(other.label)) {
				return false;
			}
			return true;
		}

		private RentalProvider getOuterType() {
			return RentalProvider.this;
		}

	}

	@Override
	public Color getForeground(Object element) {
		String paletteName = RentalUIActivator.getDefault()
				.getPreferenceStore()
				.getString(PreferenceInitializer.PREF_PALETTE);
		return RentalUIActivator.getDefault().getPaletteManager().get(paletteName).getCp().getForeground(element);
	}

	@Override
	public Color getBackground(Object element) {
		String paletteName = RentalUIActivator.getDefault()
				.getPreferenceStore()
				.getString(PreferenceInitializer.PREF_PALETTE);
		return RentalUIActivator.getDefault().getPaletteManager().get(paletteName).getCp().getBackground(element);
	}

	@Override
	public Image getImage(Object element) {
		Image result = null;
		if (element instanceof Customer) {
			result = RentalUIActivator.getDefault().getImageRegistry()
					.get(IMG_CUSTOMER_KEY);
		} else if (element instanceof RentalObject) {
			result = RentalUIActivator.getDefault().getImageRegistry()
					.get(IMG_RENTAL_OBJECT_KEY);
		} else if (element instanceof Rental) {
			result = RentalUIActivator.getDefault().getImageRegistry()
					.get(IMG_RENTAL_KEY);
		} else if (element instanceof RentalAgency) {
			result = RentalUIActivator.getDefault().getImageRegistry()
					.get(IMG_AGENCY_KEY);
		} else if (element instanceof Node) {
			result = RentalUIActivator.getDefault().getImageRegistry()
					.get(IMG_SAMPLE_KEY);
		}
		return result;
	}

	

}
