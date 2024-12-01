package web.URLShorternService.service;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public interface UrlService {

    String  createShortUrl(String longUrl);
    URI resolveShortURL(String id) throws MalformedURLException, URISyntaxException;
}
