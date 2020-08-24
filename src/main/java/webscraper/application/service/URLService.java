package webscraper.application.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

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
        pathSegments.add(prepareCityName(formService.getForm().getCity()));
        pathSegments.add("");
        uriBuilder.setPathSegments(pathSegments);

        getQueriesFromForm().entrySet().stream()
                .forEach(entry -> uriBuilder.addParameter(entry.getKey(), entry.getValue()));

        uriBuilder.setParameter("page", "1");

        log.info("url address: " + uriBuilder.build().toString());
    }

    public String getStringURL() throws URISyntaxException {
        return uriBuilder.build().toString();
    }

    public String getStringURLWithPages(int page) throws URISyntaxException {
        uriBuilder.setParameter("page", Integer.valueOf(2).toString());
        return uriBuilder.build().toString();
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

    public boolean isURLValid(String urlS) {
        try {
            URL url = new URL(urlS);
            url.toURI();
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}
