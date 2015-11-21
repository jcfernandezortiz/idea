package ideaWeb;

public class IdeaEntity {

	private String name;
	private String description;
	public IdeaEntity() {
		this.name = "idea 1";
		this.description = "mi primer idea";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
