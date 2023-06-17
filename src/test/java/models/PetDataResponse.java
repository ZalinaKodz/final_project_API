package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PetDataResponse {
    private List<String> photoUrls;
    private String name;
    private Long id;
    private Category category;
    private List<TagsItem> tags;
    private String status;
}
