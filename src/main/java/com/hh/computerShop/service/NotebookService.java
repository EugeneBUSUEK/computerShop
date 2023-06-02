package com.hh.computerShop.service;

import com.hh.computerShop.model.request.NotebookRequest;
import com.hh.computerShop.model.response.NotebookResponse;

public interface NotebookService {
    NotebookResponse createNotebook(NotebookRequest notebookRequest);
    NotebookResponse updateNotebook(NotebookRequest notebookRequest);
}
