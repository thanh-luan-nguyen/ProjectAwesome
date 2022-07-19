package com.example.aspect;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice /** lưa chuột vào để xem chức năng của annotation này */
public class GlobalControllAdvice {

    /** database関連の例外処理 */
    @ExceptionHandler(DataAccessException.class)
    public String dataAccessExceptionHandler(DataAccessException e, Model model) {

        model.addAttribute("error","");

        model.addAttribute("message","DataAccessExceptionが発生しました");

        model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);

        return "error";
    }

    /** その他の例外処理 */
    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception e, Model model) {

        model.addAttribute("error","");

        model.addAttribute("message","Exceptionが発生しました");

        model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);

        return "error";
    }
}
