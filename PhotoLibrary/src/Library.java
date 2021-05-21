
/* Mohammad Raza (mar2wcb)
 * Assignment: Homework 3: Refactor and Reuse: Towards Sustainable Code
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;


public class Library extends PhotoContainer { 
	private final int id; // instance variable for the id of the Library
	private HashSet<Album> albums; // HashSet instance used to store all the Albums in the Library

	public Library(String name, int id) { // constructor used to create a Library object
		super(name);
		this.name = name;
		this.id = id;
		this.photos = new ArrayList<Photo>(); // instantiate the ArrayList 
		this.albums = new HashSet<Album>(); // instantiate the HashSet
	}

	public HashSet<Album> getAlbums() { // getter method for HashSet of Albums
		return albums;
	}


	public int getId() { // getter method for the id of the Library
		return id;
	}

	public boolean equals(Object o) { // equals method used to see if two Library objects are equal
		if (o == null) { // checks to see if the object exists
			return false;
		}
		if (o instanceof Library) {
			Library o2 = (Library) o; // cast the object to Library
			if (getId() == (o2.getId())) { // checks to see if the instances are equal
				return true; // the two Libraries are equal
			} else {
				return false; // the two Libraries are not equal
			}
		} else {
			return false;
		}
	}

	public String toString() { // toString method used to print out a Library object
		String temp = "Library Name: " + name + "   ID: " + id + "   Photos: " + Arrays.toString(photos.toArray())
				+ " Albums: ";
		for (Album x : albums) {
			temp += x.getName() + " "; // get the name of each Album and add it to the String
		}
		return temp;
	}

	public static ArrayList<Photo> commonPhotos(Library a, Library b) {
		ArrayList<Photo> sharedPhotos = new ArrayList<Photo>(); // create a new ArrayList for the common photos
		ArrayList<Photo> aPhotos = a.getPhotos(); // ArrayList for the Photos in Library a
		ArrayList<Photo> bPhotos = b.getPhotos(); // ArrayList for the Photos in Library b

		for (Photo x : aPhotos) { // for each loop used to iterate through the ArrayList of Photos in Library a
			for (Photo y : bPhotos) { // for each loop used to iterate through the ArrayList of Photos in Library b
				if (x.equals(y)) { // checks to see if the photos from Library a and Library b are equal
					sharedPhotos.add(x); // if they are equal add them to the sharedPhotos ArrayList
				}
			}
		}
		return sharedPhotos;
	}

	public static double similarity(Library a, Library b) {
		ArrayList<Photo> sharedPhotos = commonPhotos(a, b); /*
															 * call the commonPhotos method to obtain the ArrayList
															 * containing the shared Photos in the two Libraries
															 */
		int smallerNum; // variable for the smaller size of the two Photo ArrayList

		if (sharedPhotos.size() == 0) { // checks to see if there are no Photos in the ArrayList
			return 0.0; // if so then return 0
		}

		if (a.getPhotos().size() < b.getPhotos().size()) { /* checks to see if ArrayList in Library a has a smaller
															  number of Photos */
			smallerNum = a.getPhotos().size(); // if so set it equal to the smallerNum variable we declared earlier
		} else {
			smallerNum = b.getPhotos().size(); /* if that isn't true then set smallerNum equal to the size of Library
												  b's Photo ArrayList */
		}
		return (sharedPhotos.size()
				/ ((double) smallerNum));   /*  return the number of common photos divided by the smaller size of the
											 * two, cast using double to prevent integer division
											 */
	}

	
	public boolean createAlbum(String albumName) { // method used to create a new Album object
		Album temp = new Album(albumName);
		if (this.albums.contains(temp)) { // check to see if the Album already exists
			return false;
		} else {
			this.albums.add(temp); // if not add it to the set of Albums
			return true;
		}
	}

	public boolean removeAlbum(String albumName) { // method used to remove an Album object
		for (Album x : albums) { // go through all the albums
			if (x.getName().equals(albumName)) { // check to see if the names are equal
				albums.remove(x); // if so remove the album
				return true;
			}
		}
		return false;
	}

	public boolean addPhotoToAlbum(Photo p, String albumName) { // method used to add a photo object to an Album object
		if (photos.contains(p)) { // check to see if the photo ArrayList contains the photo
			for (Album x : albums) { // go through all the albums
				if (x.getName().equals(albumName)) { // check if the names are equal 
					return x.addPhoto(p); // add the photo to the album
				}
			}
		}
		return false;
	}

	public boolean removePhotoFromAlbum(Photo p, String albumName) { // method used to remove a Photo from an Album
		for (Album x : albums) { // go through all the Albums
			if (x.getName().equals(albumName)) { // check if the names are equal
				return x.removePhoto(p); // if so remove the photo
			}
		}
		return false;
	}

	private Album getAlbumByName(String albumName) { // helper method used to get the Album based off of its name
		for (Album x : albums) { // go through all the albums
			if (x.getName().equals(albumName)) { // check if the names are equal
				return x; // if so return the album
			}
		}
		return null;
	}



	public boolean removePhoto(Photo p) { // method used to delete a photo from a library
		if (getPhotos().contains(p)) { // check to see if the ArrayList contains the photo
			photos.remove(p); // if so remove it
			for (Album x : albums) { // go through all the albums
				if (x.hasPhoto(p) == true) { // check to see if any of them have the photo
					x.removePhoto(p); // if so remove it
				}
			}
			return true;
		}
		return false;
	}
}
