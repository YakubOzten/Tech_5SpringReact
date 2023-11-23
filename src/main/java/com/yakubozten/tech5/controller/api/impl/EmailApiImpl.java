package com.yakubozten.tech5.controller.api.impl;

import com.yakubozten.tech5.business.dto.EmailDto;
import com.yakubozten.tech5.business.services.IEmailServices;
import com.yakubozten.tech5.controller.api.IEmailApi;
import com.yakubozten.tech5.utils.FrontendPortUrl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor


@RestController
@CrossOrigin(origins = FrontendPortUrl.REACT_FRONTEND_PORT_URL)
@RequestMapping("/email/api/v1")
public class EmailApiImpl implements IEmailApi<EmailDto> {
    // INJECTION

    // http://localhost:2222/email/api/v1/basic/email
    private final IEmailServices iEmailServices;

    @Override
    @PostMapping("/basic/email")
    //@PreAuthorize("hasPermission(#article, 'isEditor')")
    public EmailDto blogSendEmail(@Valid @RequestBody EmailDto emailDto) {
        return (EmailDto) iEmailServices.blogSendBasicEmail(emailDto);
    }


    // http://localhost:2222/email/api/v1/intermedia/email
    @Override
    @PostMapping("/intermedia/email")
    public EmailDto blogSendAttachmentMail(@Valid @RequestBody EmailDto emailDto) {
        return (EmailDto) iEmailServices.blogSendAttachmentMail (emailDto);
    }
}
