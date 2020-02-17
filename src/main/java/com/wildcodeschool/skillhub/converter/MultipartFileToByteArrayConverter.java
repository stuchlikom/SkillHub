package com.wildcodeschool.skillhub.converter;

import java.io.IOException;

import org.springframework.core.convert.converter.Converter;
import org.springframework.web.multipart.MultipartFile;

public class MultipartFileToByteArrayConverter implements Converter<MultipartFile, byte[]>{

	@Override
	public byte[] convert(MultipartFile source) {
	
		try {
			return source.getBytes();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			throw new RuntimeException (e.getMessage());
			
		}
	}

}
