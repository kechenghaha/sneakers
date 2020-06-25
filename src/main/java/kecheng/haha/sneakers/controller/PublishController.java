package kecheng.haha.sneakers.controller;

import kecheng.haha.sneakers.mapper.InformationMapper;
import kecheng.haha.sneakers.mapper.UserMapper;
import kecheng.haha.sneakers.model.Information;
import kecheng.haha.sneakers.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * by kecheng
 * 2020/06/09
 */
@Controller
public class PublishController {
    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }

    @Autowired
    private InformationMapper informationMapper;
    @Autowired
    private UserMapper userMapper;

    @PostMapping("/publish")
    public String doPublish(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("tag") String tag,
            HttpServletRequest request,
            Model model) {

        User user = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0)
            for (Cookie cookie : cookies)
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    user = userMapper.findByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }

        if (user == null) {
            model.addAttribute("error", "用户未登录");
            return "publish";
        }

        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);
        if (title == null || title == "") {
            model.addAttribute("error", "标题不能为空");
            return "publish";
        }
        if (description == null || description == "") {
            model.addAttribute("error", "描述不能为空");
            return "publish";
        }
        if (tag == null || tag == "") {
            model.addAttribute("error", "标签不能为空");
            return "publish";
        }

        Information information = new Information();
        information.setTitle(title);
        information.setDescription(description);
        information.setTag(tag);
        information.setCreator(user.getId());
        information.setGmtCreate(System.currentTimeMillis());
        information.setGmtModified(information.getGmtCreate());
        informationMapper.create(information);
        return "redirect:/";
    }
}

