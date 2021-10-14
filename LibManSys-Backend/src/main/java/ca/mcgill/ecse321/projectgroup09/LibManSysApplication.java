package ca.mcgill.ecse321.projectgroup09;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@SpringBootApplication
public class LibManSysApplication {

	public static void main(String[] args) {
    	SpringApplication.run(LibManSysApplication.class, args);
  	}

  	@RequestMapping("/")
  	public String greeting(){
    	return "Hello World!";
  	}

}
