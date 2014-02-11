package org.tcsaroundtheworld.common.server.db;

import java.util.Collections;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.persistence.Id;

import org.tcsaroundtheworld.common.server.Picture;
import org.tcsaroundtheworld.common.shared.PictureReference;


import com.googlecode.objectify.annotation.Unindexed;

public class PictureEntity {

	@Id
	String id;

	@Unindexed
	byte[] picture;
	@Unindexed
	String contentType;

	public static PictureEntity fromMemCache(PictureReference ref) {
		PictureEntity pe = new PictureEntity();
		try {
			Cache cache = CacheManager.getInstance().getCacheFactory().createCache(Collections.emptyMap());
			Picture pic = (Picture) cache.get(ref.getId());
			pe.id = pic.getId();
			pe.picture = pic.getImg();
			pe.contentType = pic.getContentType();
			return pe;
		} catch( Exception e ) {
			throw new RuntimeException("Failed to retrieve picture from MemCache with id="+ref.getId()+" : "+e.getMessage(),e );
		}
	}

	public Picture toPicture() {
		return new Picture(id, picture, contentType);
	}

	@Override
	public String toString() {
		return "PictureId="+id+", Content-Type="+contentType+" Content-Length="+picture.length;
	}

}
