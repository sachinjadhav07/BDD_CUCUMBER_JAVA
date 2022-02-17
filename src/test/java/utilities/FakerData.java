package utilities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.github.javafaker.Faker;

public class FakerData {
	
//	public static void main(String[] args) {
	Faker faker = new Faker();
	String streetName = faker.address().streetName();
	String number = faker.address().buildingNumber();
	String city = faker.address().city();
	String country = faker.address().country();
	String email = faker.internet().emailAddress();
	
	
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");  
	   LocalDateTime now = LocalDateTime.now();  
//	   System.out.println(dtf.format(now));  
//	
//	System.out.println(
//			String.format("%s\n%s\n%s\n%s\n%s",
//					  email,
//					  number,
//					  streetName,
//					  city,
//					  country
//					  )
//			);
	

	
//	}
}

