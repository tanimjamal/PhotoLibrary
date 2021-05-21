import java.util.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class PhotoViewer extends JFrame {

	private PhotoContainer imageLibrary;
	
	JButton previous;
	JButton next;
	JRadioButton one;
	JRadioButton two;
	JRadioButton three;
	JRadioButton four;
	JRadioButton five;
	JLabel lbl;
	JLabel caption;
	JLabel filename;
	JFrame frame;
	ButtonGroup group;

	static int counter = 1;
	static String imageDirectory = "images\\";
	static Photo p1 = new Photo(imageDirectory + "johnwall.jpg", "John Wall", "2016-12-24 ", 1);
	static Photo p2 = new Photo(imageDirectory + "bradleybeal.jpg", "Bradley Beal", "2018-01-28 ", 2);
	static Photo p3 = new Photo(imageDirectory + "isaacbonga.jpg", "Isaac Bonga", "2019-10-05 ", 3);
	static Photo p4 = new Photo(imageDirectory + "ruihachimura.jpg", "Rui Hachimura", "2019-10-01 ", 4);
	static Photo p5 = new Photo(imageDirectory + "thomasbryant.jpg", "Thomas Bryant", "2018-02-18 ", 5);
	static ArrayList<Photo> photos;

	public void setImageLibrary(Library p) {
		imageLibrary = p;
	}

	public PhotoContainer getImageLibrary() {
		return imageLibrary;
	}

	public static void main(String[] args) throws IOException {
		PhotoViewer myViewer = new PhotoViewer();
		myViewer.setImageLibrary(new Library("Library 1", 1));
		myViewer.getImageLibrary().addPhoto(p1);
		myViewer.getImageLibrary().addPhoto(p2);
		myViewer.getImageLibrary().addPhoto(p3);
		myViewer.getImageLibrary().addPhoto(p4);
		myViewer.getImageLibrary().addPhoto(p5);

		photos = myViewer.getImageLibrary().getPhotos();
	}

	public PhotoViewer() throws IOException {
		frame = new JFrame();

		previous = new JButton("Previous");
		previous.setBounds(75,225, 100, 50);
		frame.getContentPane().add(previous);

		next = new JButton("Next");
		next.setBounds(775, 225, 100, 50);
		frame.getContentPane().add(next);

		one = new JRadioButton("1");
		one.setBounds(350, 475, 35, 35);
		frame.getContentPane().add(one);

		two = new JRadioButton("2");
		two.setBounds(400, 475, 35, 35);
		frame.getContentPane().add(two);

		three = new JRadioButton("3");
		three.setBounds(450, 475, 35, 35);
		frame.getContentPane().add(three);

		four = new JRadioButton("4");
		four.setBounds(500, 475, 35, 35);
		frame.getContentPane().add(four);

		five = new JRadioButton("5");
		five.setBounds(550, 475, 35, 35);
		frame.getContentPane().add(five);

		group = new ButtonGroup();
		group.add(one);
		group.add(two);
		group.add(three);
		group.add(four);
		group.add(five);

		p1.loadImageData(p1.getFilename());
		ImageIcon imageIcon = new ImageIcon(
				new ImageIcon(p1.getImageData()).getImage().getScaledInstance(450, 350, Image.SCALE_DEFAULT));
		// https://stackoverflow.com/questions/16343098/resize-a-picture-to-fit-a-jlabel/32885963#32885963
		lbl = new JLabel(imageIcon);
		one.setSelected(true);
		lbl.setSize(450, 350);
		lbl.setLocation(250, 75);
		frame.add(lbl);
		
		caption = new JLabel("Caption: " + "\"" +  p1.getCaption() + "\"");
		caption.setSize(275,150);
		caption.setLocation(375, -30);
		caption.setFont(new Font("Times", Font.BOLD, 16));
		frame.add(caption);
		
		filename = new JLabel("Filename: " + p1.getFilename());
		filename.setSize(275,150);
		filename.setLocation(350, 365);
		filename.setFont(new Font("Times", Font.BOLD, 14));
		frame.add(filename);

		next.addActionListener(new nextButtonListener());
		previous.addActionListener(new previousButtonListener());
		one.addActionListener(new oneRadioButtonListener());
		two.addActionListener(new twoRadioButtonListener());
		three.addActionListener(new threeRadioButtonListener());
		four.addActionListener(new fourRadioButtonListener());
		five.addActionListener(new fiveRadioButtonListener());

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setSize(975,625);
		frame.setVisible(true);
	}

	private class nextButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (counter == 5)
				counter = 1;
			else
				counter++;
			try {
				switch (counter) {
				case 1:
					p1.loadImageData(p1.getFilename());
					ImageIcon imageIcon1 = new ImageIcon(new ImageIcon(p1.getImageData()).getImage()
							.getScaledInstance(450, 350, Image.SCALE_DEFAULT));
					lbl.setIcon(imageIcon1);
					lbl.setSize(450, 350);
					lbl.setLocation(250, 75);
					frame.add(lbl);
					caption.setText("Caption: " + "\"" + p1.getCaption() + "\"");
					filename.setText("Filename: " + p1.getFilename());
					break;
				case 2:
					p2.loadImageData(p2.getFilename());
					ImageIcon imageIcon = new ImageIcon(new ImageIcon(p2.getImageData()).getImage()
							.getScaledInstance(450, 350, Image.SCALE_DEFAULT));
					lbl.setIcon(imageIcon);
					lbl.setSize(450, 350);
					lbl.setLocation(250, 75);
					frame.add(lbl);
					caption.setText("Caption: " + "\"" + p2.getCaption() + "\"");
					filename.setText("Filename: " + p2.getFilename());
					break;

				case 3:
					p3.loadImageData(p3.getFilename());
					ImageIcon imageIcon2 = new ImageIcon(new ImageIcon(p3.getImageData()).getImage()
							.getScaledInstance(450, 350, Image.SCALE_DEFAULT));
					lbl.setIcon(imageIcon2);
					lbl.setSize(450, 350);
					lbl.setLocation(250, 75);
					frame.add(lbl);
					caption.setText("Caption: " + "\"" + p3.getCaption() + "\"");
					filename.setText("Filename: " + p3.getFilename());
					break;

				case 4:
					p4.loadImageData(p4.getFilename());
					ImageIcon imageIcon3 = new ImageIcon(new ImageIcon(p4.getImageData()).getImage()
							.getScaledInstance(450, 350, Image.SCALE_DEFAULT));
					lbl.setIcon(imageIcon3);
					lbl.setSize(450, 350);
					lbl.setLocation(250, 75);
					frame.add(lbl);
					caption.setText("Caption: " + "\"" + p4.getCaption() + "\"");
					filename.setText("Filename: " + p4.getFilename());
					break;

				case 5:
					p5.loadImageData(p5.getFilename());
					ImageIcon imageIcon4 = new ImageIcon(new ImageIcon(p5.getImageData()).getImage()
							.getScaledInstance(450, 350, Image.SCALE_DEFAULT));
					lbl.setIcon(imageIcon4);
					lbl.setSize(450, 350);
					lbl.setLocation(250, 75);
					frame.add(lbl);
					caption.setText("Caption: " + "\"" + p5.getCaption() + "\"");
					filename.setText("Filename: " + p5.getFilename());
					break;
				}
				switch (photos.get(counter - 1).getRating()) {
				case 1:
					one.setSelected(true);
					break;
				case 2:
					two.setSelected(true);
					break;
				case 3:
					three.setSelected(true);
					break;
				case 4:
					four.setSelected(true);
					break;
				case 5:
					five.setSelected(true);
					break;
				}
			} catch (Exception e) {
			}

		}
	}

	private class previousButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (counter == 1)
				counter = 5;
			else
				counter--;
			try {
				switch (counter) {
				case 1:
					p1.loadImageData(p1.getFilename());
					ImageIcon imageIcon1 = new ImageIcon(new ImageIcon(p1.getImageData()).getImage()
							.getScaledInstance(450, 350, Image.SCALE_DEFAULT));
					lbl.setIcon(imageIcon1);
					lbl.setSize(450, 350);
					lbl.setLocation(250, 75);
					frame.add(lbl);
					caption.setText("Caption: " + "\"" + p1.getCaption() + "\"");
					filename.setText("Filename: " + p1.getFilename());
					break;
				case 2:
					p2.loadImageData(p2.getFilename());
					ImageIcon imageIcon = new ImageIcon(new ImageIcon(p2.getImageData()).getImage()
							.getScaledInstance(450, 350, Image.SCALE_DEFAULT));
					lbl.setIcon(imageIcon);
					lbl.setSize(450, 350);
					lbl.setLocation(250, 75);
					frame.add(lbl);
					caption.setText("Caption: " + "\"" + p2.getCaption() + "\"");
					filename.setText("Filename: " + p2.getFilename());
					break;

				case 3:
					p3.loadImageData(p3.getFilename());
					ImageIcon imageIcon2 = new ImageIcon(new ImageIcon(p3.getImageData()).getImage()
							.getScaledInstance(450, 350, Image.SCALE_DEFAULT));
					lbl.setIcon(imageIcon2);
					lbl.setSize(450, 350);
					lbl.setLocation(250, 75);
					frame.add(lbl);
					caption.setText("Caption: " + "\"" + p3.getCaption() + "\"");
					filename.setText("Filename: " + p3.getFilename());
					break;

				case 4:
					p4.loadImageData(p4.getFilename());
					ImageIcon imageIcon3 = new ImageIcon(new ImageIcon(p4.getImageData()).getImage()
							.getScaledInstance(450, 350, Image.SCALE_DEFAULT));
					lbl.setIcon(imageIcon3);
					lbl.setSize(450, 350);
					lbl.setLocation(250, 75);
					frame.add(lbl);
					caption.setText("Caption: " + "\"" + p4.getCaption() + "\"");
					filename.setText("Filename: " + p4.getFilename());
					break;

				case 5:
					p5.loadImageData(p5.getFilename());
					ImageIcon imageIcon4 = new ImageIcon(new ImageIcon(p5.getImageData()).getImage()
							.getScaledInstance(450, 350, Image.SCALE_DEFAULT));
					lbl.setIcon(imageIcon4);
					lbl.setSize(450, 350);
					lbl.setLocation(250, 75);
					frame.add(lbl);
					caption.setText("Caption: " + "\"" + p5.getCaption() + "\"");
					filename.setText("Filename: " + p5.getFilename());
					break;
				}

				switch (photos.get(counter - 1).getRating()) {
				case 1:
					one.setSelected(true);
					break;
				case 2:
					two.setSelected(true);
					break;
				case 3:
					three.setSelected(true);
					break;
				case 4:
					four.setSelected(true);
					break;
				case 5:
					five.setSelected(true);
					break;
				}
			} catch (Exception e) {
			}
		}
	}

	private class oneRadioButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (one.isSelected()) {
				photos.get(counter - 1).setRating(1);
			}
		}
	}

	private class twoRadioButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (two.isSelected()) {
				photos.get(counter - 1).setRating(2);
			}
		}
	}

	private class threeRadioButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (three.isSelected()) {
				photos.get(counter - 1).setRating(3);
			}
		}
	}

	private class fourRadioButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (four.isSelected()) {
				photos.get(counter - 1).setRating(4);
			}
		}
	}

	private class fiveRadioButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (five.isSelected()) {
				photos.get(counter - 1).setRating(5);
			}
		}
	}
}
