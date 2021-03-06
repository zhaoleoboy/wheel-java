package com.leo.wheel.common.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.jodconverter.OfficeDocumentConverter;
import org.jodconverter.office.LocalOfficeManager;
import org.jodconverter.office.OfficeException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.itextpdf.text.Chunk;
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
import com.leo.wheel.utils.UuidUtils;

/**
 * 	文档相关操作的service，使用JodConverter
 * 
 * https://blog.csdn.net/xiaqingxue930914/article/details/81121581
 * 
 * 1，JODConverter + openoffice/libreoffice，
 * 	程序利用 jodconverter 去连接openoffice(或libreoffice) 服务，实现转换。类似于 MySql 和 数据库连接池。 
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

	@Value("${file.upload}")
	private String uploader;// 读取applicaton.properties配置文件，获取文件上传的目录

	// 根据操作系统获取OpenOffice安装路径
	@Value("${office.linux.home}")
	private String linuxOfficeHome;

	@Value("${office.windows.home}")
	private String winOfficeHome;

	@Value("${office.mac.home}")
	private String macOfficeHome;

	/**
	 * 	把word类型的文件或txt文件转换为pdf文件
	 * @param sourceFilePath
	 * @return
	 * @throws OfficeException
	 * @throws FileNotFoundException 
	 * @throws IOException 
	 * @throws DocumentException 
	 */
	public String preview(String sourceFilePath) throws OfficeException, FileNotFoundException {
		String result = null;
		if (StringUtils.isBlank(sourceFilePath)) {
			throw new FileNotFoundException("文件名称异常！");
		}
		String fileExt = FileUtils.getFileExt(sourceFilePath);
		sourceFilePath = uploader + sourceFilePath;// 获取绝对路径
		if (!FileUtils.isFileExists(sourceFilePath)) {
			throw new FileNotFoundException("服务器上不存在该文件，请联系管理员！");
		}
		String targetFilePath = folder + UuidUtils.getUuidByJug(false) + ".pdf";
		if ("pdf".equals(fileExt)) {
			// pdf文件直接下载
			try {
				result = FileUtils.copyFile(sourceFilePath, targetFilePath);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if ("doc".equals(fileExt) || "docx".equals(fileExt)) {
			// WORD
			result = convertWord2Pdf(sourceFilePath, targetFilePath);
		} else if ("xlsx".equals(fileExt)) {
			// EXCEL
			result = convertExcel2Pdf(sourceFilePath, targetFilePath);
		} else if ("pptx".equals(fileExt) || "ppt".equals(fileExt)) {
			// PPT
			result = convertPpt2Pdf(sourceFilePath, targetFilePath);
		} else if ("txt".equals(fileExt)) {
			// TXT
			result = convertTxt2Pdf(sourceFilePath, targetFilePath);
		} else if (RegexUtils.checkImg(sourceFilePath)) {
			// 图片，路径匹配模式有问题
			try {
				result = FileUtils.copyFile(sourceFilePath,
						folder + System.currentTimeMillis() + "." + fileExt);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 	转换word文件为pdf
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
	 * 	转换PPT文件为pdf
	 * @param sourceFilePath
	 * @throws OfficeException
	 */
	public String convertPpt2Pdf(String sourceFilePath, String targetFilePath) throws OfficeException {
		return convertWord2Pdf(sourceFilePath, targetFilePath);
	}

	/**
	 * 	转换Excel文件为pdf，Excel转换为PDF文件时，需要注意以下事项：
	 * 1，源Excel文件的列过多时，转换的时候会把每行截取到多页中，bug？
	 * 
	 * @param sourceFilePath
	 * @throws OfficeException
	 */
	public String convertExcel2Pdf(String sourceFilePath, String targetFilePath) throws OfficeException {
		return convertWord2Pdf(sourceFilePath, targetFilePath);
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
	 * 	转换TXT文件为pdf
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
			doc.add(new Chunk("")); // << this will do the trick. 否则当txt文件为空时，报异常“the document has no pages”
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
		if (RegexUtils.isLinuxOS(osName)) {
			return linuxOfficeHome;
		} else if (RegexUtils.isWindowOS(osName)) {
			return winOfficeHome;
		} else if (RegexUtils.isMacOS(osName)) {
			return macOfficeHome;
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
