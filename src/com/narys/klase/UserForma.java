package com.narys.klase;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;


import database.Narys;
import database.NarysDao;

public class UserForma extends JFrame implements ActionListener {

    JTextArea textArea = new JTextArea(); 	// registration info
    
    JComboBox monthList;
    JButton register, add, update, delete, search;
    TextField id, name, surname, email, birthDate, sex, password;
    
   
		
    public UserForma() {
        create();
        setTitle("Rendez-vous.com registration form");
        setVisible(true);
        pack();
    }

    public void actionPerformed(ActionEvent event){
		String name = this.name.getText().toString();
    
    if(!validateName(name))
		JOptionPane.showMessageDialog(this, "Incorect name", "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    public static boolean validateName(String name) { 
		final Pattern VALID_NAME = 
			    Pattern.compile("^[a-zA-Z0-9!@#$%^&*]{1,100}$", Pattern.CASE_INSENSITIVE);
		Matcher matcher = VALID_NAME.matcher(name);
		return matcher.find();
	}
    
    public void cleanFields() {
        id.setText("");
        name.setText("");
        surname.setText("");
        email.setText("");
        birthDate.setText("");
        sex.setText("");
        password.setText("");
    }
    		
    public void create() {

        Container container = getContentPane();
        container.setLayout(new GridLayout(11, 0, 0, 2));
        container.setBackground(Color.gray);
        
        textArea.setEditable(false);
        textArea.setBackground(Color.lightGray);
        textArea.setForeground(Color.black);
    
        //Id
        JPanel panelId = new JPanel();
        panelId.setBorder(new TitledBorder("ID:"));
        id = new TextField("", 20);
        panelId.add(id);
        container.add(panelId);
        
        //Name
        JPanel panelName = new JPanel();
        JPanel panelSurname = new JPanel();
        panelName.setBorder(new TitledBorder("Name:"));
        name = new TextField("", 20);
        panelName.add(name);
        container.add(panelName);
        
        //Surname
        panelSurname.setBorder(new TitledBorder("Surname:"));
        surname = new TextField("", 20);
        panelSurname.add(surname);
        container.add(panelSurname);
        
        //BirthDate
        JPanel panelBirthDate = new JPanel();
      	panelBirthDate.setBorder(new TitledBorder("Birth date: "));
      	String [] years = new String[100];
      	for(int i = 0; i < 100; i++){
      	int begin = 1910 + i;
      	years[i] = Integer.toString(begin);
      	}
      	String month[] = { "January", "February", "March", "April", "May", "June", "July",
      	"August", "September", "October", "November", "December"};
      	String day [] = new String[32];
      	for(int i = 1; i < 32; i++){
      	day[i] = Integer.toString(i);
      	}
      	JComboBox dayList = new JComboBox(day);
      	JComboBox monthList = new JComboBox(month);
      	JComboBox yearList = new JComboBox(years);
      	dayList.setSelectedIndex(1);
      	monthList.setSelectedIndex(1);
      	yearList.setSelectedIndex(50);
      	panelBirthDate.add(yearList);
      	panelBirthDate.add(monthList);
      	panelBirthDate.add(dayList);
      	container.add(panelBirthDate);
      	
      	//Sex
      	JPanel panelSex = new JPanel();
     	panelSex.setBorder(new TitledBorder("Sex: "));
     	JRadioButton man = new JRadioButton("man");
     	JRadioButton woman = new JRadioButton("woman");
     	ButtonGroup bG = new ButtonGroup();
     	bG.add(man);
     	bG.add(woman);
     	panelSex.add(man); 
     	panelSex.add(woman);
     	container.add(panelSex);
		
     	//Email
     	JPanel panelEmail = new JPanel();
        panelEmail.setBorder(new TitledBorder("Email: "));
        email = new TextField("", 20);
        panelEmail.add(email);
        container.add(panelEmail);
        
        //Password
        JPanel panelPassword = new JPanel();
        panelPassword.setBorder(new TitledBorder("Password: "));
        JPasswordField pf = new JPasswordField(15);
        pf.setEchoChar('*');
        panelPassword.add(pf);
		container.add(panelPassword);
     	
        //CheckBox
		JPanel panelCheckBox = new JPanel();
        panelCheckBox.setBorder(new TitledBorder("Agreement policy: "));
		JCheckBox agree;
		agree = new JCheckBox("I agree with the policy of this site");
		panelCheckBox.add(agree);
		agree.setAlignmentY(LEFT_ALIGNMENT);
		container.add(panelCheckBox);
		
		//Register
		JPanel panelRegister = new JPanel();	
		register = new JButton("Register");
		panelRegister.add(register);
		container.add(panelRegister);
		/*register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(container, "Data confirmed!");
			}
			});*/
		
        //Buttons
		JPanel panelSubmit = new JPanel();
        panelSubmit.setBorder(new TitledBorder("Actions:"));
        add 	= new JButton ("Add");
        update 	= new JButton ("Update");
        delete 	= new JButton ("Delete");
        search 	= new JButton ("Search");
        panelSubmit.add(add);
        panelSubmit.add(update);
        panelSubmit.add(delete);
        panelSubmit.add(search);
        container.add(panelSubmit);
        
 
        // Create table for display results from db
        JTable table = new JTable(new DefaultTableModel(new Object[]{"Id", "Name", "Surname", "Email", 
        		"Birth date", "Sex", "Password"}, 0));
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        JScrollPane scrollPane = JTable.createScrollPaneForTable(table);
	    scrollPane.setPreferredSize(new Dimension(400, 100));
	    container.add(scrollPane);
	    
        NarysDao dao = new NarysDao();

        update.addActionListener(e -> {
            Narys narys = new Narys(Integer.valueOf(id.getText()), name.getText(), surname.getText(), 
            	email.getText(), birthDate.getText(), sex.getText(), password.getText());
            dao.updateNarys(narys);
            JOptionPane.showMessageDialog(container, "user updated successfully", "Info" , 
            JOptionPane.INFORMATION_MESSAGE);
            cleanFields();
        });

        add.addActionListener(new ActionListener()
        {
        	  public void actionPerformed(ActionEvent e)
        	  {
        		  Narys narys = new Narys();
        		  String firstName = name.getText().toString();
        		  narys.setName(firstName);
        		  narys.setSurname(surname.getText());
        		  narys.setEmail(email.getText());
        		  narys.setBirthDate(birthDate.getText());
        		  narys.setSex(sex.getText());
        		  narys.setPassword(password.getText());

        	      dao.addNarys(narys);
        		  JOptionPane.showMessageDialog(container, "New user added successfully", "Info" ,
        		  JOptionPane.INFORMATION_MESSAGE);
        		  cleanFields();
        	  }
        	
        });
        
        search.addActionListener(new ActionListener()
        {
        	  public void actionPerformed(ActionEvent e)
        	  {
        		  // on new search - deletes all earlier rows with old results
        		  model.setRowCount(0);
        		  
        		  // do search in db
        		  java.util.List<Narys> nariai;
        		  if (name.getText().toString().equals("")) {
        			  nariai = dao.getAllNariai();//Narys vietoj Nariai?
        		  } else {
        			  nariai = dao.getNarysByName(name.getText().toString());
        		  }
        		  
        		  // add rows from list to table
        		  Object[] data;
        		  int rowNumber = 0;
        		  for (Narys narys : nariai) {
        			  if(!nariai.isEmpty()) {// list with results
        				  data = new Object[8];
        				  data[0] = ++rowNumber;
        				  data[1] = narys.getId();
        				  data[2] = narys.getName();
        				  data[3] = narys.getSurname();
        				  data[4] = narys.getEmail();
        				  data[5] = narys.getBirthDate();
        				  data[6] = narys.getSex();
        				  data[7] = narys.getPassword();
        				  model.addRow(data);
        			  } else { // empty list
        				  model.setRowCount(0);
        			  }
        		  }
      	    
        	  }
        	
        });
        
       
    }//create

}//class
