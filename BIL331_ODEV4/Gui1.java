
import java.awt.*;
import java.awt.event.*;

class MyFrame extends Frame implements TextListener, ActionListener{
	
	Label l1;
	Label l2;
	TextField tf;
	
	public MyFrame() {

		super("TextField Demo" );
		
		l1=new Label("No Text is Entered Yet");
		l2=new Label("Enter key is not yet hit");
		tf=new TextField(20);
		tf.setEchoChar('*');//Text field i�ine yaz�lan metni * karakteri ile saklar
		
		tf.addTextListener(this);
		tf.addActionListener(this);
		
		setLayout(new FlowLayout());
		add(l1);
		add(tf);
		add(l2);
		
		//tf.getText();
		//tf.setText();
		//tf.setEchoChar Baz� �nemli metotlarr
		//TextListener interfaceinin en �nemli ve tek metodu: textValueChanged(TextEvent e);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		l2.setText(tf.getText()); //Entere bast���m�zda textin i�indekileri l2 labeline yazar
	}

	@Override
	public void textValueChanged(TextEvent e) {
		
		l1.setText(tf.getText());//tf i�ine girilen �eyler l1 labeline de yaz�l�r
	}


	
}

public class Gui1 {



	public static void main(String args[]) {
		
		MyFrame f=new MyFrame();
		f.setSize(400,400);
		f.setVisible(true);
		
	}
	
}
