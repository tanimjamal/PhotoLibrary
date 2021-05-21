
/* Mohammad Raza (mar2wcb)
 * Assignment: Homework 3: Refactor and Reuse: Towards Sustainable Code
 */

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Comparator;

import org.junit.Test;

public class HW3Tests {

	Library lib1 = new Library("Library 1", 556); // creating a Library object
	Photo p1 = new Photo("Photo 1", "Last Night", "2018-06-01", 1); // creating Photo objects
	Photo p2 = new Photo("Photo 2", "Lit", "1993-04-29", 2);
	Photo p3 = new Photo("Photo 3", "Cali", "2018-06-05", 3);
	Photo p4 = new Photo("Photo 4", "Fun Day", "2020-02-20", 4);
	Photo p5 = new Photo("Photo 5", "Dub", "2014-10-14", 5);
	Photo p6 = new Photo("Photo 6", "Nice", "2012-1-18", 3);
	Album a1 = new Album("Album 1"); // creating an Album object
	Library lib2 = new Library("Library 2", 786);
	Library lib3 = lib2;

	public void setup() { // setup method used to add photos to the Library that will be used through out
		lib1.addPhoto(p1);
		lib1.addPhoto(p2);
		lib1.addPhoto(p3);
		lib1.addPhoto(p4);
		lib1.addPhoto(p5);
	}

	@Test
	public void testGetPhotos1() { // first test for getPhotos
		ArrayList<Photo> expected = null; // expected is a null ArrayList
		assertEquals(lib1.getPhotos(6), expected); // the parameter 6 is an invalid rating so it should return null
	}

	@Test
	public void testGetPhotos2() { // second test for getPhotos
		setup(); // setup the Library

		ArrayList<Photo> expected = new ArrayList<Photo>();
		expected.add(p3); // the method call should return an ArrayList with only these three photos
		expected.add(p4);
		expected.add(p5);
		assertEquals(lib1.getPhotos(3), expected); // getPhotos(3) returns an ArrayList with photo ratings 3 or higher
	}

	@Test
	public void testGetPhotosInMonth1() { // first test for getPhotosInMonth method
		ArrayList<Photo> expected = null; // expected is a null ArrayList
		assertEquals(lib1.getPhotosInMonth(13, -5), expected); // invalid parameters should return a null ArrayList
	}

	@Test
	public void testGetPhotosInMonth2() { // second test for getPhotosInMonth method
		setup(); // setup the Library 

		ArrayList<Photo> expected = new ArrayList<Photo>();
		expected.add(p1); // expected ArrayList should only have these two Photos:
		expected.add(p3);
		assertEquals(lib1.getPhotosInMonth(6, 2018), expected); // method call return ArrayList with photos in June 2018
	}

	@Test
	public void testGetPhotosBetween1() { // first test for getPhotosBetween
		ArrayList<Photo> expected = null; // expected is a null ArrayList
		assertEquals(lib1.getPhotosBetween("-5-11-23", "2012-08-22"), expected); // date is invalid returns null
	}

	@Test
	public void testGetPhotosBetween2() { // second test for getPhotosBetween
		setup(); // setup the Library

		ArrayList<Photo> expected = new ArrayList<Photo>();
		expected.add(p3); // expected ArrayList should only contain these two Photos
		expected.add(p4);
		assertEquals(lib1.getPhotosBetween("2018-06-05", "2020-02-28"), expected); // get photos between these two dates
	}
	
	@Test
	public void testSimilarity1() { // first test for similarity method
		lib2.addPhoto(p1); // add all the photos from lib1 to lib2
		lib2.addPhoto(p2);
		lib2.addPhoto(p3);
		lib2.addPhoto(p4);
		lib2.addPhoto(p5);

		double expected = 1.0; // expected is 1.0 because the Libraries are equal
		assertEquals(Library.similarity(lib2, lib3), expected, 0.01);
	}

