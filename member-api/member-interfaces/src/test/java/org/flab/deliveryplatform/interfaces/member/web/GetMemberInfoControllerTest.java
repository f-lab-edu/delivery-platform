package org.flab.deliveryplatform.interfaces.member.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import org.assertj.core.api.Assertions;
import org.flab.deliveryplatform.common.web.DeliveryPlatformErrorResponse;
import org.flab.deliveryplatform.common.web.DeliveryPlatformErrorResponse.DeliveryPlatformErrorResult;
import org.flab.deliveryplatform.common.web.DeliveryPlatformResponse;
import org.flab.deliveryplatform.interfaces.member.TestConfig;
import org.flab.deliveryplatform.interfaces.member.web.exception.MemberErrorCode;
import org.flab.deliveryplatform.member.application.port.SignUpMemberUseCase;
import org.flab.deliveryplatform.member.application.port.WithdrawMemberUseCase;
import org.flab.deliveryplatform.member.application.port.dto.GetMemberInfoResult;
import org.flab.deliveryplatform.member.application.port.dto.SignUpMemberCommand;
import org.flab.deliveryplatform.member.application.port.dto.SignUpMemberResult;
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
class GetMemberInfoControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private SignUpMemberUseCase signUpMemberUseCase;

    @Autowired
    private WithdrawMemberUseCase withdrawMemberUseCase;

    private ObjectMapper objectMapper = new ObjectMapper();

    private SignUpMemberCommand signUpMemberCommand;

    private Long signUpMemberId;

    @BeforeEach
    void init() {
        signUpMemberCommand = new SignUpMemberCommand(UUID.randomUUID().toString().substring(0, 20),
            UUID.randomUUID().toString().substring(0, 20) + "@gmail.com", "a1234567",
            "010-1234-5678");
        SignUpMemberResult signUpMemberResult = signUpMemberUseCase.signUp(signUpMemberCommand);
        signUpMemberId = signUpMemberResult.getId();
    }

    @AfterEach()
    void afterEach() {
        WithdrawMemberCommand withdrawMemberCommand = new WithdrawMemberCommand(
            signUpMemberCommand.getEmail(), signUpMemberCommand.getPassword());
        withdrawMemberUseCase.withdraw(withdrawMemberCommand);
    }

    @Test
    void memberInfoTest() throws Exception {
        String getMemberInfoResultString = mockMvc.perform(
                get("/members/" + signUpMemberId)
                    .contentType(MediaType.APPLICATION_JSON)
                    .characterEncoding(StandardCharsets.UTF_8))
            .andExpect(status().isOk())
            .andReturn()
            .getResponse()
            .getContentAsString();

        GetMemberInfoResult getMemberInfoResult = mapToGetMemberInfoResult(
            getMemberInfoResultString);

        Assertions.assertThat(getMemberInfoResult.getEmail())
            .isEqualTo(signUpMemberCommand.getEmail());
        Assertions.assertThat(getMemberInfoResult.getNickname())
            .isEqualTo(signUpMemberCommand.getNickname());
        Assertions.assertThat(getMemberInfoResult.getPhoneNumber())
            .isEqualTo(signUpMemberCommand.getPhoneNumber());

    }

    private GetMemberInfoResult mapToGetMemberInfoResult(String getMemberInfoResultString)
        throws JsonProcessingException {
        DeliveryPlatformResponse<GetMemberInfoResult> response = objectMapper.readValue(
            getMemberInfoResultString, new TypeReference<>() {
            });
        return response.getData();
    }

    @Test
    void invalidMemberInfoTest() throws Exception {
        String invalidString = mockMvc.perform(
                get("/members/" + Long.MAX_VALUE)
                    .contentType(MediaType.APPLICATION_JSON)
                    .characterEncoding(StandardCharsets.UTF_8))
            .andExpect(status().isBadRequest())
            .andReturn()
            .getResponse()
            .getContentAsString();

        DeliveryPlatformErrorResult errorResult = mapToErrorResult(
            invalidString);

        Assertions.assertThat(errorResult.getErrorCode())
            .isEqualTo(MemberErrorCode.M_INVALID_MEMBER_INFO.name());
    }

    private DeliveryPlatformErrorResult mapToErrorResult(String getMemberInfoResultString)
        throws JsonProcessingException {
        DeliveryPlatformErrorResponse<Object> response = objectMapper.readValue(
            getMemberInfoResultString, new TypeReference<>() {
            });
        return response.getErrorResult();
    }

}
