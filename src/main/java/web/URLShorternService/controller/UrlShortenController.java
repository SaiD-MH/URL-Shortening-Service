package web.URLShorternService.controller;

import org.hibernate.query.criteria.JpaConflictUpdateAction;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.URLShorternService.service.UrlService;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

@RestController
@RequestMapping("")
public class UrlShortenController {

    private final UrlService urlService;

    public UrlShortenController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping("/api/data/short")
    public String createShortUrl(@RequestParam("longUrl") String longUrl) {


        return urlService.createShortUrl(longUrl);
    }


    @GetMapping("/{url-id}")
    public ResponseEntity<URI> redirectToLongUrl(@PathVariable("url-id") String id) throws MalformedURLException, URISyntaxException {

        HttpHeaders httpHeaders = new HttpHeaders();
        URI uri =urlService.resolveShortURL(id);
        httpHeaders.setLocation(uri);

        System.out.println(uri.toString());
        return new ResponseEntity<>( httpHeaders ,HttpStatus.TEMPORARY_REDIRECT );
    }


}
