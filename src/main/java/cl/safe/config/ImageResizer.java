package cl.safe.config;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import cl.safe.controller.AsisteniciasController;
import cl.safe.service.FileStorageService;

/**
 * This program demonstrates how to resize an image.
 *
 * @author www.codejava.net
 *
 */
@Component
public class ImageResizer {

	private static final Logger logger = LoggerFactory.getLogger(ImageResizer.class);

	@Autowired
	HttpServletRequest request;

	@Autowired
	FileStorageService fileStorageService;

	/**
	 * Resizes an image to a absolute width and height (the image may not be
	 * proportional)
	 * 
	 * @param inputImagePath  Path of the original image
	 * @param outputImagePath Path to save the resized image
	 * @param scaledWidth     absolute width in pixels
	 * @param scaledHeight    absolute height in pixels
	 * @throws IOException
	 */
	public static void resize(String inputImagePath, String outputImagePath, int scaledWidth, int scaledHeight)
			throws IOException {
		// reads input image
		File inputFile = new File(inputImagePath);
		BufferedImage inputImage = ImageIO.read(inputFile);

		// creates output image
		BufferedImage outputImage = new BufferedImage(scaledWidth, scaledHeight, inputImage.getType());

		// scales the input image to the output image
		Graphics2D g2d = outputImage.createGraphics();
		g2d.drawImage(inputImage, 0, 0, scaledWidth, scaledHeight, null);
		g2d.dispose();

		// extracts extension of output file
		String formatName = outputImagePath.substring(outputImagePath.lastIndexOf(".") + 1);

		// writes to output file
		ImageIO.write(outputImage, formatName, new File(outputImagePath));
	}

	public String getFilePath (String fileName) throws IOException {
		Resource resource = fileStorageService.loadFileAsResource(fileName);
		return resource.getFile().getAbsolutePath().toString();
	}
	/**
	 * Resizes an image by a percentage of original size (proportional).
	 * 
	 * @param inputImagePath  Path of the original image
	 * @param outputImagePath Path to save the resized image
	 * @param percent         a double number specifies percentage of the output
	 *                        image over the input image.
	 * @throws IOException
	 */
	public static void resize(String inputImagePath, String outputImagePath, double percent) throws IOException {
		File inputFile = new File(inputImagePath);
		BufferedImage inputImage = ImageIO.read(inputFile);
		int scaledWidth = (int) (inputImage.getWidth() * percent);
		int scaledHeight = (int) (inputImage.getHeight() * percent);
		resize(inputImagePath, outputImagePath, scaledWidth, scaledHeight);
	}

	public String fileNameToBase64(String fileName) throws IOException {
		// /{fileName:.+}@PathVariable String fileName,
		// Load file as Resour ce

		System.out.println(fileName);
		Resource resource = fileStorageService.loadFileAsResource(fileName);

		// Try to determine file's content type
		String contentType = null;
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		} catch (IOException ex) {
			logger.info("Could not determine file type.");
		}

		// Fallback to the default content type if type could not be determined
		if (contentType == null) {
			contentType = "application/octet-stream";
		}

		byte[] imageBytes = loadFile(resource.getFile());
		String imageStr = Base64.encodeBase64String(imageBytes);

		Path path = new File(resource.getFilename()).toPath();
		String mimeType = Files.probeContentType(path);

		String base64 = "data:" + mimeType + ";base64," + imageStr;

		System.out.println(base64);

		return base64;

		/*
		 * System.out.println(contentType); return ResponseEntity.ok()
		 * .contentType(MediaType.parseMediaType("image/jpg"))
		 * .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" +
		 * resource.getFilename() + "\"") .body(resource);
		 */
	}

	private static byte[] loadFile(File file) throws IOException {
		InputStream is = new FileInputStream(file);

		long length = file.length();
		if (length > Integer.MAX_VALUE) {
			// File is too large
		}
		byte[] bytes = new byte[(int) length];

		int offset = 0;
		int numRead = 0;
		while (offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
			offset += numRead;
		}

		if (offset < bytes.length) {
			throw new IOException("Could not completely read file " + file.getName());
		}

		is.close();
		return bytes;
	}

}