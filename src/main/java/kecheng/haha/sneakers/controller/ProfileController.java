package kecheng.haha.sneakers.controller;

import kecheng.haha.sneakers.dto.PaginationDTO;
import kecheng.haha.sneakers.mapper.UserMapper;
import kecheng.haha.sneakers.model.User;
import kecheng.haha.sneakers.service.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * by kecheng
 * 2020/06/09
 */
@Controller
public class ProfileController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private InformationService informationService;

    @GetMapping("/profile/{action}")
    public String profile(HttpServletRequest request,
                          @PathVariable(name = "action") String action,
                          Model model,
                          @RequestParam(name = "page", defaultValue = "1") Integer page,
                          @RequestParam(name = "size", defaultValue = "5") Integer size) {
        User user = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    user = userMapper.findByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }

        if (user == null) {
            return "redirect:/";
        }
        if ("information".contains(action)) {
            model.addAttribute("section", "information");
            model.addAttribute("sectionName", "我的发布");
        } else if ("replies".contains(action)) {
            model.addAttribute("section", "replies");
            model.addAttribute("sectionName", "我的回复");
        }

        PaginationDTO pagination= informationService.list(user.getId(), page, size);
        model.addAttribute("pagination", pagination);
        return "profile";
    }

}
