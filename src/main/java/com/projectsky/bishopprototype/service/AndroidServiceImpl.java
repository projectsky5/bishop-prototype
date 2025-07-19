package com.projectsky.bishopprototype.service;

import com.projectsky.synthetichumancorestarter.audit.WeylandWatchingYou;
import org.springframework.stereotype.Service;

@Service
public class AndroidServiceImpl implements AndroidService {

    @Override
    @WeylandWatchingYou
    public String checkAndroidStatus() {
        return "Android is running";
    }
}
