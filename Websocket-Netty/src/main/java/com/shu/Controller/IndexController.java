package com.shu.Controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author shu
 * @Date: 2022/03/03/ 21:18
 * @Description
 **/
@Controller
public class IndexController {

    @RequestMapping("/")
    public String login(){
        return "index/login";
    }

    @RequestMapping("login")
    public String login(HttpServletRequest request, Model model){
        chat(request,model);
        return "index/login";
    }

    @RequestMapping("chat")
    public String chat(HttpServletRequest request,Model model){
        String username=request.getParameter("username");
        if(StringUtils.isEmpty(username)){
            return "index/login";
        }
        model.addAttribute("username",username);
        return "index/chat";
    }
}
