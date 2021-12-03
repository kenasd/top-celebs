package com.alex.top.service;

import com.alex.top.exception.ResetIsActiveException;

public interface LoaderService {

    boolean reset() throws ResetIsActiveException;
}
