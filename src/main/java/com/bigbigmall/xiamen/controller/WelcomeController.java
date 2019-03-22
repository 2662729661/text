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

        /**
         * 求两数区间的素数
         *
         * @param minimum
         * @param maximum
         * @return
         */
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

        /**
         * 九九乘法表
         *
         * @return
         */
        @RequestMapping("/multiplication")
        @ResponseBody
        public String getMultiplicationTable() {

                String mt = "[";
                for (int i = 2; i <= 9; i++) {
                        for (int j = 1; j <= 9; j++) {

                                if (j == 9 && i == 9) {
                                        mt += "{" + '"' + i + '"' + ":" + '"' + (i + "&#215;" + j + "=" + (i * j)) + '"' + "}";
                                } else if ((i == 2 || i == 3 || i == 5 || i == 7) && j == 1) {
                                        mt += "{" + '"' + i + '"' + ":" + '"' + (i + "&#215;" + j + "=" + "<font color='red'>" + (i * j)) + "</font>" + '"' + "},";
                                } else {
                                        mt += "{" + '"' + i + '"' + ":" + '"' + (i + "&#215;" + j + "=" + (i * j)) + '"' + "},";
                                }

                        }
                }
                mt += "]";
                return mt;
        }

        /**
         * 九九乘法表2
         *
         * @return
         */
        @RequestMapping("/multiplication2")
        @ResponseBody
        public String getTable() {
                String mt = "[";

                for (int i = 0; i <= 10; i++) {
                        mt += "[";
                        mt += "{" + '"' + i + '"' + ":" + '"' + i + '"' + "},";
                        if (i == 0) {
                                for (int j = 1; j <= 10; j++) {
                                        if (j == 10) {
                                                mt += "{" + '"' + i + '"' + ":" + '"' + j + '"' + "}";
                                        } else {
                                                mt += "{" + '"' + i + '"' + ":" + '"' + j + '"' + "},";
                                        }
                                }
                        } else {
                                for (int j = 1; j <= 10; j++) {
                                        if (j == 10) {
                                                mt += "{" + '"' + i + '"' + ":" + '"' + (i * j) + '"' + "}";
                                        } else {
                                                mt += "{" + '"' + i + '"' + ":" + '"' + (i * j) + '"' + "},";
                                        }
                                }
                        }
                        if (i == 10) {
                                mt += "]";
                        } else {
                                mt += "],";
                        }

                }

                mt += "]";
                return mt;
        }

        /**
         * 九九乘法表3
         *
         * @return
         */
        @RequestMapping("/multiplication3")
        @ResponseBody
        public String getMultiplication() {
                String mt = "[";
                for (int i = 9; i > 0; i--) {
                        mt += "[";
                        for (int j = 1; j <= i; j++) {
                                if (j == i) {
                                        mt += "{" + '"' + i + '"' + ":" + '"' + i + "&#215;" + j + "=" + (i * j) + '"' + "}";
                                } else {
                                        mt += "{" + '"' + i + '"' + ":" + '"' + i + "&#215;" + j + "=" + (i * j) + '"' + "},";
                                }
                        }
                        if (i == 1) {
                                mt += "]";
                        } else {
                                mt += "],";
                        }
                }
                mt += "]";
                return mt;
        }

        /**
         * 九九乘法表4
         *
         * @return
         */
        @RequestMapping("/multiplication4")
        @ResponseBody
        public String getMultiplication4() {
                String mt = "[";
                for (int i = 1; i < 10; i++) {
                        mt += "[";
                        for (int j = 1; j < 10; j++) {
                                if (j == 9) {
                                        mt += "{" + '"' + "name" + '"' + ":" + '"' + i + "*" + j + "=" + (i * j) + '"' + "}";
                                } else {
                                        mt += "{" + '"' + "name" + '"' + ":" + '"' + i + "*" + j + "=" + (i * j) + '"' + "},";
                                }
                        }
                        if (i == 9) {
                                mt += "]";
                        } else {
                                mt += "],";
                        }
                }

                System.out.println(mt);
                mt += "]";
                return mt;
        }
}
