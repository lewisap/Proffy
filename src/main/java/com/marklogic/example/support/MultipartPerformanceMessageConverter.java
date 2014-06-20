package com.marklogic.example.support;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import javax.xml.bind.JAXB;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by phoehne on 6/19/14.
 */
public class MultipartPerformanceMessageConverter implements HttpMessageConverter<Object> {

  @Override
  public boolean canRead(Class<?> aClass, MediaType mediaType) {
    return (mediaType != null && "multipart".equals(mediaType.getType()) && "mixed".equals(mediaType.getSubtype()));
  }

  @Override
  public boolean canWrite(Class<?> aClass, MediaType mediaType) {
    return (mediaType != null && "multipart".equals(mediaType.getType()) && "mixed".equals(mediaType.getSubtype()));
  }

  @Override
  public List<MediaType> getSupportedMediaTypes() {
    List<MediaType> result = new LinkedList<>();
    result.add(new MediaType("multipart", "mixed"));
    return result;
  }

  @Override
  public Object read(Class<?> aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
    Logger logger = Logger.getAnonymousLogger();
    BufferedReader br = new BufferedReader(new InputStreamReader(httpInputMessage.getBody()));

    PartParser parser = new PartParser();

    String line = br.readLine();
    while(line != null) {
      parser.appendFragment(line);
      line = br.readLine();
    }

    Object result = null;
    for (int i = 0; i < parser.partCount(); i++) {
      if (parser.contentType(i).equals("application/xml")) {
        ByteArrayInputStream bais = new ByteArrayInputStream(parser.partAtIndex(i).getBytes());
        result = JAXB.unmarshal(bais, aClass);
        bais.close();
      }
    }
    return result;
  }

  @Override
  public void write(Object o, MediaType mediaType, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {
    throw new HttpMessageNotReadableException("Unable to write multipart/mixed type");
  }
}
