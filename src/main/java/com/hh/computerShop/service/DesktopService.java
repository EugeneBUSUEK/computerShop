package com.hh.computerShop.service;

import com.hh.computerShop.model.request.DesktopRequest;
import com.hh.computerShop.model.response.DesktopResponse;

public interface DesktopService {
    DesktopResponse createDesktop(DesktopRequest desktopRequest);
    DesktopResponse updateDesktop(DesktopRequest desktopRequest);
}
