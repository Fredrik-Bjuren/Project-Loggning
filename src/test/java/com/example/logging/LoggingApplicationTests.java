
package com.example.logging;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.beans.PropertyEditor;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import java.util.List;

@SpringBootTest
class LoggingApplicationTests {
	LoggingController lc = new LoggingController();
	BindingResult br = new BindingResult() {
		@Override
		public Object getTarget() {
			return null;
		}

		@Override
		public Map<String, Object> getModel() {
			return null;
		}

		@Override
		public Object getRawFieldValue(String field) {
			return null;
		}

		@Override
		public PropertyEditor findEditor(String field, Class<?> valueType) {
			return null;
		}

		@Override
		public PropertyEditorRegistry getPropertyEditorRegistry() {
			return null;
		}

		@Override
		public String[] resolveMessageCodes(String errorCode) {
			return new String[0];
		}

		@Override
		public String[] resolveMessageCodes(String errorCode, String field) {
			return new String[0];
		}

		@Override
		public void addError(ObjectError error) {

		}

		@Override
		public String getObjectName() {
			return null;
		}

		@Override
		public void setNestedPath(String nestedPath) {

		}

		@Override
		public String getNestedPath() {
			return null;
		}

		@Override
		public void pushNestedPath(String subPath) {

		}

		@Override
		public void popNestedPath() throws IllegalStateException {

		}

		@Override
		public void reject(String errorCode) {

		}

		@Override
		public void reject(String errorCode, String defaultMessage) {

		}

		@Override
		public void reject(String errorCode, Object[] errorArgs, String defaultMessage) {

		}

		@Override
		public void rejectValue(String field, String errorCode) {

		}

		@Override
		public void rejectValue(String field, String errorCode, String defaultMessage) {

		}

		@Override
		public void rejectValue(String field, String errorCode, Object[] errorArgs, String defaultMessage) {

		}

		@Override
		public void addAllErrors(Errors errors) {

		}

		@Override
		public boolean hasErrors() {
			return false;
		}

		@Override
		public int getErrorCount() {
			return 0;
		}

		@Override
		public List<ObjectError> getAllErrors() {
			return null;
		}

		@Override
		public boolean hasGlobalErrors() {
			return false;
		}

		@Override
		public int getGlobalErrorCount() {
			return 0;
		}

		@Override
		public List<ObjectError> getGlobalErrors() {
			return null;
		}

		@Override
		public ObjectError getGlobalError() {
			return null;
		}

		@Override
		public boolean hasFieldErrors() {
			return false;
		}

		@Override
		public int getFieldErrorCount() {
			return 0;
		}

		@Override
		public List<FieldError> getFieldErrors() {
			return null;
		}

		@Override
		public FieldError getFieldError() {
			return null;
		}

		@Override
		public boolean hasFieldErrors(String field) {
			return false;
		}

		@Override
		public int getFieldErrorCount(String field) {
			return 0;
		}

		@Override
		public List<FieldError> getFieldErrors(String field) {
			return null;
		}

		@Override
		public FieldError getFieldError(String field) {
			return null;
		}

		@Override
		public Object getFieldValue(String field) {
			return null;
		}

		@Override
		public Class<?> getFieldType(String field) {
			return null;
		}
	};
	RedirectAttributes ra = new RedirectAttributes() {
		@Override
		public RedirectAttributes addAttribute(String attributeName, Object attributeValue) {
			return null;
		}

		@Override
		public RedirectAttributes addAttribute(Object attributeValue) {
			return null;
		}

		@Override
		public RedirectAttributes addAllAttributes(Collection<?> attributeValues) {
			return null;
		}

		@Override
		public Model addAllAttributes(Map<String, ?> attributes) {
			return null;
		}

		@Override
		public RedirectAttributes mergeAttributes(Map<String, ?> attributes) {
			return null;
		}

		@Override
		public boolean containsAttribute(String attributeName) {
			return false;
		}

		@Override
		public Object getAttribute(String attributeName) {
			return null;
		}

		@Override
		public Map<String, Object> asMap() {
			return null;
		}

		@Override
		public RedirectAttributes addFlashAttribute(String attributeName, Object attributeValue) {
			return null;
		}

		@Override
		public RedirectAttributes addFlashAttribute(Object attributeValue) {
			return null;
		}

		@Override
		public Map<String, ?> getFlashAttributes() {
			return null;
		}
	};
	@Autowired
	LoggingService service;
	@Autowired
	UserRepository userRepo;
	@Autowired
	TimeRegistrationRepository timeRegRepo;

	@Test
	void contextLoads() {
	}
	@Test
	void signupValidationTest() {
		RedirectView rvSignup = new RedirectView("/signup",false);
		RedirectView rvLogin = new RedirectView("/",true);
		User user = new User("Gustav","Green","MrGreen","hej@hej.se","green123","green123");
		User user2 = new User("Beorn","Blue","MrBlue","hej@hej.se","blue123","red123");
		Assertions.assertEquals(rvLogin.getUrl(),service.signupValidation(user,br,user.getRepeatPassword(),ra).getUrl());
		Assertions.assertEquals(rvSignup.getUrl(),service.signupValidation(user2,br,user2.getRepeatPassword(),ra).getUrl());

	}
	@Test
	void homeValidationTest() {
		TimeRegistration tr = new TimeRegistration(LocalDate.now(),0.0);
		TimeRegistration tr2 = new TimeRegistration(null, 2.5);
		TimeRegistration tr3 = new TimeRegistration(LocalDate.now(), 3.5);
		Assertions.assertEquals("Invalid time",service.homeValidation(tr,br));
		Assertions.assertEquals("Empty date",service.homeValidation(tr2,br));
		Assertions.assertEquals("Passed homeValidation",service.homeValidation(tr3, br));
	}
	@Test
	void addUserTest() {
		User user = new User("Gustav","Green","MrGreen","hej@hej.se","green123","green123");
		long urCount = userRepo.count();
		service.addUser(user);
		Assertions.assertEquals(userRepo.count(),urCount+1);
		Assertions.assertEquals(user.toString(),service.getUsers().get((int) userRepo.count()-1).toString());
		userRepo.delete(user);
		Assertions.assertEquals(userRepo.count(),urCount);
	}
	@Test
	void timeRegistrationTest() {
		TimeRegistration tr = new TimeRegistration(3,LocalDate.now(),2.5,"Paid leave",
				"kommentarHej", LocalDateTime.of(2023,3,15,10,45),LocalDateTime.now());
		long trCount = timeRegRepo.count();
		service.saveTime(tr);
		Assertions.assertEquals(timeRegRepo.count(),trCount+1);
		timeRegRepo.delete(tr);
		Assertions.assertEquals(timeRegRepo.count(),trCount);
	}
}
