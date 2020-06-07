package ag;

public class Diamond {

	String clarity; // claridad, pureza
	String color; // color
	String cut; // tipo de corte
	Double carat; // peso en quilates
	Double price; // costo del diamnate
	Double totalDepth; // % Altura del diamante, desde su base (table) al fin (culet)
	Double tableWidth; // ancho de la base (table) / ancho total (y_width)
	Double x_lenght; // longitud mm
	Double y_width; // ancho mm
	Double z_depth; // profundidad mm

	public Diamond() {
		super();
	}

	public Diamond(String clarity, String color, String cut, Double carat, Double price, Double totalDepth,
			Double tableWidth, Double x_lenght, Double y_width, Double z_depth) {
		super();
		this.clarity = clarity;
		this.color = color;
		this.cut = cut;
		this.carat = carat;
		this.price = price;
		this.totalDepth = totalDepth;
		this.tableWidth = tableWidth;
		this.x_lenght = x_lenght;
		this.y_width = y_width;
		this.z_depth = z_depth;
	}

	public String getClarity() {
		return clarity;
	}

	public void setClarity(String clarity) {
		this.clarity = clarity;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getCut() {
		return cut;
	}

	public void setCut(String cut) {
		this.cut = cut;
	}

	public Double getCarat() {
		return carat;
	}

	public void setCarat(Double carat) {
		this.carat = carat;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getTotalDepth() {
		return totalDepth;
	}

	public void setTotalDepth(Double totalDepth) {
		this.totalDepth = totalDepth;
	}

	public Double getTableWidth() {
		return tableWidth;
	}

	public void setTableWidth(Double tableWidth) {
		this.tableWidth = tableWidth;
	}

	public Double getX_lenght() {
		return x_lenght;
	}

	public void setX_lenght(Double x_lenght) {
		this.x_lenght = x_lenght;
	}

	public Double getY_width() {
		return y_width;
	}

	public void setY_width(Double y_width) {
		this.y_width = y_width;
	}

	public Double getZ_depth() {
		return z_depth;
	}

	public void setZ_depth(Double z_depth) {
		this.z_depth = z_depth;
	}

	@Override
	public String toString() {
		return "Diamond : [\nclarity:	" + clarity 
				+ ",\ncolor:	" + color
				+ ",\ncut:	" + cut 
				+ ",\ncarat:	" + carat 
				+ ",\nprice:	"+ price 
				+ ",\ntotalDepth:	" + totalDepth 
				+ ",\ntableWidth:	" + tableWidth 
				+ ",\nx_lenght:	" + x_lenght
				+ ",\ny_width:	" + y_width 
				+ ",\nz_depth:	" + z_depth + " ]\n";
	}

	
}
