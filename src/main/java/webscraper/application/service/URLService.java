package webscraper.application.service;

import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Creates URL addresses processing {@code FormService} data.
 * Manages going through whole et of URL addresses with different indexes.
 */
@Service
public class URLService {

    /**
     * Used to construct valid URL address piece by piece
     */
    private URIBuilder uriBuilder;
    /**
     * Source of data used to build complete URL address
     */
    private FormService formService;
    /**
     * Index storing current page
     */
    private int pageIndex;
    /**
     * Maximum page index that is possible in this search
     */
    private int pageIndexMax;

    /**
     * Initialize {@code formService} field
     *
     * @param formService instance of {@code FormService} to be assigned
     */
    @Autowired
    public URLService(FormService formService) {
        this.formService = formService;
    }

    /**
     * Builds URL using data in {@code FormService}. Firstly it adds path necessary
     * for flat rental search. Then it adds queries using data from {@code FormService},
     * where selected search options are stored. City name is prepared by change to lowercase,
     * substitute all polish signs with latin and spaces with hyphen. Last element added
     * to {@code URIBuilder} is page index.
     *
     * @throws URISyntaxException
     */
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
    }

    /**
     * Returns built URL address as a String
     *
     * @return {@code String} containing complete URL address
     * @throws URISyntaxException
     */
    public String getStringURL() throws URISyntaxException {
        return uriBuilder.build().toString();
    }

    /**
     * Returns current page index
     *
     * @return {@code int} with current page index
     */
    public int getPageIndex() {
        return pageIndex;
    }

    /**
     * given {@code int} to a {@code pageIndexMax} field
     *
     * @param pageIndexMax is {@code int} to be assigned to a {@code pageIndexMax} field
     */
    public void setPageIndexMax(int pageIndexMax) {
        this.pageIndexMax = pageIndexMax;
    }

    /**
     * Checks, if current page index is below or equal to maximum possible page index
     *
     * @return {@code true} if current page index is below or equal to maximum possible page index
     * and {@code false} in case, that current page index is over the limit and there is no page available
     * with that index
     */
    public boolean hasNextPage() {
        return pageIndex <= pageIndexMax;
    }

    /**
     * Increase current page index by one and sets that index in {@code uriBuilder}.
     */
    public void increasePageIndex() {
        pageIndex++;
        uriBuilder.setParameter("page", Integer.toString(pageIndex));
    }

    /**
     * Generates map with queries and their values using data from {@code FormService}.
     * If no option was chosen in that category, query is not included in map.
     * Id {@code String} is used as query value.
     *
     * @return {@code Map<String, String>} with queries to URL requests. Pairs in map are
     * key-value pairs of query.
     */
    private Map<String, String> getQueriesFromForm() {
        Map<String, String> queries = new HashMap<>();
        if (!formService.getForm().getLevelId().equals("all_levels")) {
            queries.put("search[filter_enum_floor_select][0]", formService.getForm().getLevelId());
        }
        if (!formService.getForm().getFurnishingsId().equals("all_furnishings")) {
            queries.put("search[filter_enum_furniture][0]", formService.getForm().getFurnishingsId());
        }
        if (!formService.getForm().getBuildingId().equals("all_buildings")) {
            queries.put("search[filter_enum_builttype][0]", formService.getForm().getBuildingId());
        }
        if (!formService.getForm().getAreaFromId().equals("any_areas")) {
            queries.put("search[filter_float_m:from]", formService.getForm().getAreaFromId());
        }
        if (!formService.getForm().getAreaToId().equals("any_areas")) {
            queries.put("search[filter_float_m:to]", formService.getForm().getAreaToId());
        }
        if (!formService.getForm().getFlatSizeId().equals("all_sizes")) {
            queries.put("search[filter_enum_rooms][0]", formService.getForm().getFlatSizeId());
        }

        return queries;
    }

    /**
     * Process city name by change to lowercase, substitute all polish signs with latin
     * and spaces with hyphen.
     *
     * @param city {@code String} with city name obtained from form
     * @return correct form of city name that can be put directly into URL address.
     */
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
            } else if (c == ' ') {
                builder.append('-');
            } else {
                builder.append(c);
            }
        }
        return builder.toString();
    }
}
