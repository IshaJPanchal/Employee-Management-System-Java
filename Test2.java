import java.util.*;
import javax.swing.*;
import java.awt.event.*;

abstract class Main {
	protected String name, mail_id, address, date, y, pass;
	protected double no1, no2;
	protected int len, year, choice = 0, temp;
	protected float bp, da, hra, pf, scf,tc, pa, ta, ps=0;
	Main() {
		Date d = new Date();
		date = d.toString();
		len = date.length();
		y = date.substring(len - 4);
		year = Integer.parseInt(y);
	}
	public boolean check(String nm, String password ){
		if(nm.equalsIgnoreCase(name) && password.equals(pass)) return true;
		else return false;
	}
	abstract public void edit_details(Test2 t2);

	protected void edit_pass(Test2 t2) {
		pass=t2.pw.getText();
	}
}

final class Admin extends Main {
	private int ch1 = 0, ch2 = 0, choice1 = 0, choice2 = 0, max_manage, max_sale, i, j, day;
	private double emp_id;
	private String dept;
	private float sal_temp;
	Management tempm;
	Sales temps;
	Admin(Test2 t2) {
		name = t2.n.getText();
		mail_id = t2.mid.getText();
		address = t2.add.getText();
		no1 = t2.no1;
		no2 = t2.no2;
		pass = t2.pw.getText();
	}

	public boolean check(String password) {
		if (password.equals(pass))
			return true;
		else
			return false;
	}

	public void edit_details(Test2 t2){
		name = t2.n.getText();
		mail_id = t2.mid.getText();
		address = t2.add.getText();
		no1 = t2.no1;
		no2 = t2.no2;
	}
	public void view_details(Test2 t2){
		t2.n1.setText("Name: "+name);t2.n1.setVisible(true);
		t2.mid1.setText("Mail ID: "+mail_id);t2.mid1.setVisible(true);
		t2.add.setEditable(false);t2.add.setText(address);t2.add1.setVisible(true);t2.add.setVisible(true);
		t2.pnl1.setText("Contact No.1: "+no1);t2.pnl1.setVisible(true);
		t2.pnl2.setText("Contact No.2: "+no2);t2.pnl2.setVisible(true);
	}
	public void view_pass(Test2 t2){
		t2.pw1.setBounds(10, 100, 300, 30);
		t2.pw1.setText("Pass Word : "+pass);
		t2.pw1.setVisible(true);
	}
	public void dept_list(Test2 t2){
		String str;
		int temp;
		dept=t2.dept.getText();
		str="\n\t\t\tManagement\nName \t\t E-Mail ID \t\t Net Salary\n";
		// Manage = i , Sale = j
		try{
			for (temp = 0; temp <= t2.i; temp++) {
				if (t2.mg[temp].department.equalsIgnoreCase(dept)) {
					str=str+t2.mg[temp].name + "\t\t" + t2.mg[temp].mail_id + "\t\t" + t2.mg[temp].net_sal+"\n";
				}
			}
		}
		catch(NullPointerException ne){}
		catch(ArrayIndexOutOfBoundsException ae){
			str=str+"No management Employee in this category\n";
		}
		str=str+"\t\t\tSales\n";
		try{
			for (temp = 0; temp <= t2.j; temp++) {
				if (dept.equalsIgnoreCase(t2.sl[temp].department)) {
					str=str+t2.sl[temp].name + "\t\t" + t2.sl[temp].mail_id + "\t\t" + t2.sl[temp].net_sal;
				}
			}
		}
		catch(NullPointerException ne){}
		catch(ArrayIndexOutOfBoundsException ae){
			str=str+"No Sales Employee in this category\n";
		}
		t2.list.setText(str);
		t2.list.setVisible(true);
	}
	public void view_sal_list(Test2 t2){
		String str;
		int temp, temp1;
		sal_temp = Float.parseFloat(t2.bp.getText());
		str="\n\t\t\tManagement\nName \t\t E-Mail ID \t\t Salary\n";
		try{
			for(temp1=0; temp1<= t2.i-1; temp1++){
				for(temp = 0; temp <= (t2.i- 1- temp1); temp++){
					t2.mg[temp].calc();
					if(t2.mg[temp].sort(t2.mg[temp+1])){
						tempm = t2.mg[temp];
						t2.mg[temp] = t2.mg[temp+1];
						t2.mg[temp+1] = tempm;
					}
				}
			}
			for(temp=0; temp<=t2.i;temp++){
				if(t2.mg[temp].net_sal>=sal_temp){
					str=str+t2.mg[temp].name + "\t\t" + t2.mg[temp].mail_id + "\t\t" + t2.mg[temp].net_sal+"\n";
				}
			}
		}
		catch(NullPointerException ne){}
		catch(ArrayIndexOutOfBoundsException ae){
			str=str+"\t\t No Management Employee in this Category\n";
		}
		str=str+"\t\t\tSales\nName \t\t E-Mail ID \t\t Salary\n";
		try{
			for(temp1=0; temp1<= t2.j-1; temp1++){
				for(temp = 0; temp <= (t2.j- 1- temp1); temp++){
					t2.sl[temp].calc();
					if(t2.sl[temp].sort(t2.sl[temp+1])){
						temps = t2.sl[temp];
						t2.sl[temp] = t2.sl[temp+1];
						t2.sl[temp+1] = temps;
					}
				}
			}
		}
		catch(NullPointerException ne){}
		catch(ArrayIndexOutOfBoundsException ae){
			str=str+"\t\t No Sales Employee in this Category\n";
		}
		try{
			for(temp=0; temp<=t2.j;temp++){
				if(t2.sl[temp].ta>=sal_temp){
					str=str+t2.sl[temp].name + "\t\t" + t2.sl[temp].mail_id + "\t\t" + t2.sl[temp].ta+"\n";
				}
			}
		}
		catch(NullPointerException ne){}
		t2.list.setText(str);t2.list.setVisible(true);
	}
	public void view_emp_list(Test2 t2){
		String str;
		int temp;
		System.out.println("Management Employee List");
		str="Management Employee List\nName \t E-Mail ID \t Net Salary\n";
		try{
			for (temp = 0; temp <= t2.i; temp++) {
				str=str+t2.mg[temp].name + "\t" + t2.mg[temp].mail_id + "\t" + t2.mg[temp].net_sal+"\n";
			}
		}
		catch(NullPointerException ne){}
		catch(ArrayIndexOutOfBoundsException ae){
			str=str+"No Management Account found\n";
		}
		System.out.println("Sales Employee List:");
		str=str+"Sales Employee List\nName \t E-Mail ID \t Net Salary\n";
		try{
			for (temp = 0; temp <= t2.j; temp++) {
				str=str+t2.sl[temp].name + "\t" + t2.sl[temp].mail_id + "\t" + t2.sl[temp].net_sal+"\n";
			}
		}
		catch(NullPointerException ne){}
		catch(ArrayIndexOutOfBoundsException ae){
			str=str+"No Sales Account found\n";
		}
		t2.list.setText(str);
	}
	public void edit_sal(Test2 t2){
		if(t2.m1==1){
			t2.mg[t2.t1].bp=Float.parseFloat(t2.bp.getText());
			t2.mg[t2.t1].calc();
		}
		else{
			t2.sl[t2.t1].ps=Float.parseFloat(t2.bp.getText());
			t2.sl[t2.t1].calc();
		}
	}
	public void leave(Test2 t2){
		ch2=t2.lt.getSelectedIndex();
		day=Integer.parseInt(t2.nd.getText());
		if(t2.m1==1){
			if(ch2==1) {
				t2.mg[t2.t1].cl=t2.mg[t2.t1].cl+day;
			}
			else if(ch2==2) {
				t2.mg[t2.t1].el=t2.mg[t2.t1].el+day;
				t2.mg[t2.t1].elc=t2.mg[t2.t1].elc-day;
			}
		}
		else{
			if(ch2==1) {
				t2.sl[t2.t1].cl=t2.sl[t2.t1].cl+day;
				t2.sl[t2.t1].clc=t2.sl[t2.t1].clc-day;
			}
			else if(ch2==2) {
				t2.sl[t2.t1].el=t2.sl[t2.t1].el+day;
				t2.sl[t2.t1].elc=t2.sl[t2.t1].elc-day;
			}
		}
	}
}

