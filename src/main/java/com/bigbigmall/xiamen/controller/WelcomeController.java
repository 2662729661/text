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
                if (minimum > 0 && maximum > 0 && maximum > minimum) {

                        String max = "";
                        for (int i = minimum; i <= maximum; i++) {
                                if (i == 1) {
                                        continue;
                                }
                                if (i == 2) {
                                        max += i + "  ";
                                        continue;
                                }

                                for (int j = 2; j < i; j++) {
                                        if (i % j == 0) {
                                                break;
                                        }
                                        if ((j + 1) == i) {
                                                max += i + "  ";
                                        }
                                }
                        }
                        System.out.println("max:" + max);
                        return max;
                }
                return "Incorrect parameters";
        }

        @RequestMapping("/multiplication")
        @ResponseBody
        public String getMultiplicationTable() {

                String mt = "[";
                for (int i = 2; i <= 9; i++) {
                        for (int j = 1; j <= 9; j++) {
                                if (j == 9 && i == 9) {
                                        mt += "{" + '"' + i + '"' + ":" + '"' + (i + "&#215;" + j + "=" + (i * j)) + '"' + "}";
                                } else {
                                        mt += "{" + '"' + i + '"' + ":" + '"' + (i + "&#215;" + j + "=" + (i * j)) + '"' + "},";
                                }

                        }
                }
                mt += "]";
                return mt;
        }
}
