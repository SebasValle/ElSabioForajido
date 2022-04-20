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

	public Vector2D add (Vector2D vec)
	{
		return new Vector2D(x + vec.getX(),y + vec.getY());
	}

	public Vector2D escalar(double valor){
		return new Vector2D(x*valor, y*valor);
	}

	public void limite(double valor)
	{
		if (x > valor)
			x = valor;

		if (x < -valor)
			x = -valor;

		if (y > valor)
			y = valor;

		if (y < -valor)
			y = -valor;
	}

	public Vector2D normalizar(){
		return new Vector2D(x / getMagnitud(), y/getMagnitud());
	}
	public double getMagnitud() {
		return Math.sqrt((x*x) + (y*y));
	}
	
	public Vector2D setDireccion(double angulo) {
		return new Vector2D(Math.cos(angulo)*getMagnitud(), Math.sin(angulo)*getMagnitud());
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
