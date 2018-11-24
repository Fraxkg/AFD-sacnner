
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


class MainPanel extends JPanel{
	Image fondo=new ImageIcon("img/fondoDefault.jpg").getImage();
public MainPanel(){
	
}
public void paintComponent(Graphics g){
	g.drawImage(fondo,0,0,getWidth(),getHeight(),this);
}
}

public class Ventana extends JFrame {
	boolean fondo=false;
	JPanel app=new MainPanel();
	JLabel resultado=new JLabel();
	String[] cadenaArray;
	String[] elementArray = new String[50];
	String cadena;
	String campo;
	String lex="";
	char car=' ';
	boolean flagExpo=false;
	boolean flagCorreo=false;
	int auxEstado;
	int carMatriz;
	int estado=0;
	int cont=0;
	int tam=0;
	int vueltas=0;
	int elemento=0;
	int total=0;
	int No=1;
	int linea=1;
	int id=100;
	int constante=200;
	/*
	 letra=0
	 digito=1
	 / = 2
	 * 3
	 - 4
	 _ 5
	 . 6
	 @ 7
	 E 8
	 +/- 9
	 cualquiera 10
	 */
	
	int [][] tabla ={{ 1, 7, 3,19,19,19,19,19,19, 8,19},
					 { 1, 1,19,19,19, 2,18,14,19,19,19},
					 { 1, 1,19,19,19,19,19,19,19,19,19},
					 {19,19,19, 4,19,19,19,19,19,19,19},
					 { 4, 4, 4, 5, 4, 4, 4, 4, 4, 4, 4},
					 {19,19, 6,19,19,19,19,19,19,19,19},
					 {19,19,19,19,19,19,19,19,19,19,19},
					 {19, 7,19,19,19,19, 9,19,11,19,19},
					 {19, 7,19,19,19,19,19,19,19,19,19},
					 {19,10,19,19,19,19,19,19,19,19,19},
					 {19,10,19,19,19,19,19,19,11,19,19},
					 {19,12,19,19,19,19,19,19,19,13,19},
					 {19,12,19,19,19,19,19,19,19,19,19},
					 {19,12,19,19,19,19,19,19,19,19,19},
					 {15,19,19,19,19,19,19,19,19,19,19},
					 {15,19,19,19,19,19,16,19,19,19,19},
					 {17,19,19,19,19,19,19,19,19,19,19},
					 {17,19,19,19,19,19,19,19,19,19,19},
					 { 1, 1,19,19,19,19,19,19,19,19,19},
					 {19,19,19,19,19,19,19,19,19,19,19}};
	public Ventana() {

	////Visual
		setVisible(true);
		setTitle("Escaner Francisco Sánchez y Omar Verdugo");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocation(0,0);
		setSize(1200,600);
		setLocation(30,30);
		setResizable(false);
		setLayout(null);
		
		add(app);
		
		app.setLayout(null);
		app.setBackground(Color.GRAY);
		app.setBounds(0, 0, 1200, 600);
		repaint();
		
		JLabel instruccion= new JLabel("Inserta la cadena a validar");
		instruccion.setBounds(450, 5, 300, 131);
		font(instruccion,"Arial",20);
		instruccion.setForeground(Color.white);
		app.add(instruccion);
		
		resultado.setBounds(420, 380, 400, 200);
		resultado.setForeground(Color.WHITE);
		font(resultado,"Arial",20);
		
		JTextArea campoCadena=new JTextArea();
		campoCadena.setBounds(420, 100, 300, 200);
		app.add(campoCadena);
		app.add(resultado);
		
		JButton enviar=new JButton("Identificar");
		enviar.setBounds(620, 350, 100, 40);
		enviar.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				//resetear cadena
				resultado.setText(" ");
				//mostar cadena
				cadena = campoCadena.getText()+' ';
				cadenaArray = cadena.split("");
				
				for(int i =0;i<cadenaArray.length;i++) {
					System.out.println(cadenaArray[i]);
				}
				
				
				//
				if(cadena.isEmpty()) {
					resultado.setText("Campo Vacío");
				}else if(cadenaArray.length>50) {
					resultado.setText("Longitud de cadena superior a 50");
				}else {
					Scanner();
					Validar();
					app.repaint();
				}
				
			}
			
		});
		app.add(enviar);
		
		JButton limpiar=new JButton("Limpiar");
		limpiar.setBounds(420, 350, 100, 40);
		app.add(limpiar);
		limpiar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				campoCadena.setText("");
				id=100;
				constante=200;
				No=1;
			}
			
		});
		app.repaint();
		
	}
	public void fontText(JTextField text,String tipo,int size){
		
		Font font=new Font(tipo,Font.BOLD,size);
		text.setFont(font);
	
	}
	
	public void font(JLabel label,String tipo,int size){
		
		Font font=new Font(tipo,Font.BOLD,size);
		label.setFont(font);
	
	}
	
	///visual fin
