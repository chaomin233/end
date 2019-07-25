package com.abcd.pay.extension;

import static feign.Util.RETRY_AFTER;
import static feign.Util.checkNotNull;
import static java.lang.String.format;
import static java.util.Locale.US;
import static java.util.concurrent.TimeUnit.SECONDS;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.deepexi.util.config.Payload;
import com.deepexi.util.extension.ApplicationException;
import com.fasterxml.jackson.databind.ObjectMapper;

import feign.Response;
import feign.RetryableException;
import feign.Util;
import feign.codec.ErrorDecoder;

/**
 * 自定义ErrorDecoder，用来解决feign调用默认抛出的是FeignExcetpion异常的问题
 * @author yangxi
 *
 */
@Component
public class MyErrorDecoder implements ErrorDecoder {

	private final RetryAfterDecoder retryAfterDecoder = new RetryAfterDecoder();
	
	@Autowired
	private ObjectMapper objectMapper;

    @Override
    public Exception decode(String methodKey, Response response) {
      ApplicationException exception = this.errorStatus(methodKey, response, objectMapper);
      Date retryAfter = retryAfterDecoder.apply(firstOrNull(response.headers(), RETRY_AFTER));
      if (retryAfter != null) {
        return new RetryableException(exception.getMessage(), exception, retryAfter);
      }
      return exception;
    }
    
    private ApplicationException errorStatus(String methodKey, Response response, ObjectMapper objectMapper) {
		String message = format("status %s reading %s", response.status(), methodKey);
		String code = response.status() + "";
		try {
			if (response.body() != null) {
				String body = Util.toString(response.body().asReader());
				Payload<?> payload = objectMapper.readValue(body, Payload.class);
				code = payload.getCode();
				message = payload.getMsg();
			}
		} catch (IOException ignored) { // NOPMD
		}
		ApplicationException applicationException = new ApplicationException(message);
		applicationException.setCode(code);
		return applicationException;
	}

    private <T> T firstOrNull(Map<String, Collection<T>> map, String key) {
      if (map.containsKey(key) && !map.get(key).isEmpty()) {
        return map.get(key).iterator().next();
      }
      return null;
    }

	/**
	 * Decodes a {@link feign.Util#RETRY_AFTER} header into an absolute date, if possible. <br> See <a
	 * href="https://tools.ietf.org/html/rfc2616#section-14.37">Retry-After format</a>
	 */
	static class RetryAfterDecoder {

	  static final DateFormat
	      RFC822_FORMAT =
	      new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'", US);
	  private final DateFormat rfc822Format;

	  RetryAfterDecoder() {
	    this(RFC822_FORMAT);
	  }

	  RetryAfterDecoder(DateFormat rfc822Format) {
	    this.rfc822Format = checkNotNull(rfc822Format, "rfc822Format");
	  }

	  protected long currentTimeMillis() {
	    return System.currentTimeMillis();
	  }

	  /**
	   * returns a date that corresponds to the first time a request can be retried.
	   *
	   * @param retryAfter String in <a href="https://tools.ietf.org/html/rfc2616#section-14.37"
	   *                   >Retry-After format</a>
	   */
	  public Date apply(String retryAfter) {
	    if (retryAfter == null) {
	      return null;
	    }
	    if (retryAfter.matches("^[0-9]+$")) {
	      long deltaMillis = SECONDS.toMillis(Long.parseLong(retryAfter));
	      return new Date(currentTimeMillis() + deltaMillis);
	    }
	    synchronized (rfc822Format) {
	      try {
	        return rfc822Format.parse(retryAfter);
	      } catch (ParseException ignored) {
	        return null;
	      }
	    }
	  }
	}
}