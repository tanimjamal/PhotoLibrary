
/* Mohammad Raza (mar2wcb)
 * Assignment: Homework 3: Refactor and Reuse: Towards Sustainable Code
 */

import java.util.Comparator;

public class PhotoRatingComparator implements Comparator<Photo> { // implements a Comparator interface of type Photo
	
	/* method used to compare two Photos based on their ratings, if the ratings are
	   identical compare the captions */
	public int compare(Photo a, Photo b) {
		int val = -(a.getRating() - b.getRating()); // compare the ratings and return in descending order
		if (val != 0) // if they aren't equal return the value
			return val;
		return (a.getCaption().compareTo(b.getCaption())); // if they are equal compare the captions and return the value
	}

}