	@Test
	public void testSimilarity2() {
		setup(); // setup lib1

		lib2.addPhoto(p2); // add these photos to lib2
		lib2.addPhoto(p4);
		lib2.addPhoto(p6); 

		double expected = 2.0 / 3.0; /* expected is 2.0/3.0 because there are only two common photos in the two
									    libraries and lib2's size is 3 */
		assertEquals(Library.similarity(lib2, lib1), expected, 0.01);
	}

	@Test
	public void testRemovePhoto1() { // first test for deletePhoto
		setup(); // setup the Library
		assertFalse(lib1.removePhoto(p6)); // return false because lib1 does not contain p6 so it isn't deleted
	}

	@Test
	public void testRemovePhoto2() { // second test for deletePhoto
		setup(); // setup the Library

		lib1.getAlbums().add(a1); // add an Album to the library
		a1.addPhoto(p2); // add these photos to the Album
		a1.addPhoto(p3);

		assertTrue(lib1.removePhoto(p3)); // deletes the photo from the Album successfully, return true 
	}
	
	@Test
	public void testCompareTo1() {	// first test for compareTo method
		Photo p1 = new Photo("Photo 1", "Good Day", "2019-06-08", 5);  // create photo objects
		Photo p2 = new Photo("Photo 2", "Out With the Fam", "2017-04-29", 4);
		assertTrue(p1.compareTo(p2) > 0); // returns a positive number because p1's date comes after p2's date
	}
	
	@Test
	public void testCompareTo2() { // seconds test for compareTo method
		Photo p1 = new Photo("Photo 1", "Good Day", "1999-06-08", 5); // create photo objects
		Photo p2 = new Photo("Photo 2", "Out With the Fam", "2017-04-29", 4);
		assertTrue(p1.compareTo(p2) < 0); // returns a negative number because p1's date comes before p2's date
	}
	
	@Test
	public void testPhotoCaptionComparator1() { // first test for PhotoCaptionComparator
		Photo p1 = new Photo("Photo 1", "Good Day", "2019-06-08", 5);
		Photo p2 = new Photo("Photo 2", "Out With the Fam", "2017-04-29", 4);
		Comparator<Photo> c = new PhotoCaptionComparator(); // create a new Comparator interface of type object
		assertTrue(c.compare(p1, p2) < 0); /* returns a negative number since the first Photo's caption comes before the
											  second Photo's caption in terms of alphabetical order */
	}
	
	@Test
	public void testPhotoCaptionComparator2() { // second test for PhotoCaptionComparator
		Photo p1 = new Photo("Photo 1", "Good Day", "2019-06-08", 1);
		Photo p2 = new Photo("Photo 2", "Good Day", "2017-04-29", 4);
		Comparator<Photo> c = new PhotoCaptionComparator(); // create a new Comparator interface of type object
		assertTrue(c.compare(p1, p2) > 0); /* since the captions are the same, it compare the two ratings, returns a
											  positive number because the second Photo's rating comes before the first
											  Photo's rating (descending order)	*/
	}

	@Test
	public void testPhotoRatingComparator1() { // first test for PhotoRatingComparator
		Photo p1 = new Photo("Photo 1", "Good Day", "2019-06-08", 2);
		Photo p2 = new Photo("Photo 2", "Good Day", "2017-04-29", 5);
		Comparator<Photo> c = new PhotoRatingComparator(); // create a new Comparator interface of type object
		assertTrue(c.compare(p1, p2) > 0); /*  returns a positive number because the second Photo's rating comes before
											   first Photo's rating     */
	}
	
	@Test
	public void testPhotoRatingComparator2() { // second test for PhotoRatingComparator
		Photo p1 = new Photo("Photo 1", "Good Day", "2019-06-08", 4);
		Photo p2 = new Photo("Photo 2", "Welcome to the Party", "2017-04-29", 4);
		Comparator<Photo> c = new PhotoRatingComparator(); // create a new Comparator interface of type object
		assertTrue(c.compare(p1, p2) < 0); /* since ratings are the same, it compares the captions, first Photo's caption
											  comes before the second Photo's caption so it returns a negative number  */
	}
}
