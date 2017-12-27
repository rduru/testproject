package com;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.NumberFormat;
import java.util.Date;
import java.awt.image.DataBufferByte;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.util.Base64;
import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Method;
import org.imgscalr.Scalr.Mode;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.magiclen.magicimage.ImageBlurring;
import org.magiclen.magicimage.ImageBuffer;
import org.magiclen.magicimage.ImageSharpen;

import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;


import com.sun.image.codec.jpeg.JPEGDecodeParam;
import com.sun.image.codec.jpeg.JPEGCodec;
import javax.xml.bind.DatatypeConverter;

@SuppressWarnings({ "serial", "restriction" })
public class ImageConvert  extends HttpServlet {
	
	Method[] scarlMethod = {
			Method.AUTOMATIC, 
			Method.BALANCED,
			Method.QUALITY,
			Method.SPEED,
			Method.ULTRA_QUALITY
	};
	
	String filePath = ".\\ConvertResult\\";
	String folderName = "";
	double targetDPI = 200.0;
	BufferedImage image = null;
    float scale = 1.0f;
    float rotation = 0f;            
    
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		String imgBase64 = "";
		String scarl_quality = "";
		String jpeg_quality = "";
		String gs_smooth = "";
		String blur_radis = "";
		String sharp_radis = "";
		StringBuffer jb = new StringBuffer();
		String line = null;
		try {
			BufferedReader reader = request.getReader();
		    while ((line = reader.readLine()) != null)
		      jb.append(line);
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			JSONParser parser = new JSONParser();
			JSONObject json = (JSONObject) parser.parse(jb.toString());
			imgBase64 = (String) json.get("img");
			scarl_quality = (String) json.get("scarl_quality");
//			folderName = (String) json.get("folderName");
			jpeg_quality = (String) json.get("jpeg_quality");
			gs_smooth = (String) json.get("gs_smooth");
			blur_radis =  (String) json.get("blur_radis");
			sharp_radis =  (String) json.get("sharp_radis");
			targetDPI = Double.parseDouble((String)json.get("target_DPI")+".0");
			converter(Float.parseFloat(sharp_radis), Integer.parseInt(blur_radis), imgBase64, Integer.parseInt(scarl_quality), Float.parseFloat((jpeg_quality.length()<2?"0.0"+jpeg_quality:"0."+jpeg_quality)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	@SuppressWarnings("restriction")
	public void converter(float sharp_radis, int blur_radis, String imgBase64, int scarl_quality, float jpeg_quality) throws Exception {
//		filePath = filePath;
		File file = new File(filePath);
		if  (!file .exists()  && !file .isDirectory()) {       
		    System.out.println("//不存在");  
		    file .mkdir();    
		} else {  
		    System.out.println("//目录存在");  
		}  
		InputStream is = null;
		String[] allStr = imgBase64.split(",");
		String head = "";
		if (allStr.length>1) {
			head = allStr[0];
			imgBase64 = allStr[1];
		}
		else {
			imgBase64 = allStr[0];
		}
		byte[] imageBytes = DatatypeConverter.parseBase64Binary(imgBase64);
		is = new ByteArrayInputStream(imageBytes);
        image = ImageIO.read(is);
        System.out.println("image.getHeight(): " + image.getHeight() + ", image.getWidth(): " + image.getWidth() + ". ");
        double dpi = Math.sqrt((image.getWidth()*image.getWidth()) + (image.getHeight()*image.getHeight()))/Math.sqrt((8.5*8.5)+(11*11));
        
        System.out.println("dpi: " + dpi + "." );
        if (dpi<(targetDPI-0.1)) {
        	scale = (float)(targetDPI/dpi);
        }            
        //================================
        

		/*
		 * Scalr.resize
		 * param {
		 * 	BufferImage image : 需要縮放的圖,
		 * 	Method method : 縮放參數(方法相關)
		 * 	Mode mode : 縮放參數(尺寸相關)
		 * 	int width : 目標寬度
		 * 	int heigth : 目標高度
		 * }
		 * output BufferImage
		 */
    	BufferedImage scaledImg = Scalr.resize(image, Method.ULTRA_QUALITY, Mode.AUTOMATIC , (int)(image.getWidth()*scale), (int)(image.getHeight()*scale));
    	JPEGEncodeParam jpegEncodeParam = JPEGCodec.getDefaultJPEGEncodeParam(scaledImg);
		//設定dpi
		jpegEncodeParam.setXDensity((int) targetDPI);
		jpegEncodeParam.setYDensity((int) targetDPI);
		jpegEncodeParam.setDensityUnit(JPEGDecodeParam.DENSITY_UNIT_DOTS_INCH);
		//設定jpeg品質
		jpegEncodeParam.setQuality(jpeg_quality, false);


    	String imagePath = filePath+blur_radis+sharp_radis+".jpg";
		FileOutputStream fis = new FileOutputStream(imagePath);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			JPEGImageEncoder jpegEncoder = JPEGCodec.createJPEGEncoder(baos);
			jpegEncoder.encode(scaledImg, jpegEncodeParam);
//			fis.flush();
//			String str = baos.toString();
			byte[] xxx = Base64.encode(baos.toByteArray());
			String str = new String (xxx, "UTF-8");
			System.out.println(head+","+str);

			byte[] imageBytes2 = DatatypeConverter.parseBase64Binary(str);
			is = new ByteArrayInputStream(imageBytes2);
	        image = ImageIO.read(is);
	        System.out.println("image.getHeight(): " + image.getHeight() + ", image.getWidth(): " + image.getWidth() + ". ");
	        double dpi2 = Math.sqrt((image.getWidth()*image.getWidth()) + (image.getHeight()*image.getHeight()))/Math.sqrt((8.5*8.5)+(11*11));
	        scale = 1.0f;
	        System.out.println("dpi: " + dpi2 + "." );
	        if (dpi<(targetDPI-0.1)) {
	        	scale = (float)(targetDPI/dpi2);
	        }            

			Date start = new Date();
//	        BlurEffect bbb = new BlurEffect(gs_smooth, image);
//	        BufferedImage bbbi = bbb.getBluredImg();
//	        BufferedImage bbbi = this.blur(image, gs_smooth);
//			BufferedImage bbbi = ImageBlurring.gaussianBlur(image, blur_radis);
//			bbbi = ImageSharpen.sharpen(bbbi, sharp_radis);
//			int[][] martrix = new int[gs_smooth][gs_smooth];
//			int[] values = new int[gs_smooth*gs_smooth+1];
//			for (int i = 0; i < image.getWidth(); i++)
//				for (int j = 0; j < image.getHeight(); j++) {
//					readPixel(image, i, j, values);
//					fillMatrix(martrix, values);
//					image.setRGB(i, j, avgMatrix(martrix));
//					int noww = i*image.getHeight()+j+1;
//					int all =  image.getWidth()*image.getHeight();
//
//			         double  p3  =  noww  /  all;
////			        NumberFormat nf  =  NumberFormat.getPercentInstance();
////			        nf.setMinimumFractionDigits( 2 );
//			         
//					System.out.println(noww+"/"+all+">>>"+((float)noww/all)*100+"%");
//				}
//			
			
			Date end = new Date();
			long usedTime = end.getTime()-start.getTime();
			System.out.println("used time:"+usedTime/1000);
//			ImageIO.write(img, "jpeg", new File("./test.jpg"));
	    	BufferedImage scaledImg2 = Scalr.resize(image, Method.ULTRA_QUALITY, Mode.AUTOMATIC , (int)(image.getWidth()*scale), (int)(image.getHeight()*scale));
	    	JPEGEncodeParam jpegEncodeParam2 = JPEGCodec.getDefaultJPEGEncodeParam(image);
			//設定dpi
	    	jpegEncodeParam2.setXDensity((int) targetDPI);
	    	jpegEncodeParam2.setYDensity((int) targetDPI);
	    	jpegEncodeParam2.setDensityUnit(JPEGDecodeParam.DENSITY_UNIT_DOTS_INCH);
			//設定jpeg品質
			jpegEncodeParam.setQuality(jpeg_quality, false);


			JPEGImageEncoder jpegEncode2 = JPEGCodec.createJPEGEncoder(fis);
			jpegEncode2.encode(image, jpegEncodeParam2);
			fis.flush();
	        //=======
		} finally {
			fis.close();
			baos.close();
		}
//	
//        if(scarl_quality>=0) {
//        	converter2(scarl_quality, jpeg_quality);
//        } else {
//        	for(int i=0; i<scarlMethod.length; i++) {
//        		converter2(i, jpeg_quality);
//        	}
//        }
	}

	private static void readPixel(BufferedImage img, int x, int y, int[] pixels) {
		int xStart = x - 1;
		int yStart = y - 1;
		int current = 0;
		for (int i = xStart; i < 3 + xStart; i++)
			for (int j = yStart; j < 3 + yStart; j++) {
				int tx = i;
				if (tx < 0) {
					tx = -tx;

				} else if (tx >= img.getWidth()) {
					tx = x;
				}
				int ty = j;
				if (ty < 0) {
					ty = -ty;
				} else if (ty >= img.getHeight()) {
					ty = y;
				}
				pixels[current++] = img.getRGB(tx, ty);

			}
	}

	private static void fillMatrix(int[][] matrix, int[] values) {
		int filled = 0;
		for (int i = 0; i < matrix.length; i++) {
			int[] x = matrix[i];
			for (int j = 0; j < x.length; j++) {
				x[j] = values[filled++];
			}
		}
	}

	private static int avgMatrix(int[][] matrix) {
		int r = 0;
		int g = 0;
		int b = 0;
		for (int i = 0; i < matrix.length; i++) {
			int[] x = matrix[i];
			for (int j = 0; j < x.length; j++) {
				if (j == 1) {
					continue;
				}
				Color c = new Color(x[j]);
				r += c.getRed();
				g += c.getGreen();
				b += c.getBlue();
			}
		}
		return new Color(r / 8, g / 8, b / 8).getRGB();

	}
	@SuppressWarnings("restriction")
	public void converter2(int methodFlag, float jpeg_quality) throws Exception {
		/*
		 * Scalr.resize
		 * param {
		 * 	BufferImage image : 需要縮放的圖,
		 * 	Method method : 縮放參數(方法相關)
		 * 	Mode mode : 縮放參數(尺寸相關)
		 * 	int width : 目標寬度
		 * 	int heigth : 目標高度
		 * }
		 * output BufferImage
		 */
		System.out.println("do quality : "+scarlMethod[methodFlag].toString());
    	String imagePath = filePath+"["+scarlMethod[methodFlag].toString()+"]"+"["+jpeg_quality+"f].jpg";
    	BufferedImage scaledImg = Scalr.resize(image, scarlMethod[methodFlag], Mode.AUTOMATIC , (int)(image.getWidth()*scale), (int)(image.getHeight()*scale));
    	JPEGEncodeParam jpegEncodeParam = JPEGCodec.getDefaultJPEGEncodeParam(scaledImg);
		//設定dpi
		jpegEncodeParam.setXDensity((int) targetDPI);
		jpegEncodeParam.setYDensity((int) targetDPI);
		jpegEncodeParam.setDensityUnit(JPEGDecodeParam.DENSITY_UNIT_DOTS_INCH);
		//設定jpeg品質
		jpegEncodeParam.setQuality(jpeg_quality, false);

		
		FileOutputStream fis = new FileOutputStream(imagePath);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			JPEGImageEncoder jpegEncoder = JPEGCodec.createJPEGEncoder(baos);
			jpegEncoder.encode(scaledImg, jpegEncodeParam);
//			fis.flush();
//			String str = baos.toString();
			byte[] xxx = Base64.encode(baos.toByteArray());
			String str = new String (xxx, "UTF-8");
//			System.out.println(str);

//			filePath = filePath+"-"+folderName+"//";
			File file = new File(filePath);
			if  (!file .exists()  && !file .isDirectory()) {       
			    System.out.println("//不存在");  
			    file .mkdir();    
			} else {  
			    System.out.println("//目录存在");  
			}  
			InputStream is = null;
//			String[] allStr = imgBase64.split(",");
//			String head = "";
//			if (allStr.length>1) {
//				head = allStr[0];
//				imgBase64 = allStr[1];
//			}
//			else {
//				imgBase64 = allStr[0];
//			}
			byte[] imageBytes = DatatypeConverter.parseBase64Binary(str);
			is = new ByteArrayInputStream(imageBytes);
	        image = ImageIO.read(is);
	        System.out.println("image.getHeight(): " + image.getHeight() + ", image.getWidth(): " + image.getWidth() + ". ");
	        double dpi = Math.sqrt((image.getWidth()*image.getWidth()) + (image.getHeight()*image.getHeight()))/Math.sqrt((8.5*8.5)+(11*11));
	        
	        System.out.println("dpi: " + dpi + "." );
	        if (dpi<(targetDPI-0.1)) {
	        	scale = (float)(targetDPI/dpi);
	        }            
	        //================================
	        

			/*
			 * Scalr.resize
			 * param {
			 * 	BufferImage image : 需要縮放的圖,
			 * 	Method method : 縮放參數(方法相關)
			 * 	Mode mode : 縮放參數(尺寸相關)
			 * 	int width : 目標寬度
			 * 	int heigth : 目標高度
			 * }
			 * output BufferImage
			 */
	    	BufferedImage scaledImg2 = Scalr.resize(image, Method.ULTRA_QUALITY, Mode.AUTOMATIC , (int)(image.getWidth()*scale), (int)(image.getHeight()*scale));
	    	JPEGEncodeParam jpegEncodeParam2 = JPEGCodec.getDefaultJPEGEncodeParam(scaledImg2);
			//設定dpi
	    	jpegEncodeParam2.setXDensity((int) targetDPI);
	    	jpegEncodeParam2.setYDensity((int) targetDPI);
	    	jpegEncodeParam2.setDensityUnit(JPEGDecodeParam.DENSITY_UNIT_DOTS_INCH);
			//設定jpeg品質
			jpegEncodeParam.setQuality(jpeg_quality, false);

			
//			FileOutputStream fis2 = new FileOutputStream(imagePath);
			
//			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			try {
				JPEGImageEncoder jpegEncode2r = JPEGCodec.createJPEGEncoder(fis);
				jpegEncoder.encode(scaledImg, jpegEncodeParam);
				fis.flush();
//				String str = baos.toString();
//				byte[] xxx = Base64.encode(baos.toByteArray());
//				String str = new String (xxx, "UTF-8");
//				System.out.println(head+","+str);
			} finally {
				fis.close();
				baos.close();
				System.out.println("done");
			}
	//	
//	        if(scarl_quality>=0) {
//	        	converter2(scarl_quality, jpeg_quality);
//	        } else {
//	        	for(int i=0; i<scarlMethod.length; i++) {
//	        		converter2(i, jpeg_quality);
//	        	}
//	        }
		
		} finally {
//			fis.close();
			baos.close();
		}
	}
	
