
public class Recipe {
protected String recipeName;
protected String description;
protected String createdOn;
protected String author;

public Recipe(String recipeName, String description, String createdOn, String author) {
	super();
	this.recipeName = recipeName;
	this.description = description;
	this.createdOn = createdOn;
	this.author = author;
}
public String getRecipeName() {
	return recipeName;
}
public void setRecipeName(String recipeName) {
	this.recipeName = recipeName;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public String getCreatedOn() {
	return createdOn;
}
public void setCreatedOn(String createdOn) {
	this.createdOn = createdOn;
}
public String getAuthor() {
	return author;
}
public void setAuthor(String author) {
	this.author = author;
}
}
