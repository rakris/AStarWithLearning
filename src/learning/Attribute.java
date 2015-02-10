package learning;

public class Attribute {

	private AttributeType type;
	private boolean value;
	public AttributeType getType() {
		return type;
	}
	public void setType(AttributeType type) {
		this.type = type;
	}
	public boolean getValue() {
		return value;
	}
	public void setValue(String valueString) {
		if(valueString.equals(type.falseString))
			this.value = false;
		else
			this.value = true;
	}
}
