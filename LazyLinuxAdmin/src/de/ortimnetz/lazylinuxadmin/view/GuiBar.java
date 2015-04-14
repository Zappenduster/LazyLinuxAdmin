package de.ortimnetz.lazylinuxadmin.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Label;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JDialog;
import javax.swing.JProgressBar;

import de.ortimnetz.lazylinuxadmin.controller.Controller;

public class GuiBar extends JDialog implements Observer{

	private JProgressBar bar;
	
	public GuiBar() {
	super();
	Controller.getInstance().addObserver(this);
//	frame.setTitle("Progress ...");
//	frame.setSize(500, 150);
//	frame.setVisible(true);
	this.setModal(true);
	bar = new JProgressBar(0,100);
	this.add(BorderLayout.CENTER,bar);
	this.add(BorderLayout.NORTH, new Label("Progress ..."));
	this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
	bar.setPreferredSize(new Dimension(400,100));
	
	this.pack();
	bar.setStringPainted(true);
	this.setVisible(true);
	}

	int x=0;
	@Override
	public void update(Observable o, Object arg) {
		x++;
		bar.setMaximum(Controller.getInstance().getProgressMax());
		bar.setValue(x);
		if (bar.getPercentComplete()==1) {
			this.dispose();
		}
		
	}
	
	
	
}
