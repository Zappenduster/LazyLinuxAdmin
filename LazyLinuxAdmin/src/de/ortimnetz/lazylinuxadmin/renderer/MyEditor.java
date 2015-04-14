package de.ortimnetz.lazylinuxadmin.renderer;

import java.awt.Component;

import javax.swing.DefaultCellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;

public class MyEditor extends DefaultCellEditor{

	private JTextField textField;
	
	public MyEditor(JTextField textField) {
		super(textField);
		this.textField = textField;
		
	}
	
	@Override
	public Object getCellEditorValue() {
		// TODO Auto-generated method stub
		System.out.println("getCellEditorValue");
		return textField;
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		// TODO Auto-generated method stub
		System.out.println("getTableCellEditorComponent");
		return textField;
	}


	
	
}
