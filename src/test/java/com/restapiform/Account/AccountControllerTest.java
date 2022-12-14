package com.restapiform.Account;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restapiform.MockMvcTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@MockMvcTest
public class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 신규 회원가입 실패 테스트
     * @throws Exception
     */
    @DisplayName("신규 회원가입 실패 - 값이 비어있는 request body")
    @Test
    void requestBodyErrorToSignUpAccount() throws Exception {
        // given
        Map<String, String> input = new HashMap<>();
        input.put("email", "");
        input.put("password", "1234567890");
        input.put("name", "test");
        input.put("birth", "19950803");

        // when
        mockMvc.perform(post("/account")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))

                // then
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    /**
     * 신규 회원가입 성공 테스트
     * @throws Exception
     */
    @DisplayName("신규 회원가입 성공")
    @Test
    void completeToSignUpAccount() throws Exception {
        // given
        Map<String, String> input = new HashMap<>();
        input.put("email", "wyehgus@naver.com");
        input.put("password", "1234567890");
        input.put("name", "test");
        input.put("birth", "19950803");

        // when
        mockMvc.perform(post("/account")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))

        // then
        .andExpect(status().isCreated())
        .andDo(print());
    }

}