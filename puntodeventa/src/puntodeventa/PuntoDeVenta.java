package puntodeventa;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class PuntoDeVenta {

	private static ArrayList<Producto> productos = new ArrayList<Producto>();
	private static ArrayList<Venta> ventas = new ArrayList<Venta>();

	
	public final static int OPCION_MENU_SALIR = 7;
	public final static int OPCION_MENU_IMPRIMIR = 6;
	public final static int OPCION_MENU_CARRO = 5;
	public final static int OPCION_MENU_ACTUALIZAR = 4;
	public final static int OPCION_MENU_ELIMINAR = 3;
	public final static int OPCION_MENU_PRODUCTOS = 2;
	public final static int OPCION_MENU_CREAR = 1;
	
	public static void main(String[] args) {
		int opcionSeleccionada;
		do {
			opcionSeleccionada = menu();
			switch (opcionSeleccionada) {
			case OPCION_MENU_CREAR:
				crearProducto();
				break;
			case OPCION_MENU_PRODUCTOS:
				verProducto();
				break;
			case OPCION_MENU_ELIMINAR:
				eliminarProducto();
				break;
			case OPCION_MENU_ACTUALIZAR:
				actualizarProducto();
				break;
			case OPCION_MENU_CARRO:
				agregarProductoAlCarro();
				break;
			case OPCION_MENU_IMPRIMIR:
				imprimirVentas();
				break;
			
			}
			
		} while (opcionSeleccionada != OPCION_MENU_SALIR);
		
		System.out.printf("Seleccionó la opción %d", opcionSeleccionada);
	}

	


	private static void crearProducto() {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Por favor ingrese un código para su producto");
		String codigoDeProducto = scanner.nextLine();
		
		System.out.println("Por favor ingrese un nombre para su producto");
		String nombreProducto = scanner.nextLine();
		
		System.out.println("Por favor ingrese un precio para su producto");
		
		try {
			int precioProducto = scanner.nextInt();
			
			Producto productoNuevo = new Producto(codigoDeProducto,nombreProducto,precioProducto);
			productos.add(productoNuevo);
		
		} catch (java.util.InputMismatchException ime) {
			System.out.println("\n El precio debe ser en números \n");
		}
	}


	private static void verProducto() {
		for (Producto productos2 : productos) {
			System.out.printf("\n-------------------------------- \n Codigo: %s Producto: %s Precio: %d \n \n", productos2.getCodigo(),productos2.getNombre(),productos2.getPrecio());
		}
		
	}
	
	
	
	private static void eliminarProducto() {
	
			System.out.println("ingrese el codigo del producto");
			Scanner scanner = new Scanner(System.in);
			String codigos = scanner.nextLine();
			/*
			 * for(Producto p : productos) { if ( p.getCodigo().equalsIgnoreCase(codigos)) {
			 * productos.remove(p); System.out.printf("Se elimino el producto %s",
			 * p.getNombre()); }
			 */
			for (int i = 0; i < productos.size(); i++) {
                Producto get = productos.get(i);
                
                if (codigos.equals(get.getCodigo())){
                    productos.remove(i);
                    break;
                }
			
		}
	}
	
	private static Producto	buscarProducto(String codigo) {
		for(Producto producto: productos) {
			if (producto.getCodigo().equalsIgnoreCase(codigo)) {
				return producto;
			}
		}
		
		return null;
		
	}
	
	
	private static void agregarProductoAlCarro() {
		Venta venta = new Venta();
		boolean seguirAgregandoProductos = true;
		
		do {
		verProducto();
		
		System.out.println("ingrese el codigo del producto que quiere comprar");
		Scanner scanner = new Scanner(System.in);
		String codigo = scanner.nextLine();
		Producto producto = buscarProducto(codigo);
		
		
		System.out.println("ingrese la cantidad quiere comprar");
		try {
			int cantidad = scanner.nextInt();
			LineaDetalle lineaDetalle = new LineaDetalle(cantidad,producto);
			venta.agregarLineaDetalle(lineaDetalle);
		} catch (java.util.InputMismatchException ime) {
			System.out.println("\n Solo se permiten números para la cantidad \n");
		}
		System.out.println("Desea Agregar mas productos al carro: [si/no]");
		String seguirAgregandoProductosStr = scanner.next();
		
		seguirAgregandoProductos = seguirAgregandoProductosStr.equalsIgnoreCase("SI")?true:false;
		}while(seguirAgregandoProductos);
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("¿La venta fue pagada? SI/NO");
		boolean ventaPagada = scanner.nextLine().equalsIgnoreCase("SI")?true:false;
		
		if (ventaPagada) {
			ventas.add(venta);
		}
		
	}


	private static void actualizarProducto() {
		boolean seguirModificandoProductos = true;
		
		do {
			System.out.println("ingrese el codigo del producto");
			Scanner scanner = new Scanner(System.in);
			String codigos = scanner.nextLine();
			Producto producto = buscarProducto(codigos);	
			
			
			System.out.println("Por favor ingrese el nuevo un nombre para su producto");
			String nombreProducto = scanner.nextLine();
			producto.setNombre(nombreProducto);
			
			try{
				System.out.println("Por favor ingrese el nuevo precio para su producto");
				int precioProducto = scanner.nextInt();
				producto.setPrecio(precioProducto);	
			} catch (java.util.InputMismatchException ime) {
				System.out.println("\n Solo se permiten números para la cantidad \n");
			}
			
			System.out.println("Desea modificar mas productos al carro: [si/no]");
			String seguirModificandoProductosStr = scanner.next();
			
			seguirModificandoProductos = seguirModificandoProductosStr.equalsIgnoreCase("SI")?true:false;
		
		}while(seguirModificandoProductos);
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("¿Term? SI/NO");
		boolean productoActualizado = scanner.nextLine().equalsIgnoreCase("SI")?true:false;
		
		
	}
	
	private static void imprimirVentas() {
		System.out.println("\n***********VENTAS***********\n");
		System.out.println("=============================\n");
		System.out.println("Fecha \t Monto");
		for(Venta venta: ventas) {
			System.out.printf("%td %tB %tY %tH:%tM \t %d"
			,venta.getFecha()
			,venta.getFecha()
			,venta.getFecha()
			,venta.getFecha()
			,venta.getFecha()
			,venta.calcularTotal());
		}
		System.out.printf("%n%n");
		
	}

	private static int menu() {
		System.out.println("\n----------------------- \n *MENU PUNTO DE VENTA* \n-----------------------");
		System.out.println("1. CREAR PRODUCTO");
		System.out.println("2. VER PRODUCTO");
		System.out.println("3. ELIMINAR PRODUCTO");
		System.out.println("4. ACTUALIZAR PRODUCTO");
		System.out.println("5. AGREGAR PRODUCTOS AL CARRO");
		System.out.println("6. IMPRIMIR BOLETA");
		System.out.println("7. EXIT");
		
		System.out.println("\n-------------------------------- \n *POR FAVOR DIGITE UNA OPCIÓN* \n--------------------------------");
		
		Scanner scanner = new Scanner( System.in);	
		
		try {
			int opcionSeleccionada = scanner.nextInt();
			return opcionSeleccionada;
		} catch (java.util.InputMismatchException ime) {
			System.out.println("\n Solo se permiten números \n");
		}
		return 0;
	}
}
