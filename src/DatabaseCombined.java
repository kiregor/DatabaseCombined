import java.awt.*;
import java.awt.event.*;

public class DatabaseCombined {
	Frame f;
	Button b1, b2;
	
	public DatabaseCombined() {
		
		f = new Frame();
		
		DatabaseRetrieve dbr = new DatabaseRetrieve(f);
		DatabaseSend dbs = new DatabaseSend(f);
		
		
		f.setLayout(new GridLayout(2,1));
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		b1 = new Button("Data Entry");
		b2 = new Button("View Records");
		
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dbs.f.setLocation(f.getLocation());
				dbs.f.setVisible(true);
				f.setVisible(false);
			}
		});
		
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dbr.f.setLocation(f.getLocation());
				dbr.f.setVisible(true);
				dbr.getData();
				f.setVisible(false);
			}
		});
		
		f.add(b1);
		f.add(b2);
		
		f.setSize(200, 200);
		f.setVisible(true);
	}
	
	public static void main(String[] args) {
		new DatabaseCombined();
	}
}

