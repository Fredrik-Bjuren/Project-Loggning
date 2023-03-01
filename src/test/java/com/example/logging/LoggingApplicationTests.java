package com.example.logging;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class LoggingApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void enumSumTest(){
		List<TimeRegistration> times = new ArrayList<>();
		times.add(new TimeRegistration(2.0,TypeRegTime.WORK,"2022-12-22",""));
		times.add(new TimeRegistration(2.0,TypeRegTime.WORK,"2022-12-23",""));
		times.add(new TimeRegistration(2.0,TypeRegTime.WORK,"2022-12-24",""));
		times.add(new TimeRegistration(2.0,TypeRegTime.WORK,"2022-12-25",""));
		times.add(new TimeRegistration(0.5,TypeRegTime.PAID_LEAVE,"2022-12-25",""));
		times.add(new TimeRegistration(2.0,TypeRegTime.PAID_LEAVE,"2022-12-26",""));
		User user = new User(times);
		Assertions.assertEquals(8.0,user.getEnumSum(TypeRegTime.WORK));
		Assertions.assertEquals(2.5,user.getEnumSum(TypeRegTime.PAID_LEAVE));
	}



}
