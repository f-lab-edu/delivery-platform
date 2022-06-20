package org.flab.deliveryplatform.interfaces.member.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.charset.StandardCharsets;
import org.assertj.core.api.Assertions;
import org.flab.deliveryplatform.common.web.DeliveryPlatformErrorResponse;
import org.flab.deliveryplatform.common.web.DeliveryPlatformErrorResponse.DeliveryPlatformErrorResult;
import org.flab.deliveryplatform.interfaces.member.TestConfig;
import org.flab.deliveryplatform.interfaces.member.web.exception.MemberErrorCode;
import org.flab.deliveryplatform.member.application.port.WithdrawMemberUseCase;
import org.flab.deliveryplatform.member.application.port.dto.SignUpMemberCommand;
import org.flab.deliveryplatform.member.application.port.dto.WithdrawMemberCommand;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
@AutoConfigureMockMvc
@Import(TestConfig.class)
class SignUpMemberControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private WithdrawMemberUseCase withdrawMemberUseCase;

    private ObjectMapper objectMapper = new ObjectMapper();

    private SignUpMemberCommand signUpMemberCommand;

    @BeforeEach
    void init() {
        signUpMemberCommand = new SignUpMemberCommand("nicknameInit",
            "nicknameInit@gmail.com", "a1234567", "010-1234-5678");
    }

    @AfterEach()
    void afterEach() {
        WithdrawMemberCommand withdrawMemberCommand = new WithdrawMemberCommand(
            "nicknameInit@gmail.com", "a1234567");
        withdrawMemberUseCase.withdraw(withdrawMemberCommand);
    }

    @Test
    void signUp() throws Exception {
        mockMvc.perform(
                post("/members/signUp")
                    .contentType(MediaType.APPLICATION_JSON)
                    .characterEncoding(StandardCharsets.UTF_8)
                    .content(mapToString(signUpMemberCommand)))
            .andExpect(status().isOk());
    }

    @Test
    void signUpWithDuplicateEmailTest() throws Exception {
        mockMvc.perform(
                post("/members/signUp")
                    .contentType(MediaType.APPLICATION_JSON)
                    .characterEncoding(StandardCharsets.UTF_8)
                    .content(mapToString(signUpMemberCommand)))
            .andExpect(status().isOk());

        String duplicatedEmailString = mockMvc.perform(
                post("/members/signUp")
                    .contentType(MediaType.APPLICATION_JSON)
                    .characterEncoding(StandardCharsets.UTF_8)
                    .content(mapToString(signUpMemberCommand)))
            .andExpect(status().isBadRequest())
            .andReturn()
            .getResponse()
            .getContentAsString();

        DeliveryPlatformErrorResult errorResult = mapToErrorResult(
            duplicatedEmailString);
        Assertions.assertThat(errorResult.getErrorCode())
            .isEqualTo(MemberErrorCode.M_DUPLICATED_EMAIL.name());
    }

    private <T> String mapToString(T data) throws JsonProcessingException {
        return objectMapper.writeValueAsString(data);
    }

    private DeliveryPlatformErrorResult mapToErrorResult(String getMemberInfoResultString)
        throws JsonProcessingException {
        DeliveryPlatformErrorResponse<Object> response = objectMapper.readValue(
            getMemberInfoResultString, new TypeReference<>() {
            });
        return response.getErrorResult();
    }


}
