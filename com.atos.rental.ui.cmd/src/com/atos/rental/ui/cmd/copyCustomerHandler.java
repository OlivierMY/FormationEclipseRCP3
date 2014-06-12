package com.atos.rental.ui.cmd;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.ImageTransfer;
import org.eclipse.swt.dnd.RTFTransfer;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.handlers.HandlerUtil;

import com.atos.rental.ui.IRentalUIConstants;
import com.atos.rental.ui.RentalUIActivator;
import com.opcoach.training.rental.Customer;

public class copyCustomerHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ISelection currentSelection = HandlerUtil.getCurrentSelection(event);
		if (currentSelection instanceof IStructuredSelection) {
			IStructuredSelection isel = (IStructuredSelection) currentSelection;

			Clipboard clipboard = new Clipboard(Display.getCurrent());
			String textData = "";
			String rtfData = "{\\rtf1\\b\\i ";
			for (Object obj : isel.toArray()) {
				Customer cus = (Customer) obj;
				textData = textData + cus.getDisplayName() + "\n";
				rtfData = rtfData + cus.getDisplayName() + "\n";
			}
			rtfData = rtfData + "}";
			Image img = (Image) RentalUIActivator.getDefault()
					.getImageRegistry().get(IRentalUIConstants.IMG_AGENCY_KEY);
			TextTransfer textTransfer = TextTransfer.getInstance();
			RTFTransfer rtfTransfer = RTFTransfer.getInstance();
			ImageTransfer imgTransfer = ImageTransfer.getInstance();
			Transfer[] transfers = new Transfer[] { textTransfer, rtfTransfer,
					imgTransfer };
			Object[] data = new Object[] { textData, rtfData,
					img.getImageData() };
			clipboard.setContents(data, transfers, DND.CLIPBOARD);
			clipboard.dispose();
		}

		return null;
	}

}