abstract class User extends Main {
	protected float net_sal, gross_sal;
	protected String department;
	protected double id;

	abstract public void view_sal(Test2  t2);

	public void details(Test2 t2) {
		t2.n1.setText("Name :"+name);
		t2.n1.setVisible(true);
		t2.mid1.setText("Mail-ID :"+mail_id);
		t2.mid1.setVisible(true);
		t2.add.setText(address);
		t2.add.setVisible(true);
		t2.add1.setVisible(true);
		t2.pnl1.setText("Contact No. 1 :"+no1);
		t2.pnl1.setVisible(true);
		t2.pnl2.setText("Contact No. 2 :"+no2);
		t2.pnl2.setVisible(true);
		t2.eid1.setText("Employee ID :"+id);
		t2.eid1.setVisible(true);
		t2.dept1.setText("Department :"+department);
		t2.dept1.setVisible(true);
	}

	public boolean check(double id, String pass) {
		if (this.id == id && pass.equals(this.pass))
			return true;
		else
			return false;
	}

	public void edit_details(Test2 t2){
		name = t2.n.getText();
		mail_id = t2.mid.getText();
		address = t2.add.getText();
		no1 = t2.no1;
		no2 = t2.no2;
		id = t2.id;
		department = t2.dept.getText();
	}
	protected void view_pass(Test2 t2) {
		t2.pw1.setText("Password :"+pass);
		t2.pw1.setBounds(10, 100, 300, 30);
		t2.pw1.setVisible(true);
	}

	abstract public void leave(Test2 t2);

	abstract public void view_leave(Test2 t2);
}

final class Management extends User {
	private int yr, day;
	public int cl = 10, el = 10, elc = 0;
	private String y;

	Management(Test2 t2) {
		name = t2.n.getText();
		mail_id = t2.mid.getText();
		address = t2.add.getText();
		no1 = t2.no1;
		no2 = t2.no2;
		pass = t2.pw.getText();
		id = t2.id;
		department = t2.dept.getText();
	}

	public boolean sort(Management m) {
		if (net_sal > m.net_sal)
			return false;
		else
			return true;
	}

	public void calc() {
		da = bp * 97 / 100;
		hra = bp * 10 / 100;
		pf = bp * 12 / 100;
		scf = bp * 0.1f / 100;
		gross_sal = bp + da + hra;
		net_sal = gross_sal - pf - scf;
	}

	public void view_sal(Test2 t2) {
		t2.bp1.setText("Basic Pay :\t"+bp);
		t2.da.setText("DA :\t"+da);
		t2.hra.setText("HRA :\t"+hra);
		t2.gs.setText("Gross Salary:\t"+gross_sal);
		t2.ns.setText("Net Salary :\t"+net_sal);
	}

	public void leave(Test2 t2) {
		Date d1 = new Date();
		y = d1.toString();
		len = y.length();
		y = y.substring(len - 4);
		yr = Integer.parseInt(y);
		choice=t2.lt.getSelectedIndex();
			day=Integer.parseInt(t2.nd.getText());
		if (year == yr) {
			switch (choice) {
				case 1:
					if (cl - day >= 0) {
						cl = cl - day;
						t2.note.setText("Leave Granted\t Total no. of leaves left:" + cl);
					} else{
						t2.note.setText("Request exceeds the no. of leaves available");
					}
					t2.note.setVisible(true);
					break;
				case 2:
					System.out.print("Enter no.of days:");
					if (el - day >= 0) {
						el = el - day;
						elc = elc + day;
						t2.note.setText("Leave Granted\t Total no. of leaves left:" + el);
					} else{
						t2.note.setText("Request exceeds the no. of leaves available");
					}
					t2.note.setVisible(true);
					break;
				case 3:
					break;
				default:
					System.out.println("Invalid choice");
			}
		} else {
			cl = 10;
			el = el + (year - yr) * 10;
			yr = year;
			System.out.print("1.Casual Leave \n2.Earned leave\n3.Back to menu\nEnter Choice:");
			switch (choice) {
				case 1:
					if (cl - day >= 0) {
						cl = cl - day;
						t2.note.setText("Leave Granted\t Total no. of leaves left:" + cl);
					}
					else{
						t2.note.setText("Request exceeds the no. of leaves available");
					}
					t2.note.setVisible(true);
					break;
				case 2:
					if (el - day >= 0) {
						el = el - day;
						elc = elc + day;
						t2.note.setText("Leave Granted\t Total no. of leaves left:" + el);
					} else{
						t2.note.setText("Request exceeds the no. of leaves available");
					}
					t2.note.setVisible(true);
					break;
				case 3:
					break;
				default:
					System.out.println("Invalid choice");
			}
		}
	}

	public void view_leave(Test2 t2) {
		t2.note.setText("Leaves Talen CL:"+(10-cl)+"\tEL:"+elc);
		t2.note.setVisible(true);
	}	
}

final class Sales extends User {
	private int yr, day;
	public int cl=10, el=15, elc, clc;
	private String y;

	Sales(Test2 t2) {
		name = t2.n.getText();
		mail_id = t2.mid.getText();
		address = t2.add.getText();
		no1 = t2.no1;
		no2 = t2.no2;
		pass = t2.pw.getText();
		id = t2.id;
		department = t2.dept.getText();
	}

