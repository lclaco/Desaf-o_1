package puntodeventa;
import java.util.ArrayList;
import java.util.Calendar;
public class Venta {

	private Calendar fecha = Calendar.getInstance();
	private ArrayList<LineaDetalle> lineasDetalle = new ArrayList<>();
	
		
	public Venta(Calendar fecha, ArrayList<LineaDetalle> lineasDetalle) {
		
		this.fecha = fecha;
		this.lineasDetalle = lineasDetalle;
	}

	public Venta() {
		
	}
	
	public void agregarLineaDetalle(LineaDetalle lineaDetalle) {
		this.lineasDetalle.add(lineaDetalle);
	}

	public Calendar getFecha() {
		return fecha;
	}

	public void setFecha(Calendar fecha) {
		this.fecha = fecha;
	}

	public ArrayList<LineaDetalle> getLineasDetalle() {
		return lineasDetalle;
	}

	public void setLineasDetalle(ArrayList<LineaDetalle> lineasDetalle) {
		this.lineasDetalle = lineasDetalle;
	}
	
	public int calcularTotal() {
		int total =0;
		for (LineaDetalle lineasDetalles : lineasDetalle) {
			total = total + lineasDetalles.calcularSubTotal();
		}
		return total;
		
	}
	
}
