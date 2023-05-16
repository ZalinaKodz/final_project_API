package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PetModel {
    private List<String> photoUrls;
    private String name;
    private Integer id;
    private Category category;
    private List<TagsItem> tags;
    private String status;
}