	public boolean sort(Sales s) {
		if (ta > s.ta)
			return false;
		else
			return true;
	}

	public void calc() {
		pa=tc*100/10;
		ta=ps+pa;
	}

	public void view_sal(Test2 t2) {
		t2.bp1.setText("Basic Pay :\t"+ps);t2.da.setText("Total Sale Done: \t"+tc);t2.gs.setText("Ammount Added :\t"+pa);t2.ns.setText("Total Salary :\t"+ta);
	}

	public void leave(Test2 t2) {
		Date d1 = new Date();
		y = d1.toString();
		len = y.length();
		y = y.substring(len - 4);
		yr = Integer.parseInt(y);
		choice=t2.lt.getSelectedIndex();
		choice=choice+1;
		day=Integer.parseInt(t2.nd.getText());
		if (year == yr) {
			switch (choice) {
				case 1:
					if (cl - day >= 0) {
						cl = cl - day;
						t2.note.setText("Leave Granted\t Total no. of leaves left:" + cl);
						t2.note.setVisible(true);
					} else{
						t2.note.setText("Request exceeds the no. of leaves available");
						t2.note.setVisible(true);
					}
					break;
				case 2:
					System.out.print("Enter no.of days:");
					if (el - day >= 0) {
						el = el - day;
						elc = elc + day;
						t2.note.setText("Leave Granted\t Total no. of leaves left:" + el);
						t2.note.setVisible(true);
					} else{
						t2.note.setText("Request exceeds the no. of leaves available");
						t2.note.setVisible(true);
					}
					break;
				case 3:
					break;
				default:
					System.out.println("Invalid choice");
			}
		} else {
			cl = 10;
			el = el + (year - yr) * 10;
			yr = year;
			switch (choice) {
				case 1:
					if (cl - day >= 0) {
						cl = cl - day;
						t2.note.setText("Leave Granted\t Total no. of leaves left:" + cl);
						t2.note.setVisible(true);
					}
					else{
						t2.note.setText("Request exceeds the no. of leaves available");
						t2.note.setVisible(true);
					}
					break;
				case 2:
					if (el - day >= 0) {
						el = el - day;
						elc = elc + day;
						t2.note.setText("Leave Granted\t Total no. of leaves left:" + el);
						t2.note.setVisible(true);
					} else{
						t2.note.setText("Request exceeds the no. of leaves available");
						t2.note.setVisible(true);
					}
					break;
				case 3:
					break;
				default:
					System.out.println("Invalid choice");
			}
		}
	}

	public void view_leave(Test2 t2) {
		t2.note.setText("No. of Leaves taken: CL:"+clc+"\tEL:"+elc);
		t2.note.setVisible(true);
	}
}

public class Test2 implements ActionListener{
	JFrame f = new JFrame("Employee Management System");
	int a1 = 0, m1 = 0, s1 = 0, su1=0, si1=0,b1=0, ps1=0, vd1=0, ed1=0, la1=0, vl1=0, ep1=0, cp1=0,vemp1=0, vdeptli1=0, vsalli1=0, vallli1=0, es1=0, edit_leave1=0;

	JLabel n1, mid1, dept1, eid1, add1, pnl1, pnl2, pw1, note, nd1, bp1, da, hra, gs, pf, scf, ns;

	JTextField n, mid, dept, eid, pn1, pn2, pw,nd,bp;

	JTextArea add;

	JTextPane list;

	JButton b, su, si, ad, m, s, bk, ps, vd, ed, la, vl, vemp, vdeptli, vsalli, vallli, es, edit_leave;		//ps(pay slip) = view Sal

	JMenuBar mb;
	JMenu menu;
	JMenuItem ep, cp, lo;
	JComboBox lt;
	String leave_type[]={"CL","EL"};

	String date, y, pass;
	double no1, no2, id;
	int len, year, choice = 0, temp, t1, i = 0, j = 0, k = 0;
	Admin a[] = new Admin[2];
	Management mg[] = new Management[100];
	Sales sl[] = new Sales[100];

