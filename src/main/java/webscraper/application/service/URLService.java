package webscraper.application.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class URLService {

    private URIBuilder uriBuilder;
    private FormService formService;

    @Autowired
    public URLService(FormService formService) throws URISyntaxException {
        this.formService = formService;
        this.uriBuilder = new URIBuilder("https://www.olx.pl");
    }

    public void buildURL() throws URISyntaxException {
        List<String> pathSegments = new ArrayList<>();
        pathSegments.add("nieruchomosci");
        pathSegments.add("mieszkania");
        pathSegments.add("wynajem");
        pathSegments.add(formService.getForm().getCity().toLowerCase());
        pathSegments.add("");
        uriBuilder.setPathSegments(pathSegments);

        if(!formService.getForm().getBuildingId().equals("all_buildings")) {
            uriBuilder.setParameter("search[filter_enum_builttype][0]", formService.getForm().getBuildingId());
        }
        uriBuilder.setParameter("page", "1");
/*
        Map<String, String> queries = new HashMap<>();
        queries.put("search%5Bfilter_enum_floor_select%5D%5B0%5D", formService.getForm().getLevelId());
        */

        log.info("url adress: " + uriBuilder.build().toString());
    }

    public String getStringURL() throws URISyntaxException {
        return uriBuilder.build().toString();
    }

    public String getStringURLWithPages(int page) throws URISyntaxException {
        uriBuilder.setParameter("page", Integer.valueOf(2).toString());
        return uriBuilder.build().toString();
    }




}
