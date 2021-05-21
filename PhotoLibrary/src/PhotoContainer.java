
/* Mohammad Raza (mar2wcb)
 * Assignment: Homework 3: Refactor and Reuse: Towards Sustainable Code
 */

import java.util.*;

public abstract class PhotoContainer {
	
	protected String name; // instance variable for name of Album object
	protected ArrayList<Photo> photos; // instance variable for a list of Photo objects in the Album

	public PhotoContainer(String name) {
		this.name = name;
		this.photos = new ArrayList<Photo>();
	}
	
	public String getName() { // getter method to get name of the Album
		return this.name;
	}

	public ArrayList<Photo> getPhotos() { // getter method to get the HashSet of photos in the Album
		return this.photos;
	}

	public void setName(String name) { // setter method to change the name of the Album
		this.name = name;
	}

	public boolean addPhoto(Photo p) { // method used to add a Photo object to an Album
		if (p == null) { // check to see if the Photo is null 
			return false;
		} else if (this.photos.contains(p) == false) { // check to see if the Photo is already in the HashSet
			this.photos.add(p); // if not add it to the HashSet
			return true;
		}
		return false;
	}

	public boolean hasPhoto(Photo p) { // method used to check if a Photo object exists in an Album
		return this.photos.contains(p);
	}

	public boolean removePhoto(Photo p) { // method used to remove a Photo object from an Album
		return this.photos.remove(p);
	}

	public int numPhotos() { // method used to find the number of Photos in the HashSet
		return this.photos.size();
	}

	public boolean equals(Object o) { // method used to check if two Album objects are equal
		if (o == null) { // check to see if the object passed in is null
			return false;
		}
		if (o instanceof Album) { // check to see if the object is an instance of Album
			Album o2 = (Album) o;
			if (this.name.equals((o2.name))) { // compare the two instances 
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public String toString() { // method used to print out an Album object
		String stringAlbum = (this.name + "\n");
		for (Photo p : this.photos) {
			stringAlbum += p.getFilename() + "\n"; // get the filenames of each album and add it to String
		}
		return stringAlbum;
	}

	public int hashCode() { // returns a unique integer value for the Album object
		return (int) name.hashCode();
	}
	
	public ArrayList<Photo> getPhotos(int rating) { // method used to return an ArrayList of photos with a rating >= the parameter
		ArrayList<Photo> withinRating = new ArrayList<Photo>();
		if (rating < 1 || rating > 5) { // check to see if the rating is valid
			return null;
		} else {
			for (Photo x : photos) { // go through all the photos
				if (x.getRating() >= rating) { // check to see if the rating is >=
					withinRating.add(x); // if so add it to the ArrayList being returned
				}
			}
		}
		return withinRating;
	}

	public ArrayList<Photo> getPhotosInYear(int year) { // method used to return a ArrayList of photos that were taken in a given year
		ArrayList<Photo> withinYear = new ArrayList<Photo>();
		if (year > 9999 || year < 0) { // check to see if the year is valid
			return null;
		} else {
			for (Photo x : this.photos) { // go through all the photos
				String temp = x.getDateTaken(); // get the date for the current photo
				if (DateLibrary.getYear(temp) == year) { // check to see if the years are equal
					withinYear.add(x); // if so add it to the ArrayList
				}
			}
		}
		return withinYear;
	}

	public ArrayList<Photo> getPhotosInMonth(int month, int year) { /* method used to return a ArrayList of photos that
																	 were taken in a given year and month */
		ArrayList<Photo> withinMonth = new ArrayList<Photo>();
		if (month > 12 || month < 1) { // check if the month is valid
			return null;
		} else if (year > 9999 || year < 0) { // check if the year is valid
			return null;
		} else {
			for (Photo x : this.photos) { // go through all the photos
				String temp = x.getDateTaken(); // get the date for the current photo
				if (DateLibrary.getYear(temp) == year && DateLibrary.getMonth(temp) == month) { // check if the year and month are equal
					withinMonth.add(x); // if so add it to the ArrayList
				}
			}
		}
		return withinMonth;
	}

	public ArrayList<Photo> getPhotosBetween(String beginDate, String endDate) { // method used to get the photos between two dates
		ArrayList<Photo> between = new ArrayList<Photo>();
		if (DateLibrary.isValidDate(beginDate) == false || DateLibrary.isValidDate(endDate) == false) { // check if dates are valid
			return null;
		} else if (DateLibrary.compare(endDate, beginDate) == -1) { // check if endDate comes before beginDate
			return null;
		} else {
			for (Photo x : this.photos) { // go through all the photos
				// String temp = x.getDateTaken();
				if ((DateLibrary.compare(x.getDateTaken(), beginDate) >= 0)
						&& (DateLibrary.compare(x.getDateTaken(), endDate) < 0)) { // check to see if the photo date is between the two dates
					between.add(x); // if so add it the the ArrayList
				}
			}
		}
		return between;
	}


}
