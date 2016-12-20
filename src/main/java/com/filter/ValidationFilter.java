package com.filter;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebFilter(urlPatterns = "/users")
public class ValidationFilter implements Filter {

    private Map<String, String> validationMap = new HashMap<String, String>();

    public void init(FilterConfig filterConfig) {

        InputStream resourceAsStream = ValidationFilter.class.getClassLoader().getResourceAsStream("validation.properties");
        Properties properties = new Properties();

        try {
            properties.load(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String key : properties.stringPropertyNames()) {
            String value = properties.getProperty(key);
            validationMap.put(key, value);
        }
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        String name = request.getParameter("name_ui");
        String age = request.getParameter("age_ui");

        if (!StringUtils.isEmpty(name) && StringUtils.isNotEmpty(age)) {
            if (!checkAge(age)) {
                chain.doFilter(request, response);
            }else {
                errorMsg(response, "Only numbers");
            }

        } else {
            errorMsg(response, ":(");
        }
    }

    private void errorMsg(ServletResponse response, String msg) {
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (writer != null) {
            writer.println(msg);
            writer.flush();
        }
    }

    private boolean checkAge(String age) {

        String age_from_property = validationMap.get("age_from_property.regexp");

        Pattern pattern = Pattern.compile(age_from_property);
        Matcher matcher = pattern.matcher(age);

        return matcher.matches();
    }

    public void destroy() {

    }
}
