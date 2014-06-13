package com.atos.rental.ui;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.InvalidRegistryObjectException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;

import com.atos.rental.ui.colorPalette.Palette;

/**
 * The activator class controls the plug-in life cycle
 */
public class RentalUIActivator extends AbstractUIPlugin implements
		IRentalUIConstants {

	private Map<String, Palette> paletteManager = new HashMap<String, Palette>();

	public Map<String, Palette> getPaletteManager() {
		return paletteManager;
	}

	@Override
	protected void initializeImageRegistry(ImageRegistry reg) {
		Bundle b = FrameworkUtil.getBundle(getClass());

		reg.put(IMG_CUSTOMER_KEY, ImageDescriptor.createFromURL(b
				.getEntry("icons/Customers.png")));
		reg.put(IMG_RENTAL_KEY,
				ImageDescriptor.createFromURL(b.getEntry("icons/Rentals.png")));
		reg.put(IMG_RENTAL_OBJECT_KEY, ImageDescriptor.createFromURL(b
				.getEntry("icons/RentalObjects.png")));
		reg.put(IMG_AGENCY_KEY,
				ImageDescriptor.createFromURL(b.getEntry("icons/Agency.png")));
		reg.put(IMG_SAMPLE_KEY,
				ImageDescriptor.createFromURL(b.getEntry("icons/sample.gif")));
	}

	// The plug-in ID
	public static final String PLUGIN_ID = "com.atos.rental.ui"; //$NON-NLS-1$

	// The shared instance
	private static RentalUIActivator plugin;

	/**
	 * The constructor
	 */
	public RentalUIActivator() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext
	 * )
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		showExtensionViews();
		initiateColorManager();
	}

	private void initiateColorManager() {
		IExtensionRegistry er = Platform.getExtensionRegistry();
		for (IConfigurationElement ce : er
				.getConfigurationElementsFor("com.atos.rental.ui.ColorPalette")) {
			System.out.println("Rajout de la palette : " + ce.getAttribute("name"));
			try {
				paletteManager
						.put(ce.getAttribute("id"),
								new Palette(
										ce.getAttribute("id"),
										ce.getAttribute("name"),
										(IColorProvider) ce
												.createExecutableExtension("colorPaletteClass")));
			} catch (InvalidRegistryObjectException e) {
				e.printStackTrace();
			} catch (CoreException e) {
				e.printStackTrace();
			}
			System.out.println("Rajout de la palette : " + ce.getAttribute("name") + "OK");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext
	 * )
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static RentalUIActivator getDefault() {
		return plugin;
	}

	private void showExtensionViews() {
		IExtensionRegistry er = Platform.getExtensionRegistry();
		for (IConfigurationElement ce : er
				.getConfigurationElementsFor("org.eclipse.ui.views")) {
			if (!"category".equals(ce.getName())) {
				System.out.println("Plugin : " + ce.getNamespaceIdentifier()
						+ "\tVue : " + ce.getAttribute("name") + "\tType :"
						+ ce.getName());
			}

		}
	}

}
