package com.myweddingplanner.back.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/weddings")
@Tag(name = "API bodas",
        description = "CRUD bodas de la App")
public class WeddingController {
}
