package matematicas;

public class Vector2D {
	private double x, y;
	
	public Vector2D(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	//Sobrecarga de mEtodos, inicializa los vectores en ceros
	public Vector2D() {
		x = 0;
		y = 0;
	}
	
	public Vector2D add(Vector2D vec) {
		return new Vector2D(x+vec.getX(), y+vec.getY());
	}
	
	public Vector2D restar(Vector2D vec) {
		return new Vector2D(x-vec.getX(), y-vec.getY());
	}
	
	public Vector2D escalar(double valor) {
		return new Vector2D(x*valor, y*valor);
	}
	
	public Vector2D limite(double valor) {
		/*if(x>valor)
			x = valor;
		if(x<-valor)
			x = -valor;
		if(y>valor)
			y = valor;
		if(y<-valor)
			y = -valor;*/
		if(getMagnitud() > valor) {
			return this.normalizacion().escalar(valor);
		}
		return this;
	}
	
	public Vector2D normalizacion() {	//Esto serA un vector unitario.
		double magnitud = getMagnitud();
		
		return new Vector2D(x/magnitud, y/magnitud);
	}
	
	public double getMagnitud() {
		return Math.sqrt((x*x) + (y*y));
	}
	
	public Vector2D setDireccion(double angulo) {
		double magnitud = getMagnitud();
		
		return new Vector2D(Math.cos(angulo)*magnitud, Math.sin(angulo)*magnitud);
	}
	
	public double getAngulo() {
		return Math.asin(y/getMagnitud());
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
}
