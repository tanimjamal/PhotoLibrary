
/* Mohammad Raza (mar2wcb)
 * Assignment: Homework 3: Refactor and Reuse: Towards Sustainable Code
 */

import java.util.ArrayList;

public class Album extends PhotoContainer { // album inherits methods and fields from PhotoContainer

	public Album(String name) { // constructor to create an Album object
		super(name); // calling the super class constructor
		this.name = name;
		this.photos = new ArrayList<Photo>();
	}
}
