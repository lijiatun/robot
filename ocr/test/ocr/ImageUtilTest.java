package ocr;

import java.io.File;

import org.junit.Test;

import com.lijiatun.ocr.util.ColorEnum;
import com.lijiatun.ocr.util.ImageUtil;


public class ImageUtilTest {

	/**
	 * 测试打印
	 */
	@Test
	public void testPrint() 
	{
		File file = new File("D://image.png");
		try {
			ImageUtil.imagePrint(file);
		} catch (Exception e) {
			System.out.println("图片扫码错误");
		}
	}
	/**
	 * 测试转化为黑白照片
	 */
	@Test
	public void testToBlackWhite()
	{
		File file = new File("D://111.jpg");
		try {
			ImageUtil.toBlackWhite(file);
		} catch (Exception e) {
			System.out.println("图片扫码错误");
		}
	}
	
	/**
	 * 测试转化为黑白照片
	 */
	@Test
	public void testChangeImageColor()
	{
		File file = new File("D://1.jpg");
		//File file = new File("D://2.png");
		try 
		{
			ImageUtil.changeImageColor(file,ColorEnum.white,ColorEnum.green);
			//ImageUtil.changeImageColor(file,ColorEnum.white,ColorEnum.blue);
		} 
		catch (Exception e) 
		{
			System.out.println("图片扫码错误:"+e);
		}
	}
	/**
	 * 测试转化为黑白照片
	 */
	@Test
	public void testSave72DPIJpgImage()
	{
		File file = new File("D://挂靠协议1-1.jpg");
		try 
		{
			ImageUtil.save72DPIJpgImage(file,file);
		} 
		catch (Exception e) 
		{
			System.out.println("生成72dpi的jpg图片错误:"+e);
		}
	}

}
