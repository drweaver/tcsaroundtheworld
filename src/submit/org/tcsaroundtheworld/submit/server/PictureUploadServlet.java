package org.tcsaroundtheworld.submit.server;


import gwtupload.server.MemoryFileItemFactory;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.tcsaroundtheworld.common.server.Picture;

import com.google.appengine.api.images.Image;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.api.images.Transform;

public class PictureUploadServlet extends HttpServlet {

	static final int MEMCACHE_LIMIT = 1023 * 1024;

	private static Logger log = Logger.getLogger(PictureUploadServlet.class.getName());

	private static final long serialVersionUID = 1L;
	private final ImagesService imagesService = ImagesServiceFactory.getImagesService();

	private Cache cache;

	@SuppressWarnings("unchecked")
	@Override
	public void doPost(final HttpServletRequest request, final HttpServletResponse response)  throws ServletException, IOException {

		String key = "";
		String status = "Unknown";

		final FileItemFactory fileItemFactory = new MemoryFileItemFactory();
		final ServletFileUpload uploadHandler = new ServletFileUpload(fileItemFactory);
		try {
			cache = CacheManager.getInstance().getCacheFactory().createCache(Collections.emptyMap());
			/*
			 * Parse the request
			 */
			@SuppressWarnings("rawtypes")
			final List items = uploadHandler.parseRequest(request);
			@SuppressWarnings("rawtypes")
			final Iterator itr = items.iterator();
			if( !itr.hasNext() ) {
				throw new RuntimeException("No files were received");
			}
			final FileItem item = (FileItem) itr.next();

			if(item.isFormField()) {
				throw new RuntimeException("Recieved form field instead of file");
			}
			if(!item.getContentType().startsWith("image/")) {
				throw new RuntimeException("Invalid file type, must be an image");
			}
			log.info("Field Name = "+item.getFieldName()+", Field Name = "+item.getFieldName()+
					", Content type = "+item.getContentType()+", File Size = "+item.getSize());

			byte[] data = Arrays.copyOf(item.get(), Long.valueOf( item.getSize() ).intValue());
			while( data.length > MEMCACHE_LIMIT ) {
				log.info("Image too large, resizing... (size="+data.length+")");
				Image image = ImagesServiceFactory.makeImage( data );
				final Transform transform = ImagesServiceFactory.makeResize(image.getWidth()/2, image.getHeight()/2 );
				image = imagesService.applyTransform(transform, image);
				data = image.getImageData();
			}

			key = UUID.randomUUID().toString();

			cache.put( key, new Picture(key, data, item.getContentType()) );

			log.info("Memcache key = " + key + ", final size = " + data.length );


			status = "OK";
		}catch(final FileUploadException ex) {
			status = "Error encountered while parsing the request"+ex.getMessage();
			log("Error encountered while parsing the request",ex);
		} catch(final Exception ex) {
			status = "Error encountered while uploading file"+ex.getMessage();
			log("Error encountered while uploading file",ex);
		}

		response.setContentType("text/html");
		response.getWriter().write("{ \"status\": \""+status+"\", \"key\": \""+key+"\" }");
		response.getWriter().flush();


	}

	@Override
	protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {

		//		final String blobKeyString = req.getParameter("blobkey");
		//
		//		final BlobKey blobKey = new BlobKey(blobKeyString);

		log.info("Handling picture upload response XML");

		//		resp.setContentType("text/xml");

		final String status = req.getParameter("status");
		final String key = req.getParameter("key");
		final String contentType = req.getParameter("content-type");

		final ServletOutputStream outputStream = resp.getOutputStream();
		outputStream.print("<?xml version=\"1.0\" ?>");
		outputStream.print("<picture-upload-response>");
		outputStream.print("<status>"+status+"</status>");
		if( status.equals("OK") ) {
			outputStream.print("<key>"+key+"</key>");
			outputStream.print("<content-type>"+contentType+"</content-type>");
		}
		//		outputStream.print("<blobkey>"+blobKeyString+"</blobkey>");
		//		outputStream.print("<imgurl>"+imagesService.getServingUrl(blobKey)+"</imgurl>");
		outputStream.print("</picture-upload-response>");

		outputStream.flush();

		resp.setStatus(HttpServletResponse.SC_OK);
	}
}
