package org.tcsaroundtheworld.common.server;

import java.io.IOException;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.tcsaroundtheworld.common.server.db.DAO;

import com.google.appengine.api.images.Image;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.api.images.Transform;

public class PictureServlet extends HttpServlet {

	Logger log = Logger.getLogger(PictureServlet.class.getName());

	private static final long serialVersionUID = 1L;

	private Cache cache;

	ImagesService imagesService = ImagesServiceFactory.getImagesService();

	DAO dao = new DAO();

	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String ref = req.getParameter("q");
		String sizeStr = req.getParameter("s");

		if( ref == null || ref.length() == 0 || !ref.matches("[a-z\\d-]+") ) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

		String sizedRef = ref;

		if( sizeStr != null && sizeStr.matches("\\d+") ) {
			sizedRef = ref+"-s"+sizeStr;
		}

		try {
			cache = CacheManager.getInstance().getCacheFactory().createCache(Collections.emptyMap());

			Picture sizedPic = (Picture) cache.get(sizedRef);
			if( sizedPic == null ) {
				Picture pic = (Picture) cache.get(ref);
				if( pic == null ) {
					pic = dao.getPicture(ref);
					// put it in memcache for next time
					// cache.put( ref, pic );
					if( pic == null ) {
						throw new NullPointerException("pic");
					}
				}

				int size = Integer.parseInt(sizeStr);
				Image image = ImagesServiceFactory.makeImage(pic.getImg());
				if( image.getWidth() > image.getHeight() ) {
					double aspect = new Double(image.getWidth()) / new Double(image.getHeight());
					Double height = new Double(size) * aspect;
					Transform transform = ImagesServiceFactory.makeResize(height.intValue(), size);
					Image transformed = imagesService.applyTransform(transform, image);
					int transformedWidth = transformed.getWidth();
					double left_x = (transformedWidth - size) / 2;
					transform = ImagesServiceFactory.makeCrop(left_x/transformedWidth, 0, (left_x + size)/transformedWidth, 1);
					transformed = imagesService.applyTransform(transform, transformed);
					sizedPic = new Picture(sizedRef, transformed.getImageData(), pic.getContentType());
				} else if( image.getHeight() > image.getWidth() ){
					double aspect = new Double(image.getHeight()) / new Double(image.getWidth()) ;
					Double width = new Double(size) * aspect;
					Transform transform = ImagesServiceFactory.makeResize(size, width.intValue());
					Image transformed = imagesService.applyTransform(transform, image);
					int transformedHeight  = transformed.getHeight();
					double right_x = (transformedHeight  - size) / 2;
					transform = ImagesServiceFactory.makeCrop(0, right_x/transformedHeight , 1, (right_x + size)/transformedHeight );
					transformed = imagesService.applyTransform(transform, transformed);
					sizedPic = new Picture(sizedRef, transformed.getImageData(), pic.getContentType());
				} else {
					Transform transform = ImagesServiceFactory.makeResize(size, size);
					Image transformed = imagesService.applyTransform(transform, image);
					sizedPic = new Picture(sizedRef, transformed.getImageData(), pic.getContentType());
				}
				cache.put( sizedRef, sizedPic );
			}


			resp.setContentType(sizedPic.getContentType());
			resp.getOutputStream().write(sizedPic.getImg());

			resp.setStatus(HttpServletResponse.SC_OK);

		} catch( Exception e ) {
			log.log(Level.WARNING, "Failed to serve image with id="+ref+": "+e.getMessage(), e);
			resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}

	}

}
