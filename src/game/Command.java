/**
 * 
 */
package game;

/**
 * @author dalencourt
 * 
 */
public class Command {
	private String description;
	private int id;
	private String type;

	public Command(String desctiption, int id, String type) {
		this.description = desctiption;
		this.id = id;
		this.type = type;
	}

	public String getDescription() {
		return this.description;
	}

	public int getID() {
		return this.id;
	}

	public String getType() {
		return this.type;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Command other = (Command) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
}
