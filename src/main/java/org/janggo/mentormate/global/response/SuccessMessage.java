package org.janggo.mentormate.global.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum SuccessMessage {

    ;

    private final HttpStatus httpStatus;
    private final String message;

    public int getStatusCode(){
        return httpStatus.value();
    }
}