	Test2() {
		// All GUI variables code

		n1 = new JLabel();n1.setBounds(10, 50, 100, 30);n1.setText("Name:");
		n = new JTextField();n.setBounds(120, 50, 280, 30);

		mid1 = new JLabel();mid1.setBounds(10, 100, 100, 30);mid1.setText("E-Mail ID:");
		mid = new JTextField();mid.setBounds(120, 100, 280, 30);

		dept1 = new JLabel();dept1.setBounds(10, 150, 110, 30);dept1.setText("Department:");
		dept = new JTextField();dept.setBounds(120, 150, 280, 30);

		add1 = new JLabel();add1.setBounds(10, 200, 100, 30);add1.setText("Address:");
		add = new JTextArea(); add.setBounds(120, 200, 280, 90);

		pnl1 = new JLabel();pnl1.setBounds(10, 300, 100, 30);pnl1.setText("Mobile No1:");
		pn1 = new JTextField();pn1.setBounds(120, 300, 280, 30);

		pnl2 = new JLabel();pnl2.setBounds(10, 350, 100, 30);pnl2.setText("Mobile No2:");
		pn2 = new JTextField();pn2.setBounds(120, 350, 280, 30);

		pw1 = new JLabel();pw1.setBounds(10, 400, 100, 30);pw1.setText("Password:");
		pw = new JTextField();pw.setBounds(120, 400, 280, 30);

		eid1 = new JLabel();eid1.setBounds(10, 450, 100, 30);eid1.setText("Emp ID:");
		eid = new JTextField();eid.setBounds(120, 450, 280, 30);

		b = new JButton("Submit");b.setBounds(190, 500, 95, 30);b.addActionListener(this);

		ad = new JButton("Admin");ad.setBounds(200, 250, 90, 40);ad.addActionListener(this);

		s = new JButton("Sales");s.setBounds(200, 300, 90, 40);s.addActionListener(this);

		m = new JButton("Management");m.setBounds(180, 350, 140, 40);m.addActionListener(this);

		su = new JButton("Sign UP");su.setBounds(200, 250, 90, 40);su.addActionListener(this);

		si = new JButton("Sign IN");si.setBounds(200, 300, 90, 40);si.addActionListener(this);

		bk = new JButton("Back");bk.setBounds(200, 550, 70, 30);bk.addActionListener(this);

		ps = new JButton("Pay Slip");ps.setBounds(180, 50, 150, 40);ps.addActionListener(this);

		vd = new JButton("View Details");vd.setBounds(180, 100, 150, 40);vd.addActionListener(this);

		ed =new JButton("Edit Details");ed.setBounds(180, 150, 150, 40);ed.addActionListener(this);

		la = new JButton("Leave Application");la.setBounds(180, 200, 150, 40);la.addActionListener(this);

		vl = new JButton("View Leave");vl.setBounds(180, 250, 150, 40);vl.addActionListener(this);

		es = new JButton("Edit Salary");es.setBounds(180, 100, 150, 40);es.addActionListener(this);	

		edit_leave = new JButton("Edit Leave");edit_leave.setBounds(180, 150, 150, 40);edit_leave.addActionListener(this);

		lt=new JComboBox<>(leave_type);lt.setBounds(50, 100, 50, 30);lt.addActionListener(this);

		nd1=new JLabel();nd1.setBounds(10, 150, 80, 40);nd1.setText("No. Of Days :");

		nd=new JTextField();nd.setBounds(90, 150, 50, 30);

		note=new JLabel();note.setBounds(10,600,600,30);

		bp = new JTextField();bp.setBounds(120, 50, 280, 40);

		bp1=new JLabel();bp1.setBounds(10, 50, 300, 30);
		da=new JLabel();da.setBounds(10, 100, 300, 30);
		hra=new JLabel();hra.setBounds(10, 150, 300, 30);
		gs=new JLabel();gs.setBounds(10, 200, 300, 30);
		pf=new JLabel();pf.setBounds(10, 250, 300, 30);
		scf=new JLabel();scf.setBounds(10, 300, 300, 30);
		ns=new JLabel();ns.setBounds(10, 350, 300, 30);

		mb=new JMenuBar();
		menu =new JMenu("Menu");
		ep=new JMenuItem("Edit Password");ep.addActionListener(this);
		cp=new JMenuItem("View Password");cp.addActionListener(this);
		lo=new JMenuItem("Log out");lo.addActionListener(this);

		menu.add(lo);menu.add(cp);menu.add(ep);mb.add(menu);

		list=new JTextPane();list.setBounds(5, 10, 500, 500);list.setEditable(false);

		vemp=new JButton("View / Edit Employee");vemp.setBounds(160, 100, 190, 40);vemp.addActionListener(this);

		vdeptli=new JButton("View Department List");vdeptli.setBounds(160, 150, 190, 40);vdeptli.addActionListener(this);

		vsalli=new JButton("View Employee List By Pay");vsalli.setBounds(160, 200, 190, 40);vsalli.addActionListener(this);

		vallli=new JButton("View All Employee List");vallli.setBounds(160, 250, 190, 40);vallli.addActionListener(this);
		// Adding in frame part
		
		f.add(m);f.add(s);f.add(ad);f.add(n1);f.add(n);f.add(mid1);f.add(mid);f.add(dept1);f.add(dept);f.add(eid1);f.add(eid);f.add(add1);f.add(add);f.add(pnl1);f.add(pn1);f.add(pnl2);f.add(pn2);f.add(pw1);f.add(pw);f.add(b);f.add(su);f.add(si);f.add(bk);f.add(note);
		f.add(ps);f.add(vd);f.add(ed);f.add(la);f.add(vl);f.setJMenuBar(mb);f.add(lt);f.add(nd1);f.add(nd);f.add(bp1);f.add(bp);f.add(da);f.add(hra);f.add(gs);f.add(pf);f.add(scf);f.add(ns);f.add(list);f.add(vemp);f.add(vdeptli);f.add(vsalli);f.add(vallli);f.add(es);f.add(edit_leave);
		
		n1.setVisible(false);n.setVisible(false);mid.setVisible(false);mid1.setVisible(false);dept1.setVisible(false);dept.setVisible(false);eid1.setVisible(false);eid.setVisible(false);add.setVisible(false);add1.setVisible(false);pnl1.setVisible(false);
		pn1.setVisible(false);pnl2.setVisible(false);pn2.setVisible(false);pw1.setVisible(false);pw.setVisible(false);b.setVisible(false);su.setVisible(false);si.setVisible(false);bk.setVisible(false);note.setVisible(false);mb.setVisible(false);
		ps.setVisible(false);vd.setVisible(false);ed.setVisible(false);la.setVisible(false);vl.setVisible(false);lt.setVisible(false);nd1.setVisible(false);nd.setVisible(false);bp.setVisible(false);bp1.setVisible(false);da.setVisible(false);hra.setVisible(false);gs.setVisible(false);pf.setVisible(false);scf.setVisible(false);ns.setVisible(false);
		list.setVisible(false);vemp.setVisible(false);vdeptli.setVisible(false);vsalli.setVisible(false);vallli.setVisible(false);es.setVisible(false);edit_leave.setVisible(false);

		f.setSize(500, 700);
		f.setLayout(null);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==m && vemp1==1){
			m1=1;
			System.out.println("Correct Choice!!");
			s.setVisible(false);m.setVisible(false);
			ed.setBounds(180, 100, 150, 40);vl.setBounds(180, 200, 150, 40);
			eid1.setText("Enter Emp. ID:");eid1.setVisible(true);eid.setVisible(true);b.setVisible(true);bk.setVisible(true);
		}
		if(e.getSource()==s && vemp1==1){
			s1=1;
			s.setVisible(false);m.setVisible(false);
			eid1.setText("Enter Emp. ID:");eid1.setVisible(true);eid.setVisible(true);b.setVisible(true);bk.setVisible(true);
		}
		else if (e.getSource() == su && (a1 == 1 || s1 == 1 || m1 == 1)) {
			su.setVisible(false);si.setVisible(false);
			su1 = 1;
			n1.setVisible(true);n.setVisible(true);mid.setVisible(true);mid1.setVisible(true);add.setVisible(true);add1.setVisible(true);pnl1.setVisible(true);pn1.setVisible(true);pnl2.setVisible(true);pn2.setVisible(true);pw1.setVisible(true);pw.setVisible(true);bk.setVisible(true);
			if (s1 == 1 || m1 == 1) {
				b.setVisible(true);eid1.setVisible(true);eid.setVisible(true);dept1.setVisible(true);dept.setVisible(true);
			}
			else if (a1 == 1) {
				b.setBounds(190, 500, 95, 30);
				b.setVisible(true);
			}
		}
		else if (e.getSource() == si && (a1 == 1 || s1 == 1 || m1 == 1)) {
			su.setVisible(false);si.setVisible(false);
			pw1.setBounds(10, 100, 100, 30);
			pw.setBounds(120, 100, 280, 30);
			b.setBounds(200, 200, 95, 30);
			si1 = 1;
			n1.setVisible(true);n.setVisible(true);pw1.setVisible(true);pw.setVisible(true);b.setVisible(true);bk.setVisible(true);
		}

		// GUI after MENU

