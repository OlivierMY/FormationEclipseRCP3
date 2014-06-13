package com.atos.rental.ui.colorPalette;

import org.eclipse.jface.viewers.IColorProvider;

public class Palette {
	
	private String id, name;
	private IColorProvider cp;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public IColorProvider getCp() {
		return cp;
	}
	public void setCp(IColorProvider cp) {
		this.cp = cp;
	}
	public Palette(String id, String name, IColorProvider cp) {
		super();
		this.id = id;
		this.name = name;
		this.cp = cp;
	}

}
