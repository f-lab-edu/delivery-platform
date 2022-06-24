package org.flab.deliveryplatform.member.interfaces.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import org.assertj.core.api.Assertions;
import org.flab.deliveryplatform.common.web.dto.DeliveryPlatformErrorResponse;
import org.flab.deliveryplatform.common.web.dto.DeliveryPlatformErrorResponse.DeliveryPlatformErrorResult;
import org.flab.deliveryplatform.member.application.port.WithdrawMemberUseCase;
import org.flab.deliveryplatform.member.application.port.dto.SignUpMemberCommand;
import org.flab.deliveryplatform.member.application.port.dto.WithdrawMemberCommand;
import org.flab.deliveryplatform.member.interfaces.TestContextConfiguration;
import org.flab.deliveryplatform.member.interfaces.web.exception.MemberErrorCode;
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
@Import(TestContextConfiguration.class)
class SignUpMemberControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private WithdrawMemberUseCase withdrawMemberUseCase;

    @Autowired
    private ObjectMapper objectMapper;

    private SignUpMemberCommand signUpMemberCommand;

    @BeforeEach
    void init() {
        signUpMemberCommand = new SignUpMemberCommand(UUID.randomUUID().toString().substring(0, 20),
            UUID.randomUUID().toString().substring(0, 20) + "@gmail.com", "a1234567",
            "010-1234-5678");
    }

    @AfterEach()
    void afterEach() {
        WithdrawMemberCommand withdrawMemberCommand = new WithdrawMemberCommand(
            signUpMemberCommand.getEmail(), signUpMemberCommand.getPassword());
        withdrawMemberUseCase.withdraw(withdrawMemberCommand);
    }

    @Test
    void signUp() throws Exception {
        mockMvc.perform(
                post("/members")
                    .contentType(MediaType.APPLICATION_JSON)
                    .characterEncoding(StandardCharsets.UTF_8)
                    .content(mapToString(signUpMemberCommand)))
            .andExpect(status().isOk());
    }

    @Test
    void signUpWithDuplicateEmailTest() throws Exception {
        mockMvc.perform(
                post("/members")
                    .contentType(MediaType.APPLICATION_JSON)
                    .characterEncoding(StandardCharsets.UTF_8)
                    .content(mapToString(signUpMemberCommand)))
            .andExpect(status().isOk());

        String duplicatedEmailString = mockMvc.perform(
                post("/members")
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

    private DeliveryPlatformErrorResult mapToErrorResult(String memberDataString)
        throws JsonProcessingException {
        DeliveryPlatformErrorResponse<Object> response = objectMapper.readValue(
            memberDataString, new TypeReference<>() {
            });
        return response.getErrorResult();
    }


}
