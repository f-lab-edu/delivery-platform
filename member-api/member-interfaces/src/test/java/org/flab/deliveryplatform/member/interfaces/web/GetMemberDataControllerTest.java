package org.flab.deliveryplatform.member.interfaces.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import org.assertj.core.api.Assertions;
import org.flab.deliveryplatform.common.web.dto.DeliveryPlatformErrorResponse;
import org.flab.deliveryplatform.common.web.dto.DeliveryPlatformErrorResponse.DeliveryPlatformErrorResult;
import org.flab.deliveryplatform.common.web.dto.DeliveryPlatformResponse;
import org.flab.deliveryplatform.member.application.port.SignUpMemberUseCase;
import org.flab.deliveryplatform.member.application.port.WithdrawMemberUseCase;
import org.flab.deliveryplatform.member.application.port.dto.MemberData;
import org.flab.deliveryplatform.member.application.port.dto.SignUpMemberCommand;
import org.flab.deliveryplatform.member.application.port.dto.SignUpMemberResult;
import org.flab.deliveryplatform.member.application.port.dto.WithdrawMemberCommand;
import org.flab.deliveryplatform.member.interfaces.TestContextConfiguration;
import org.flab.deliveryplatform.member.interfaces.web.exception.MemberErrorCode;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
@AutoConfigureMockMvc
@Import(TestContextConfiguration.class)
class GetMemberDataControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private SignUpMemberUseCase signUpMemberUseCase;

    @Autowired
    private WithdrawMemberUseCase withdrawMemberUseCase;

    @Autowired
    private ObjectMapper objectMapper;

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


    void getMemberDataTest() throws Exception {
        String memberDataString = mockMvc.perform(
                get("/members/" + signUpMemberId)
                    .contentType(MediaType.APPLICATION_JSON)
                    .characterEncoding(StandardCharsets.UTF_8))
            .andExpect(status().isOk())
            .andReturn()
            .getResponse()
            .getContentAsString();

        MemberData memberData = mapToMemberData(
            memberDataString);

        Assertions.assertThat(memberData.getEmail())
            .isEqualTo(signUpMemberCommand.getEmail());
        Assertions.assertThat(memberData.getNickname())
            .isEqualTo(signUpMemberCommand.getNickname());
        Assertions.assertThat(memberData.getPhoneNumber())
            .isEqualTo(signUpMemberCommand.getPhoneNumber());

    }

    private MemberData mapToMemberData(String memberDataString)
        throws JsonProcessingException {
        DeliveryPlatformResponse<MemberData> response = objectMapper.readValue(
            memberDataString, new TypeReference<>() {
            });
        return response.getData();
    }

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

    private DeliveryPlatformErrorResult mapToErrorResult(String memberDataString)
        throws JsonProcessingException {
        DeliveryPlatformErrorResponse<Object> response = objectMapper.readValue(
            memberDataString, new TypeReference<>() {
            });
        return response.getErrorResult();
    }

}