		else if(e.getSource()==vd && a1==1 && (m1==1 || s1==1)){
			ps.setVisible(false);es.setVisible(false);vd.setVisible(false);vl.setVisible(false);edit_leave.setVisible(false);
			if(m1==1){
				mg[t1].details(this);
			}
			else{
				sl[t1].details(this);
			}
			vd1=1;
		}
		else if(e.getSource()==vl && a1==1 && (m1==1 || s1==1)){
			ps.setVisible(false);es.setVisible(false);vd.setVisible(false);vl.setVisible(false);edit_leave.setVisible(false);
			if(m1==1){
				mg[t1].view_leave(this);
			}
			else{
				sl[t1].view_leave(this);
			}
			vl1=1;
		}
		else if(e.getSource()==ps && a1==1 && (m1==1 || s1==1)){
			ps.setVisible(false);es.setVisible(false);vd.setVisible(false);vl.setVisible(false);edit_leave.setVisible(false);
			if(s1==1){
				bp1.setVisible(true);da.setVisible(true);gs.setVisible(true);ns.setVisible(true);
				sl[t1].calc();
				sl[t1].view_sal(this);
			}
			else {
				bp1.setVisible(true);da.setVisible(true);hra.setVisible(true);gs.setVisible(true);pf.setVisible(true);scf.setVisible(true);ns.setVisible(true);
				mg[t1].calc();
				mg[t1].view_sal(this);
			}
			ps1=1;
		}
		else if(e.getSource()==es && a1==1 && (m1==1 || s1==1)){
			es1=1;
			bp1.setBounds(10, 50, 100, 40);bp1.setText("Basic Salary :");
			bp1.setVisible(true);bp.setVisible(true);b.setVisible(true);
			ps.setVisible(false);es.setVisible(false);vd.setVisible(false);vl.setVisible(false);edit_leave.setVisible(false);
			
		}
		else if(e.getSource()==edit_leave && a1==1 && (m1==1 || s1==1)){
			ps.setVisible(false);es.setVisible(false);vd.setVisible(false);vl.setVisible(false);edit_leave.setVisible(false);
			edit_leave1=1;
			lt.setVisible(true);nd1.setVisible(true);nd.setVisible(true);b.setVisible(true);bk.setVisible(true);
		}
		else if(e.getSource()==ps && (s1==1||m1==1)){
			ps.setVisible(false);vd.setVisible(false);ed.setVisible(false);la.setVisible(false);vl.setVisible(false);
			ps1=1;
			if(s1==1){
				bp1.setVisible(true);da.setVisible(true);gs.setVisible(true);ns.setVisible(true);
				sl[temp].calc();
				sl[temp].view_sal(this);
			}
			else {
				//bp1, da, hra, gs, pf, scf, ns
				bp1.setVisible(true);da.setVisible(true);hra.setVisible(true);gs.setVisible(true);pf.setVisible(true);scf.setVisible(true);ns.setVisible(true);
				mg[temp].calc();
				mg[temp].view_sal(this);
			}
			bk.setVisible(true);
		}
		else if(e.getSource()==vd && (s1==1 || m1==1 || a1==1)){
			vd1=1;
			ps.setVisible(false);vd.setVisible(false);ed.setVisible(false);la.setVisible(false);vl.setVisible(false);
			//Chage bounds
			n1.setBounds(10, 50, 300, 30);mid1.setBounds(10, 100, 300, 30);dept1.setBounds(10, 150, 300, 30);
			add.setEditable(false);
			pnl1.setBounds(10, 300, 300, 30);pnl2.setBounds(10, 350, 300, 30);eid1.setBounds(10, 450, 300, 30);
			if(a1==1){
				vemp.setVisible(false);vdeptli.setVisible(false);vsalli.setVisible(false);vallli.setVisible(false);
				a[temp].view_details(this);
			}
			else if(s1==1){
				sl[temp].details(this);
			}
			else {
				mg[temp].details(this);
			}
			bk.setVisible(true);
		}
		else if(e.getSource()==ed && (s1==1 || m1==1|| a1==1)){
			ed1=1;
			ps.setVisible(false);vd.setVisible(false);ed.setVisible(false);la.setVisible(false);vl.setVisible(false);

			n1.setVisible(true);n.setVisible(true); mid.setVisible(true);mid1.setVisible(true); add.setVisible(true);add1.setVisible(true); pnl1.setVisible(true);pn1.setVisible(true); pnl2.setVisible(true);pn2.setVisible(true); eid1.setVisible(true);eid.setVisible(true); dept1.setVisible(true);dept.setVisible(true);
			bk.setVisible(true);b.setVisible(true);
			if(a1==1){
				dept.setVisible(false);dept1.setVisible(false);eid.setVisible(false);eid1.setVisible(false);vemp.setVisible(false);vdeptli.setVisible(false);vsalli.setVisible(false);vallli.setVisible(false);
			}
			//Call the funcion in Submit logic
		}
		else if(e.getSource()==la && (s1==1 || m1==1)){
			la1=1;
			ps.setVisible(false);vd.setVisible(false);ed.setVisible(false);la.setVisible(false);vl.setVisible(false);
			lt.setVisible(true);nd1.setVisible(true);nd.setVisible(true);b.setVisible(true);bk.setVisible(true);
			//Call the funcion in Submit logic
		}
		else if(e.getSource()==vl && (s1==1 || m1==1)){
			vl1=1;
			ps.setVisible(false);vd.setVisible(false);ed.setVisible(false);la.setVisible(false);vl.setVisible(false);
			note.setBounds(10,100,600,30);
			if(s1==1){
				sl[temp].view_leave(this);
			}
			else {
				mg[temp].view_leave(this);
			}
			bk.setVisible(true);
		}
		else if(e.getSource()==ep && (s1==1 || m1==1 || a1==1)){
			ep1=1;
			ps.setVisible(false);vd.setVisible(false);ed.setVisible(false);la.setVisible(false);vl.setVisible(false);
			vemp.setVisible(false);vdeptli.setVisible(false);vsalli.setVisible(false);vallli.setVisible(false);
			pw1.setBounds(10, 100, 100, 30);pw.setBounds(120, 100, 100, 30);
			pw1.setVisible(true);pw.setVisible(true);
			bk.setVisible(true);b.setVisible(true);
			//Call the funcion in Submit logic
		}
		else if(e.getSource()==cp && (s1==1 || m1==1 || a1==1)){
			cp1=1;
			ps.setVisible(false);vd.setVisible(false);ed.setVisible(false);la.setVisible(false);vl.setVisible(false);
			if(a1==1){
				vemp.setVisible(false);vdeptli.setVisible(false);vsalli.setVisible(false);vallli.setVisible(false);
				a[temp].view_pass(this);
			}
			else if(s1==1){
				sl[temp].view_pass(this);
			}
			else {
				mg[temp].view_pass(this);
			}
			bk.setVisible(true);
		}
		else if(e.getSource()==lo && (s1==1 || m1==1 || a1==1)){
			ps.setVisible(false);vd.setVisible(false);ed.setVisible(false);la.setVisible(false);vl.setVisible(false);mb.setVisible(false);note.setVisible(false);
			bp1.setVisible(false);da.setVisible(false);hra.setVisible(false);gs.setVisible(false);pf.setVisible(false);scf.setVisible(false);ns.setVisible(false);
			lt.setVisible(false);note.setVisible(false);nd1.setVisible(false);nd.setVisible(false);
			n1.setVisible(false);n.setVisible(false);mid1.setVisible(false);mid.setVisible(false);add1.setVisible(false);add.setVisible(false);pnl1.setVisible(false);pn1.setVisible(false);
			pnl2.setVisible(false);pn2.setVisible(false);eid1.setVisible(false);eid.setVisible(false);dept1.setVisible(false);dept.setVisible(false);pw1.setVisible(false);pw.setVisible(false);
			
			n1.setText("Name:");mid1.setText("Mail-ID:");add1.setText("Address:");pnl1.setText("Contact No.1");pnl2.setText("Contact No.2");eid1.setText("ID:");dept1.setText("Department:");pw1.setText("Password:");

			add.setEditable(true);
			n1.setBounds(10, 50, 100, 30);mid1.setBounds(10, 100, 100, 30);dept1.setBounds(10, 150, 110, 30);pnl1.setBounds(10, 300, 100, 30);pnl2.setBounds(10, 350, 100, 30);eid1.setBounds(10, 450, 100, 30);note.setBounds(10,600,600,30);pw1.setBounds(10, 400, 100, 30);
				
			b.setVisible(false);bk.setVisible(false);
			s.setVisible(true);m.setVisible(true);ad.setVisible(true);
			nd.setText("");bp.setText("");
			if(a1==1){
			ed.setBounds(180, 100, 150, 40);vl.setBounds(180, 150, 150, 40);vd.setBounds(180, 250, 150, 40);
			mb.setVisible(false);ed.setVisible(false);vemp.setVisible(false);vdeptli.setVisible(false);vsalli.setVisible(false);vallli.setVisible(false);vd.setVisible(false);
			a1=0;
			}
			else{
				ps1=0;vd1=0;ed1=0;la1=0;vl1=0;cp1=0;ep1=0;
				if(s1==1){	
					s1=0;
				}
				else {
					m1=0;
				}
			}
		}
		else if(e.getSource()==vemp && a1==1){
			vemp1=1;
			vemp.setVisible(false);vdeptli.setVisible(false);vsalli.setVisible(false);vallli.setVisible(false);ed.setVisible(false);vd.setVisible(false);
			s.setVisible(true);m.setVisible(true);bk.setVisible(true);
		}
		else if(e.getSource()==vdeptli && a1==1){
			vdeptli1=1;
			vemp.setVisible(false);vdeptli.setVisible(false);vsalli.setVisible(false);vallli.setVisible(false);ed.setVisible(false);vd.setVisible(false);
			dept1.setVisible(true);dept.setVisible(true);b.setVisible(true);
		}
		else if(e.getSource()==vallli && a1==1){
			vallli1=1;
			vemp.setVisible(false);vdeptli.setVisible(false);vsalli.setVisible(false);vallli.setVisible(false);ed.setVisible(false);vd.setVisible(false);
			a[temp].view_emp_list(this);
			list.setVisible(true);bk.setVisible(true);
		}
		else if(e.getSource()==vsalli && a1==1){
			vsalli1=1;
			bp1.setText("Basic Pay");
			vemp.setVisible(false);vdeptli.setVisible(false);vsalli.setVisible(false);vallli.setVisible(false);ed.setVisible(false);vd.setVisible(false);
			bp1.setVisible(true);bp.setVisible(true);b.setVisible(true);
		}
		else if (e.getSource() == ad) {
			su.setVisible(true);si.setVisible(true);bk.setVisible(true);
			ad.setVisible(false);s.setVisible(false);m.setVisible(false);
			a1 = 1;
		}
		else if (e.getSource() == s && a1==0) {
			s1 = 1;
			ad.setVisible(false);s.setVisible(false);m.setVisible(false);
			su.setVisible(true);si.setVisible(true);bk.setVisible(true);
		}
		else if (e.getSource() == m && a1==0) {
			su.setVisible(true);si.setVisible(true);bk.setVisible(true);
			m1 = 1;
			ad.setVisible(false);s.setVisible(false);m.setVisible(false);
		}
		
