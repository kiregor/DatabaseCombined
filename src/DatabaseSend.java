import java.sql.*;
import java.awt.*;
import java.awt.event.*;

public class DatabaseSend implements ActionListener{
	
	Frame f;
	TextField input2, input3;
	Button bupload;
	
	public DatabaseSend(Frame mainFrame) {
		input2 = new TextField(20);
		input3 = new TextField(20);
		bupload = new Button("Save");
		Panel p1 = new Panel(), p2 = new Panel();
		
		p1.setLayout(new GridLayout(2,2));
		p2.setLayout(new BorderLayout());
		
		Label l2 = new Label("Name:"), l3 = new Label("Marks:");
		
		
		f = new Frame("Input info");
		
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				mainFrame.setLocation(f.getLocation());
				f.setVisible(false);
				mainFrame.setVisible(true);
			}
		});
		
		bupload.addActionListener(this);

		p1.add(l2);
		p1.add(input2);
		p1.add(l3);
		p1.add(input3);
		p2.add(bupload);
		
		f.add(p1);
		f.add(p2, BorderLayout.SOUTH);
		
		f.setSize(300, 100);
		
	}

	
	public void actionPerformed(ActionEvent e) {
				
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/QA", "root", "");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select max(regno)+1 from school");
			rs.next();
			st.executeUpdate("insert into school values (" + Integer.toString(rs.getInt(1)) + ",'" + input2.getText() + "'," + input3.getText() + ")");
		}
		catch(Exception x) {
			System.out.println(e.toString());
		}

		input2.setText("");
		input3.setText("");

	}
}