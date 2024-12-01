package web.URLShorternService.service.imp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.URLShorternService.entity.Url;
import web.URLShorternService.repository.UrlRepository;
import web.URLShorternService.service.UrlService;
import web.URLShorternService.thirdParty.HashValueGenerator;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

@Service
public class UrlServiceImpl implements UrlService {
    @Value("${BASE_URL}")
    private String baseURI;

    private final UrlRepository urlRepository;
    private final HashValueGenerator hashValueGenerator;


    public UrlServiceImpl(UrlRepository urlRepository, HashValueGenerator hashValueGenerator) {
        this.urlRepository = urlRepository;
        this.hashValueGenerator = hashValueGenerator;
    }

    @Override
    @Transactional
    public String createShortUrl(String longUrl) {

        Url url = new Url(
                longUrl,
                hashValueGenerator.generateHashValue()
        );


        Url savedURL = urlRepository.save(url);
        return baseURI + savedURL.getShortUrl();
    }


    @Override
    public URI resolveShortURL(String id) throws MalformedURLException, URISyntaxException {

        Url url = urlRepository.findByShortUrl(id);

        return URI.create(url.getLongUrl());
    }
}