	/**
	* 模糊执行方法
	* @param img 原图片
	* @param radius 模糊权重
	* @return 模糊后图片
	*/
	public static BufferedImage blur(BufferedImage img,int radius) throws Exception{
		int height = img.getHeight();
		int width = img.getWidth();
		int[] values = getPixArray(img, width, height);
		values = doBlur(values, width, height, radius);
		img.setRGB(0, 0, width, height, values, 0, width);
		return img;
	}

	/**
	* 获取图像像素矩阵
	* @param im
	* @param w
	* @param h
	* @return
	*/
	private static int[] getPixArray(Image im, int w, int h) { 
		int[] pix = new int[w * h]; 
		PixelGrabber pg = null; 
		try { 
			pg = new PixelGrabber(im, 0, 0, w, h, pix, 0, w); 
			if (pg.grabPixels() != true) 
			try { 
				throw new java.awt.AWTException("pg error" + pg.status()); 
			} catch (Exception eq) { 
				eq.printStackTrace(); 
			} 
		} catch (Exception ex) { 
			ex.printStackTrace();
		} 
		return pix; 
	}

	/**
	* 高斯模糊算法。
	* @param pix
	* @param w
	* @param h
	* @param radius
	* @return
	*/
	public static int[] doBlur(int[] pix,int w,int h,int radius) throws Exception {
		int wm = w - 1;
		int hm = h - 1;
		int wh = w * h;
		int div = radius + radius + 1;
		int r[] = new int[wh];
		int g[] = new int[wh];
		int b[] = new int[wh];
		int rsum, gsum, bsum, x, y, i, p, yp, yi, yw;
		int vmin[] = new int[Math.max(w, h)];
		int divsum = (div + 1) >> 1;
		divsum *= divsum;
		int dv[] = new int[256 * divsum];
		for (i = 0; i < 256 * divsum; i++) {
			dv[i] = (i / divsum);
		}
		yw = yi = 0;
		int[][] stack = new int[div][3];
		int stackpointer;
		int stackstart;
		int[] sir;
		int rbs;
		int r1 = radius + 1;
		int routsum, goutsum, boutsum;
		int rinsum, ginsum, binsum;
		for (y = 0; y < h; y++) {
			rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;
			for (i = -radius; i <= radius; i++) {
				p = pix[yi + Math.min(wm, Math.max(i, 0))];
				sir = stack[i + radius];
				sir[0] = (p & 0xff0000) >> 16;
				sir[1] = (p & 0x00ff00) >> 8;
				sir[2] = (p & 0x0000ff);
				rbs = r1 - Math.abs(i);
				rsum += sir[0] * rbs;
				gsum += sir[1] * rbs;
				bsum += sir[2] * rbs;
				if (i > 0) {
					rinsum += sir[0];
					ginsum += sir[1];
					binsum += sir[2];
				} else {
					routsum += sir[0];
					goutsum += sir[1];
					boutsum += sir[2];
				}
			}
			stackpointer = radius;
			for (x = 0; x < w; x++) {
				r[yi] = dv[rsum];
				g[yi] = dv[gsum];
				b[yi] = dv[bsum];
				rsum -= routsum;
				gsum -= goutsum;
				bsum -= boutsum;
				stackstart = stackpointer - radius + div;
				sir = stack[stackstart % div];
				routsum -= sir[0];
				goutsum -= sir[1];
				boutsum -= sir[2];
				if (y == 0) {
					vmin[x] = Math.min(x + radius + 1, wm);
				}
				p = pix[yw + vmin[x]];
				sir[0] = (p & 0xff0000) >> 16;
				sir[1] = (p & 0x00ff00) >> 8;
				sir[2] = (p & 0x0000ff);
				rinsum += sir[0];
				ginsum += sir[1];
				binsum += sir[2];
				rsum += rinsum;
				gsum += ginsum;
				bsum += binsum;
				stackpointer = (stackpointer + 1) % div;
				sir = stack[(stackpointer) % div];
				routsum += sir[0];
				goutsum += sir[1];
				boutsum += sir[2];
				rinsum -= sir[0];
				ginsum -= sir[1];
				binsum -= sir[2];
				yi++;
			}
			yw += w;
		}
		for (x = 0; x < w; x++) {
			rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;
			yp = -radius * w;
			for (i = -radius; i <= radius; i++) {
				yi = Math.max(0, yp) + x;
				sir = stack[i + radius];
				sir[0] = r[yi];
				sir[1] = g[yi];
				sir[2] = b[yi];
				rbs = r1 - Math.abs(i);
				rsum += r[yi] * rbs;
				gsum += g[yi] * rbs;
				bsum += b[yi] * rbs;
				if (i > 0) {
					rinsum += sir[0];
					ginsum += sir[1];
					binsum += sir[2];
				} else {
					routsum += sir[0];
					goutsum += sir[1];
					boutsum += sir[2];
				}
				if (i < hm) {
					yp += w;
				}
			}
			yi = x;
			stackpointer = radius;
			for (y = 0; y < h; y++) {
				pix[yi] = (0xff000000 & pix[yi]) | (dv[rsum] << 16)
				| (dv[gsum] << 8) | dv[bsum];
				rsum -= routsum;
				gsum -= goutsum;
				bsum -= boutsum;
				stackstart = stackpointer - radius + div;
				sir = stack[stackstart % div];
				routsum -= sir[0];
				goutsum -= sir[1];
				boutsum -= sir[2];
				if (x == 0) {
					vmin[y] = Math.min(y + r1, hm) * w;
				}
				p = x + vmin[y];
				sir[0] = r[p];
				sir[1] = g[p];
				sir[2] = b[p];
				rinsum += sir[0];
				ginsum += sir[1];
				binsum += sir[2];
				rsum += rinsum;
				gsum += ginsum;
				bsum += binsum;
				stackpointer = (stackpointer + 1) % div;
				sir = stack[stackpointer];
				routsum += sir[0];
				goutsum += sir[1];
				boutsum += sir[2];
				rinsum -= sir[0];
				ginsum -= sir[1];
				binsum -= sir[2];
				yi += w;
			}
		}
		return pix;
	}
}
