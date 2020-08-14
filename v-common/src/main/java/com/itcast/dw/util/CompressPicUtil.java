/**
 *
 * @date 2020年4月22日
 */
package com.itcast.dw.util;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Iterator;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;

import org.apache.commons.lang3.StringUtils;

/**
 * 一句话功能简述
 *
 * @author liudawei
 */

public class CompressPicUtil {

	public static void main(String[] args) throws IOException {
		String stpa = "E:\\cesi\\download.jpg";
//		compressPic(stpa, st1);

		BufferedImage image = ImageIO.read(new FileInputStream(stpa));

		System.out.println(image.getData().getDataBuffer().getSize());
		byte[] a = bufferedImageTobytes(image,0.2f);
		System.out.println(a.length);
		System.out.println(Base64.getEncoder().encodeToString(a));
	}


	/**
     *
     * 自己设置压缩质量来把图片压缩成byte[]
     *
     * @param image
     *            压缩源图片
     * @param quality
     *            压缩质量，在0-1之间，
     * @return 返回的字节数组
     */
    public static byte[] bufferedImageTobytes(BufferedImage image, float quality) {
        // 如果图片空，返回空
        /*if (image == null) {
            return null;
        } */
        // 得到指定Format图片的writer
        Iterator<ImageWriter> iter = ImageIO
                .getImageWritersByFormatName("jpg");// 得到迭代器
        ImageWriter writer = (ImageWriter) iter.next(); // 得到writer

        // 得到指定writer的输出参数设置(ImageWriteParam )
        ImageWriteParam iwp = writer.getDefaultWriteParam();
        iwp.setCompressionMode(ImageWriteParam.MODE_EXPLICIT); // 设置可否压缩
        iwp.setCompressionQuality(quality); // 设置压缩质量参数

        iwp.setProgressiveMode(ImageWriteParam.MODE_DISABLED);

        ColorModel colorModel = image.getColorModel();
        // 指定压缩时使用的色彩模式
        iwp.setDestinationType(new javax.imageio.ImageTypeSpecifier(colorModel,
                colorModel.createCompatibleSampleModel(16, 16)));

        // 开始打包图片，写入byte[]
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(); // 取得内存输出流
        IIOImage iIamge = new IIOImage(image, null, null);
        try {
            // 此处因为ImageWriter中用来接收write信息的output要求必须是ImageOutput
            // 通过ImageIo中的静态方法，得到byteArrayOutputStream的ImageOutput
            writer.setOutput(ImageIO
                    .createImageOutputStream(byteArrayOutputStream));
            writer.write(null, iIamge, iwp);

        } catch (IOException e) {
            System.out.println("write errro");
            e.printStackTrace();
        }
        return byteArrayOutputStream.toByteArray();
    }

}
