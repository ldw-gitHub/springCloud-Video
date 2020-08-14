package com.itcast.dw.test.exam;

import com.csvreader.CsvReader;
import com.itcast.dw.util.CompressPicUtil;
import org.apache.poi.ss.formula.functions.T;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description
 * @author: liudawei
 * @date: 2020/5/13 9:58
 */
public class ReadCsv {

  public static void main(String[] args) throws IOException {
    String[] image = {"174班黄伟娴", "183班郭芯彤", "八年级照片13张赵劲康", "电商20191班张佳琼", "电子商务20181班照片周玉莲",
      "饭店182班照片王菊平", "饭店20192史资红", "饭店20193班李双双", "饭店管理20191班冯妮娜", "九年级学生22人照片 孔凡强",
      "七年级叶成龙", "汽修20181班毛武毅", "汽修20191班申逸剑","饭店20181班学生学生电子照片屈娇（25人）"};

    String basePath = "E:\\全校学生人脸信息\\new";
    while (true) {
      int a = 0;
      for (String path : image) {
        File file = new File(basePath + File.separator + path);
        File[] files = file.listFiles();
        for (File im : files) {

          BufferedImage bufferedImage = ImageIO.read(new FileInputStream(im));

          if (im.length() / 1024 > 200) {
            a++;
            byte[] bytes = CompressPicUtil.bufferedImageTobytes(bufferedImage, 0.0002f);
            System.out.println(im.getAbsolutePath());
            im.delete();
            FileOutputStream out = new FileOutputStream(new File(im.getAbsolutePath()));
            out.write(bytes);
          }
        }
      }

      System.out.println("a ========== " + a);

      if(a == 0){
        break;
      }
    }

  }

/*    public static void main(String[] args) {
*//*       String filePath = "C:\\Users\\20190322\\Desktop\\育新\\学生\\PersonInfo_20200513092836_174.csv";
    String imagePath = "E:\\全校学生人脸信息\\174班黄伟娴";
    String newFilePath = "E:\\全校学生人脸信息\\new\\174班黄伟娴\\";*//*

    String basePath = "E:\\全校学生人脸信息\\";
//    String fileName = "电商20191班张佳琼";
//    String fileName = "电子商务20181班照片周玉莲";
//    String fileName = "饭店182班照片王菊平";
//    String fileName = "饭店20192史资红";
//    String fileName = "饭店20193班李双双";
//    String fileName = "饭店管理20191班冯妮娜";
//    String fileName = "九年级学生22人照片 孔凡强";
//    String fileName = "七年级叶成龙";
//    String fileName = "汽修20181班毛武毅";
//    String fileName = "汽修20191班申逸剑";
    String fileName = "饭店20181班学生学生电子照片屈娇（25人）";
    String newFilePath = basePath + "new"  + File.separator + fileName;

    File foudler = new File(newFilePath);
    if (!foudler.exists()) {
      foudler.mkdirs();
    }

    List<Map<String, String>> objects = readFromCSV(new Character(','), basePath + fileName + ".csv");

    System.out.println(newFilePath);

    File imageFile = new File(basePath + fileName);
    File[] files = imageFile.listFiles();
    for (File file : files) {
      String name = file.getName().substring(0, file.getName().lastIndexOf("."));
      for (Map<String, String> map : objects) {
        if (name.contains(map.get("name"))) {
          file.renameTo(new File(newFilePath + File.separator + map.get("name") + "_" + map.get("no") + ".jpg"));
        }
      }
    }


  }*/

  /**
   * Read from CSV
   *
   * @param separator 分隔符
   * @param filePath  文件路径
   * @return
   */
  public static List<Map<String, String>> readFromCSV(Character separator, String filePath) {
    CsvReader reader = null;
    List<Map<String, String>> result = new ArrayList<>();
    try {
      //如果生产文件乱码，windows下用gbk，linux用UTF-8
      reader = new CsvReader(filePath, separator, Charset.forName("GBK"));

      // 读取标题
      reader.readHeaders();
      // 逐条读取记录，直至读完
      while (reader.readRecord()) {
        Map<String, String> rtMap = new HashMap<>();
        rtMap.put("name", reader.get("*姓名").replaceAll("\\s*", ""));
        rtMap.put("no", reader.get("*学工号").replaceAll("\\s*", ""));
        //读取第一例
//        System.out.println(reader.get(0));
//        System.out.println(reader.get("*姓名").replaceAll("\\s*", ""));
        //读取指定名字的列

        result.add(rtMap);
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (null != reader) {
        reader.close();
      }
    }

    return result;
  }
}