/*Modo águila
		JLabel background;
		ImageIcon img = new ImageIcon("img/americanPower.jpg");
		background= new JLabel("",img,JLabel.CENTER);
		background.setBounds(0,0,1200,600);
		
		JButton modoAguila=new JButton("Modo: Águila");
		modoAguila.setBounds(1000, 500, 150, 50);
		modoAguila.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
			
				if(fondo==false) {
					app.add(background);
					app.repaint();
					fondo=true;
				}else {
					app.remove(background);
					app.repaint();
					fondo=false;
				}
				
			}
			
		});
		app.add(modoAguila);
fin modo aguila*/
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Ventana v= new Ventana();
		}

	
	
public void Scanner() {
	
	tam=cadenaArray.length;
	char aux=' ';
	do {
		aux=car;
		car=cadenaArray[cont].charAt(0);
		
		if(car==' ') {
			
			if(aux==' ') {
				lex="";
			}else if(aux!=' ' || cont<tam){
				elementArray[elemento] = lex;
				elemento++;
				lex="";
			}
		}else {
			lex=lex+car;
		}
		
		cont++;

	}while(cont<tam);
	cont=0;
	elemento=0;
	
}

public void Validar() {
	
	/*
	 
Tokens Valor inicial
Delimitadores 50
Operadores 70

Identificadores (i) 100

Constantes (c) 200
Reglas 300

( 50 + 70
) 51 - 71
; 52 * 72
	 / 73 */
	
	
	
	
	do {

		
		if(elementArray[vueltas].equals("+")) {
			
			String Numero=String.valueOf(No);
			String Linea=String.valueOf(linea);
			Salida(Numero,Linea,"+","7","70");
			
		}else if(elementArray[vueltas].equals("-")) {
			String Numero=String.valueOf(No);
			String Linea=String.valueOf(linea);
			Salida(Numero,Linea,"-","7","71");
			
		}else if(elementArray[vueltas].equals("*")) {
			String Numero=String.valueOf(No);
			String Linea=String.valueOf(linea);
			Salida(Numero,Linea,"*","7","72");
			
		}else if(elementArray[vueltas].equals("/")) {
			String Numero=String.valueOf(No);
			String Linea=String.valueOf(linea);
			Salida(Numero,Linea,"/","","73");
			
		}else if(elementArray[vueltas].equals("(")) {
			String Numero=String.valueOf(No);
			String Linea=String.valueOf(linea);
			Salida(Numero,Linea,"(","5","50");
			
		}else if(elementArray[vueltas].equals(")")) {
			String Numero=String.valueOf(No);
			String Linea=String.valueOf(linea);
			Salida(Numero,Linea,")","5","51");
			
		}else if(elementArray[vueltas].equals(";")) {
			String Numero=String.valueOf(No);
			String Linea=String.valueOf(linea);
			Salida(Numero,Linea,";","5","52");
			linea++;
		}else {
			cadena = elementArray[vueltas];
			cadenaArray = cadena.split("");
			calcular();
		}
		
		No++;
		vueltas++;
		
		
	}while(elementArray[vueltas]!=null);
	vueltas=0;
	int No=1;
	int linea=1;
	
}
//calcular cadena
	public void calcular() {
		
		tam=cadenaArray.length;
		do {
			auxEstado=estado;
			cadenaArray[cont]=cadenaArray[cont].toLowerCase();
			car=cadenaArray[cont].charAt(0);
			/*
			 letra=0
			 digito=1
			 / = 2
			 * 3
			 - 4
			 _ 5
			 . 6
			 @ 7
			 E 8
			 +/- 9
			 cualquiera 10
			 */
			if(flagExpo==false&&(car=='a' ||car=='b'||car=='c'||car=='d'||car=='e'||car=='f'||car=='g'||car=='h'||car=='i'||car=='j'||car=='k'||car=='m'||car=='n'||car=='l'||car=='o'||car=='p'||car=='q'||car=='r'||car=='s'||car=='u'||car=='t'||car=='w'||car=='x'||car=='y'||car=='z')) {
				
			carMatriz=0;
			
			}else if(car=='1' || car=='2' || car=='3' || car=='4' || car=='5' || car=='6' || car=='7' || car=='8' || car=='9' || car=='0') {
				carMatriz=1;
			}else if(car=='/') {
				carMatriz=2;
			}else if(car=='*') {
				carMatriz=3;
			}else if(car=='_') {
				carMatriz=5;
			}else if(car=='.') {
				flagCorreo=true;
				carMatriz=6;
			}else if(car=='@') {
				carMatriz=7;
			}else if(car=='e') {
				carMatriz=8;
			}else if(car=='+'||car=='-') {
				carMatriz=9;
			}else{
				carMatriz=10;
			}
			if(estado==7) {
				flagExpo=true;
			}
			estado= tabla[auxEstado][carMatriz];
			
			cont++;
		}while(cont<tam);
		
		if (estado==1 && flagCorreo==false) {
			String Numero=String.valueOf(No);
			String Linea=String.valueOf(linea);
			String identificador=String.valueOf(id+1);
			id=id+1;
			SalidaId(elementArray[vueltas],identificador,Linea);
			Salida(Numero,Linea,elementArray[vueltas],"1",identificador);
		}else if(estado==7){
		
			String Numero=String.valueOf(No);
			String Linea=String.valueOf(linea);
			String consta=String.valueOf(constante+1);
			constante=constante+1;
			SalidaConst(elementArray[vueltas],consta,Linea);
			Salida(Numero,Linea,elementArray[vueltas],"2",consta);
		}else if(estado==10){
		
			String Numero=String.valueOf(No);
			String Linea=String.valueOf(linea);
			String consta=String.valueOf(constante+1);
			constante=constante+1;
			SalidaConst(elementArray[vueltas],consta,Linea);
			Salida(Numero,Linea,elementArray[vueltas],"2",consta);
		}else if(estado==12 ){
		
			String Numero=String.valueOf(No);
			String Linea=String.valueOf(linea);
			String consta=String.valueOf(constante+1);
			constante=constante+1;
			SalidaConst(elementArray[vueltas],consta,Linea);
			Salida(Numero,Linea,elementArray[vueltas],"2",consta);
		}else if(estado==19){
			String Numero=String.valueOf(No);
			String Linea=String.valueOf(linea);
			String consta=String.valueOf(constante+1);
			String novalido=elementArray[vueltas];
			resultado.setText("Error en la linea: "+Linea+" "+novalido+" no es válida");
		}else {
			String Numero=String.valueOf(No);
			String Linea=String.valueOf(linea);
			String consta=String.valueOf(constante+1);
			String novalido=elementArray[vueltas];
			resultado.setText("Error en la linea: "+Linea+" "+novalido+" no es válida");
		}
		flagExpo=false;
		flagCorreo=false;
		estado=0;
		tam=0;
		cont=0;
		car=' ';
		carMatriz=0;
	}
	
	public void SalidaId(String token, String valor, String linea) {
		tablaSalidaId arrayObjetosid[]=new tablaSalidaId[4];
		 
        //Creamos objetos en cada posicion
		arrayObjetosid[0]=new tablaSalidaId(token,valor,linea);
        
   
        System.out.println("Identificador "+arrayObjetosid[0].getToken()+" "+"Valor: "+ arrayObjetosid[0].getValor()+" "+"linea: "+arrayObjetosid[0].getLinea());
        
	
	}
	public class tablaSalidaId{
		private String token;
		private String valor;
	    private String linea;
	    public String getToken() {
			return token;
		}
		public void setToken(String token) {
			this.token = token;
		}
		public String getValor() {
			return valor;
		}
		public void setValor(String valor) {
			this.valor = valor;
		}
		public String getLinea() {
			return linea;
		}
		public void setLinea(String linea) {
			this.linea = linea;
		}
		
		public tablaSalidaId(String token, String valor, String linea) {
			this.token = token;
	        this.valor = valor;
	        this.linea = linea;
	      
	       
		} 
	    
	}
	public void SalidaConst(String token, String valor, String linea){
		tablaSalidaConst arrayObjetosConst[]=new tablaSalidaConst[4];
		 
        //Creamos objetos en cada posicion
		arrayObjetosConst[0]=new tablaSalidaConst(token,valor,linea);
        
   
        System.out.println("Constante "+arrayObjetosConst[0].getToken()+" "+"Valor: "+ arrayObjetosConst[0].getValor()+" "+"linea: "+arrayObjetosConst[0].getLinea());
        
	}
	public class tablaSalidaConst{
		private String token;
		private String valor;
	    private String linea;
	    public String getToken() {
			return token;
		}
		public void setToken(String token) {
			this.token = token;
		}
		public String getValor() {
			return valor;
		}
		public void setValor(String valor) {
			this.valor = valor;
		}
		public String getLinea() {
			return linea;
		}
		public void setLinea(String linea) {
			this.linea = linea;
		}
		
	    
	    public tablaSalidaConst(String token, String valor, String linea) {
			this.token = token;
	        this.valor = valor;
	        this.linea = linea;
	      
	       
		} 
	    
	}
	public void Salida(String No, String linea, String token, String tipo, String codigo){
		//Creamos un array de objetos de la clase empleados
        Tabla arrayObjetos[]=new Tabla[3];
 
        //Creamos objetos en cada posicion
        arrayObjetos[0]=new Tabla(No, linea, token, tipo,codigo);
        
   
        System.out.println("No. "+arrayObjetos[0].getNo()+" "+"Linea: "+arrayObjetos[0].getLinea()+" "+"Token: "+ arrayObjetos[0].getToken()+" "+"Tipo: "+arrayObjetos[0].getTipo()+" "+"Codigo: "+arrayObjetos[0].getCodigo());
        
	}
	
	public class Tabla {

		private String no;
	    private String linea;
	    private String token;
	    private String tipo;
	    private String codigo;
	    
		public String getNo() {
			return no;
		}

		public void setNo(String no) {
			this.no = no;
		}

		public String getLinea() {
			return linea;
		}

		public void setLinea(String linea) {
			this.linea = linea;
		}

		public String getToken() {
			return token;
		}

		public void setToken(String token) {
			this.token = token;
		}

		public String getTipo() {
			return tipo;
		}

		public void setTipo(String tipo) {
			this.tipo = tipo;
		}

		public String getCodigo() {
			return codigo;
		}

		public void setCodigo(String codigo) {
			this.codigo = codigo;
		}

		public Tabla(String no, String linea, String token, String tipo, String codigo) {
			this.no = no;
	        this.linea = linea;
	        this.token = token;
	        this.tipo = tipo;
	        this.codigo = codigo;
	       
		} 
		  
		}
	

}
