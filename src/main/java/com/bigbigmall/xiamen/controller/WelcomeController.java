package com.bigbigmall.xiamen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author lian
 */
@Controller
@RequestMapping("/")
public class WelcomeController {

        @RequestMapping("/primeNumbers")
        @ResponseBody
        public String getPrimeNumbers(int minimum, int maximum) {
                if (minimum > 0 && (maximum > 0 && maximum > minimum)) {

                        String max = "";
                        for (int i = minimum; i <= maximum; i++) {

                                if (i == 2 || i == 3 || i == 5 || i == 7) {
                                        max += i + "  ";
                                }

                                if (!(i % 2 == 0 || i % 3 == 0 || i % 5 == 0 || i % 7 == 0)) {
                                        max += i + "  ";
                                }
                        }
                        return max;
                }
                return "Incorrect parameters";
        }

}
