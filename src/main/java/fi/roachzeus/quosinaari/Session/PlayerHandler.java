package fi.roachzeus.quosinaari.Session;

import java.util.stream.Stream;

import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.DataProviderListener;
import com.vaadin.data.provider.Query;
import com.vaadin.shared.Registration;

//import com.vaadin.server.VaadinSession;

public class PlayerHandler implements DataProvider<String, String> {

	public PlayerHandler(){
		// cnstrcrt
		//System.out.println("Player handler object created!");
	}

	@Override
	public boolean isInMemory() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size(Query<String, String> query) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Stream<String> fetch(Query<String, String> query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void refreshItem(String item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void refreshAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Registration addDataProviderListener(DataProviderListener<String> listener) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