		// Back Button Logic Start

		else if(e.getSource()==bk && a1==1 && (m1==1 || s1==1) && (vd1==1 || vl1==1 || ps1==1 || es1==1 || edit_leave1==1)){
			vd.setVisible(true);vl.setVisible(true);ps.setVisible(true);es.setVisible(true);edit_leave.setVisible(true);
			if(vd1==1){
				n1.setVisible(false);n1.setText("Name:");mid1.setVisible(false);mid1.setText("Mail-ID:");add1.setVisible(false);add1.setText("Address:");add.setVisible(false);pnl1.setVisible(false);pnl1.setText("Contact No.1");pnl2.setVisible(false);pnl2.setText("Contact No.2");eid1.setVisible(false);eid1.setText("ID:");dept1.setVisible(false);dept1.setText("Department:");
				// Reset Bounds
				n1.setBounds(10, 50, 100, 30);mid1.setBounds(10, 100, 100, 30);dept1.setBounds(10, 150, 110, 30);
				pnl1.setBounds(10, 300, 100, 30);pnl2.setBounds(10, 350, 100, 30);eid1.setBounds(10, 450, 100, 30);
				vd1=0;
			}
			else if(vl1==1){
				note.setBounds(10,600,600,30);note.setVisible(false);
				vl1=0;
			}
			else if(ps1==1){
				ps1=0;
				bp1.setVisible(false);da.setVisible(false);hra.setVisible(false);gs.setVisible(false);pf.setVisible(false);scf.setVisible(false);ns.setVisible(false);
			}
			else if(es1==1){
				if(b1==1) b.setVisible(false);
				bp.setVisible(false);bp1.setVisible(false);
				es1=0;
			}
			else if(edit_leave1==1){
				edit_leave1=0;
				note.setVisible(false);lt.setVisible(false);nd1.setVisible(false);nd.setVisible(false);
			}
		}
		else if(e.getSource()==bk && a1==1 && vemp1==1){
			if(m1==1 || s1==1){
				eid.setVisible(false);eid1.setVisible(false);eid1.setText("Emp ID :");b.setVisible(false);s.setVisible(true);m.setVisible(true);
				ps.setVisible(false);es.setVisible(false);edit_leave.setVisible(false);vl.setVisible(false);vd.setVisible(false);
				if(m1==1) m1=0;
				else s1=0;
			}
			else{
				vemp1=0;
				ed.setBounds(160, 300, 190, 40);vd.setBounds(160, 350, 190, 40);
				m.setVisible(false);s.setVisible(false);bk.setVisible(false);vemp.setVisible(true);vdeptli.setVisible(true);vsalli.setVisible(true);vallli.setVisible(true);ed.setVisible(true);vd.setVisible(true);
			}
		}
		else if(e.getSource()==bk && (a1 == 1 || s1 == 1 || m1 == 1) && si1 == 1 &&(ps1==1 || vd1==1 || ed1==1 || la1==1 || vl1==1 || cp1==1 || ep1==1 || vdeptli1==1 || vsalli1==1 || vallli1==1)) {
			if(ps1==1){
				bp1.setVisible(false);da.setVisible(false);hra.setVisible(false);gs.setVisible(false);pf.setVisible(false);scf.setVisible(false);ns.setVisible(false);
				ps1=0;
			}
			else if(vd1==1){
				n1.setVisible(false);n1.setText("Name:");mid1.setVisible(false);mid1.setText("Mail-ID:");add1.setVisible(false);add1.setText("Address:");add.setVisible(false);pnl1.setVisible(false);pnl1.setText("Contact No.1");pnl2.setVisible(false);pnl2.setText("Contact No.2");eid1.setVisible(false);eid1.setText("ID:");dept1.setVisible(false);dept1.setText("Department:");
				// Reset Bounds
				n1.setBounds(10, 50, 100, 30);mid1.setBounds(10, 100, 100, 30);dept1.setBounds(10, 150, 110, 30);
				pnl1.setBounds(10, 300, 100, 30);pnl2.setBounds(10, 350, 100, 30);eid1.setBounds(10, 450, 100, 30);
				vd1=0;
			}
			else if(ed1==1){
				n1.setVisible(false);n.setVisible(false);mid1.setVisible(false);mid.setVisible(false);add1.setVisible(false);add.setVisible(false);pnl1.setVisible(false);pn1.setVisible(false);pnl2.setVisible(false);pn2.setVisible(false);eid1.setVisible(false);eid.setVisible(false);dept1.setVisible(false);dept.setVisible(false);
				add.setEditable(true);
				ed1=0;
			}
			else if(la1==1){
				lt.setVisible(false);note.setVisible(false);nd1.setVisible(false);nd.setVisible(false);
				la1=0;
			}
			else if(vl1==1){
				note.setBounds(10,600,600,30);note.setVisible(false);
				vl1=0;
			}
			else if(cp1==1){
				pw1.setVisible(false);pw1.setText("Password:");pw1.setBounds(10, 400, 100, 30);
				cp1=0;
			}
			else if(ep1==1){
				pw1.setVisible(false);pw.setVisible(false);
				ep1=0;
			}
			else if(vallli1==1){
				list.setVisible(false);
				vallli1=0;
			}
			else if(vdeptli1==1){
				list.setVisible(false);
				vdeptli1=0;
			}
			else if(vsalli1==1){
				list.setVisible(false);
				b.setVisible(false);bp1.setVisible(false);bp.setVisible(false);
				vsalli1=0;
			}
			b.setVisible(false);bk.setVisible(false);
			// Visible
			if(a1==1){
				// Menu display Logic 
				ed.setBounds(160, 300, 190, 40);vd.setBounds(160, 350, 190, 40);
				ed.setVisible(true);vemp.setVisible(true);vdeptli.setVisible(true);vsalli.setVisible(true);vallli.setVisible(true);vd.setVisible(true);
			}
			else{
				ps.setVisible(true);vd.setVisible(true);ed.setVisible(true);la.setVisible(true);vl.setVisible(true);cp.setVisible(true);ep.setVisible(true);
			}
		}

