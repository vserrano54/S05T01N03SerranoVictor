package cat.itacademy.barcelonactiva.serrano.victor.s05.t01.n03.model.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import cat.itacademy.barcelonactiva.serrano.victor.s05.t01.n03.model.dto.FlorEntityDto;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class FlorService {
	
	@Value("${spring.external.service.base-url}")
	private String basePath;
	
	@Autowired
	private RestTemplate restTemplate;
	
	public List<FlorEntityDto> findAll(){
	    
		FlorEntityDto[] response =	restTemplate.getForObject(basePath+"/getAll",FlorEntityDto[].class);
	   
		return Arrays.asList(response);
	}
	
	public void save(FlorEntityDto florentity) {
		
		restTemplate.postForObject(basePath+"/add", florentity, FlorEntityDto.class);
	}
	
	public void update(Integer id,FlorEntityDto florentity) {
		
		restTemplate.put(basePath+"/update/"+id, florentity);
		
		
	}
	
	public void deleteById(int id) {
		
		restTemplate.delete(basePath+"/delete/"+id);
	}
	
	public FlorEntityDto findById(int id) {
		
		FlorEntityDto response = restTemplate.getForObject(basePath+"/getOne/"+id,FlorEntityDto.class);
		   
		return response;
	}
	
	
	
	


}
