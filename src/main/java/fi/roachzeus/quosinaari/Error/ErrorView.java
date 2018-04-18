package fi.roachzeus.quosinaari.Error;

import com.vaadin.navigator.View;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;

public class ErrorView extends CssLayout implements View{

	private static final long serialVersionUID = 1L;
	
	public ErrorView(){
		
		Label er = new Label("Some error shit goin on...");
		this.addComponent(er);
		
	}
	
}