		else if (e.getSource() == bk && (a1 == 1 || s1 == 1 || m1 == 1) && su1 == 1) {
			su1 = 0;
			// invisible
			n1.setVisible(false);n.setVisible(false);mid1.setVisible(false);mid.setVisible(false);dept1.setVisible(false);dept.setVisible(false);add1.setVisible(false);add.setVisible(false);pnl1.setVisible(false);pn1.setVisible(false);eid1.setVisible(false);eid.setVisible(false);pnl2.setVisible(false);pn2.setVisible(false);pw1.setVisible(false);pw.setVisible(false);b.setVisible(false);note.setVisible(false);
			// visible
			su.setVisible(true);si.setVisible(true);bk.setVisible(true);
			b.setBounds(190, 500, 95, 30);
		}
		else if (e.getSource() == bk && (a1 == 1 || s1 == 1 || m1 == 1) && si1 == 1) {
			n1.setVisible(false);n.setVisible(false);pw1.setVisible(false);pw.setVisible(false);b.setVisible(false);note.setVisible(false);
			pw1.setBounds(10, 400, 100, 30);pw.setBounds(120, 400, 280, 30);b.setBounds(190, 500, 95, 30);
			si1 = 0;
			bk.setVisible(true);su.setVisible(true);si.setVisible(true);
		}
		else if (e.getSource() == bk && (a1 == 1 || s1 == 1 || m1 == 1)) {
			su.setVisible(false);si.setVisible(false);bk.setVisible(false);
			ad.setVisible(true);s.setVisible(true);m.setVisible(true);
			if (a1 == 1)
				a1 = 0;
			else if (s1 == 1)
				s1 = 0;
			else if (m1 == 1)
				m1 = 0;
		}		

		// Back Button Logic End

		// Submit Button Logic Start

