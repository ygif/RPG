package rpgSource.util;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class DescribableRenderer implements ListCellRenderer<Describable>{

	public DescribableRenderer() {
		
	}

	@Override
	public Component getListCellRendererComponent(JList<? extends Describable> list, Describable value, int index,
			boolean isSelected, boolean cellHasFocus) {
		DefaultListCellRenderer dlcr = new DefaultListCellRenderer();
		JLabel jl = (JLabel) dlcr.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		jl.setText(value.getName());
		jl.setToolTipText(value.getDescription());
		
		return jl;
	}
}
