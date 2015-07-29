package com.example.myproject.web;

import com.example.myproject.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;

@Controller
public class UserListController {

    private static final Logger logger = LoggerFactory.getLogger(UserListController.class);
    private final UserService userService;

    @Inject
    public UserListController(final UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/user_list.html")
    public ModelAndView getListUsersView() {
        logger.debug("Received request to get user list view");
        ModelMap model = new ModelMap();
        model.addAttribute("users", userService.getList());
        return new ModelAndView("user_list", model);
    }

}
