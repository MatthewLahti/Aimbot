import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.Java2DFrameConverter;

import java.awt.*;
import java.awt.image.BufferedImage;

public class FrameGrab {
	private FFmpegFrameGrabber grabber;
	private Java2DFrameConverter converter;
	private Frame frame;
	private BufferedImage buf;
	int w;
	int h;
	Color tmp;
	int red;
	int green;
	int blue;

	public FrameGrab(){
		try {
			w = 1920;
			h = 1080;
			grabber = FFmpegFrameGrabber.createDefault("title=obs64");
			grabber.setFormat("gdigrab");
			grabber.setImageWidth(w);
			grabber.setImageHeight(h);
			grabber.start();
			converter = new Java2DFrameConverter();
		}
		catch (FrameGrabber.Exception ex) {
			System.out.println("Error creating Frame Grabber");
			System.exit(-1);
		}
	}

	public void whereShoot(){
		try {
			frame = grabber.grab();
			buf = converter.getBufferedImage(frame);
			MoveMouse mouse = new MoveMouse();
			int wStart = 200;
			int wEnd = 1720;
			int hStart = 300;
			int hEnd = 780;
			boolean stop = false;
			for (int j = hStart; j < hEnd + 1; j+=2){
				if (stop){
					break;
				}
				// Enemy highlight colour Purple; Red: 160 - 255, Green: 75 - 130, Blue: 170 - 255.
				for (int i = wStart; i < wEnd + 1; i+=2){
					tmp = new Color(buf.getRGB(i,j));
					red = tmp.getRed();
					blue = tmp.getBlue();
					green = tmp.getGreen();
					if (red >= 160 && red <= 255 && green >= 75 && green <= 130 && blue >= 170 && blue <= 255) {
						mouse.moveHere(i,j);
						stop = true;
						break;
					}
				}
			}
		}
		catch (FrameGrabber.Exception ex){
		}
	}
}