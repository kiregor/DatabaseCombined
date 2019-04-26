import java.awt.*;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseRetrieve {
	Frame f;
	ResultSet rs;
	Button b1, b2, b3;
	TextField t1,t2,t3,t4,t5;
	Label l1,l2,l3,l4,l5;
	
	public DatabaseRetrieve(Frame mainFrame) {
		t1 = new TextField();
		t2 = new TextField();
		t3 = new TextField();
		t4 = new TextField();
		t5 = new TextField();
		
		b1 = new Button("Next");
		b2 = new Button("Update");
		b3 = new Button("Previous");
		
		l1 = new Label("Regno:");
		l2 = new Label("Name:");
		l3 = new Label("Mark:");
		l4 = new Label("Percentage:");
		l5 = new Label("Result:");
		
		Panel p1 = new Panel();
		p1.setLayout(new GridLayout(2,1));
		
		f = new Frame("Records");
		f.setLayout(new GridLayout(6,2));
		
		p1.add(b1);
		p1.add(b3);
		
		f.add(l1);
		f.add(t1);
		f.add(l2);
		f.add(t2);
		f.add(l3);
		f.add(t3);
		f.add(l4);
		f.add(t4);
		f.add(l5);
		f.add(t5);
		f.add(b2);
		f.add(p1);
		
		f.setSize(400, 400);
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				mainFrame.setLocation(f.getLocation());
				f.setVisible(false);
				mainFrame.setVisible(true);
			}
		});
		
		getData();
		
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent x) {
				try {
					if(rs.next()) {
						showRecord();
					}
				}
				catch(Exception e) {
					System.out.println(e.toString());
				}
			}
		});
		
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent x) {
				getData();
			}
		});
		
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent x) {
				try {
					if(rs.previous()) {
						showRecord();
					}
				}
				catch(Exception e) {
					System.out.println(e.toString());
				}
			}
		});
	}
	
	public void showRecord() {
		try {
			t1.setText(Integer.toString(rs.getInt(1)));
			t2.setText(rs.getString(2));
			t3.setText(Integer.toString(rs.getInt(3)));
			t4.setText(Float.toString(rs.getInt(3)*100/150f)+"%");
			if(Float.parseFloat(t4.getText().substring(0, t4.getText().length()-2))>60f) {
				t5.setText("Pass");
			}
			else {
				t5.setText("Fail");
			}
		}
		catch(Exception e) {
			System.out.println(e.toString());
		}
	}
	
	public void getData() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/QA", "root", "");
			Statement st = con.createStatement();
			rs = st.executeQuery("select * from school");
			if(rs.next()) {
				showRecord();
			}
		}
		catch(Exception e) {
			System.out.println(e.toString());
		}
	}
}