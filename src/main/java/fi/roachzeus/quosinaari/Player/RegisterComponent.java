package fi.roachzeus.quosinaari.Player;

import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

public class RegisterComponent extends CustomComponent {

	private static final long serialVersionUID = 356026945705794123L;
	private final Label nameLabel;
	private TextField name;
	private Button start;
	private VerticalLayout panelContent;
	private Panel panel;
	
	public RegisterComponent(){
		
		this.addStyleName("qRegister");
		panel = new Panel("Ilmoittautuminen");
        VerticalLayout panelContent = new VerticalLayout();
        panel.setContent(panelContent);
		
		nameLabel = new Label("Kirjoita nimesi: ");
		name = new TextField();
        
        // move logic to model/presenter
        start = new Button("Ilmoittaudu",
        		
        		new Button.ClickListener() {
			
			private static final long serialVersionUID = 1L;

		@Override
        public void buttonClick(ClickEvent event) {
            
			if(name.getValue().toString().length() < 1){ 
				System.out.println("Name is empty.");
				return;
			}
        }
        });
       
        panelContent.addComponent(nameLabel);
        panelContent.addComponent(name);
        panelContent.addComponent(start);
        
        setCompositionRoot(panel);
        // Set the size as undefined at all levels
        panelContent.setSizeUndefined();
        panel.setSizeUndefined();
        // this is not needed for a Composite
        setSizeUndefined();
	}
	

}
