package com.omb.ocpp.groovy.supplier

import com.omb.ocpp.groovy.ConfirmationSupplier
import com.omb.ocpp.server.iso15118.dto.AuthorizeRequest
import com.omb.ocpp.server.iso15118.dto.AuthorizeResponse
import com.omb.ocpp.server.iso15118.dto.IdTokenInfo
import com.omb.ocpp.server.iso15118.dto.MessageContent

import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.temporal.ChronoUnit

class AuthorizeResponseConfirmationSupplier implements ConfirmationSupplier<AuthorizeRequest, AuthorizeResponse> {
    private static final Instant CLASS_LOAD_DATE = Instant.now()

    @Override
    AuthorizeResponse getConfirmation(UUID sessionUuid, AuthorizeRequest request) {
        MessageContent messageContent = new MessageContent();
        messageContent.setContent("test");
        messageContent.setFormat(MessageContent.Format.ASCII);
        messageContent.setLanguage("en");

        IdTokenInfo idTokenInfo = new IdTokenInfo();
        idTokenInfo.setStatus(IdTokenInfo.Status.ACCEPTED);
        Instant tempInstant = Instant.now().plus(1, ChronoUnit.DAYS);
        ZonedDateTime zdt = ZonedDateTime.ofInstant(tempInstant, ZoneId.systemDefault());
        idTokenInfo.setCacheExpiryDateTime(GregorianCalendar.from(zdt));
        idTokenInfo.setChargingPriority(1);
        idTokenInfo.setLanguage1("en");
        idTokenInfo.setPersonalMessage(messageContent);

        AuthorizeResponse authorizeResponse = new AuthorizeResponse();
        authorizeResponse.setCertificateStatus(AuthorizeResponse.CertificateStatus.ACCEPTED);
        authorizeResponse.setEvseId(Arrays.asList(1, 2));
        authorizeResponse.setIdTokenInfo(idTokenInfo);
        return authorizeResponse;
    }

    @Override
    Instant getClassLoadDate() {
        return CLASS_LOAD_DATE;
    }
}
