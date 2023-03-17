package com.example.logging;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Collection;
import java.util.Enumeration;
import java.util.Map;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.web.servlet.function.RequestPredicates.contentType;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class LoggingControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    ObjectMapper mapper;

    @Test
    void loadLogin() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get("/")).andExpect(status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.content().
                        string(containsString("Loggs")));

    }

    @Test
    void postLogin() throws Exception {

        mvc.perform(MockMvcRequestBuilders
                        .post("/").param("username","Fregget").param("password","Fredrik")
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/home"));

        mvc.perform(MockMvcRequestBuilders
                        .post("/").param("username","Fregge").param("password","Fredrik")
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/"));


    }


    @Test
    void home() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get("/home").sessionAttr("user",new User()))
                .andExpect(status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.content().
                        string(containsString("Loggs")));
    }

    @Test
    void registration() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .post("/home").sessionAttr("user",new User("Fregget","Fredrik","Fredrik","Test"))
                        .param("timeRegistration", String.valueOf(new TimeRegistration()))
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/home"));

    }

    @Test
    void loadSignup() {
    }

    @Test
    void submitSignupPost() {
    }

    @Test
    void logout() {
    }

    HttpSession httpSession = new HttpSession() {
        @Override
        public long getCreationTime() {
            return 0;
        }

        @Override
        public String getId() {
            return null;
        }

        @Override
        public long getLastAccessedTime() {
            return 0;
        }

        @Override
        public ServletContext getServletContext() {
            return null;
        }

        @Override
        public void setMaxInactiveInterval(int i) {

        }

        @Override
        public int getMaxInactiveInterval() {
            return 0;
        }

        @Override
        public Object getAttribute(String s) {
            return null;
        }

        @Override
        public Enumeration<String> getAttributeNames() {
            return null;
        }

        @Override
        public void setAttribute(String s, Object o) {

        }

        @Override
        public void removeAttribute(String s) {

        }

        @Override
        public void invalidate() {

        }

        @Override
        public boolean isNew() {
            return false;
        }
    };
    Model model = new Model() {
        @Override
        public Model addAttribute(String attributeName, Object attributeValue) {
            return null;
        }

        @Override
        public Model addAttribute(Object attributeValue) {
            return null;
        }

        @Override
        public Model addAllAttributes(Collection<?> attributeValues) {
            return null;
        }

        @Override
        public Model addAllAttributes(Map<String, ?> attributes) {
            return null;
        }

        @Override
        public Model mergeAttributes(Map<String, ?> attributes) {
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
        public RedirectAttributes mergeAttributes(Map<String, ?> attributes) {
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

        @Override
        public Model addAllAttributes(Map<String, ?> attributes) {
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
    };
}