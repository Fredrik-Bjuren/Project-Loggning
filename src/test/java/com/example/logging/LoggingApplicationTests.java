
package com.example.logging;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.beans.PropertyEditor;
import java.util.List;
import java.util.Map;

import java.util.ArrayList;
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

	@Test
	void contextLoads() {
	}
/*	@Test
	void confirmPasswordTest() {
		// pw & cPw = same -> expect login
		// pw & cPw != same -> expect signup (wrong input)
		User user = new User("Fredrik","email","fredrik","bjuren","login","login");
		User user2 = new User("Fredrik","email","fredrik","bjuren","signup","sign");
		Assertions.assertEquals("login",lc.validation(user, br));
		Assertions.assertEquals("signup",lc.validation(user2, br));
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
	}*/



}
