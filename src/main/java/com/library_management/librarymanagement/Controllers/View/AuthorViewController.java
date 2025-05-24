package com.library_management.librarymanagement.Controllers.View;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;

import com.library_management.librarymanagement.Service.AuthorServ;

@Controller
@RequestMapping("author")
public class AuthorViewController {
    @Autowired
    private AuthorServ authorServ;

    @PostMapping(path = "/admin/add")
    public String addAuthor(@ModelAttribute )
}
