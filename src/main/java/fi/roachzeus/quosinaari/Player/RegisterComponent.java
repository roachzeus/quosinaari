package fi.roachzeus.quosinaari.Player;

import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import redis.clients.jedis.Jedis;

import com.vaadin.ui.Button.ClickEvent;

public class RegisterComponent extends CustomComponent {

	private static final long serialVersionUID = 356026945705794123L;
	private final Label nameLabel;
	private TextField name;
	private Button start;
	private VerticalLayout panelContent;
	private Panel panel;
	private boolean registerOK = false;
	
	public RegisterComponent(){
		
		this.addStyleName("qRegister");
		panel = new Panel("Ilmoittautuminen");
        VerticalLayout panelContent = new VerticalLayout();
        panel.setContent(panelContent);
		
		nameLabel = new Label("Kirjoita nimesi: ");
		name = new TextField();
        
        // move logic to model/presenter
        start = new Button("Ilmoittaudu");
        start.addClickListener(event -> BtnClick());
       
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
	
	private void BtnClick(){
		if(name.getValue().toString().length() < 1){ 
			System.out.println("Name is empty.");
			return;
		}
		Jedis j = new Jedis(PlayerView.REDIS_HOST);
		
		j.clientSetname("player");
		
		j.hset(UI.getCurrent().getSession().getSession().getId(), "plrName", name.getValue().toString());
		System.out.println(name.getValue().toString());
		
		if(j.hget(UI.getCurrent().getSession().getSession().getId(), "plrName").equals(name.getValue().toString())){
			registerOK = true;
			this.setVisible(false);
			

		}
		j.close();
		
	}
	public boolean registerOk(){
		return registerOK;
	}

}
