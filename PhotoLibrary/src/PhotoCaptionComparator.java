
/* Mohammad Raza (mar2wcb)
 * Assignment: Homework 3: Refactor and Reuse: Towards Sustainable Code
 */

import java.util.Comparator;

public class PhotoCaptionComparator implements Comparator<Photo> { // implements a Comparator interface of type Photo

	/* method used to compare two Photos based on their caption, if captions are
	   identical compare the ratings */
	public int compare(Photo a, Photo b) {
		int val = a.getCaption().compareTo(b.getCaption()); // compare the two Photo's captions
		if (val != 0) // if the captions aren't equal return the value
			return val;
		return -(a.getRating() - b.getRating()); /*  if they are equal, compare the ratings and return the value in
												     descending order */
	}

}
