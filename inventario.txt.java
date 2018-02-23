import java.io.*;
import java.util.*;
class Productos implements Serializable{
	private static final long serialVersionUID = 1L;
	protected String ID;
	protected String nombreProducto;
	protected String unidades;
	protected int cantidad;
	protected String descripcion;
	protected int precioCompra;
	protected int precioVenta;
	public Productos() {}
	Productos(String ID, String nombreProducto, String unidades,int cantidad, String descripcion, int precioCompra, int precioVenta) {
	this.ID=ID;
    this.nombreProducto=nombreProducto;
    this.unidades=unidades;
    this.descripcion=descripcion;
    this.precioCompra=precioCompra;
    this.precioVenta=precioVenta;
    this.cantidad=cantidad;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		this.ID = iD;
	}
	public String getNombreProducto() {
		return nombreProducto;
	}
	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}
	public String getUnidades() {
		return unidades;
	}
	public void setUnidades(String unidades) {
		this.unidades = unidades;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public int getPrecioCompra() {
		return precioCompra;
	}
	public void setPrecioCompra(int precioCompra) {
		this.precioCompra = precioCompra;
	}
	public int getPrecioVenta() {
		return precioVenta;
	}
	public void setPrecioVenta(int precioVenta) {
		this.precioVenta = precioVenta;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public String toString() {
		return("El producto de nombre "+nombreProducto+"\nCon ID "+ID+"\nCuya unidad  es "+unidades+"\nLa cantidad de productos es "+cantidad+"\nLa Descripción es "+descripcion+"\nSu precio de venta es $"
				+ precioVenta+"\nSu precio de compra es $"+precioCompra);
	}
}
class Venta implements Serializable{
	
	private static final long serialVersionUID = 1L;
	int ganancia;
	int IVA;
	int cantidadV;
	String nombreProductoV;
	String IDV;
	int total;
	Date fecha;
	
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getCantidadV() {
		return cantidadV;
	}
	public void setCantidadV(int cantidadV) {
		this.cantidadV = cantidadV;
	}
	public String getNombreProductoV() {
		return nombreProductoV;
	}
	public void setNombreProductoV(String nombreProductoV) {
		this.nombreProductoV = nombreProductoV;
	}
	public String getIDV() {
		return IDV;
	}
	public void setIDV(String iDV) {
		IDV = iDV;
	}
	public Venta(int ganancia) {
		this.ganancia = ganancia;
	}
	public int getIVA() {
		return IVA;
	}
	public void setIVA(int iVA) {
		IVA = iVA;
	}
	public double getGanancia() {
		return ganancia;
	}
	public void setGanancia(int ganancia) {
		this.ganancia = ganancia;
	}
	public String toString() {
		return "La fecha de compra es "+fecha+"\nSe vendieron "+cantidadV+" de "+nombreProductoV+" con ID "+IDV+" y el total de la ganancia fue $"+ganancia+" mas el IVA $"+IVA+" el precio total es de $"+(ganancia+IVA);
	}
}
public class Inventario {
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner lector= new Scanner(System.in); //Programa que guarda objetos de tipo inventario(cualquier numero)
		//Cada vez que se corre el programa crea un nuevo archivo
		int num=30;
		java.util.Date fechaActual = new java.util.Date();
		Productos[]producto=new Productos[100];
		Venta[]venta=new Venta[100];
		int cont3=1,total=0;
		for(int i=0;i<100;i++){
			File extra=new File("Venta"+cont3);
			if(extra.exists()){
				cont3++;
			}
			else{
				i=100;
			}
		}
		String name1="producto";
		File file=new File(name1);//Esto hace que puedas crear un archivo nuevo cada corrida
		if(!file.exists()){
			try{
			file.createNewFile();//se crea el archivo con la condicion de no existe
			System.out.println(file.getName()+" Se ha creado el archivo");
			}
			catch(IOException ex){
				ex.printStackTrace();
		    }
			for(int i=0;i<num;i++){
				System.out.println("Introduce el ID del producto");
				lector.nextLine();
				String ID = lector.nextLine();
				System.out.println("Introduce nombre del producto");
				String nombreProducto=lector.nextLine();
				System.out.println("Introduce la unidad de medida");
				String unidades=lector.nextLine();
				System.out.println("Introduce numero de productos");
				int cantidad = lector.nextInt();
				System.out.println("Introduce descripcion");
				lector.nextLine();
				String descripcion = lector.nextLine();
				System.out.println("Introduce el precio de compra");
				int precioCompra = lector.nextInt();
				System.out.println("Introduce el precio de la venta");
				int precioVenta = lector.nextInt();
				producto[i]=new Productos(ID,nombreProducto,unidades,cantidad,descripcion,precioCompra,precioVenta);
				if(producto[i].getCantidad()<100) {
					System.out.println("La cantidad de productos es demasiado baja");
				}
				System.out.println();
				try{
					@SuppressWarnings("resource")
					ObjectOutputStream ObjetoArchivo= new ObjectOutputStream(new FileOutputStream(name1));
					ObjetoArchivo.writeObject(producto);				
				}
				catch(Exception mono){
				}
			}
		}
		else {
			try{
				@SuppressWarnings("resource")
				ObjectInputStream ObjetoArchivo1=new ObjectInputStream(new FileInputStream(name1));
				Productos[] PersonalRec=(Productos[])ObjetoArchivo1.readObject();
				int aux=0;	
				while(PersonalRec[aux]!=null){
					producto[aux]=PersonalRec[aux];
					aux++;
					num=aux;
					//Busca el archivo introducido y lo imprime en pantalla
				}
			}
			catch(Exception mono){
			}
		}
		String name2="Venta"+cont3;
		File file1=new File(name2);//Esto hace que puedas crear un archivo nuevo cada corrida
		if(!file1.exists()){
			try{
			file1.createNewFile();//se crea el archivo con la condicion de no existe
			System.out.println(file1.getName()+" Se ha creado el archivo");
			}
			catch(IOException ex){
				ex.printStackTrace();
		    }
		}
		System.out.println("=====================================");
		System.out.println("Desea realizar alguna operacion\n1=si\n2=no");
	int res1=lector.nextInt();
	int cont=0,s=0;
	while(res1==1){
	System.out.println("introduzca accion que desea realizar\n1=Ver inventario\n2=Buscar producto\n3=Ver archivo anterior\n4=Salir\n5=Cambiar producto"
			+ "\n6=Generar una compra\n7=Agregar un elemento\n8=Eliminar producto\n9=Mostrar venta\n10=Comprar producto\n11=Calcular ganancias pasadas");
	int op=lector.nextInt();
		switch(op){
		case 1:
			System.out.println("=====================================");
			try{
				@SuppressWarnings("resource")
				ObjectInputStream ObjetoArchivo1=new ObjectInputStream(new FileInputStream(name1));
				Productos[] PersonalRec=(Productos[])ObjetoArchivo1.readObject();
				for(int i=0;i<num;i++){		
						System.out.println(PersonalRec[i]);
						System.out.println();
						//lee los objetos guardados
			}
			}
				catch(Exception mono){
				
			}
			System.out.println("=====================================");
			break;
		case 2:
			System.out.println("Introduce el ID del producto a buscar");
			lector.nextLine();
			System.out.println("=====================================");
			String ID1=lector.nextLine();
			for(int i=0;i<num;i++){
				if(ID1.equals(producto[i].getID())){
					System.out.println(producto[i].toString());
					//busca un objeto serializado con el ID del producto
				}
			}
			System.out.println("=====================================");
			break;
		case 4:
			res1=2;
			break;
		case 3:
			int con1=1;
			System.out.println("El nombre de los archivos son los siguientes:");
			while(con1<cont3) {
				System.out.println("Venta"+con1);
				con1++;
			}
			System.out.println("introduce el nombre del archivo a buscar");
			lector.nextLine();
			String nombrearch=lector.nextLine();
			System.out.println("=====================================");
			
			try{
				@SuppressWarnings("resource")
				ObjectInputStream ObjetoArchivo1=new ObjectInputStream(new FileInputStream(nombrearch));
				Venta[] PersonalRec=(Venta[])ObjetoArchivo1.readObject();
				int aux=0;	
				while(PersonalRec[aux]!=null){
					System.out.println(PersonalRec[aux]);
					System.out.println();
					aux++;
					//Busca el archivo introducido y lo imprime en pantalla
				}
			}
			catch(Exception mono){
			}
			System.out.println("=====================================");
			break;
		case 5:
			System.out.println("Introduce el ID del producto a buscar");
			lector.nextLine();
			System.out.println("=====================================");
			String ID2=lector.nextLine();
			for(int i=0;i<num;i++){
				if(ID2.equals(producto[i].getID())){
					System.out.println("Introduce el ID del producto");
					lector.nextLine();
					String ID = lector.nextLine();
					System.out.println("Introduce nombre del producto");
					String nombreProducto=lector.nextLine();
					System.out.println("Introduce la unidad de medida");
					String unidades=lector.nextLine();
					System.out.println("Introduce numero de productos");
					int cantidad = lector.nextInt();
					System.out.println("Introduce descripcion");
					lector.nextLine();
					String descripcion = lector.nextLine();
					System.out.println("Introduce el precio de compra");
					int precioCompra = lector.nextInt();
					System.out.println("Introduce el precio de la venta");
					int precioVenta = lector.nextInt();
					producto[i].setID(ID);
					producto[i].setNombreProducto(nombreProducto);
					producto[i].setUnidades(unidades);
					producto[i].setCantidad(cantidad);
					producto[i].setDescripcion(descripcion);
					producto[i].setPrecioCompra(precioCompra);
					producto[i].setPrecioVenta(precioVenta);
					System.out.println();
					if(producto[i].getCantidad()<100) {
						System.out.println("La cantidad de productos es demasiado baja");
					}
					try{
						@SuppressWarnings("resource")
						ObjectOutputStream ObjetoArchivo= new ObjectOutputStream(new FileOutputStream(name1));
						ObjetoArchivo.writeObject(producto);
						
					}
					catch(Exception mono){
					}
				}
			}
			System.out.println("=====================================");
			break;
		case 6:
			int w=1,aviso=0;
			while(w!=0) {
				System.out.println("Introduce el ID del producto que desea comprar");
				lector.nextLine();
				String ID=lector.nextLine();
				System.out.println("Introduce la cantidad de productos");
				int cantidad=lector.nextInt();
				for(int i=0;i<num;i++) {
					if(producto[i].getID().equals(ID)) {
						int o=producto[i].getCantidad()-cantidad;
						producto[i].setCantidad(o);
						int x,y,ganancia,IVA=0;
						x=cantidad*producto[i].getPrecioCompra();
						y=cantidad*producto[i].getPrecioVenta();
						ganancia=y-x;
						IVA=(ganancia*16)/100;
							if(venta[s]!=null) {
								s++;
								venta[s]=new Venta(ganancia);
								venta[s].setGanancia(ganancia);
								venta[s].setCantidadV(cantidad);
								venta[s].setNombreProductoV(producto[i].getNombreProducto());
								venta[s].setIDV(producto[i].getID());
								venta[s].setIVA(IVA);
								total=total+ganancia+IVA;
								venta[s].setTotal(total);
								venta[s].setFecha(fechaActual);
							}
							else {
							venta[s]=new Venta(ganancia);
							venta[s].setGanancia(ganancia);
							venta[s].setCantidadV(cantidad);
							venta[s].setNombreProductoV(producto[i].getNombreProducto());
							venta[s].setIDV(producto[i].getID());
							venta[s].setIVA(IVA);
							total=total+ganancia+IVA;
							venta[s].setTotal(total);
							venta[s].setFecha(fechaActual);
							}
						try{
							@SuppressWarnings("resource")
							ObjectOutputStream ObjetoArchivo= new ObjectOutputStream(new FileOutputStream(name2));
							ObjetoArchivo.writeObject(venta);
						}
						catch(Exception mono){
						}
						try{
							@SuppressWarnings("resource")
							ObjectOutputStream ObjetoArchivo= new ObjectOutputStream(new FileOutputStream(name1));
							ObjetoArchivo.writeObject(producto);
							
						}
						catch(Exception mono){
							
						}
					}
					if(producto[i].getCantidad()<100) {
						aviso++;
					}
				}
				if(aviso!=0) {
					System.out.println("La cantidad de productos es demasiado baja");
					aviso=0;
				}
				System.out.println("Desea continuar?\n0=no\n1=si");
				w=lector.nextInt();
			}
			cont=1;
			break;
		case 7:
			num++;
			for(int i=num-1;i<num;i++){
				System.out.println("Introduce el ID del producto");
				lector.nextLine();
				String ID = lector.nextLine();
				System.out.println("Introduce nombre del producto");
				String nombreProducto=lector.nextLine();
				System.out.println("Introduce la unidad de medida");
				String unidades=lector.nextLine();
				System.out.println("Introduce numero de productos");
				int cantidad = lector.nextInt();
				System.out.println("Introduce descripcion");
				lector.nextLine();
				String descripcion = lector.nextLine();
				System.out.println("Introduce el precio de compra");
				int precioCompra = lector.nextInt();
				System.out.println("Introduce el precio de la venta");
				int precioVenta = lector.nextInt();
				producto[i]=new Productos(ID,nombreProducto,unidades,cantidad,descripcion,precioCompra,precioVenta);
				System.out.println();
				try{
					@SuppressWarnings("resource")
					ObjectOutputStream ObjetoArchivo= new ObjectOutputStream(new FileOutputStream(name1));
					ObjetoArchivo.writeObject(producto);
					
				}
				catch(Exception mono){
				}
				if(producto[i].getCantidad()<100) {
					System.out.println("La cantidad de productos es demasiado baja");
				}
			}
			break;
		case 8:
			System.out.println("Introduce el ID del elemento a eliminar");
			lector.nextLine();
			String ID3=lector.nextLine();
			for(int i=0;i<num;i++){
				if(ID3.equals(producto[i].getID())){
					for(int j=i;j<num;j++) {
						if(j==num-1) {
							
						}
						else {
						producto[j]=producto[j+1];
						}
					}
				}
			}
			num--;
			System.out.println("=====================================");
			try{
				@SuppressWarnings("resource")
				ObjectOutputStream ObjetoArchivo= new ObjectOutputStream(new FileOutputStream(name1));
				ObjetoArchivo.writeObject(producto);
			}
			catch(Exception mono){
			}
			break;
		case 9:
			System.out.println("=====================================");
			if(cont==1) {
				try{
					@SuppressWarnings("resource")
					ObjectInputStream ObjetoArchivo1=new ObjectInputStream(new FileInputStream(name2));
					Venta[] PersonalRec=(Venta[])ObjetoArchivo1.readObject();
					int aux=0;	
					while(PersonalRec[aux]!=null){
						System.out.println(PersonalRec[aux]);
						aux++;
					}
					System.out.println("El total de la ganancia es $"+total);
				}
				catch(Exception mono){
				}
			System.out.println("=====================================");
			}
			else {
				System.out.println("No se ha regritrado ninguna venta");
				System.out.println("=====================================");
			}
			break;
		case 10:
			System.out.println("Introduce el ID para comprar producto");
			String ID5=lector.nextLine();
			ID5=lector.nextLine();
			System.out.println("Introduce la cantidad de producto a insertar");
			int cantidad=lector.nextInt();
			for(int i=0;i<num;i++) {
				if(producto[i].getID().equals(ID5)) {
					if((producto[i].getPrecioCompra()*cantidad)>total) {
						System.out.println("No tienes dinero suficiente");
					}
					else {
						total=total-(producto[i].getPrecioCompra()*cantidad);
						producto[i].setCantidad(producto[i].getCantidad()+cantidad);
						System.out.println("La compra del producto se realizo");
					}
				}
			}
			System.out.println("=====================================");
			break;
		case 11:
			int con=1,acum = 0;
			Venta[]ventas=new Venta[100];
			System.out.println("El nombre de los archivos son los siguientes:");
			while(con<cont3) {
				System.out.println("Venta"+con);
				con++;
			}
			System.out.println("introduce el numero del archivo de inicio");
			lector.nextLine();
			int nombrearch1=lector.nextInt();
			System.out.println("=====================================");
			System.out.println("introduce el numero del archivo de final");
			lector.nextLine();
			int nombrearch2=lector.nextInt();
			System.out.println("=====================================");
			while(nombrearch1<=nombrearch2) {
				try{
					@SuppressWarnings("resource")
					ObjectInputStream ObjetoArchivo1=new ObjectInputStream(new FileInputStream("Venta"+nombrearch1));
					Venta[] PersonalRec=(Venta[])ObjetoArchivo1.readObject();
					int aux=0;	
					while(PersonalRec[aux]!=null){
						System.out.println(PersonalRec[aux]);
						ventas[aux]=PersonalRec[aux];
						aux++;
						con=aux;
					}
					System.out.println("=====================================");
				}
				catch(Exception mono){
				}
				acum=acum+ventas[con-1].getTotal();
				nombrearch1++;
			}
			System.out.println("El total de ganancias de esa fecha es $"+acum);
			System.out.println("Menos el 16% de IVA perteneciente a hacienda es $"+(acum*16)/100);
			acum=acum-(acum*16)/100;
			System.out.println("El total libre de impuestos es $"+acum);
			System.out.println("=====================================");
			break;
		}
		}
	    }
        }