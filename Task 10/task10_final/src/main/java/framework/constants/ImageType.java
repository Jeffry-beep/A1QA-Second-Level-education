package framework.constants;

public enum ImageType {

	PNG(".png");

	private String type;

	private ImageType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

}
