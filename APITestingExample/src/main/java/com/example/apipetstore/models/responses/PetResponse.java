package com.example.apipetstore.models.responses;

import java.util.List;
import lombok.Data;

public @Data class PetResponse {
	private List<String> photoUrls;
	private String name;
	private String id;
	private Category category;
	private List<TagsItem> tags;
	private String status;
}