		else if(e.getSource()==b && a1==1 && (m1==1 || s1==1) && edit_leave1==1){
			b1=1;b.setVisible(false);
			a[temp].leave(this);
		}
		else if(e.getSource()==b && a1==1 && (m1==1 || s1==1) && es1==1){
			b1=1;
			a[temp].edit_sal(this);bk.setVisible(true);
		}
		else if(e.getSource()==b && a1==1 && m1==1){
			id=Double.parseDouble(eid.getText());
			try{
				for(t1=0;t1<=i;t1++) {
					if(mg[t1].id==id) {
						eid1.setVisible(false);eid.setVisible(false);b.setVisible(false);
						es.setVisible(true);ps.setVisible(true);vd.setVisible(true);vl.setVisible(true);edit_leave.setVisible(true);bk.setVisible(true);	//View Management Employee through Admin
						break;
					}
				}
			}
			catch(NullPointerException ne){}
			catch(ArrayIndexOutOfBoundsException ae){
				note.setText("No User found!!");
			}
		}
		else if(e.getSource()==b && a1==1 && s1==1){
			id=Double.parseDouble(eid.getText());
			try{
				for(t1=0;t1<=j;t1++) {
					if(sl[t1].id==id) {
						eid1.setVisible(false);eid.setVisible(false);b.setVisible(false);
						es.setVisible(true);ps.setVisible(true);vd.setVisible(true);vl.setVisible(true);edit_leave.setVisible(true);bk.setVisible(true);	//View Sales Employee through Admin
						break;
					}
				}
			}
			catch(NullPointerException ne){}
			catch(ArrayIndexOutOfBoundsException ae){
				note.setText("No User found!!");
			}
		}
		else if(e.getSource()==b && (s1==1 || m1==1 || a1==1) && (ed1==1 || la1==1 || ep1==1 || vdeptli1==1 || vsalli1==1)){
			if(ed1==1){
				System.out.println("Edit details code in execution");
				no1=Double.parseDouble(pn1.getText());
				no2=Double.parseDouble(pn2.getText());

				bk.setVisible(true);
				if(s1==1){
					id=Double.parseDouble(eid.getText());
					sl[temp].edit_details(this);
				}
				else if(m1==1){
					id=Double.parseDouble(eid.getText());
					mg[temp].edit_details(this);
					System.out.println("Condition accepted!!");
				}
				else{
					a[temp].edit_details(this);
				}
			}
			else if(la1==1){
				
				if(s1==1){
					sl[temp].leave(this);
				}
				else{
					mg[temp].leave(this);
				}
				System.out.println("This code is Executed");
				bk.setVisible(true);
			}
			else if(ep1==1){
				pass=pw.getText();
				pw1.setVisible(false);pw.setVisible(false);b.setVisible(false);
				bk.setVisible(true);
				if(s1==1){
					sl[temp].edit_pass(this);
				}
				else if(m1==1){
					mg[temp].edit_pass(this);
				}
				else{
					a[temp].edit_pass(this);
				}
			}
			else if(vdeptli1==1){
				b.setVisible(false);dept1.setVisible(false);dept.setVisible(false);
				a[temp].dept_list(this);
				bk.setVisible(true);
			}
			else if(vsalli1==1){
				b.setVisible(false);bp1.setVisible(false);bp.setVisible(false);
				a[temp].view_sal_list(this);
				bk.setVisible(true);
			}
		}
		else if (e.getSource() == b && (a1 == 1 || s1 == 1 || m1 == 1) && su1 == 1) {
			no1 = Double.parseDouble(pn1.getText());
			no2 = Double.parseDouble(pn2.getText());
			if (s1 == 1) {
				id = Double.parseDouble(eid.getText());
				System.out.println("Creating a new Sales Account ..."); // Create a new Object of Sales Class
				if (j == 100) {
					note.setText("Note : No more Sales Account can be created!!");
					note.setVisible(true);
				} else {
					sl[j] = new Sales(this);
					j++;
					n.setText("");
					pw.setText("");
					note.setText("New Sales Account Created Successfully!!");
					note.setVisible(true);
				}
			}
			else if (m1 == 1) {
				id = Double.parseDouble(eid.getText());
				System.out.println("Creating a new Management Account ..."); // Create a new Object of Management Class
				if (i == 100) {
					note.setText("Note : No more Management Account can be Created!!");
					note.setVisible(true);
				} else {
					mg[i] = new Management(this);
					i++;
					note.setText("New Management Account created Successfully!!");
					note.setVisible(true);
				}
			}
			else {
				System.out.println("Creating a new Admin Account ..."); // Create an Object of Admin Class
				if (k == 2){
					note.setText("Note : No more Admin Accounts can be created!!");
					note.setVisible(true);
				}
				else {
					a[k] = new Admin(this);
					k++;
					note.setText("New Admin Account Created Successfully!!");
					note.setVisible(true);
				}

			}
			bk.setVisible(true);
			n.setText("");mid.setText("");dept.setText("");add.setText("");eid.setText("");pn1.setText("");pn2.setText("");pw.setText("");
		} 

		else if (e.getSource() == b && (a1 == 1 || s1 == 1 || m1 == 1) && si1 == 1) {
			b1=1;
			String name;
			name = n.getText();
			pass = pw.getText();
			pw1.setBounds(10, 400, 100, 30);
			pw.setBounds(120, 400, 280, 30);
			b.setBounds(190, 500, 95, 30);
			n1.setVisible(false);n.setVisible(false);pw1.setVisible(false);pw.setVisible(false);b.setVisible(false);
			if (a1 == 1) {
				System.out.println("Checking for Admin Account ..."); // Check for Admin Account
				 try{
					for(temp=0;temp<=k;temp++){
						if(a[temp].check(name, pass)){
							note.setVisible(false);bk.setVisible(false);
							ed.setBounds(160, 300, 190, 40);vd.setBounds(160, 350, 190, 40);
							mb.setVisible(true);ed.setVisible(true);vemp.setVisible(true);vdeptli.setVisible(true);vsalli.setVisible(true);vallli.setVisible(true);vd.setVisible(true);
							break;
						}
					}
					if(temp>k) {
						note.setText("Invalid Password!!");
						note.setVisible(true);
					}
				}
				catch(NullPointerException ne){
					note.setText("Invalid credentials!!");
					note.setVisible(true);
					System.out.println("No User Found!!");
				}
			} else if (s1 == 1) {
				System.out.println("Checking for Sales Account ..."); // Check for Sales Account
				try{
					for(temp=0;temp<=j;temp++){
						if(sl[temp].check(name, pass)){
							note.setVisible(false);bk.setVisible(false);
							ps.setVisible(true);vd.setVisible(true);ed.setVisible(true);la.setVisible(true);vl.setVisible(true);mb.setVisible(true);
							break;
						}
					}
					if(temp>j){
						note.setText("Invalid credentials!!");
						note.setVisible(true);
					}
				}
				catch(NullPointerException ne){
					note.setText("Invalid credentials!!");
					note.setVisible(true);
					System.out.println("No User Found!!");
				}
			} else if (m1 == 1) {
				System.out.println("Checking for Management Account ..."); // Check for Management Account
				try{
					for(temp=0;temp<=i;temp++){
						if(mg[temp].check(name, pass)){
							note.setVisible(false);bk.setVisible(false);
							ps.setVisible(true);vd.setVisible(true);ed.setVisible(true);la.setVisible(true);vl.setVisible(true);mb.setVisible(true);
							break;
						}
					}
					if(temp>i){
						note.setText("Invalid credentials!!");
						note.setVisible(true);
					}
				}
				catch(NullPointerException ne){
					note.setText("Invalid credentials!!");
					note.setVisible(true);
					System.out.println("No User Found!!");
				}
			}
			n.setText("");pw.setText("");
		}
		
		// Submit Button Logic End

		else
			System.out.println("Not working");
	}
	
	public static void main(String[] args) {
		Test2 t=new Test2();
	}
}
