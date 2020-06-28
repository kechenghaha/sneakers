package kecheng.haha.sneakers.controller;

import kecheng.haha.sneakers.dto.PaginationDTO;
import kecheng.haha.sneakers.mapper.UserMapper;
import kecheng.haha.sneakers.model.User;
import kecheng.haha.sneakers.service.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
/**
 * by kecheng
 * 2020/06/09
 */
@Controller
public class IndexController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private InformationService informationService;

    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "3") Integer size) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0)
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    User user = userMapper.findByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        PaginationDTO pagination = informationService.list(page, size);
        model.addAttribute("pagination", pagination);
        return "index";
    }
}
