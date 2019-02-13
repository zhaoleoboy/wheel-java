package com.leo.wheel.common.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;

import org.jodconverter.OfficeDocumentConverter;
import org.jodconverter.office.LocalOfficeManager;
import org.jodconverter.office.OfficeException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.leo.wheel.utils.FileUtils;
import com.leo.wheel.utils.RegexUtils;

/**
 * 文档相关操作的service
 * 
 * https://blog.csdn.net/xiaqingxue930914/article/details/81121581
 * 
 * 1，JODConverter + openoffice/libreoffice，
 * 程序利用 jodconverter 去连接openoffice(或libreoffice) 服务，实现转换。类似于 MySql 和 数据库连接池。 
 * 
 * 2，引入 aspose相关jar包，调用API进行转换，相较于jodconverter： 
 * a、收费;
 * b、转换速度快，且不用在服务器上额外安装软件;
 * c、不同文档的转换需要用到不同的jar包
 * 
 * @author leo
 *
 */
@Service
public class DocService {

	@Value("${file.viewer}")
	private String folder;// 读取applicaton.properties配置文件，获取文件上传的目录

	/**
	 * 把word类型的文件或txt文件转换为pdf文件
	 * @param sourceFilePath
	 * @return
	 * @throws OfficeException
	 * @throws IOException 
	 * @throws DocumentException 
	 */
	public String preview(String sourceFilePath) throws OfficeException {
		String result = null;
		String fileExt = FileUtils.getFileExt(sourceFilePath);
		// TODO 修改为全局唯一ID
		String targetFilePath = folder + System.currentTimeMillis() + ".pdf";
		if ("pdf".equals(fileExt) || "jpg".equals(fileExt)) {
			try {
				result = FileUtils.downloadFile(sourceFilePath, targetFilePath);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if ("doc".equals(fileExt) || "docx".equals(fileExt)) {
			result = convertWord2Pdf(sourceFilePath, targetFilePath);
		} else if ("txt".equals(fileExt)) {
			result = convertTxt2Pdf(sourceFilePath, targetFilePath);
		} else if (RegexUtils.checkImg(sourceFilePath)) {
			try {
				result = FileUtils.downloadFile(sourceFilePath, folder + System.currentTimeMillis() + ".jpg");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 转换word文件为pdf
	 * @param sourceFilePath
	 * @throws OfficeException
	 */
	public String convertWord2Pdf(String sourceFilePath, String targetFilePath) throws OfficeException {
		long start = System.currentTimeMillis();
		String officeHome = getOfficeHome();
		LocalOfficeManager officeManager = LocalOfficeManager.builder().portNumbers(8100).officeHome(officeHome)
				.taskExecutionTimeout(5 * 60 * 100L) // 设置任务超时时长
				.taskQueueTimeout(1000 * 1000 * 60 * 24L) // 设置任务队列超时为24小时
				.maxTasksPerProcess(10).build();
		if (officeManager == null)
			throw new RuntimeException("初始化OpenOffice失败");
		officeManager.start();
		OfficeDocumentConverter converter = new OfficeDocumentConverter(officeManager);
		converter.convert(new File(sourceFilePath), new File(targetFilePath));
		officeManager.stop();
		// TODO 优化多线程的场景
		// 或者 JodConverter.convert(new File(sourceFilePath)).to(new
		// File(targetFilePath)).execute();
		long end = System.currentTimeMillis();
		System.out.println(String.format("word文档转换为pdf文件花费的时间为：%s", end - start));
		return targetFilePath;
	}

	/**
	 * TODO 在线转换！
	 * @param sourceFilePath
	 * @param targetFilePath
	 * @throws OfficeException
	 */
	public void onlineConvertWord2Pdf(String sourceFilePath, String targetFilePath) throws OfficeException {

	}

	/**
	 * 转换TXT文件为pdf
	 * @param sourceFilePath
	 * @param targetFilePath
	 * @return
	 */
	public String convertTxt2Pdf(String sourceFilePath, String targetFilePath) {
		String result = null;
		try {
			long start = System.currentTimeMillis();
			BaseFont bfChinese = getBaseFont();
			Font FontChinese = new Font(bfChinese, 12, Font.NORMAL);
			FileOutputStream out = new FileOutputStream(targetFilePath);
			Rectangle rect = new Rectangle(PageSize.A4.rotate());
			Document doc = new Document(rect);
			PdfWriter.getInstance(doc, out);
			doc.open();
			Paragraph p = new Paragraph();
			p.setFont(FontChinese);
			BufferedReader read = new BufferedReader(new FileReader(sourceFilePath));
			String line = read.readLine();
			while (line != null) {
				p.add(line + "\n");
				line = read.readLine();
			}
			read.close();
			doc.add(p);
			doc.close();
			result = targetFilePath;
			long end = System.currentTimeMillis();
			System.out.println(String.format("txt文档转换为pdf文件花费的时间为：%s", end - start));
		} catch (DocumentException | IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 获取OpenOffice的安装目录，注意openOffice的安装目录必须与这里返回的目录相对应！
	 * @return
	 */
	public String getOfficeHome() {
		String osName = System.getProperty("os.name");
		System.out.println("操作系统名称:" + osName);
		if (Pattern.matches("Linux.*", osName)) {
			return "/opt/openoffice.org4";
		} else if (Pattern.matches("Windows.*", osName)) {
			return "C:\\Program Files (x86)\\OpenOffice 4";
		} else if (Pattern.matches("Mac.*", osName)) {
			return "/Application/OpenOffice.org.app/Contents";
		}
		return null;
	}

	/**
	 * 根据当前操作系统获取baseFont
	 * @return
	 * @throws DocumentException
	 * @throws IOException
	 */
	public static BaseFont getBaseFont() throws DocumentException, IOException {
		String osName = System.getProperty("os.name");
		System.out.println("操作系统名称:" + osName);
		if (Pattern.matches("Linux.*", osName)) {
			return BaseFont.createFont("/usr/share/fonts/fallback/simhei.ttf", BaseFont.IDENTITY_H,
					BaseFont.NOT_EMBEDDED);
		} else if (Pattern.matches("Windows.*", osName)) {
			return BaseFont.createFont("C:\\Windows\\Fonts\\simhei.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
		}
		return null;
	}

	public static void main(String[] args) throws OfficeException, DocumentException, IOException {
		DocService docService = new DocService();
		docService.preview("test.txt");
	}
}
