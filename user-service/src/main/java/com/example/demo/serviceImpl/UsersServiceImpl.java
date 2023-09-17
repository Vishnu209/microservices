package com.example.demo.serviceImpl;

import com.example.demo.repository.UsersRepository;
import com.example.demo.request.UsersRequest;
import com.example.demo.response.ApiResponse;
import com.example.demo.service.UsersService;
import com.example.demo.utils.ErrorMessage;
import com.example.demo.utils.SuccessMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;
    private final SuccessMessage successMessage;
    private final ErrorMessage errorMessage;

    public UsersServiceImpl(UsersRepository usersRepository, SuccessMessage successMessage, ErrorMessage errorMessage) {
        this.usersRepository = usersRepository;
        this.successMessage = successMessage;
        this.errorMessage = errorMessage;
    }

    @Override
    public ResponseEntity<ApiResponse> createUser(UsersRequest usersRequest) {
        ApiResponse apiResponse = new ApiResponse();
        if(StringUtils.isEmpty(usersRequest.getEmail())){
            apiResponse.setMessage(errorMessage.getEmaiInvalid());
        }
        return null;
    }
}
