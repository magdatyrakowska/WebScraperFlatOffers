package webscraper.application.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

@Slf4j
@Service
public class URLService {

    private URIBuilder uriBuilder;
    private FormService formService;
    private int pageIndex;
    private int pageIndexMax;

    @Autowired
    public URLService(FormService formService) {
        this.formService = formService;
    }

    public void buildURL() throws URISyntaxException {
        uriBuilder = new URIBuilder("https://www.olx.pl");
        List<String> pathSegments = new ArrayList<>();
        pathSegments.add("nieruchomosci");
        pathSegments.add("mieszkania");
        pathSegments.add("wynajem");
        pathSegments.add(prepareCityName(formService.getForm().getCity()));
        pathSegments.add("");
        uriBuilder.setPathSegments(pathSegments);

        getQueriesFromForm().entrySet()
                .stream()
                .forEach(entry -> uriBuilder.addParameter(entry.getKey(), entry.getValue()));

        pageIndex = 1;
        pageIndexMax = 1;
        uriBuilder.setParameter("page", Integer.toString(pageIndex));

        log.info("url address: " + uriBuilder.build().toString());
    }

    public String getStringURL() throws URISyntaxException {
        return uriBuilder.build().toString();
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndexMax(int pageIndexMax) {
        this.pageIndexMax = pageIndexMax;
    }

    public boolean hasNextPage() {
        return pageIndex <= pageIndexMax;
    }

    public void increasePageIndex() {
        pageIndex++;
        uriBuilder.setParameter("page", Integer.toString(pageIndex));
    }

    private Map<String, String> getQueriesFromForm() {
        Map<String, String> queries = new HashMap<>();
        if(!formService.getForm().getLevelId().equals("all_levels")) {
            queries.put("search[filter_enum_floor_select][0]", formService.getForm().getLevelId());
        }
        if(!formService.getForm().getFurnishingsId().equals("all_furnishings")) {
            queries.put("search[filter_enum_furniture][0]", formService.getForm().getFurnishingsId());
        }
        if(!formService.getForm().getBuildingId().equals("all_buildings")) {
            queries.put("search[filter_enum_builttype][0]", formService.getForm().getBuildingId());
        }
        if(!formService.getForm().getAreaFromId().equals("any_areas")) {
            queries.put("search[filter_float_m:from]", formService.getForm().getAreaFromId());
        }
        if(!formService.getForm().getAreaToId().equals("any_areas")) {
            queries.put("search[filter_float_m:to]", formService.getForm().getAreaToId());
        }
        if(!formService.getForm().getFlatSizeId().equals("all_sizes")) {
            queries.put("search[filter_enum_rooms][0]", formService.getForm().getFlatSizeId());
        }

        return queries;
    }

    private static String prepareCityName(String city) {
        StringBuilder builder = new StringBuilder();
        city = city.toLowerCase();

        for (char c : city.toCharArray()) {
            if (c == 'ą') {
                builder.append('a');
            } else if (c == 'ć') {
                builder.append('c');
            } else if (c == 'ę') {
                builder.append('e');
            } else if (c == 'ł') {
                builder.append('l');
            } else if (c == 'ń') {
                builder.append('n');
            } else if (c == 'ó') {
                builder.append('o');
            } else if (c == 'ś') {
                builder.append('s');
            } else if (c == 'ź' || c == 'ż') {
                builder.append('z');
            } else {
                builder.append(c);
            }
        }
        return builder.toString();
    }
}
