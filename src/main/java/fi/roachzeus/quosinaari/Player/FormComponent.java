package fi.roachzeus.quosinaari.Player;

import java.util.ArrayList;

import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

public class FormComponent extends CustomComponent{

	private static final long serialVersionUID = 2049937040068696255L;
	private VerticalLayout panelContent;
	private Panel panel;
	private Label question;
	private Button a, b, c, d;
	private Button ok, clear, start;
	private StringBuilder bob;
	private ArrayList<String> data;
	private long startTime, endTime;
	
	
	public FormComponent(){
		
		this.addStyleName("qForm");
		panel = new Panel("Kysymys");
        VerticalLayout panelContent = new VerticalLayout();
        panel.setContent(panelContent);
        
        clear = new Button("Clear");
        clear.addClickListener(e -> this.clear());
        
        start = new Button("Start");
        start.addClickListener(e -> this.start());
        
        data = this.getQuestionData();
        
		question = new Label(data.get(0));
		
		a = new Button("A: ");
		a.setStyleName("qLabel");
		a.addClickListener(e -> this.tap(e));
		
		b = new Button("B: ");
		b.setStyleName("qLabel");
		b.addClickListener(e -> this.tap(e));
		
		c = new Button("C: ");
		c.setStyleName("qLabel");
		c.addClickListener(e -> this.tap(e));
		
		d = new Button("D: ");
		d.setStyleName("qLabel");
		d.addClickListener(e -> this.tap(e));
		
		ok = new Button("OKbtn");
		ok.addClickListener(e -> this.submit());
		ok.setEnabled(false);
		
		panelContent.addComponent(clear);
		panelContent.addComponent(start);
		panelContent.addComponent(question);
		panelContent.addComponent(a);
		panelContent.addComponent(b);
		panelContent.addComponent(c);
		panelContent.addComponent(d);
		panelContent.addComponent(ok);
		
		setCompositionRoot(panel);
        // Set the size as undefined at all levels
        panelContent.setSizeUndefined();
        panel.setSizeUndefined();
        // this is not needed for a Composite
        setSizeUndefined();
        
        bob = new StringBuilder();
        bob.setLength(0);
		
	}
	public void start(){
		
		startTime = System.currentTimeMillis();
		a.setCaption("A: " + data.get(1));
		b.setCaption("B: " + data.get(2));
		c.setCaption("C: " + data.get(3));
		d.setCaption("D: " + data.get(4));
		
	}
	
	private void clear(){
		a.setEnabled(true);
		b.setEnabled(true);
		c.setEnabled(true);
		d.setEnabled(true);
		bob.setLength(0);
		
		//System.out.println("");	
		
	}
	private void tap(Button.ClickEvent e){
		e.getComponent().setEnabled(false);
		
		switch (e.getComponent().getCaption().charAt(0)){
			case ('A'):
				bob.append("A");
				break;
			case ('B'):
				bob.append("B");
				break;
			case ('C'):
				bob.append("C");
				break;
			case ('D'):
				bob.append("D");
				break;
			default:
				//nada
				break;
		
		}
		System.out.println("bob: " + bob);
		if(bob.length() > 3 && bob.length() < 5){
			ok.setEnabled(true);
		}
		
	}
	private void submit(){
		
		// stuff todo...
		
		endTime = System.currentTimeMillis();
		//finally flush bob
		if(bob.equals(data.get(5))){
			
			//oikein
		}
		
		bob.setLength(0);
		ok.setEnabled(false);
		System.out.println("Time: "  + String.format("%.2f", (endTime-startTime)/1000.00d));
		
		
		
	}
	public void show(){
		this.setVisible(true);
	}
	public void hide(){
		this.setVisible(false);
	}
	private ArrayList<String> getQuestionData(){
		
		ArrayList<String> list = new ArrayList<String>();
		
		list.add("Laita seuraavat asiat järjestykseen.");
		list.add("Vaihtoehto A");
		list.add("Vaihtoehto B");
		list.add("Vaihtoehto C");
		list.add("Vaihtoehto D");
		list.add("ABCD");
		
		return list;
		
	}

}
