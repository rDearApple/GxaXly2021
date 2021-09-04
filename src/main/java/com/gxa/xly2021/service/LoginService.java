package com.gxa.xly2021.service;

import com.gxa.xly2021.dto.ResultDto;
import com.gxa.xly2021.entity.Empl;

import javax.servlet.http.HttpSession;

public interface LoginService {
    ResultDto login(Empl empl, HttpSession session);
}
