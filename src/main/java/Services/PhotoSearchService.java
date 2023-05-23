package Services;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.customsearch.v1.Customsearch;

import com.google.api.services.customsearch.v1.CustomsearchRequestInitializer;
import com.google.api.services.customsearch.v1.model.Result;
import com.google.api.services.customsearch.v1.model.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PhotoSearchService {

    private final Customsearch customsearch;

    @Autowired
    public PhotoSearchService() throws GeneralSecurityException, IOException {
        JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
        customsearch = new Customsearch.Builder(GoogleNetHttpTransport.newTrustedTransport(), jsonFactory, null)
                .setApplicationName("Youssadasd")
                .setGoogleClientRequestInitializer(new CustomsearchRequestInitializer("AIzaSyBzGNFPTF7W7UJQGegydzpiHPtVx9bpxwI"))
                .build();
    }

    public List<String> searchPhotos(String brand, String model) throws IOException {
        String query = brand + " " + model + " photo";
        Customsearch.Cse.List search = customsearch.cse().list();
        search.setQ(query);
        search.setCx("23aa6f58504994312");
        search.setNum(5); // Количество фотографий для получения
        search.setSearchType("image");

        Search result = search.execute();
        List<Result> items = result.getItems();

        List<String> photoUrls = new ArrayList<>();
        if (items != null) {
            for (Result item : items) {
                photoUrls.add(item.getLink());
            }
        }
        return photoUrls;
    }
}