package mini;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import project.controller.ProductController;

public class ImageUtil {

	/**
	 * 이미지를 정사각형으로 잘라서 저장하고, 파일 이름을 반환함
	 * @param category
	 * @param fname
	 * @return
	 */
	public String squareImage(String fname) {
		String newFname = null;
		try {
			File file = new File(ProductController.UPLOAD_PATH + "/" + fname);
			BufferedImage buffer = ImageIO.read(file);
			int width = buffer.getWidth();
			int height = buffer.getHeight();
			int size = width, x = 0, y = 0;
			if (width > height) {
				size = height;
				x = (width - size) / 2;
			} else if (width < height) {
				size = width;
				y = (height - size) / 2;
			}
			
			String[] ext = fname.split("\\.");
			String format = ext[ext.length - 1];
			if (format.equals("jfif"))
				format = "jpg";
			newFname = "item" + System.currentTimeMillis() + "." + format;
			
			BufferedImage dest = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
			Graphics2D g = dest.createGraphics();
			g.setComposite(AlphaComposite.Src);
			g.drawImage(buffer, 0, 0, size, size, x, y, x + size, y + size, null);
			g.dispose();
			
			OutputStream os = new FileOutputStream(ProductController.UPLOAD_PATH + "/" + newFname);
			ImageIO.write(dest, format, os);
			os.close();
			file.delete();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newFname;
	}
	
}