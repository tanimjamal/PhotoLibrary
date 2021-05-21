import java.io.*;
import java.awt.image.*;
import javax.imageio.*;

/* Mohammad Raza (mar2wcb)
 * Assignment: Homework 3: Refactor and Reuse: Towards Sustainable Code
 */

public class Photo implements Comparable<Photo>{ // implements a Comparable of type Photo
	
	private final String caption; // caption instance variable, never changes therefore is a constant, private for encapsulation
	private final String filename; // filename instance variable, never changes therefore is a constant, private for encapsulation
	private int rating; // rating instance variable, can change hence does not have keyword final, private for encapsulation
	private final String dateTaken; // dateTaken instance, never changes therefore is a constant, private for encapsulation
	protected BufferedImage imageData; 

	public Photo(String filename, String caption) { // constructor with no given rating or String value
		this.filename = filename;
		this.caption = caption;
		this.rating = 1; // rating is set equal to a default value of 1
		this.dateTaken = "1901-01-01"; // dateTaken set to default value
	}

	public Photo(String filename, String caption, String dateTaken, int rating) { // constructor with a rating value passed as a parameter
		this.filename = filename;
		this.caption = caption;
		if (rating > 5 || rating < 1) { // checks to see if the input rating value is valid
			this.rating = 1; // if the rating value is > 5 or < 0 then it is set equal to a default value of 1
		} else {
			this.rating = rating;
		}
		if(DateLibrary.isValidDate(dateTaken) == false) { // check to see if data is valid
			this.dateTaken = "1901-01-01";
		}
		else {
			this.dateTaken = dateTaken;
		}
	}
	
	public String getDateTaken( ) { // getter method for the date taken of Photo object
		return dateTaken;
	}

	public String getCaption() { // getter method for the caption of a Photo object
		return caption;
	}

	public String getFilename() { // getter method for the file name of a Photo object
		return filename;
	}

	public int getRating() { // getter method for the rating of a Photo object
		return rating;
	}
	
	public BufferedImage getImageData() {
		return imageData;
	}
	
	public boolean setImageData(BufferedImage img) {
		try {
			this.imageData = img;
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean setRating(int newRating) { // method used to change the rating of a Photo object
		if (newRating != rating && newRating <= 5 && newRating >= 1) { /* checks to see if the rating is valid and is
																		  not the same value of the original rating */
			this.rating = newRating; // if so the rating is changed to the new value
			return true;
		} else {
			return false;
		}
	}

	public boolean equals(Object o) { // method that checks if two Photo objects are the same
		if (o == null) { // checks to see if the object exists
			return false;
		}
		if (o instanceof Photo) {
			Photo p2 = (Photo) o; // cast the object to Photo
			if (this.caption.equals(p2.getCaption()) && this.filename.equals(p2.getFilename())) { /* checks to see if the
																											   instances are equal */
				return true; // the two Photos are equal
			} else {
				return false; // the two Photos are not equal
			}
		} else {
			return false;
		}
	}

	public String toString() { // used to print out instances of a Photo object
		return "(" + this.filename + ", " + this.caption + ")";
	}
	
	public int hashCode() { // hashCode produces a unique integer for a Photo filename
		return (int) filename.hashCode();
	}
	
	/* compareTo method used to compare the current Photo with another Photo,
	 * returns the comparison of the dates of the two Photos if the dates are equal
	 * then it compares the captions and returns the value     */
	public int compareTo(Photo p) {
		int val = this.dateTaken.compareTo(p.dateTaken); // compares the dates of the Photos
		if (val != 0)
			return val; // if they aren't equal return the value
		return this.caption.compareTo(p.caption); // if they are equal compare the captions
	}
	
	public boolean loadImageData(String filename) throws IOException {
		try {
			this.imageData = ImageIO.read(new File(filename));
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}


