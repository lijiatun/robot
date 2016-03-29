package com.lijiatun.ocr.util;

import java.awt.Color;
import java.awt.Image;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

public class ImageUtil {
	/**
	 * 扫描打印图片
	 * 
	 * @param file
	 * @throws Exception
	 */
	public static void imagePrint(File file) throws Exception {
		BufferedImage bi = (BufferedImage) ImageIO.read(file);
		// 获取图像的宽度和高度
		int width = bi.getWidth();
		int height = bi.getHeight();
		// 扫描图片
		for (int i = 0; i < height; i++) 
		{
			for (int j = 0; j < width; j++) 
			{
				// 行扫描
				int dip = bi.getRGB(j, i);
				if (dip == -1) 
				{
					System.out.print(" ");
				} 
				else 
				{
					System.out.print("♦");
				}
			}
			System.out.println();// 换行
		}

	}

	/**
	 * 处理黑白图片
	 */
	public static File toBlackWhite(File file) throws Exception 
	{
		Image image = ImageIO.read(file);
		int srcH = image.getHeight(null);
		int srcW = image.getWidth(null);
		BufferedImage bufferedImage = new BufferedImage(srcW, srcH, BufferedImage.TYPE_3BYTE_BGR);
		bufferedImage.getGraphics().drawImage(image, 0, 0, srcW, srcH, null);
		bufferedImage = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null).filter(bufferedImage,
				null);
		String format = getImageFormatName(file);
		// 输出文件
		File tempFile = new File(file.getParentFile(), "temp." + format);
		ImageIO.write(bufferedImage, format, tempFile);
		return file;
	}

	/**
	 * 获取图片格式
	 */
	public static String getImageFormatName(File file) throws Exception {
		ImageInputStream iis = ImageIO.createImageInputStream(file);
		Iterator<ImageReader> iter = ImageIO.getImageReaders(iis);
		if (!iter.hasNext()) {
			return null;
		}
		ImageReader reader = iter.next();
		iis.close();
		return reader.getFormatName();
	}

	/**
	 * 改变指定的颜色
	 */
	public static void changeImageColor(File file,ColorEnum oldColor,ColorEnum newColor) throws Exception 
	{
		int[] rgb = new int[3];
		/**
		 * 用来处理图片的缓冲流
		 */
		BufferedImage bi = null;
		/**
		 * 用ImageIO将图片读入到缓冲中
		 */
		bi = ImageIO.read(file);
		/**
		 * 得到图片的长宽
		 */
		int width = bi.getWidth();
		int height = bi.getHeight();
		int minx = bi.getMinX();
		int miny = bi.getMinY();
		/**
		 * 这里是遍历图片的像素，因为要处理图片的背色，所以要把指定像素上的颜色换成目标颜色 这里 是一个二层循环，遍历长和宽上的每个像素
		 */
		for (int i = minx; i < width; i++) 
		{
			for (int j = miny; j < height; j++) 
			{
				// System.out.print(bi.getRGB(jw, ih));
				/**
				 * 得到指定像素（i,j)上的RGB值，
				 */
				int pixel = bi.getRGB(i, j);
				/**
				 * 分别进行位操作得到 r g b上的值
				 */
				rgb[0] = (pixel & 0xff0000) >> 16;
				rgb[1] = (pixel & 0xff00) >> 8;
				rgb[2] = (pixel & 0xff);
				if (ColorEnum.black.equals(oldColor)) {
					if (rgb[0] >= 0 && rgb[0] <= 51 && rgb[1] >= 0 && rgb[1] <= 51 && rgb[2] >= 0 && rgb[2] <= 51) {
						/**
						 * 这里是判断通过，则把该像素换成新的颜色
						 */
						if (ColorEnum.black.equals(newColor)) {
							bi.setRGB(i, j, 0xFF000000);
						}
						if (ColorEnum.blue.equals(newColor)) {
							bi.setRGB(i, j, 0xFF0000FF);
						}
						if (ColorEnum.green.equals(newColor)) {
							bi.setRGB(i, j, 0xFF00FF00);
						}
						if (ColorEnum.red.equals(newColor)) {
							bi.setRGB(i, j, 0xFFFF0000);
						}
						if (ColorEnum.white.equals(newColor)) {
							bi.setRGB(i, j, 0xFFFFFFFF);
						}
						if (ColorEnum.yellow.equals(newColor)) {
							bi.setRGB(i, j, 0xFFFFFF00);
						}

					}
				}
				if (ColorEnum.blue.equals(oldColor)) {
					/**
					 * 进行换色操作
					 */
					if (rgb[0] >= 0 && rgb[0] <= 170 && rgb[1] >= 0 && rgb[1] <= 212 && rgb[2] >= 127 && rgb[2] <= 255) {
						/**
						 * 这里是判断通过，则把该像素换成新的颜色
						 */
						if (ColorEnum.black.equals(newColor)) {
							bi.setRGB(i, j, 0xFF000000);
						}
						if (ColorEnum.blue.equals(newColor)) {
							bi.setRGB(i, j, 0xFF0000FF);
						}
						if (ColorEnum.green.equals(newColor)) {
							bi.setRGB(i, j, 0xFF00FF00);
						}
						if (ColorEnum.red.equals(newColor)) {
							bi.setRGB(i, j, 0xFFFF0000);
						}
						if (ColorEnum.white.equals(newColor)) {
							bi.setRGB(i, j, 0xFFFFFFFF);
						}
						if (ColorEnum.yellow.equals(newColor)) {
							bi.setRGB(i, j, 0xFFFFFF00);
						}
					}
				}
				if (ColorEnum.green.equals(oldColor)) {
					/**
					 * 进行换色操作
					 */
					if (rgb[0] >= 0 && rgb[0] <= 170 && rgb[1] >= 127 && rgb[1] <= 255 && rgb[2] >= 63
							&& rgb[2] <= 86) {
						/**
						 * 这里是判断通过，则把该像素换成新的颜色
						 */
						if (ColorEnum.black.equals(newColor)) {
							bi.setRGB(i, j, 0xFF000000);
						}
						if (ColorEnum.blue.equals(newColor)) {
							bi.setRGB(i, j, 0xFF0000FF);
						}
						if (ColorEnum.green.equals(newColor)) {
							bi.setRGB(i, j, 0xFF00FF00);
						}
						if (ColorEnum.red.equals(newColor)) {
							bi.setRGB(i, j, 0xFFFF0000);
						}
						if (ColorEnum.white.equals(newColor)) {
							bi.setRGB(i, j, 0xFFFFFFFF);
						}
						if (ColorEnum.yellow.equals(newColor)) {
							bi.setRGB(i, j, 0xFFFFFF00);
						}
					}
				}
				if (ColorEnum.red.equals(oldColor)) {
					/**
					 * 进行换色操作
					 */
					if (rgb[0] >= 191 && rgb[0] <= 255 && rgb[1] >= 0 && rgb[1] <= 86 && rgb[2] >= 0 && rgb[2] <= 86) {
						/**
						 * 这里是判断通过，则把该像素换成新的颜色
						 */
						if (ColorEnum.black.equals(newColor)) {
							bi.setRGB(i, j, 0xFF000000);
						}
						if (ColorEnum.blue.equals(newColor)) {
							bi.setRGB(i, j, 0xFF0000FF);
						}
						if (ColorEnum.green.equals(newColor)) {
							bi.setRGB(i, j, 0xFF00FF00);
						}
						if (ColorEnum.red.equals(newColor)) {
							bi.setRGB(i, j, 0xFFFF0000);
						}
						if (ColorEnum.white.equals(newColor)) {
							bi.setRGB(i, j, 0xFFFFFFFF);
						}
						if (ColorEnum.yellow.equals(newColor)) {
							bi.setRGB(i, j, 0xFFFFFF00);
						}
					}
				}
				if (ColorEnum.white.equals(oldColor)) {
					/**
					 * 进行换色操作
					 */
					if (rgb[0] >= 204 && rgb[0] <= 255 && rgb[1] >= 204 && rgb[1] <= 255 && rgb[2] >= 204
							&& rgb[2] <= 255) {
						/**
						 * 这里是判断通过，则把该像素换成新的颜色
						 */
						if (ColorEnum.black.equals(newColor)) {
							bi.setRGB(i, j, 0xFF000000);
						}
						if (ColorEnum.blue.equals(newColor)) {
							bi.setRGB(i, j, 0xFF0000FF);
						}
						if (ColorEnum.green.equals(newColor)) {
							bi.setRGB(i, j, 0xFF00FF00);
						}
						if (ColorEnum.red.equals(newColor)) {
							bi.setRGB(i, j, 0xFFFF0000);
						}
						if (ColorEnum.white.equals(newColor)) {
							bi.setRGB(i, j, 0xFFFFFFFF);
						}
						if (ColorEnum.yellow.equals(newColor)) {
							bi.setRGB(i, j, 0xFFFFFF00);
						}
					}
				}
				if (ColorEnum.yellow.equals(oldColor)) {
					/**
					 * 进行换色操作
					 */
					if (rgb[0] >= 127 && rgb[0] <= 255 && rgb[1] >= 127 && rgb[1] <= 255 && rgb[2] >= 0
							&& rgb[2] <= 86) {
						/**
						 * 这里是判断通过，则把该像素换成新的颜色
						 */
						if (ColorEnum.black.equals(newColor)) {
							bi.setRGB(i, j, 0xFF000000);
						}
						if (ColorEnum.blue.equals(newColor)) {
							bi.setRGB(i, j, 0xFF0000FF);
						}
						if (ColorEnum.green.equals(newColor)) {
							bi.setRGB(i, j, 0xFF00FF00);
						}
						if (ColorEnum.red.equals(newColor)) {
							bi.setRGB(i, j, 0xFFFF0000);
						}
						if (ColorEnum.white.equals(newColor)) {
							bi.setRGB(i, j, 0xFFFFFFFF);
						}
						if (ColorEnum.yellow.equals(newColor)) {
							bi.setRGB(i, j, 0xFFFFFF00);
						}
					}
				}

			}
		}
		/**
		 * 将缓冲对象保存到新文件中
		 */
		FileOutputStream ops = new FileOutputStream(new File("D://adsfdsf.jpg"));
		ImageIO.write(bi, "jpg", ops);
		ops.flush();
		ops.close();
	}
	
	public static String toHexFromColor(Color color) {
		String r, g, b;
		StringBuilder su = new StringBuilder();
		r = Integer.toHexString(color.getRed());
		g = Integer.toHexString(color.getGreen());
		b = Integer.toHexString(color.getBlue());
		r = r.length() == 1 ? "0" + r : r;
		g = g.length() == 1 ? "0" + g : g;
		b = b.length() == 1 ? "0" + b : b;
		r = r.toUpperCase();
		g = g.toUpperCase();
		b = b.toUpperCase();
		su.append("0xFF");
		su.append(r);
		su.append(g);
		su.append(b);
		// 0xFF0000FF
		return su.toString();
	}

}
